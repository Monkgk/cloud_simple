package com.ruoyi.system.service;

import com.common.Entity.Cinema;
import com.ruoyi.common.core.domain.Ztree;

import java.util.List;

/**
 * 影院信息 服务层
 */
public interface ICinemaService {
    /**
     * 查询影院信息集合
     *
     * @param cinema 影院信息
     * @return 影院信息集合
     */
    public List<Cinema> selectCinemaList(Cinema cinema);

    /**
     * 查询所有影院
     *
     * @return 影院列表
     */
    public List<Cinema> selectCinemaAll();


    /**
     * 根据影院Id查询影院
     * @param cinemaId
     * @return
     */
    public Cinema selectCinemaById(Integer cinemaId);


    /**
     * 批量删除影院信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteCinemaByIds(String ids) throws Exception;

    /**
     * 新增保存影院信息
     *
     * @param cinema 影院信息
     * @return 结果
     */
    public int insertCinema(Cinema cinema);

    /**
     * 修改保存影院信息
     *
     * @param cinema 影院信息
     * @return 结果
     */
    public int updateCinema(Cinema cinema);

    /**
     * 查询部门管理树
     *
     * @param cinema 部门信息
     * @return 所有部门信息
     */
    List<Ztree> selectCinemaTree(Cinema cinema);


    /**
     * 删除影院信息
     *
     * @param cinema_id 影院Id
     * @return 结果
     */
    public int deleteCinemaById(Integer cinema_id);
    /**
     * 查询放映厅数量
     *
     * @param cinema_id id
     * @return 结果
     */
    public int selectRoomCount(Integer cinema_id);
}
