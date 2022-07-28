package com.ruoyi.system.service.impl;

import com.common.Entity.Session;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.CinemaMapper;
import com.ruoyi.system.mapper.FilmMapper;
import com.ruoyi.system.mapper.SessionMapper;
import com.ruoyi.system.mapper.ShowRoomMapper;
import com.ruoyi.system.service.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class SessionServiceImpl implements ISessionService {

    @Autowired
    private SessionMapper   sessionMapper;
    @Resource
    private CinemaMapper    cinemaMapper;
    @Resource
    private FilmMapper      filmMapper;
    @Resource
    private ShowRoomMapper  showRoomMapper;

    @Override
    public List<Session> selectSessionList(Session session) {
        List<Session> sessionList =  sessionMapper.selectSessionList(session);
        for (Session session1:sessionList){
            session1.setCinema(cinemaMapper.selectCinemaById(session1.getCinema_id()));
            session1.setFilm(filmMapper.selectFilmById(session1.getFilm_id()));
            session1.setRoom(showRoomMapper.selectShowRoomById(session1.getRoom_id()));
        }
        return sessionList;
    }

    @Override
    public List<Session> selectSessionAll() {
        return sessionMapper.selectSessionAll();
    }


    @Override
    public Session selectSessionById(Integer sessionId) {
        Session session1 = sessionMapper.selectSessionById(sessionId);
        session1.setCinema(cinemaMapper.selectCinemaById(session1.getCinema_id()));
        session1.setFilm(filmMapper.selectFilmById(session1.getFilm_id()));
        session1.setRoom(showRoomMapper.selectShowRoomById(session1.getRoom_id()));
        return session1;
    }

    @Override
    public int deleteSessionByIds(String ids) throws Exception {
        Integer[] sessionIds = Convert.toIntArray(ids);
        for (Integer sessionId : sessionIds)
        {
            Session session = selectSessionById(sessionId);
//            if (countUserSessionById(sessionId) > 0)
//            {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
//            }
        }
        return sessionMapper.deleteSessionByIds(sessionIds);
    }


    @Override
    public int insertSession(Session session) {
        return sessionMapper.insertSession(session);
    }

    @Override
    public int updateSession(Session session) {
        return sessionMapper.updateSession(session);
    }

    @Override
    public List<Ztree> selectSessionTree(Session session) {
        List<Session> sessionList = sessionMapper.selectSessionList(session);
        List<Ztree> ztrees = initZtree(sessionList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param sessionList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Session> sessionList)
    {
        return initZtree(sessionList, null);
    }

    /**
     * 对象转部门树
     *
     * @param sessionList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Session> sessionList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (Session session : sessionList)
        {
            if (UserConstants.DEPT_NORMAL.equals(session.getSession_status()))
            {
                Ztree ztree = new Ztree();
                ztree.setId((long)session.getSession_id());
//                ztree.setpId(dept.getParentId());
                ztree.setName(String.valueOf(session.getSession_id()));
                ztree.setTitle(String.valueOf(session.getSession_id()));
//                if (isCheck)
//                {
//                    ztree.setChecked(roleDeptList.contains((long)session.getSession_id() + session.getSession_name()));
//                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }


    @Override
    public int deleteSessionById(Integer session_id) {
        return sessionMapper.deleteSessionById(session_id);
    }

}
