package com.focus.ttnet.entity;

import com.focus.ttnet.ApplicationTests;
import com.focus.ttnet.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserRepositoryTest extends ApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne(){

        Optional<User> optional = userRepository.findById(1L);
        User user = optional.get();
        System.out.println(user);

    }

}
