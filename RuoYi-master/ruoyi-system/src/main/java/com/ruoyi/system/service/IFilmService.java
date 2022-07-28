package com.ruoyi.system.service;

import com.common.Entity.Film;
import com.ruoyi.common.core.domain.Ztree;

import java.util.List;

/**
 * 影片信息 服务层
 */
public interface IFilmService {
    /**
     * 查询影片信息集合
     *
     * @param film 影片信息
     * @return 影片信息集合
     */
    public List<Film> selectFilmList(Film film);

    /**
     * 查询所有影片
     *
     * @return 影片列表
     */
    public List<Film> selectFilmAll();


    /**
     * 根据影片Id查询影片
     * @param filmId
     * @return
     */
    public Film selectFilmById(Integer filmId);


    /**
     * 批量删除影片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteFilmByIds(String ids) throws Exception;

    /**
     * 新增保存影片信息
     *
     * @param film 影片信息
     * @return 结果
     */
    public int insertFilm(Film film);

    /**
     * 修改保存影片信息
     *
     * @param film 影片信息
     * @return 结果
     */
    public int updateFilm(Film film);

    /**
     * 查询部门管理树
     *
     * @param film 部门信息
     * @return 所有部门信息
     */
    List<Ztree> selectFilmTree(Film film);
}
