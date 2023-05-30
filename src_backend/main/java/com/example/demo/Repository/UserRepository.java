package com.example.demo.Repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String username);
    Optional<User> findById(Long id);
    List<User> findAll();
    List<User> findAllByLogged(boolean logged);
}
