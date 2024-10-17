package com.electronics.store.electronocs_Store.repository;

import com.electronics.store.electronocs_Store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String Password);
    List<User> findByNameContaining(String keyword);


}
