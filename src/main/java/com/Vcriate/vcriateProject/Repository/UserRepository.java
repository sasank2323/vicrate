package com.Vcriate.vcriateProject.Repository;

import com.Vcriate.vcriateProject.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}