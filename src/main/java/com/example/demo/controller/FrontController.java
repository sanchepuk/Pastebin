package com.example.demo.controller;

import com.example.demo.api.request.PasteBoxRequest;
import com.example.demo.api.response.PasteBoxUrlResponse;
import com.example.demo.service.PasteBoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FrontController {

    private final PasteBoxService pasteBoxService;

    public FrontController(PasteBoxService pasteBoxService) {
        this.pasteBoxService = pasteBoxService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pastes", pasteBoxService.getFirstPublicPasteBoxes());
        model.addAttribute("pasteRequest", new PasteBoxRequest());
        return "index"; // thymeleaf шаблон index.html
    }

    @GetMapping("/{hash}")
    public String getByHash(Model model, @PathVariable String hash) {
        model.addAttribute("paste", pasteBoxService.getByHash(hash));
        return "paste";
    }

    @PostMapping("/")
    public String add(@ModelAttribute PasteBoxRequest request){
        PasteBoxUrlResponse urlResponse = pasteBoxService.create(request);
        return "redirect:/" + urlResponse.url();
    }
}
