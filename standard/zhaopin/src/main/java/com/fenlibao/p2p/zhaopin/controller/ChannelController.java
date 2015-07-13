package com.fenlibao.p2p.zhaopin.controller;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.weixin.domain.Qrcode;
import com.fenlibao.p2p.zhaopin.domain.Channel;
import com.fenlibao.p2p.zhaopin.domain.Rule;
import com.fenlibao.p2p.zhaopin.service.ChannelService;
import com.fenlibao.p2p.zhaopin.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

/**
 * Created by lenovo on 2015/6/17.
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    @Autowired
    private RuleService ruleService;

    @RequestMapping("/list")
    public ModelAndView list(Page<Channel> page) {
        ModelAndView modelAndView = new ModelAndView();
        page = channelService.findPage(page);
        modelAndView.addObject(page);

        List<Rule> rules = this.ruleService.findAll();
        modelAndView.addObject("rules",rules);
        modelAndView.setViewName("zhaopin/channel");
        modelAndView.addObject("home",3);
        return modelAndView;
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public RedirectView insert(Page<Channel> page,Channel channel) {
        channel.setDel(false);
        channel.setStatus(false);
        this.channelService.insertSelective(channel);
        RedirectView redirectView = new RedirectView("/channel/list");
        return redirectView;
    }

    @RequestMapping(value = "/updateStatus/{id}",method = RequestMethod.GET)
    public @ResponseBody
    RedirectView updateStatus(@PathVariable Long id,Page<Channel> page) {
        Channel channel = this.channelService.selectByPrimaryKey(id);
        channel.setStatus(channel.getStatus() == null ? true : !channel.getStatus());
        this.channelService.updateByPrimaryKeySelective(channel);
        RedirectView redirectView = new RedirectView("/channel/list");
        return redirectView;
    }

    @RequestMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id,Page<Channel> page) {
        Channel channel = this.channelService.selectByPrimaryKey(id);
        channel.setDel(true);
        int count = this.channelService.updateByPrimaryKeySelective(channel);
        RedirectView redirectView = new RedirectView("/channel/list");
        return redirectView;
    }

    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) throws IOException {
        Channel channel = this.channelService.selectByPrimaryKey(id);
        Qrcode qrcode = this.channelService.getQrLimitScene(channel.getSceneId());

        String fileName = channel.getName() + qrcode.getSuffix();
        fileName=new String(fileName.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题
        byte[] data = qrcode.getBytes();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity(data, headers, HttpStatus.CREATED);
    }
}
