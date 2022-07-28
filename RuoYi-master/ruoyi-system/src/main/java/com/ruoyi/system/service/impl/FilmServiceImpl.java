package com.ruoyi.system.service.impl;

import com.common.Entity.Film;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.FilmMapper;
import com.ruoyi.system.service.IFilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmServiceImpl implements IFilmService {
    @Autowired
    private FilmMapper filmMapper;

    @Override
    public List<Film> selectFilmList(Film film) {
        return filmMapper.selectFilmList(film);
    }

    @Override
    public List<Film> selectFilmAll() {
        return filmMapper.selectFilmAll();
    }


    @Override
    public Film selectFilmById(Integer filmId) {
        return filmMapper.selectFilmById(filmId);
    }

    @Override
    public int deleteFilmByIds(String ids) throws Exception {
        Integer[] filmIds = Convert.toIntArray(ids);
        for (Integer filmId : filmIds)
        {
            Film film = selectFilmById(filmId);
//            if (countUserFilmById(filmId) > 0)
//            {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
//            }
        }
        return filmMapper.deleteFilmByIds(filmIds);
    }


    @Override
    public int insertFilm(Film film) {
        return filmMapper.insertFilm(film);
    }

    @Override
    public int updateFilm(Film film) {
        return filmMapper.updateFilm(film);
    }


    @Override
    public List<Ztree> selectFilmTree(Film film) {
        List<Film> filmList = filmMapper.selectFilmList(film);
        List<Ztree> ztrees = initZtree(filmList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param filmList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Film> filmList)
    {
        return initZtree(filmList, null);
    }

    /**
     * 对象转部门树
     *
     * @param filmList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Film> filmList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (Film film : filmList)
        {
            if (UserConstants.DEPT_NORMAL.equals(film.getFilm_status()))
            {
                Ztree ztree = new Ztree();
                ztree.setId((long)film.getFilm_id());
//                ztree.setpId(dept.getParentId());
                ztree.setName(film.getFilm_name());
                ztree.setTitle(film.getFilm_name());
//                if (isCheck)
//                {
//                    ztree.setChecked(roleDeptList.contains((long)cinema.getCinema_id() + cinema.getCinema_name()));
//                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }
}
