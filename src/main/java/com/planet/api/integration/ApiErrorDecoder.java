package com.planet.api.integration;

import com.planet.api.exception.InternalServerErrorException;
import com.planet.api.exception.NotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import static com.planet.api.constant.MessageErrorPlanetApi.PLANET_API_FAILED_INTEGRATION_SWAPI_CODE;
import static com.planet.api.constant.MessageErrorSWAPI.SWAPI_PLANETS_NOT_FOUND_CODE;
import static com.planet.api.model.Error.singleError;

@Slf4j
public class ApiErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == HttpStatus.NOT_FOUND.value()) {
            throw new NotFoundException(singleError(SWAPI_PLANETS_NOT_FOUND_CODE));
        }else if (response.status() >= HttpStatus.BAD_REQUEST.value()){
            throw new InternalServerErrorException(singleError(PLANET_API_FAILED_INTEGRATION_SWAPI_CODE));
        }
        return errorDecoder.decode(s,response);
    }
}
