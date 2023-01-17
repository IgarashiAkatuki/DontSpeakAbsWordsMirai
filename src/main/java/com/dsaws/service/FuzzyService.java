package com.dsaws.service;

import com.dsaws.common.entity.Result;
import com.dsaws.common.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class FuzzyService implements Callable {
    private HashMap param;

    public FuzzyService(HashMap param) {
        this.param = param;
    }

    @Override
    public Result call() throws Exception {
        Result result = null;
        ObjectMapper mapper = new ObjectMapper();
        String s = HttpUtil.doPost("https://project.midsummra.com/api/fuzzyQuery", param);
        try {
            result = mapper.readValue(s, Result.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
