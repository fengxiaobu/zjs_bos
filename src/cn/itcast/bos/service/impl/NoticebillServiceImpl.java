package cn.itcast.bos.service.impl;

import cn.itcast.bos.dao.NoticebillDao;
import cn.itcast.bos.domain.Noticebill;
import cn.itcast.bos.service.NoticebillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * luopa 在 2017/3/24 创建.
 */
@Service
@Transactional
public class NoticebillServiceImpl implements NoticebillService {
    @Resource
    private NoticebillDao noticebillDao;
    @Override
    public void save(Noticebill model) {
            noticebillDao.save(model);
    }
}
