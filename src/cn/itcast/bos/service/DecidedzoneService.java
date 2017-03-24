package cn.itcast.bos.service;

import cn.itcast.bos.domain.Decidedzone;
import cn.itcast.bos.utils.PageBean;

/**
 * luopa 在 2017/3/22 创建.
 */
public interface DecidedzoneService {
    void query(PageBean pageBean);

    void save(Decidedzone model, String subareaid);
}
