package cn.itcast.bos.service;

import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * luopa 在 2017/3/18 创建.
 */
public interface SubareaService {
    void pageQuery(PageBean pageBean);

    void saveBatch(List<Subarea> list);

    List<Subarea> findSubareasByIDs(String ids);

    List<Subarea> findByCondition(DetachedCriteria dc);
}
