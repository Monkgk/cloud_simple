package com.ruoyi.web.controller.films;

import com.common.Entity.Film;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.IFilmService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/films/film")
public class FilmController extends BaseController {
    private String prefix = "films/film";

    @Autowired
    private IFilmService iFilmService;

    @RequiresPermissions("films:film:view")
    @GetMapping()
    public String film(){
        return prefix+"/film";
    }

    @RequiresPermissions("films:film:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Film film)
    {
        startPage();
        List<Film> list = iFilmService.selectFilmList(film);
        return getDataTable(list);
    }

    @RequiresPermissions("films:film:remove")
    @Log(title = "影片管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(iFilmService.deleteFilmByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增影片
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存影片
     */
    @RequiresPermissions("films:film:add")
    @Log(title = "影片管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Film film)
    {
        return toAjax(iFilmService.insertFilm(film));
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
            String filePath = RuoYiConfig.getUploadPath("film");
//            String filePath = "film";
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
     * 修改影片
     */
    @GetMapping("/edit/{film_id}")
    public String edit(@PathVariable("film_id") Integer film_id, ModelMap mmap)
    {
        mmap.put("film", iFilmService.selectFilmById(film_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存影片
     */
    @RequiresPermissions("films:film:edit")
    @Log(title = "影片管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Film film)
    {
        return toAjax(iFilmService.updateFilm(film));
    }
}
