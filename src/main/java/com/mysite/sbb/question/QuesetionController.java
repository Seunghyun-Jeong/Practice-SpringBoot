package com.mysite.sbb.question;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuesetionController {
    @GetMapping("/question/list")
    public String List() {
        return "question_list";
    }
}
