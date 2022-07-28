package com.ruoyi.system.service;

import com.common.Entity.Actor;

import java.util.List;

/**
 * 演员信息 服务层
 */
public interface IActorService {
    /**
     * 查询演员信息集合
     *
     * @param actor 演员信息
     * @return 演员信息集合
     */
    public List<Actor> selectActorList(Actor actor);

    /**
     * 查询所有演员
     *
     * @return 演员列表
     */
    public List<Actor> selectActorAll();


    /**
     * 根据演员Id查询演员
     * @param actorId
     * @return
     */
    public Actor selectActorById(Integer actorId);


    /**
     * 批量删除演员信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteActorByIds(String ids) throws Exception;

    /**
     * 新增保存演员信息
     *
     * @param actor 演员信息
     * @return 结果
     */
    public int insertActor(Actor actor);

    /**
     * 修改保存演员信息
     *
     * @param actor 演员信息
     * @return 结果
     */
    public int updateActor(Actor actor);


}
