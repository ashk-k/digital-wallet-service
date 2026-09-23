package com.ashkaan.digital_wallet_service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashkaan.digital_wallet_service.repositories.UserRepository;
import com.ashkaan.digital_wallet_service.repositories.WalletRepository;

import org.springframework.transaction.annotation.Transactional;

@Service 
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;

    public AuthService(UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        WalletRepository walletRepository) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
            this.walletRepository = walletRepository;
        }
@Transactional 
public User register(RegisterRequest request) {
if (userRepository.findByEmail(request.getEmail()).isPresent()) {
    throw new RuntimeException("The Email is Already Registered");
    }

            User user = new User();

            user.setName(request.getName());
            user.setEmail(request.getEmail());

            String hashedPassword = passwordEncoder.encode(request.getPassword()); //most important

            user.setPassword(hashedPassword);

            User savedUser = userRepository.save(user);

            Wallet wallet = new Wallet();
            wallet.setUser(savedUser);

            walletRepository.save(wallet);

            return savedUser;
        }

public User login(LoginRequest request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("PASSWORD OR EMAIL IS INVALID"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new RuntimeException("EMAIL OR PASSWORD IS INVALID");
    }

    return user;
}

}
