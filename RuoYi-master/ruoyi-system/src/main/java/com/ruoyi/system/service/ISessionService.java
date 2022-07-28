package com.ruoyi.system.service;

import com.common.Entity.Session;
import com.ruoyi.common.core.domain.Ztree;

import java.util.List;

/**
 * 场次信息 服务层
 */
public interface ISessionService {
    /**
     * 查询场次信息集合
     *
     * @param session 场次信息
     * @return 场次信息集合
     */
    public List<Session> selectSessionList(Session session);


    /**
     * 查询所有场次
     *
     * @return 场次列表
     */
    public List<Session> selectSessionAll();


    /**
     * 根据场次Id查询场次
     * @param sessionId
     * @return
     */
    public Session selectSessionById(Integer sessionId);


    /**
     * 批量删除场次信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    public int deleteSessionByIds(String ids) throws Exception;

    /**
     * 新增保存场次信息
     *
     * @param session 场次信息
     * @return 结果
     */
    public int insertSession(Session session);

    /**
     * 修改保存场次信息
     *
     * @param session 场次信息
     * @return 结果
     */
    public int updateSession(Session session);

    /**
     * 查询部门管理树
     *
     * @param session 部门信息
     * @return 所有部门信息
     */
    List<Ztree> selectSessionTree(Session session);


    /**
     * 删除场次信息
     *
     * @param session_id 场次Id
     * @return 结果
     */
    public int deleteSessionById(Integer session_id);
}
