package com.page_service.service.impl;

import com.common.Entity.User;
import com.common.Util.SessionUtils;
import com.common.Util.UUIDUtils;
import com.common.Vo.ResultCode;
import com.common.Vo.ResultVo;
import com.feign.clients.UserClient;
import com.page_service.service.ChangeService;
import com.page_service.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class ChangeServiceImpl implements ChangeService {
//    private final static String RESOURCE_SAVE_PATH=System.getProperty("user.dir")+"/src/main/resources/static/img/";
//    private final static String RESOURCE_SAVE_PATH="F:\\img\\";
    @Value("${file.save_path}")
    private String RESOURCE_SAVE_PATH;

    @Autowired
    UserClient userClient;
    @Autowired
    TokenUtils  tokenUtils;

    @Override
    public ResultVo changeHead(String type, MultipartFile file) {
        if (type.equals("head")){
            //修改头像
            //获取原始文件名称（包括格式）
            String originalFileName = file.getOriginalFilename();

            //获取文件类型，以最后一个‘.’为标识
            String file_type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);

            //获取文件名称（不包含格式）
//            String file_name = originalFileName.substring(0,originalFileName.lastIndexOf("."));

            //设置文件新名称：当前事件+文件名称（不包含格式）
            User cur_user = SessionUtils.getCurrentUser();
            String fileName = UUIDUtils.randomUUID()+cur_user.getUser_id() +"." +file_type;

            //读取文件
            File old_targetFile = null;
            if (cur_user.getUser_head()!=null&&!cur_user.getUser_head().equals("default_head.png")){
                old_targetFile = new File(RESOURCE_SAVE_PATH+cur_user.getUser_head());
            }
            File new_targetFile = new File(RESOURCE_SAVE_PATH,fileName);
            if(!new_targetFile.exists()){
                try {
                    //创建文件
                    new_targetFile.createNewFile();
                    //存入图片
                    file.transferTo(new_targetFile);
                    //删除旧文件
                    if(old_targetFile!=null&&old_targetFile.exists()){
                        old_targetFile.delete();
                    }
                    userClient.setHeadByUserId(cur_user.getUser_id(),fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResultVo.returnFail(ResultCode.FAIL);
                }
            }
        }
        return ResultVo.success(ResultCode.SUCCESSS);
    }

    @Override
    public ResultVo changeInfo(HttpServletRequest request, String type, Map<String,Object> data) {
        int user_id = tokenUtils.getUserId(tokenUtils.getToken(request));
        return userClient.setUserInfo(user_id,type,data);
    }
}
