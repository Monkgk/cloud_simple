package com.ruoyi.system.service.impl;

import com.common.Entity.Cinema;
import com.common.Entity.ShowRoom;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.mapper.CinemaMapper;
import com.ruoyi.system.mapper.ShowRoomMapper;
import com.ruoyi.system.service.IShowRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowRoomServiceImpl implements IShowRoomService {

    @Autowired
    private ShowRoomMapper showroomMapper;
    @Resource
    private CinemaMapper    cinemaMapper;

    @Override
    public List<ShowRoom> selectShowRoomList(ShowRoom showroom) {
        return showroomMapper.selectShowRoomList(showroom);
    }

    @Override
    public List<ShowRoom> selectShowRoomAll() {
        return showroomMapper.selectShowRoomAll();
    }


    @Override
    public ShowRoom selectShowRoomById(Integer showroomId) {
        return showroomMapper.selectShowRoomById(showroomId);
    }

    @Override
    public int deleteShowRoomByIds(String ids) throws Exception {
        Integer[] showroomIds = Convert.toIntArray(ids);
        for (Integer showroomId : showroomIds)
        {
            ShowRoom showroom = selectShowRoomById(showroomId);
//            if (countUserShowRoomById(showroomId) > 0)
//            {
//                throw new ServiceException(String.format("%1$s已分配,不能删除", post.getPostName()));
//            }
        }
        return showroomMapper.deleteShowRoomByIds(showroomIds);
    }


    @Override
    public int insertShowRoom(ShowRoom showroom) {
        return showroomMapper.insertShowRoom(showroom);
    }

    @Override
    public int updateShowRoom(ShowRoom showroom) {
        return showroomMapper.updateShowRoom(showroom);
    }

    @Override
    public List<Cinema> selectCinemaList(Integer roomId) {
        return cinemaMapper.selectCinemaAll();
    }



    @Override
    public List<Ztree> roleDeptTreeData(SysRole role) {
        return null;
    }

    @Override
    public List<Ztree> selectRoomTree(ShowRoom showroom) {
        List<ShowRoom> showroomList = showroomMapper.selectShowRoomList(showroom);
        List<Ztree> ztrees = initZtree(showroomList);
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param showroomList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<ShowRoom> showroomList)
    {
        return initZtree(showroomList, null);
    }

    /**
     * 对象转部门树
     *
     * @param showroomList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<ShowRoom> showroomList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (ShowRoom showroom : showroomList)
        {
            if (UserConstants.DEPT_NORMAL.equals(showroom.getRoom_status()))
            {
                Ztree ztree = new Ztree();
                ztree.setId((long)showroom.getRoom_id());
//                ztree.setpId(dept.getParentId());
                ztree.setName(showroom.getRoom_name());
                ztree.setTitle(showroom.getRoom_name());
//                if (isCheck)
//                {
//                    ztree.setChecked(roleDeptList.contains((long)showroom.getRoom_id() + showroom.getRoom_name()));
//                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

}
