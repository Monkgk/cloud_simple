package com.ruoyi.web.controller.cinema;

import com.common.Entity.Cinema;
import com.common.Entity.Film;
import com.common.Entity.Session;
import com.common.Entity.ShowRoom;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.service.ICinemaService;
import com.ruoyi.system.service.IFilmService;
import com.ruoyi.system.service.ISessionService;
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
@RequestMapping("/cinema/session")
public class SessionController extends BaseController {
    private String prefix = "cinema/session";

    @Autowired
    private ISessionService iSessionService;
    @Resource
    private ICinemaService  iCinemaService;
    @Resource
    private IFilmService    iFilmService;
    @Resource
    private IShowRoomService    iShowRoomService;

    @RequiresPermissions("cinema:session:view")
    @GetMapping()
    public String session(){
        return prefix+"/session";
    }

    @RequiresPermissions("cinema:session:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Session session)
    {
        startPage();
        List<Session> list = iSessionService.selectSessionList(session);
        return getDataTable(list);
    }

    @RequiresPermissions("cinema:session:remove")
    @Log(title = "场次管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        try
        {
            return toAjax(iSessionService.deleteSessionByIds(ids));
        }
        catch (Exception e)
        {
            return error(e.getMessage());
        }
    }

    /**
     * 新增场次
     */
    @GetMapping("/add/{session_id}")
    public String add(@PathVariable("session_id")Integer   session_id,  ModelMap mmap)
    {
        mmap.put("info",iSessionService.selectSessionById(session_id));
        return prefix + "/add";
    }
//    @GetMapping("/add")
//    public String add(ModelMap mmap)
//    {
////        mmap.put("info",iSessionService.selectSessionById(1));
//        return prefix + "/add";
//    }

    /**
     * 新增保存场次
     */
    @RequiresPermissions("cinema:session:add")
    @Log(title = "场次管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated Session session)
    {
        return toAjax(iSessionService.insertSession(session));
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
            String filePath = RuoYiConfig.getUploadPath("session");
//            String filePath = "session";
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
     * 修改场次
     */

    @GetMapping("/edit/{session_id}")
    public String edit(@PathVariable("session_id") Integer session_id, ModelMap mmap)
    {
        mmap.put("info", iSessionService.selectSessionById(session_id));
        return prefix + "/edit";
    }

    /**
     * 修改保存场次
     */
    @RequiresPermissions("cinema:session:edit")
    @Log(title = "场次管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated Session session)
    {
        return toAjax(iSessionService.updateSession(session));
    }


    /**
     * 选择部门树
     *
     * @param cinema_id 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectCinemaTree/{cinema_id}", "/selectCinemaTree/{cinema_id}/{excludeId}" })
    public String selectCinemaTree(@PathVariable("cinema_id") Integer cinema_id,
                                 @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("cinema", iCinemaService.selectCinemaById(cinema_id));
        mmap.put("excludeId", excludeId);
        return prefix + "/cinematree";
    }
//    @GetMapping(value = { "/selectCinemaTree", "/selectCinemaTree/{cinema_id}/{excludeId}" })
//    public String selectCinemaTree(
//                                   @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
//    {
////        mmap.put("cinema", iCinemaService.selectCinemaById(cinema_id));
//        mmap.put("excludeId", excludeId);
//        return prefix + "/cinematree";
//    }

    /**
     * 选择部门树
     *
     * @param film_id 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectFilmTree/{film_id}", "/selectFilmTree/{film_id}/{excludeId}" })
    public String selectFilmTree(@PathVariable("film_id") Integer film_id,
                                 @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("film", iFilmService.selectFilmById(film_id));
        mmap.put("excludeId", excludeId);
        return prefix + "/filmtree";
    }
    /**
     * 选择部门树
     *
     * @param room_id 部门ID
     * @param excludeId 排除ID
     */
    @GetMapping(value = { "/selectRoomTree/{room_id}", "/selectRoomTree/{room_id}/{excludeId}" })
    public String selectRoomTree(@PathVariable("room_id") Integer room_id,
                                 @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        mmap.put("room", iShowRoomService.selectShowRoomById(room_id));
        mmap.put("excludeId", excludeId);
        return prefix + "/roomtree";
    }

    /**
     * 加载部门列表树
     */
    @GetMapping("/cinematreeData")
    @ResponseBody
    public List<Ztree> cinematreeData()
    {
        List<Ztree> ztrees = iCinemaService.selectCinemaTree(new Cinema());
        return ztrees;
    }
    /**
     * 加载部门列表树
     */
    @GetMapping("/filmtreeData")
    @ResponseBody
    public List<Ztree> filmtreeData()
    {
        List<Ztree> ztrees = iFilmService.selectFilmTree(new Film());
        return ztrees;
    }
    /**
     * 加载部门列表树
     */
    @GetMapping("/roomtreeData")
    @ResponseBody
    public List<Ztree> roomtreeData()
    {
        List<Ztree> ztrees = iShowRoomService.selectRoomTree(new ShowRoom());
        return ztrees;
    }


//    /**
//     * 加载角色部门（数据权限）列表树
//     */
//    @GetMapping("/roleDeptTreeData")
//    @ResponseBody
//    public List<Ztree> deptTreeData(SysRole role)
//    {
//        List<Ztree> ztrees = iSessionService.roleDeptTreeData(role);
//        return ztrees;
//    }
}
