package com.ruoyi.system.mapper;

import com.common.Entity.Actor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 演员查询 数据层
 */
@Mapper
public interface ActorMapper {
    /**
     * 查询演员数据集合
     * @param actor
     * @return
     */
    List<Actor> selectActorList(Actor actor);

    /**
     * 查询所有演员
     * @return
     */
    List<Actor> selectActorAll();

    /**
     * 根据演员ID查询演员
     * @param actorId
     * @return
     */
    Actor selectActorById(Integer actorId);

    /**
     * 批量删除演员信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActorByIds(Integer[] ids);

    /**
     * 新增演员信息
     * @param actor
     * @return
     */
    int insertActor(Actor actor);

    /**
     * 修改演员信息
     * @param actor
     * @return
     */
    int updateActor(Actor actor);
}
