package com.data.controller;

import com.data.model.dto.response.DataResponse;
import com.data.model.entity.User;
import com.data.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getUsers() {
        return new ResponseEntity<>(new DataResponse<>(userService.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<User>> getUserById(@PathVariable Long id) {
        return new ResponseEntity<>(new DataResponse<>(userService.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> insertUser(@RequestBody User user) {
        return new ResponseEntity<>(new DataResponse<>(userService.save(user), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DataResponse<User>> updateUser(@RequestBody User user, @RequestParam Long id) {
        return new ResponseEntity<>(new DataResponse<>(userService.update(user, id), HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<DataResponse<Boolean>> deleteUser(@RequestParam Long id) {
        return new ResponseEntity<>(new DataResponse<>(userService.delete(id), HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
    }
}
