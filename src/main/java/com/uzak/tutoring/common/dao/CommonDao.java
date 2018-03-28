package com.uzak.tutoring.common.dao;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月29日
 */
@Service
public class CommonDao<T> implements IBaseDao<T> {
	@Autowired
	private EntityManager entityManager;

	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		boolean flag = false;
		try {
			getSession().saveOrUpdate(entity);
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
			getSession().delete(entity);
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
			getSession().update(entity);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public T fill(T entity) {
		Class<? extends CommonDao> clazz = this.getClass();
		try {
			T t = (T) clazz.newInstance();
			StringBuilder hql = new StringBuilder("from " + clazz.getSimpleName() + " where 1=1 ");
			Field[] fields = this.getClass().getDeclaredFields();
			for (Field f : fields) {
				if (f.isAnnotationPresent(Id.class)) {
					f.setAccessible(true);
					Object o = f.get(this);
					if (o != null) {
						String name = f.getName();
						// 首字母转大写
						name = name.substring(0, 1).toUpperCase() + name.substring(1);
						hql.append(" and ").append(name).append(" = ");
						if (String.class.getName().equals(f.getType().getTypeName())) {
							hql.append("'").append(o.toString()).append("'");
						} else {
							hql.append(o.toString());
						}
					}
				}
			}
			Session session = getSession();
			Query q = session.createQuery(hql.toString());
			List<T> list = q.list();
			if (list.size() > 0) {
				Object obj = list.get(0);
				return (T) obj;
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<T> query(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>) query.list();
	}

	@Override
	public List<T> query(String hql, int pageIndex, int pageSize, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return (List<T>) query.setFirstResult((pageIndex) * pageSize).setMaxResults(pageSize).list();
	}

	@Override
	public int executeHql(String hql, Object... params) {
		Query query = getSession().createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

	@Override
	public int executeSql(String sql, Object... params) {
		Query query = getSession().createSQLQuery(sql);
		for (int i = 0; i < params.length; i++) {
			query.setParameter(i, params[i]);
		}
		return query.executeUpdate();
	}

}
