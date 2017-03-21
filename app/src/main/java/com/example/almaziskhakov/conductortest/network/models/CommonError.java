package com.example.almaziskhakov.conductortest.network.models;

import java.util.List;
import java.util.Map;

/**
 * Created by Ilya Eremin on 12.08.2016.
 */
public class CommonError {

    private Error error;

    public String getMessage() {
        String message = error.error == null ? "" : (error.error + '\n');
        if (error.validations != null) {
            for (String key : error.validations.keySet()) {
                message += key + " " + '\n';
            }
        }
        return message;
    }

    public Map<String, List<String>> getValidations() {
        return error.validations;
    }

    public static class Error {
//        String                    id;
//        int                       status;
        private String                    error;
        private Map<String, List<String>> validations;
    }

}
