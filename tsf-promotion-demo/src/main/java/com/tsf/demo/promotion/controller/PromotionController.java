package com.tsf.demo.promotion.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tsf.demo.promotion.util.TraceHeadersUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019/12/25.
 *
 * @author ZhangSF
 */
@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/query")
    public JSONObject query() {

        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", "00" + i);
            jsonObject.put("status", "on");
            jsonArray.add(i, jsonObject);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("promotions", jsonArray);
        return jsonObject;
    }

    @GetMapping("/item/discount")
    public Object itemDiscount(HttpServletRequest servletRequest) {
        HttpHeaders headers = new TraceHeadersUtil().buildTraceHeaders(servletRequest);
        headers.set("x-trace-service", "promotion");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://shop/api/v1/shop/product/deliver", String.class);
        return responseEntity.getBody();
    }


}
