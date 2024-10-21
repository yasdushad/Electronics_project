package com.electronics.store.electronocs_Store.controller;

import com.electronics.store.electronocs_Store.dto.Failure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class addNewFailureController {
    private Logger logger = LoggerFactory.getLogger(addNewFailureController.class);
    @GetMapping("/failure/new")
    public String showFailureForm(Model model) {
        model.addAttribute("failure","Yash");
       logger.info("Hi");
        return "addNewFailure"; // Refers to add-failure.html in templates folder
    }


}
