package com.fenlibao.p2p.zhaopin.controller;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.zhaopin.domain.RelationshipHistory;
import com.fenlibao.p2p.zhaopin.service.RelationshipHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;

/**
 * Created by Administrator on 2015/6/18.
 */
@RestController
@RequestMapping("/history")
public class RelationshipHistoryController {

    @Inject
    private RelationshipHistoryService relationshipHistoryService;

    @RequestMapping("/list")
    public ModelAndView list(Page<RelationshipHistory> page) {
        ModelAndView modelAndView = new ModelAndView();
        page = relationshipHistoryService.findPage(page);
        modelAndView.addObject(page);
        modelAndView.setViewName("zhaopin/history");
        return modelAndView;
    }
}
