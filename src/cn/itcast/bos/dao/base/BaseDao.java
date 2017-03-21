package cn.itcast.bos.dao.base;

import cn.itcast.bos.utils.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.io.Serializable;
import java.util.List;

/**
 * luopa 在 2017/3/13 创建.
 */
public interface BaseDao<T> {
    /**
     * 添加
     * @param entity
     */
    public void save(T entity);

    /**
     * 删除
     * @param entity
     */
    public void delete(T entity);

    /**
     * 修改
     * @param entity
     */
    public void update(T entity);

    /**
     * 查询所有
     */
    public List<T> findAll();

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public T findByID(Serializable id);

    /**
     * 根据条件查询
     * @param criteria
     * @return
     */
    public  List<T> findByCriteria(DetachedCriteria criteria);

    /**
     * 根据命名查询语句查询
     */
    public List<T> findByNamedQuery(String queryName,Object...args);
    /**
     * 执行增删改操作的命名语句
     */
    public void executeNamedQuery(String queryName,Object ...args);
    /**
     * 通用分页查询方法
     */
    public void pageQuery(PageBean pageBean);

    void saveOrupdate(T t);
}
