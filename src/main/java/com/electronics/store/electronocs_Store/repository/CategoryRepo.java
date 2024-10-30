package com.electronics.store.electronocs_Store.repository;

import com.electronics.store.electronocs_Store.entity.Category;
import com.electronics.store.electronocs_Store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends JpaRepository<Category,String> {
}
