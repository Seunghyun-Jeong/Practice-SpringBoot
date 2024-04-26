package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/question/list")
    public String List(Model model) {
        List<Question> questionsList = this.questionService.getList();
        model.addAttribute("questionsList", questionsList);
        return "question_list";
    }
}
