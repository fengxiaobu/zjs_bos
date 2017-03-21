package cn.itcast.bos.service;

import cn.itcast.bos.domain.Region;
import cn.itcast.bos.utils.PageBean;

import java.util.List;

/**
 * luopa 在 2017/3/18 创建.
 */
public interface RegionService {
    void saveBatch(List<Region> list);

    void pageQuery(PageBean pageBean);

    Region findByID(String ids);

    void delete(String ids);

    List<Region> finAll();
}
