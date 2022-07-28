package com.ruoyi.system.mapper;

import com.common.Entity.Session;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 场次查询 数据层
 */
@Mapper
public interface SessionMapper {
    /**
     * 查询场次数据集合
     * @param session
     * @return
     */
    List<Session> selectSessionList(Session session);

    /**
     * 查询所有场次
     * @return
     */
    List<Session> selectSessionAll();

    /**
     * 根据场次ID查询场次
     * @param sessionId
     * @return
     */
    Session selectSessionById(Integer sessionId);

    /**
     * 批量删除场次信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSessionByIds(Integer[] ids);

    /**
     * 新增场次信息
     * @param session
     * @return
     */
    int insertSession(Session session);

    /**
     * 修改场次信息
     * @param session
     * @return
     */
    int updateSession(Session session);

    /**
     * 删除场次信息
     * @param session_id
     * @return
     */
    int deleteSessionById(Integer session_id);

    /**
     * 查询下级放映厅信息
     * @param session_id
     * @return
     */
    int selectRoomCountBySessionId(Integer session_id);
}
