package ru.dsoccer1980.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.dsoccer1980.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
