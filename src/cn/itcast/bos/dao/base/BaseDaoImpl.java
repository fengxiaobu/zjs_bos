package cn.itcast.bos.dao.base;

import cn.itcast.bos.utils.PageBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * luopa 在 2017/3/13 创建.
 */
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
    @Resource
   public void setSF(SessionFactory sessionFactory){
       super.setSessionFactory(sessionFactory);
   }
    public Class<T> domain;

    public BaseDaoImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
        domain = (Class<T>) actualTypeArguments[0];
    }

    /**
     * 添加
     *
     * @param entity
     */
    @Override
    public void save(T entity) {
        this.getHibernateTemplate().save(entity);
    }

    /**
     * 删除
     *
     * @param entity
     */
    @Override
    public void delete(T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    /**
     * 修改
     *
     * @param entity
     */
    @Override
    public void update(T entity) {
        this.getHibernateTemplate().update(entity);
    }

    /**
     * 查询所有
     */
    @Override
    public List<T> findAll() {
        String hql = "FROM " + domain.getSimpleName();
        return (List<T>) this.getHibernateTemplate().find(hql);
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    @Override
    public T findByID(Serializable id) {
        return this.getHibernateTemplate().get(domain, id);
    }

    /**
     * 根据条件查询
     *
     * @param criteria
     * @return
     */
    @Override
    public List<T> findByCriteria(DetachedCriteria criteria) {
        return (List<T>) this.getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * 根据命名查询语句查询
     *
     * @param queryName
     * @param args
     */
    @Override
    public List<T> findByNamedQuery(String queryName, Object... args) {
        return (List<T>) this.getHibernateTemplate().findByNamedQuery(queryName, args);
    }

    /**
     * 执行增删改操作的命名语句
     *
     * @param queryName
     * @param args
     */
    @Override
    public void executeNamedQuery(String queryName, Object... args) {
        Session session = this.getSessionFactory().getCurrentSession();
        Query query = session.getNamedQuery(queryName);
        if (args != null && args.length > 0) {
            int i = 0;
            for (Object arg : args) {
                query.setParameter(i++, arg);
            }
        }
        query.executeUpdate();
    }

    /**
     * 通用分页查询方法
     *
     * @param pageBean
     */
    @Override
    public void pageQuery(PageBean pageBean) {
        Integer currentPage = pageBean.getCurrentPage();//当前页数
        Integer pageSize = pageBean.getPageSize();//每页显示数量
        DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();//离线程序对象
        //设置查询结果集
        detachedCriteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
        Long aLong = list.get(0);
        //重置查询结果集
        detachedCriteria.setProjection(null);
        detachedCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        Integer firstResult = (currentPage - 1) * pageSize;
        Integer maxResults = pageSize;
        //分页查询
        List recordList = this.getHibernateTemplate().findByCriteria(detachedCriteria, firstResult, maxResults);

        pageBean.setTotal(aLong.intValue());
        pageBean.setRows(recordList);
    }

    @Override
    public void saveOrupdate(T t) {
        this.getHibernateTemplate().saveOrUpdate(t);
    }
}
