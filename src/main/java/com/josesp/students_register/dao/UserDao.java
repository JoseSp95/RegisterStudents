package com.josesp.students_register.dao;

import com.josesp.students_register.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface UserDao extends PagingAndSortingRepository<User, Long> {
}
