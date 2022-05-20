package com.ncc.mn.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/view")
public class ProductController {

    @GetMapping(value = "/product")
    public String getAllProduct(Model model) {
        return null;
    }
}
