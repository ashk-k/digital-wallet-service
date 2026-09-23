package com.ashkaan.digital_wallet_service.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ashkaan.digital_wallet_service.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //Optional user ka mtlb h user mil bhi skta h, aur nahi bhi...
}