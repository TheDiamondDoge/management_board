package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.exceptions.ProjectDoesNotExist;
import com.aiksanov.api.project.util.enums.cost.CostRowTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping("/testError")
    public void test() throws ProjectDoesNotExist {
        System.out.println(CostRowTypes.CHARGE);
        System.out.println(CostRowTypes.CHARGE);
    }
}
