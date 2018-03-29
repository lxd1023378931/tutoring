package com.uzak.tutoring.common.util;

import java.util.List;

import org.hibernate.Session;

/**
 * 实体类基础操作接口
 * 
 * @author 梁秀斗
 * @mail liangxiudou@zving.com
 * @date 2018年3月28日
 */
public interface IDaoUtil<T> {
	/**
	 * 获取Session
	 * 
	 * @return
	 */
	Session getSession();

	/**
	 * 保持或修改实体
	 * 
	 * @return
	 */
	boolean saveOrUpdate(T entity);

	/**
	 * 删除实体
	 * 
	 * @return
	 */
	boolean delete(T entity);

	/**
	 * 保持实体
	 * 
	 * @param entity
	 * @return
	 */
	boolean save(T entity);

	/**
	 * 修改实体
	 * 
	 * @return
	 */
	boolean update(T entity);

	/**
	 * 根据主键查询实体
	 * 
	 * @return
	 */
	boolean fill(T entity);

	/**
	 * 执行hql更新语句
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	int executeHql(String hql, Object... params);

	/**
	 * 执行sql更新语句
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	int executeSql(String sql, Object... params);

	/**
	 * 不分页执行hql查询语句
	 * 
	 * @param hql
	 * @return
	 */
	List<T> query(String hql, Object... params);

	/**
	 * 分页执行hql查询语句
	 * 
	 * @param hql
	 * @return
	 */
	List<T> query(String hql, int pageIndex, int pageSize, Object... params);
}