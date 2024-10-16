package com.electronics.store.electronocs_Store.repository;

import com.electronics.store.electronocs_Store.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {


}
