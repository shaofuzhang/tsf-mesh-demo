package com.tsf.demo.user.controller;

import com.tsf.demo.user.util.TraceHeadersUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.SchemaOutputResolver;

/**
 * Created on 2019/12/21.
 *
 * @author ZhangSF
 */
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/create")
    public Object create(HttpServletRequest servletRequest) {
        HttpHeaders headers = new TraceHeadersUtil().buildTraceHeaders(servletRequest);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://shop/api/v1/shop/items", String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/account/query")
    public Object accountQuery(HttpServletRequest servletRequest) {
        try {
            HttpHeaders headers = new TraceHeadersUtil().buildTraceHeaders(servletRequest);
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("x-trace-service", "user");
            ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://shop/api/v1/shop/order", String.class);
            return responseEntity.getBody();
        } catch (Exception ex) {
            logger.error("access shop service err", ex);
        }
        return "访问SHOP异常";
    }
}

