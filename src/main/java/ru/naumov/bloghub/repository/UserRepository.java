package ru.naumov.bloghub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.naumov.bloghub.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
