package com.josesp.students_register.service;

import com.josesp.students_register.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> findAll(Pageable pageable);
    User findById(Long id);
    void save(User user);
    void deleteById(Long id);

}
