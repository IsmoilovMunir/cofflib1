package org.cofflib.repositories;

import org.cofflib.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}
