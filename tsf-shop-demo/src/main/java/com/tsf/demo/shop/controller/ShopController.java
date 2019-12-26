package com.tsf.demo.shop.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tsf.demo.shop.util.TraceHeadersUtil;
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
 * Created on 2019/12/24.
 *
 * @author ZhangSF
 */
@RequestMapping("/api/v1/shop")
@RestController
public class ShopController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/items")
    public JSONObject items() {
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < 3; i++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("itemId", "00" + i);
            jsonObject.put("itemName", "cloth" + i);
            jsonArray.add(i, jsonObject);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("items", jsonArray);

        return jsonObject;
    }

    @GetMapping("/order")
    public String order(HttpServletRequest servletRequest) {
        HttpHeaders headers = new TraceHeadersUtil().buildTraceHeaders(servletRequest);
        headers.set("x-trace-service", "shop");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://promotion/api/v1/promotion/query", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/product/deliver")
    public JSONObject productDeliver() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("itemId", "002");
        jsonObject.put("destination", "shenzhen");
        return jsonObject;
    }
}
