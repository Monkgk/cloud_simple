package com.page_service.service.impl;

import com.page_service.service.PhotoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class PhotoServiceImpl implements PhotoService {
    @Value("${file.save_path}")
    private String RESOURCE_SAVE_PATH;

    @Override
    public byte[] getPhotoByFileUrl(String url) throws IOException {
        File file = new File(RESOURCE_SAVE_PATH+url);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}
