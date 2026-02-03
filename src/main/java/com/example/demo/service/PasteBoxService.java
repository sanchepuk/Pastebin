package com.example.demo.service;

import com.example.demo.api.request.PasteBoxRequest;
import com.example.demo.api.response.PasteBoxResponse;
import com.example.demo.api.response.PasteBoxUrlResponse;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getByHash(String hash);
    List<PasteBoxResponse> getFirstPublicPasteBoxes();
    PasteBoxUrlResponse create(PasteBoxRequest request);
}
