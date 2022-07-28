package com.page_service.service;

import com.common.Vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ChangeService {

    /**
     * 修改用户头像
     * @param type
     * @param file
     * @return
     */
    ResultVo    changeHead(String type, MultipartFile file);

    /**
     * 修改用户性别、用户名、密码中的其中一项
     * @param request
     * @param type
     * @param data
     * @return
     */
    ResultVo changeInfo(HttpServletRequest request, String type, Map<String, Object> data);
}
