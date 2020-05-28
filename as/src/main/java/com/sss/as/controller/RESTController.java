/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.controller;

import com.sss.as.mqtt.MqttSubPubAS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class RESTController {

    @GetMapping("/linkapp")
    public void linkApp(@RequestParam("redirect_url") String url, HttpServletResponse response) throws IOException {
        // TODO: OAuth will replace with actual redirection
        response.addHeader("AccessToken", "dummy-access-token");
        response.sendRedirect(url);
    }
}
