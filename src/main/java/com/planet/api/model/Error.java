package com.planet.api.model;

import com.google.gson.Gson;
import com.planet.api.util.PropertiesUtil;
import org.springframework.http.HttpStatus;

public class Error extends ErrorModel {

    public Error() {
    }

    public static String singleError(String code){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(PropertiesUtil.getErrorMessage(code));
        return error.toString();
    }

    public static String singleError(HttpStatus httpStatus){
        Error error = new Error();
        error.setCode(String.valueOf(httpStatus.value()));
        error.setMessage(httpStatus.getReasonPhrase());
        return error.toString();
    }

    public static String singleError(String code, String message){
        Error error = new Error();
        error.setCode(code);
        error.setMessage(message);
        return error.toString();
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
