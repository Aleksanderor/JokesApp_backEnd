package com.example.jokes.repository;

import com.example.jokes.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNick(String nick);

    @Override
    List<User> findAll();


}
