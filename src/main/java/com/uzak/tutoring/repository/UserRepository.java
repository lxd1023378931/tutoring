package com.uzak.tutoring.repository;

import java.util.List;

import javax.persistence.Table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.uzak.tutoring.dao.User;

@Repository
@Table(name = "User")
public interface UserRepository extends JpaRepository<User, Long> {
	List<User> findByNameContaining(String name);

	User getUserByID(Long id);

	User save(User u);

	@Query("select t from User t where t.name=:name")
	public User findUserByName(@Param("name") String name);
}
