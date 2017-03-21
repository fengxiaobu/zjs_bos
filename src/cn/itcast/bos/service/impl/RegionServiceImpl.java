package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.RegionDao;
import cn.itcast.bos.domain.Region;
import cn.itcast.bos.service.RegionService;
import cn.itcast.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * luopa 在 2017/3/18 创建.
 */
@Service
@Transactional
public class RegionServiceImpl implements RegionService {
    @Resource
    private RegionDao regionDao;

    @Override
    public void saveBatch(List<Region> list) {
        for (Region region : list) {
            regionDao.saveOrupdate(region);
        }
    }

    @Override
    public void pageQuery(PageBean pageBean) {
        regionDao.pageQuery(pageBean);
    }

    @Override
    public Region findByID(String ids) {
        return regionDao.findByID(ids);
    }

    @Override
    public void delete(String ids) {
        String[] strings = ids.split(",");
        for (String string : strings) {
            Region region = regionDao.findByID(ids);
            regionDao.delete(region);
        }
    }

    @Override
    public List<Region> finAll() {
        return regionDao.findAll();
    }
}
