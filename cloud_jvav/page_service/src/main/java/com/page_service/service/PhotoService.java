package com.page_service.service;

import java.io.IOException;

public interface PhotoService {

    /**
     * 获取图片
     * @param url
     * @return
     * @throws IOException
     */
    public byte[] getPhotoByFileUrl(String url) throws IOException;
}
