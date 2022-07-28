package com.ruoyi.system.mapper;

import com.common.Entity.ShowRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 放映厅查询 数据层
 */
@Mapper
public interface ShowRoomMapper {
    /**
     * 查询放映厅数据集合
     * @param showroom
     * @return
     */
    List<ShowRoom> selectShowRoomList(ShowRoom showroom);

    /**
     * 查询所有放映厅
     * @return
     */
    List<ShowRoom> selectShowRoomAll();

    /**
     * 根据放映厅ID查询放映厅
     * @param showroomId
     * @return
     */
    ShowRoom selectShowRoomById(Integer showroomId);

    /**
     * 批量删除放映厅信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShowRoomByIds(Integer[] ids);

    /**
     * 新增放映厅信息
     * @param showroom
     * @return
     */
    int insertShowRoom(ShowRoom showroom);

    /**
     * 修改放映厅信息
     * @param showroom
     * @return
     */
    int updateShowRoom(ShowRoom showroom);
}
