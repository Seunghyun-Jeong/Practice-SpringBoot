package com.mysite.sbb.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class QuesetionController {
    @GetMapping("/question/list")
    @ResponseBody
    public String List() {
        return "question list";
    }
}
