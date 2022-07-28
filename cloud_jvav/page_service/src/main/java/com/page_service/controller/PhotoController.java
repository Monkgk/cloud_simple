package com.page_service.controller;

import com.page_service.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class PhotoController {
    @Autowired
    PhotoService    photoService;

    @RequestMapping("/get/photo/{type}/{file_name}")
    @ResponseBody
    public byte[] getPhotoByUrl(@PathVariable("type") String type,
                         @PathVariable("file_name") String file_name) throws IOException {
        return photoService.getPhotoByFileUrl(type+"/"+file_name);
    }
}
