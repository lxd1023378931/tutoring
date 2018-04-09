package com.uzak.tutoring.common.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uzak.tutoring.common.dao.IDao;
import com.uzak.tutoring.exception.NullKeyException;
import com.uzak.tutoring.util.ObjectUtil;

/**
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
@Service
@Cacheable
@Transactional
public class DaoUtil<T extends IDao> implements IDaoUtil<T> {
	@Autowired
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		boolean flag = false;
		try {
			getSession().merge(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean delete(T entity) {
		boolean flag = false;
		try {
			getSession().delete(getSession().merge(entity));
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	@Override
	public boolean save(T entity) {
		boolean flag = false;
		try {
			getSession().save(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 更新
	 * 
	 * @param entity
	 * @return
	 */
	public boolean update(T entity) {
		boolean flag = false;
		try {
			getSession().merge(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean fill(T entity) {
		boolean flag = false;
		Class<? extends IDao> clazz = entity.getClass();
		try {
			List<Object> params = new ArrayList<>();
			StringBuilder hql = new StringBuilder("from " + clazz.getSimpleName() + " where 1=1 ");
			Field[] fields = clazz.getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Id.class)) {
					f.setAccessible(true);
					String name = f.getName();
					Object o = f.get(entity);
					// (不为空 && (是字符串但是不为空 || 是数字但不为0))
					if (ObjectUtil.isNotEmpty(o)) {
						// 首字母转大写
						name = name.substring(0, 1).toUpperCase() + name.substring(1);
						hql.append(" and ").append(name).append(" = ? ");
						params.add(o);
					} else {
						throw new NullKeyException("主键为空:" + name);
					}
				}
			}
			Session session = getSession();
			@SuppressWarnings("unchecked")
			Query<T> q = session.createQuery(hql.toString());
			for (int i = 0; i < params.size(); i++) {
				q.setParameter(i, params.get(i));
			}

			List<T> list = q.list();
			if (list.size() > 0) {
				T t = list.get(0);
				Field[] fieldsA = clazz.getDeclaredFields();
				Field[] fieldsB = t.getClass().getDeclaredFields();
				for (int i = 0; i < fieldsA.length; i++) {
					fieldsA[i].setAccessible(true);
					fieldsB[i].setAccessible(true);
					fieldsA[i].set(entity, fieldsB[i].get(t));
				}
				flag = true;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NullKeyException e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<T> query(String hql, Object... params) {
		return query(hql, false, params);
	}

	@Override
	public List<T> query(String hql, boolean cacheable, Object... params) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		// 是否开启查询缓存，数据变化不频繁查询参数较稳定的建议开启查询缓存
		query.setCacheable(cacheable);
		return (List<T>) query.list();
	}

	@Override
	public List<T> queryByPage(String hql, int pageIndex, int pageSize, Object... params) {
		return query(hql, false, pageIndex, pageSize, params);
	}

	@Override
	public List<T> query(String hql, boolean cacheable, int pageIndex, int pageSize, Object... params) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		// 是否开启查询缓存，数据变化不频繁查询参数较稳定的建议开启查询缓存
		query.setCacheable(cacheable);
		return (List<T>) query.setFirstResult((pageIndex) * pageSize).setMaxResults(pageSize).list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<T> querySql(String sql, Object... params) {
		Query<T> query = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>) query.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<T> querySqlByPage(String sql, int pageIndex, int pageSize, Object... params) {
		Query<T> query = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>) query.setFirstResult((pageIndex) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public int executeHql(String hql, Object... params) {
		@SuppressWarnings("unchecked")
		Query<T> query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public int executeSql(String sql, Object... params) {
		Query<T> query = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public int count(String fromsql, Object... params) {
		String sql = "select count(1) " + fromsql;
		Query<T> query = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		@SuppressWarnings("rawtypes")
		List list = query.list();
		if (list != null && list.size() == 1) {
			return Integer.valueOf(list.get(0).toString());
		}
		return 0;
	}
}
