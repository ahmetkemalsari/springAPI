package com.udemy.myProject.Repositories;

import com.udemy.myProject.Models.Hoax;
import com.udemy.myProject.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface HoaxRepository extends JpaRepository<Hoax, Long>, JpaSpecificationExecutor<Hoax> {
    Page<Hoax> findByUser(User user, Pageable page);
}
