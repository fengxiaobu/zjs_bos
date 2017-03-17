package cn.itcast.bos.service;

import cn.itcast.bos.domain.Staff;
import cn.itcast.bos.utils.PageBean;

/**
 * luopa 在 2017/3/16 创建.
 */
public interface StaffService {
    void save(Staff model);

    void pageQuery(PageBean pageBean);

    Staff findByID(String id);

    void delete(String sid);

    void update(Staff staff);

    void restore(String ids);
}
