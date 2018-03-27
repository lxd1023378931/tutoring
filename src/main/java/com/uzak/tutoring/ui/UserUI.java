package com.uzak.tutoring.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.uzak.tutoring.dao.User;
import com.uzak.tutoring.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserUI {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("getUserByID")
	@ResponseBody
	public User getUserByID(Long id) {
		User user = userRepository.getUserByID(id);
		System.out.println(user.getName());
		return user;
	}

	@RequestMapping("/save")
	@ResponseBody
	public void saveUser(User u) {
		userRepository.save(u);
	}
}
