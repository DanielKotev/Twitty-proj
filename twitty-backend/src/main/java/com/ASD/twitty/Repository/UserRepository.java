package com.ASD.twitty.Repository;

import com.ASD.twitty.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
