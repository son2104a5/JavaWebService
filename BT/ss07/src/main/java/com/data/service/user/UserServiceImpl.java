package com.data.service.user;

import com.data.model.entity.User;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found user with id: " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found user with id: " + id));
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public boolean delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cannot found user with id: " + id));
        userRepository.deleteById(id);
        return true;
    }
}
