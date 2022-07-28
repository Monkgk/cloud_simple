package com.ruoyi.system.service.impl;

import com.common.Entity.Actor;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.mapper.ActorMapper;
import com.ruoyi.system.service.IActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActorServiceImpl implements IActorService {
    @Autowired
    private ActorMapper actorMapper;

    @Override
    public List<Actor> selectActorList(Actor actor) {
        return actorMapper.selectActorList(actor);
    }

    @Override
    public List<Actor> selectActorAll() {
        return actorMapper.selectActorAll();
    }


    @Override
    public Actor selectActorById(Integer actorId) {
        return actorMapper.selectActorById(actorId);
    }

    @Override
    public int deleteActorByIds(String ids) throws Exception {
        Integer[] actorIds = Convert.toIntArray(ids);
        for (Integer actorId : actorIds)
        {
            Actor actor = selectActorById(actorId);
//            if (countUserActorById(actorId) > 0)
//            {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
//            }
        }
        return actorMapper.deleteActorByIds(actorIds);
    }


    @Override
    public int insertActor(Actor actor) {
        return actorMapper.insertActor(actor);
    }

    @Override
    public int updateActor(Actor actor) {
        return actorMapper.updateActor(actor);
    }



}
