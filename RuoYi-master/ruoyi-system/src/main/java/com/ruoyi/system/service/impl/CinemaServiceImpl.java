package com.ruoyi.system.service.impl;

import com.common.Entity.Cinema;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.CinemaMapper;
import com.ruoyi.system.service.ICinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CinemaServiceImpl implements ICinemaService {

    @Autowired
    private CinemaMapper cinemaMapper;

    @Override
    public List<Cinema> selectCinemaList(Cinema cinema) {
        return cinemaMapper.selectCinemaList(cinema);
    }

    @Override
    public List<Cinema> selectCinemaAll() {
        return cinemaMapper.selectCinemaAll();
    }


    @Override
    public Cinema selectCinemaById(Integer cinemaId) {
        return cinemaMapper.selectCinemaById(cinemaId);
    }

    @Override
    public int deleteCinemaByIds(String ids) throws Exception {
        Integer[] cinemaIds = Convert.toIntArray(ids);
        for (Integer cinemaId : cinemaIds)
        {
            Cinema cinema = selectCinemaById(cinemaId);
//            if (countUserCinemaById(cinemaId) > 0)
//            {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
//            }
        }
        return cinemaMapper.deleteCinemaByIds(cinemaIds);
    }


    @Override
    public int insertCinema(Cinema cinema) {
        return cinemaMapper.insertCinema(cinema);
    }

    @Override
    public int updateCinema(Cinema cinema) {
        return cinemaMapper.updateCinema(cinema);
    }

    @Override
    public List<Ztree> selectCinemaTree(Cinema cinema) {
        List<Cinema> cinemaList = cinemaMapper.selectCinemaList(cinema);
        List<Ztree> ztrees = initZtree(cinemaList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param cinemaList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Cinema> cinemaList)
    {
        return initZtree(cinemaList, null);
    }

    /**
     * 对象转部门树
     *
     * @param cinemaList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<Cinema> cinemaList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (Cinema cinema : cinemaList)
        {
            if (UserConstants.DEPT_NORMAL.equals(cinema.getCinema_status()))
            {
                Ztree ztree = new Ztree();
                ztree.setId((long)cinema.getCinema_id());
//                ztree.setpId(dept.getParentId());
                ztree.setName(cinema.getCinema_name());
                ztree.setTitle(cinema.getCinema_name());
//                if (isCheck)
//                {
//                    ztree.setChecked(roleDeptList.contains((long)cinema.getCinema_id() + cinema.getCinema_name()));
//                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }


    @Override
    public int deleteCinemaById(Integer cinema_id) {
        return cinemaMapper.deleteCinemaById(cinema_id);
    }

    @Override
    public int selectRoomCount(Integer cinema_id) {
        return cinemaMapper.selectRoomCountByCinemaId(cinema_id);
    }

}
