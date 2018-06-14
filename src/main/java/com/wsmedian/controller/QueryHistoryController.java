package com.wsmedian.controller;

import com.wsmedian.repository.QueryHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by szagoret
 */

@Controller
@RequestMapping("/history")
public class QueryHistoryController {

    private final QueryHistoryRepository queryHistoryRepository;

    public QueryHistoryController(QueryHistoryRepository queryHistoryRepository) {
        this.queryHistoryRepository = queryHistoryRepository;
    }

    @GetMapping
    public ModelAndView showHistoryPage() {

        ModelAndView modelAndView = new ModelAndView("history");
        modelAndView.addObject("queryHistoryList", queryHistoryRepository.findAll());

        return modelAndView;
    }
}
