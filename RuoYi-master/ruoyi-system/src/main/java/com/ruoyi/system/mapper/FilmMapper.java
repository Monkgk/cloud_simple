package com.ruoyi.system.mapper;

import com.common.Entity.Film;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 影片查询 数据层
 */
@Mapper
public interface FilmMapper {
    /**
     * 查询影片数据集合
     * @param film
     * @return
     */
    List<Film> selectFilmList(Film film);

    /**
     * 查询所有影片
     * @return
     */
    List<Film> selectFilmAll();

    /**
     * 根据影片ID查询影片
     * @param filmId
     * @return
     */
    Film selectFilmById(Integer filmId);

    /**
     * 批量删除影片信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFilmByIds(Integer[] ids);

    /**
     * 新增影片信息
     * @param film
     * @return
     */
    int insertFilm(Film film);

    /**
     * 修改影片信息
     * @param film
     * @return
     */
    int updateFilm(Film film);
}
