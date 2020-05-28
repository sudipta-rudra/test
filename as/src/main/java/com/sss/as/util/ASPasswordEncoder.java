/*
 *  Copyright 2020 Sony Semiconductor Solutions Corp.
 *
 *  This is UNPUBLISHED PROPRIETARY SOURCE CODE of Sony Semiconductor Solutions Corp.
 *  No part of this file may be copied, modified, sold, and distributed in any
 *  form or by any means without prior explicit permission in writing from
 *  Sony Semiconductor Solutions Corp.
 */

package com.sss.as.util;

import org.springframework.security.crypto.password.PasswordEncoder;

public class ASPasswordEncoder implements PasswordEncoder {

    private static ASPasswordEncoder asPasswordEncoder;

    private ASPasswordEncoder() {
    }

    public static ASPasswordEncoder getInstance() {
        if (asPasswordEncoder == null) asPasswordEncoder = new ASPasswordEncoder();
        return asPasswordEncoder;
    }

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return charSequence.equals(s);
    }
}
