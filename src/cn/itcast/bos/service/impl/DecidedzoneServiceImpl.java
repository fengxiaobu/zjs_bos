package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.DecidedzoneDao;
import cn.itcast.bos.dao.SubareaDao;
import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.domain.Subarea;
import cn.itcast.bos.service.DecidedzoneService;
import cn.itcast.bos.utils.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * luopa 在 2017/3/22 创建.
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements DecidedzoneService {
    @Resource
    private DecidedzoneDao decidedzoneDao;
    @Resource
    protected SubareaDao subareaDao;
    @Override
    public void query(PageBean pageBean) {
    decidedzoneDao.pageQuery(pageBean);
    }

    @Override
    public void save(Decidedzone model, String subareaid) {
        decidedzoneDao.save(model);
        Subarea subarea = subareaDao.findByID(subareaid);
       subarea.setDecidedzone(model);
    }
}
