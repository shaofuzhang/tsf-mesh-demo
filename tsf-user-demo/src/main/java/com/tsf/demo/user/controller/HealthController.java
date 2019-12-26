package com.tsf.demo.user.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * Created on 2019/12/24.
 *
 * @author ZhangSF
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public JSONObject health() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("status", "UP");
        return jsonObject;
    }


    @Resource
    private RequestMappingHandlerMapping handlerMapping;

    @GetMapping("/route")
    public void getAllUrl() {
        Map map = this.handlerMapping.getHandlerMethods();
        Set set = map.keySet();
        for (Object object : set) {
            RequestMappingInfo info = (RequestMappingInfo) object;
            String reqURIs = info.getPatternsCondition().toString();
            System.out.println(reqURIs.substring(1, reqURIs.length() - 1));
        }
    }
}
