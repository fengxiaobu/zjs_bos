package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.StaffDao;
import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.service.StaffService;
import cn.itcast.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * luopa 在 2017/3/16 创建.
 */
@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Resource
    private StaffDao staffDao;
    
    @Override
    public void save(Staff model) {
        staffDao.save(model);
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        staffDao.pageQuery(pageBean);
    }

    @Override
    public Staff findByID(String id) {
        return  staffDao.findByID(id);
    }

    @Override
    public void delete(String ids) {
        String[] split = ids.split(",");
        for (int i=0;i<split.length;i++){
            Staff staff = staffDao.findByID(split[i]);
            staff.setDeltag("1");//逻辑删除
        }
    }

    @Override
    public void update(Staff staff) {
        staffDao.update(staff);
    }

    @Override
    public void restore(String ids) {
        String[] split = ids.split(",");
        for (int i=0;i<split.length;i++){
            Staff staff = staffDao.findByID(split[i]);
            staff.setDeltag("0");
        }
    }

    @Override
    public List<Staff> findByCondition(DetachedCriteria dc) {
        return staffDao.findByCriteria(dc);
    }
}
