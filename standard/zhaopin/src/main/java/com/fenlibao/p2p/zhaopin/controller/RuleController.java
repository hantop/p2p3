package com.fenlibao.p2p.zhaopin.controller;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.zhaopin.domain.Rule;
import com.fenlibao.p2p.zhaopin.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Created by Administrator on 2015/6/18.
 */
@RestController
@RequestMapping("/rule")
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/list")
    public ModelAndView list(Page<Rule> page) {
        ModelAndView modelAndView = new ModelAndView();
        page = ruleService.findPage(page);
        modelAndView.addObject(page);
        modelAndView.setViewName("zhaopin/rule");
        modelAndView.addObject("home",2);
        return modelAndView;
    }

    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.POST)
    public @ResponseBody RedirectView updateStatus(@PathVariable Long id,Page<Rule> page) {
        Rule rule = this.ruleService.selectByPrimaryKey(id);
        rule.setStatus(rule.getStatus() == null ? true : !rule.getStatus());
        int count = this.ruleService.updateByPrimaryKeySelective(rule);
        RedirectView redirectView = new RedirectView("/rule/list");
        return redirectView;
    }

    @RequestMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id,Page<Rule> page) {
        Rule rule = this.ruleService.selectByPrimaryKey(id);
        rule.setDel(true);
        int count = this.ruleService.updateByPrimaryKeySelective(rule);
        RedirectView redirectView = new RedirectView("/rule/list");
        return redirectView;
    }

    @RequestMapping("/update/{id}")
    public RedirectView update(@PathVariable Long id,Page<Rule> page,Rule rule) {
        int count = this.ruleService.updateByPrimaryKeySelective(rule);
        RedirectView redirectView = new RedirectView("/rule/list");
        return redirectView;
    }


    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public RedirectView insert(Page<Rule> page,Rule rule) {
        this.ruleService.insertSelective(rule);
        RedirectView redirectView = new RedirectView("/rule/list");
        return redirectView;
    }
}
