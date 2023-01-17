package com.dsaws.service;

import com.dsaws.common.entity.Result;
import com.dsaws.common.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class SelectService implements Callable {

    private HashMap<String,String> param;

    public SelectService(HashMap param){
        this.param = param;
    }

    @Override
    public Result call() {
        Result result = null;
        String s = HttpUtil.doPost("https://project.midsummra.com/api/getTranslationsFromPersistence", param);
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper.readValue(s, Result.class);
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }
}
