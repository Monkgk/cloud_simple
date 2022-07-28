package com.ruoyi.web.controller.actor;

import com.common.Entity.Actor;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.IActorService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
@RequestMapping("/actor/user")
public class ActorController extends BaseController {
    private String prefix = "actor/user";

    @Autowired
    private IActorService iActorService;

    @RequiresPermissions("actor:user:view")
    @GetMapping()
    public String actor(){
        return prefix+"/actor";
    }

    @RequiresPermissions("actor:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Actor actor)
    {
        startPage();
        List<Actor> list = iActorService.selectActorList(actor);
        return getDataTable(list);
    }

    @RequiresPermissions("actor:user:remove")
    @Log(title = "演员管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(iActorService.deleteActorByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增演员
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存演员
     */
    @RequiresPermissions("actor:user:add")
    @Log(title = "演员管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Actor actor)
    {
        return toAjax(iActorService.insertActor(actor));
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
            String filePath = RuoYiConfig.getUploadPath("actor");
//            String filePath = "actor";
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
     * 修改演员
     */
    @GetMapping("/edit/{actor_id}")
    public String edit(@PathVariable("actor_id") Integer actor_id, ModelMap mmap)
    {
        mmap.put("actor", iActorService.selectActorById(actor_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存演员
     */
    @RequiresPermissions("actor:user:edit")
    @Log(title = "演员管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Actor actor)
    {
        return toAjax(iActorService.updateActor(actor));
    }
}
