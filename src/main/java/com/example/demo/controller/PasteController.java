package com.example.demo.controller;

import com.example.demo.api.request.PasteBoxRequest;
import com.example.demo.api.response.PasteBoxResponse;
import com.example.demo.api.response.PasteBoxUrlResponse;
import com.example.demo.service.PasteBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PasteController {
    @Autowired
    private PasteBoxService pasteBoxService;

    @GetMapping("/paste/")
    public List<PasteBoxResponse> getPublicPasteList(){
        return pasteBoxService.getFirstPublicPasteBoxes();
    }

    @GetMapping("/paste/{hash}")
    public String getByHash(@PathVariable String hash){
        return pasteBoxService.getByHash(hash).data();
    }

    @PostMapping("/paste/")
    public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request){
        return pasteBoxService.create(request);
    }
}
