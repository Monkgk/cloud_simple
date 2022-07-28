package com.ruoyi.system.service;

import com.common.Entity.Cinema;
import com.common.Entity.ShowRoom;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysRole;

import java.util.List;

/**
 * 影院信息 服务层
 */
public interface IShowRoomService {
    /**
     * 查询影院信息集合
     *
     * @param showroom 影院信息
     * @return 影院信息集合
     */
    public List<ShowRoom> selectShowRoomList(ShowRoom showroom);

    /**
     * 查询所有影院
     *
     * @return 影院列表
     */
    public List<ShowRoom> selectShowRoomAll();


    /**
     * 根据影院Id查询影院
     * @param showroomId
     * @return
     */
    public ShowRoom selectShowRoomById(Integer showroomId);


    /**
     * 批量删除影院信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteShowRoomByIds(String ids) throws Exception;

    /**
     * 新增保存影院信息
     *
     * @param showroom 影院信息
     * @return 结果
     */
    public int insertShowRoom(ShowRoom showroom);

    /**
     * 修改保存影院信息
     *
     * @param showroom 影院信息
     * @return 结果
     */
    public int updateShowRoom(ShowRoom showroom);

    /**
     * 根据部门ID查询信息
     *
     * @param roomId 部门ID
     * @return 部门信息
     */
    public List<Cinema> selectCinemaList(Integer roomId);


    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Ztree> roleDeptTreeData(SysRole role);

    /**
     * 查询部门管理树
     *
     * @param room 部门信息
     * @return 所有部门信息
     */
    List<Ztree> selectRoomTree(ShowRoom room);
}
