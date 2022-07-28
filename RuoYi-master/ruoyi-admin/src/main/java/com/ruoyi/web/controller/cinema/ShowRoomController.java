package com.ruoyi.web.controller.cinema;

import com.common.Entity.Cinema;
import com.common.Entity.ShowRoom;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ICinemaService;
import com.ruoyi.system.service.IShowRoomService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/cinema/showroom")
public class ShowRoomController extends BaseController {
    private String prefix = "cinema/showroom";

    @Autowired
    private IShowRoomService iShowRoomService;
    @Resource
    private ICinemaService  iCinemaService;

    @RequiresPermissions("cinema:showroom:view")
    @GetMapping()
    public String cinema(){
        return prefix+"/showroom";
    }

    @RequiresPermissions("cinema:showroom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ShowRoom showroom)
    {
        startPage();
        List<ShowRoom> list = iShowRoomService.selectShowRoomList(showroom);
        return getDataTable(list);
    }

    @RequiresPermissions("cinema:showroom:remove")
    @Log(title = "放映厅管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(iShowRoomService.deleteShowRoomByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增放映厅
     */
    @GetMapping("/add/{cinema_id}")
    public String add(@PathVariable("cinema_id")Integer   cinema_id,  ModelMap mmap)
    {
        mmap.put("cinema",iCinemaService.selectCinemaById(cinema_id));
        return prefix + "/add";
    }


    /**
     * 新增保存放映厅
     */
    @RequiresPermissions("cinema:showroom:add")
    @Log(title = "放映厅管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ShowRoom showroom)
    {
        return toAjax(iShowRoomService.insertShowRoom(showroom));
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
            String filePath = RuoYiConfig.getUploadPath("showroom");
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
     * 修改放映厅
     */

    @GetMapping("/edit/{room_id}")
    public String edit(@PathVariable("room_id") Integer room_id, ModelMap mmap)
    {
        mmap.put("room", iShowRoomService.selectShowRoomById(room_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存放映厅
     */
    @RequiresPermissions("cinema:showroom:edit")
    @Log(title = "放映厅管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ShowRoom showRoom)
    {
        return toAjax(iShowRoomService.updateShowRoom(showRoom));
    }


    /**
     * 选择部门树
     *
     * @param cinema_id 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectDeptTree/{cinema_id}", "/selectDeptTree/{cinema_id}/{excludeId}" })
    public String selectDeptTree(@PathVariable("cinema_id") Integer cinema_id,
                                 @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("cinema", iCinemaService.selectCinemaById(cinema_id));
        mmap.put("excludeId", excludeId);
        return prefix + "/tree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = iCinemaService.selectCinemaTree(new Cinema());
        return ztrees;
    }


    /**
     * 加载角色部门（数据权限）列表树
     */
    @GetMapping("/roleDeptTreeData")
    @ResponseBody
    public List<Ztree> deptTreeData(SysRole role)
    {
        List<Ztree> ztrees = iShowRoomService.roleDeptTreeData(role);
        return ztrees;
    }
}
