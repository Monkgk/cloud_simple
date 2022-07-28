package com.ruoyi.web.controller.cinema;

import com.common.Entity.Cinema;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ICinemaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/cinema/cinema")
public class CinemaController extends BaseController {
    private String prefix = "cinema/cinema";

    @Autowired
    private ICinemaService iCinemaService;

    @RequiresPermissions("cinema:cinema:view")
    @GetMapping()
    public String cinema(){
        return prefix+"/cinema";
    }

    @RequiresPermissions("cinema:cinema:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cinema cinema)
    {
        startPage();
        List<Cinema> list = iCinemaService.selectCinemaList(cinema);
        return getDataTable(list);
    }

//    @RequiresPermissions("cinema:cinema:remove")
//    @Log(title = "影院管理", businessType = BusinessType.DELETE)
//    @PostMapping("/remove")
//    @ResponseBody
//    public AjaxResult remove(String ids)
//    {
//        try
//        {
//
//            return toAjax(iCinemaService.deleteCinemaByIds(ids));
//        }
//        catch (Exception e)
//        {
//            return error(e.getMessage());
//        }
//    }

    @RequiresPermissions("cinema:cinema:remove")
    @Log(title = "影院管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove/{cinema_id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("cinema_id") Integer cinema_id)
    {
        if (iCinemaService.selectRoomCount(cinema_id) > 0)
        {
            return AjaxResult.warn("存在下级放映厅,不允许删除");
        }
//        if (iCinemaService.checkDeptExistUser(cinema_id))
//        {
//            return AjaxResult.warn("部门存在用户,不允许删除");
//        }
        return toAjax(iCinemaService.deleteCinemaById(cinema_id));
    }

    /**
     * 新增影院
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存影院
     */
    @RequiresPermissions("cinema:cinema:add")
    @Log(title = "影院管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Cinema cinema)
    {
        return toAjax(iCinemaService.insertCinema(cinema));
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(@RequestParam("file") MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath("cinema");
//            String filePath = "cinema";
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
//            String url = serverConfig.getUrl() + fileName;
            String url = "http://localhost:8080/get/photo" + fileName;
            AjaxResult ajax = AjaxResult.success();
            fileName = fileName.substring(fileName.lastIndexOf("/")+1,fileName.length());
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 修改影院
     */
    @GetMapping("/edit/{cinema_id}")
    public String edit(@PathVariable("cinema_id") Integer cinema_id, ModelMap mmap)
    {
        mmap.put("cinema", iCinemaService.selectCinemaById(cinema_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存影院
     */
    @RequiresPermissions("cinema:cinema:edit")
    @Log(title = "影院管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Cinema cinema)
    {
        return toAjax(iCinemaService.updateCinema(cinema));
    }
}
