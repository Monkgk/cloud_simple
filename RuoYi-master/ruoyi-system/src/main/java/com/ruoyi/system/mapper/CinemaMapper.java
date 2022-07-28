package com.ruoyi.system.mapper;

import com.common.Entity.Cinema;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 影院查询 数据层
 */
@Mapper
public interface CinemaMapper {
    /**
     * 查询影院数据集合
     * @param cinema
     * @return
     */
    List<Cinema> selectCinemaList(Cinema cinema);

    /**
     * 查询所有影院
     * @return
     */
    List<Cinema> selectCinemaAll();

    /**
     * 根据影院ID查询影院
     * @param cinemaId
     * @return
     */
    Cinema selectCinemaById(Integer cinemaId);

    /**
     * 批量删除影院信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCinemaByIds(Integer[] ids);

    /**
     * 新增影院信息
     * @param cinema
     * @return
     */
    int insertCinema(Cinema cinema);

    /**
     * 修改影院信息
     * @param cinema
     * @return
     */
    int updateCinema(Cinema cinema);

    /**
     * 删除影院信息
     * @param cinema_id
     * @return
     */
    int deleteCinemaById(Integer cinema_id);

    /**
     * 查询下级放映厅信息
     * @param cinema_id
     * @return
     */
    int selectRoomCountByCinemaId(Integer cinema_id);
}
