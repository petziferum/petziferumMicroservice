package com.petziferum.productservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/wled")
    public ResponseEntity<String> wledOne() {
        String url = "http://192.168.1.24/json/state";
        String state = "{'on':'t', 'v':'true', 'bri':'255', 'transition':'0'}";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, state, String.class);
        return response;
    }

    @GetMapping("/text")
    public ResponseEntity<ArrayList<FileData>> getFile() throws Exception {
        return ResponseEntity.ok(productService.readData());
    }
}
