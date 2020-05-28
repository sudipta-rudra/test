/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.controller;

import com.sss.as.request.GetManagementEndPointRequest;
import com.sss.as.response.ManagementEndPointResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTControllerDevice {

    @PostMapping("GetManagementEndPoint")
    public ResponseEntity<ManagementEndPointResponse> managementEndPoint(
            @RequestBody GetManagementEndPointRequest getManagementEndPointRequest) {

        if (getManagementEndPointRequest != null) {
            ManagementEndPointResponse managementEndPointResponse = new ManagementEndPointResponse();
            managementEndPointResponse.payload.stringify();
            return new ResponseEntity<>(managementEndPointResponse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FAILED_DEPENDENCY);
        }
    }
}
