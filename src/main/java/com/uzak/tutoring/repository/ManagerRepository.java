package com.uzak.tutoring.repository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uzak.tutoring.entity.Manager;
/**
 * 管理员操作类
 * 
 * @author Liang Xiudou
 * @date 2018/3/28
 */
@Repository
@Table(name = "Manager")
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	List<Manager> findByNameContaining(String name);

	Manager getUserByID(Long id);

	@SuppressWarnings("unchecked")
	Manager save(Manager m);

	@Query("select t from Manager t where t.name=:name")
	public Manager findUserByName(@Param("name") String name);
}
