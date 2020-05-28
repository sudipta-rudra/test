
import logging

import azure.functions as func
import numpy as np
import json
import requests

from os import path

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

def post_to_create_reference_data(nice_ml_url):

    #  API url
    url = nice_ml_url + "/createreferencedata"
    logging.info(f'post url is {url}')
    
    payload={}
    payload['Create'] = True

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
    result = json.dumps({
        'id': event.id,
        'data': event.get_json(),
        'topic': event.topic,
        'subject': event.subject,
        'event_type': event.event_type,
    })

    logging.info('Python EventGrid trigger processed an event: %s', result)
    try : 
        config_json = readjson_from_file("/home/site/wwwroot/config.json")
        nice_ml_url = config_json['NICE_ML_APP_SERVICE_URL']
    except KeyError :
        logging.info('could not load config file properly keys not found exiting function')
        exit(0)
        
    post_to_create_reference_data(nice_ml_url)

    logging.info('done with all processing exiting function')
