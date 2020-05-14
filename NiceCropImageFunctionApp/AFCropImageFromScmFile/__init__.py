import logging

import azure.functions as func
import numpy as np
import requests
import cv2 as cv
import json
import os
import base64
import io
import sys
from azure.storage.blob import BlobServiceClient, BlobClient, ContainerClient, PublicAccess


def convert_cvmat_to_base64(img):
    if not img is None:
        retval, buffer = cv.imencode('.jpg', img)

        # Convert to base64 encoding and show start of data
        img_string_base64 = base64.b64encode(buffer)

        return img_string_base64


def readjson_from_file(filename):
    try:
        fp = open(filename, "r")
    except:
        logging.info(f"WARNING: cant open file {filename} ")
        return {}
    try:
        object = json.load(fp)
    except json.JSONDecodeError:
        logging.info(f"WARNING: not a json file {filename}")
        object = {}
    fp.close()

    return object


def post_to_face_recognize(img_cv_mat, ci_path, timestamp , nice_ml_url):
    #  API url
    url = nice_ml_url + "/facerecognize"
    logging.info(f'post url is {url}')

    payload = readjson_from_file("/home/site/wwwroot/FaceRecognitionRequest.json")

    img_string_base64 = convert_cvmat_to_base64(img=img_cv_mat)

    try:
        payload['ImagePath'] = ci_path
        payload['ImageData'] = img_string_base64.decode("utf-8")
        payload['DateTimeStamp'] = timestamp
    except:
        print('could not put data to key not found in json')
        return

    logging.info('payload added')
    # Content type must be included in the header
    header = {"content-type": "application/json"}

    logging.info('sending post request')
    # Performs a POST on the specified url to get response
    response = requests.post(url, data=json.dumps(
        payload), headers=header, verify=False)

    logging.info('recived  response')
    logging.info(response)
    # convert response to json format
    response_json = response.json()
    logging.info(f"status response {response_json['status']}")

   # print(response_json)

def main(event: func.EventGridEvent):
    # pushed from windows
    
    data = event.get_json()
    scm_path = data['url']
    logging.info('scm_path: %s', scm_path)
    scenemarks_index = scm_path.find('scenemarks')
    full_path_to_file = scm_path[scenemarks_index:]
    logging.info('full_path_to_file: %s', full_path_to_file)
    path_split = full_path_to_file.split('/')
    
    if not (len(path_split) == 4) :
        logging.info('scm_path: %s', scm_path)
        return

    container_name = path_split[0] + '/' + path_split[1] + '/' + path_split[2]
    remote_file_name = path_split[3]
    deviceId = path_split[1]
    scenemode = path_split[2]
    logging.info('remote container_name: %s', container_name)
    if not full_path_to_file.endswith(".scm"):
        return
    
    try : 
        config_json = readjson_from_file("/home/site/wwwroot/config.json")
        conn_str=config_json['Storage_account_connection_string']
        nice_ml_url = config_json['NICE_ML_APP_SERVICE_URL']
        container_cropped_images = config_json['Cropped_images_container_name']
    except KeyError :
        logging.info('could not load config file properly keys not found exiting function')
        exit(0)
    
    # Create the BlobServiceClient that is used to call the Blob service for the storage account
    blob_service_client = BlobServiceClient.from_connection_string(conn_str=conn_str)
    local_path = os.path.expanduser("~/Sample")
    if not os.path.exists(local_path):
        os.makedirs(os.path.expanduser("~/Sample"))
    # Download the blob(s).
    # Add '_DOWNLOADED' as prefix to '.txt' so you can see both files in Documents.
    blob_client = blob_service_client.get_blob_client(
            container=container_name, blob=remote_file_name)
    local_file_name = os.path.join(local_path, remote_file_name)
    with open(local_file_name, "wb") as my_blob:
            my_blob.writelines([blob_client.download_blob().readall()])
    fp = open(local_file_name, "r")
    if not fp.mode == 'r':
        fp.close()
        return

    scenemark_str =fp.read()
    fp.close()
    os.remove(local_file_name)
    #logging.info('contents: %s', scenedata)
    logging.info(cv.__version__)
    scenedata = json.loads(scenemark_str)
    #parse the scm file and extract the image data
    if set(scenedata) >= {'SceneMarkID','DetectedObjects'}:
        count = 0
        for detectedobjects in scenedata['DetectedObjects']:
            imagedata = detectedobjects['DetectedImage']['ImageData']
            height = detectedobjects['Resolution']['Height']
            width = detectedobjects['Resolution']['Width']
            startCol = detectedobjects['XCoordinate']
            startRow = detectedobjects['YCoordinate']
            endRow = startRow + height
            endCol = startCol + width
            #convert base64 to cv mat
            nparr = np.fromstring(base64.b64decode(imagedata), np.uint8)
            image = cv.imdecode(nparr, cv.IMREAD_COLOR)
            # crop image and store local
            croppedImage = image[startRow:endRow, startCol:endCol]

            local_cropped_file_name = scenedata['SceneMarkID'] + "_Cropped_" + str(count) + ".jpg"
            full_cropped_path_to_file = os.path.join(local_path, local_cropped_file_name)
            cv.imwrite(full_cropped_path_to_file, croppedImage)

            #store in detectedimages folder
            container_cropped_name = container_cropped_images+"/" + deviceId + "/" + scenemode
            
            # Upload the created file, use local_file_name for the blob name
            blob_client = blob_service_client.get_blob_client(container=container_cropped_name, blob=local_cropped_file_name)
            with open(full_cropped_path_to_file, "rb") as data:
                blob_client.upload_blob(data, overwrite=True)

            post_to_face_recognize(
                img_cv_mat=croppedImage, ci_path=blob_client.url, timestamp=scenedata['DateTimeStamp'],nice_ml_url=nice_ml_url)

            #delete the file from local storage
            os.remove(full_cropped_path_to_file)
            count = count + 1
