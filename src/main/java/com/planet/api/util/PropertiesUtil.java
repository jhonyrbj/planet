package com.planet.api.util;

import lombok.extern.log4j.Log4j2;

import java.util.ResourceBundle;

@Log4j2
public final class PropertiesUtil {
    private static final String ERROR_PROPERTIES = "messages";

    private PropertiesUtil() {
    }

    public static String getErrorMessage(String key){
        try{
            ResourceBundle bundle = ResourceBundle.getBundle(ERROR_PROPERTIES);
            return bundle.getString(key);
        }catch (Exception e){
            log.warn(e.getMessage());
            return "";
        }
    }
}
