/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.response;

public class LinkAppResponse {
    public String LoginLink;

    public LinkAppResponse(String loginLink) {
        LoginLink = loginLink;
    }

    @Override
    public String toString() {
        return "LinkAppResponse{" +
                "LoginLink='" + LoginLink + '\'' +
                '}';
    }

    public String getLoginLink() {
        return LoginLink;
    }

    public void setLoginLink(String loginLink) {
        LoginLink = loginLink;
    }
}
