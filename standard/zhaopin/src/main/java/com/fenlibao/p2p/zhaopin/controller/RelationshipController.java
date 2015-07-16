package com.fenlibao.p2p.zhaopin.controller;

import com.fenlibao.p2p.common.page.Page;
import com.fenlibao.p2p.zhaopin.domain.Relationship;
import com.fenlibao.p2p.zhaopin.domain.Rule;
import com.fenlibao.p2p.zhaopin.service.RelationshipService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.inject.Inject;
import java.io.IOException;

/**
 * Created by Administrator on 2015/6/18.
 */
@RestController
@RequestMapping("/relationship")
public class RelationshipController {

    @Inject
    private RelationshipService relationshipService;

    @RequestMapping("/list")
    public ModelAndView list(Page<Relationship> page) {
        ModelAndView modelAndView = new ModelAndView();
        page = relationshipService.findPage(page);
        modelAndView.addObject(page);
        modelAndView.setViewName("zhaopin/relationship");
        modelAndView.addObject("home",1);
        return modelAndView;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public RedirectView update(Page<Rule> page,Relationship record,MultipartFile file) {
        String fileName = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            if(fileName != null && bytes != null && !"".equals(fileName.trim()) && bytes.length > 0) {
                record.setFileName(fileName);
                record.setResume(bytes);
                if(fileName.lastIndexOf(".") > 0) {
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    record.setSuffix(suffix);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int count = this.relationshipService.updateByPrimaryKeySelective(record);

        RedirectView redirectView = new RedirectView("/relationship/list");
        return redirectView;
    }

    @RequestMapping("/download/{id}")
    public ResponseEntity<byte[]> download(@PathVariable long id) throws IOException {
        Relationship record = this.relationshipService.selectByPrimaryKey(id);
        String fileName = record.getFileName();
        byte[] data = record.getResume();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        return new ResponseEntity(data, headers, HttpStatus.CREATED);
    }

    @RequestMapping("/tryStatus/{id}")
    public RedirectView tryStatus(@PathVariable Long id) {
        int count = this.relationshipService.tryStatus(id);
        RedirectView redirectView = new RedirectView("/relationship/list");
        return redirectView;
    }

    @RequestMapping("/positiveStatus/{id}")
    public RedirectView positiveStatus(@PathVariable Long id) {

        int count = this.relationshipService.positiveStatus(id);

        RedirectView redirectView = new RedirectView("/relationship/list");
        return redirectView;
    }
}
