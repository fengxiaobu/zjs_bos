package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.SubareaService;
import cn.itcast.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * luopa 在 2017/3/18 创建.
 */
@Service
@Transactional
public class SubareaSericeImpl implements SubareaService {
    @Resource
    private SubareaDao subareaDao;

    @Override
    public void pageQuery(PageBean pageBean) {
        subareaDao.pageQuery(pageBean);
    }

    @Override
    public void saveBatch(List<Subarea> list) {
        for (Subarea subarea : list) {
            subareaDao.saveOrupdate(subarea);
        }
    }

    @Override
    public List<Subarea> findSubareasByIDs(String ids) {
        List<Subarea> subareaList = new ArrayList<>();
        String[] strings = ids.split(",");
        for (String id : strings) {
            Subarea subarea = subareaDao.findByID(id);
            subareaList.add(subarea);
        }
        return subareaList;
    }
}
