package com.dsaws.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtil {

    /**
     *
     * @param param K-V键值对
     * @return json
     */
    public static String createJson(Map param){
        String json = null;

        if (!param.isEmpty()){
            try {
                ObjectMapper mapper = new ObjectMapper();
                json = mapper.writeValueAsString(mapper);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return json;
    }
}
