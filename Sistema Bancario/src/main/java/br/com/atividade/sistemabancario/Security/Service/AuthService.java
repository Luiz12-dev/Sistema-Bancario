package br.com.atividade.sistemabancario.Security.Service;

import br.com.atividade.sistemabancario.DTOs.AuthResponseDto;
import br.com.atividade.sistemabancario.DTOs.LoginDto;
import br.com.atividade.sistemabancario.DTOs.RegisterDto;
import br.com.atividade.sistemabancario.Enum.Role;
import br.com.atividade.sistemabancario.Security.jwt.JwtUtils;

import br.com.atividade.sistemabancario.models.Account;
import br.com.atividade.sistemabancario.models.User;
import br.com.atividade.sistemabancario.repositorys.AccountRepository;
import br.com.atividade.sistemabancario.repositorys.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;


    @Autowired
    public AuthService(UserRepository userRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public void register(RegisterDto registerDto){
        if(userRepository.findByUsername(registerDto.getUsername()).isPresent()){
            throw new RuntimeException("The username is already in use");
        }

        if(userRepository.findByEmail(registerDto.getEmail()).isPresent()){
            throw new RuntimeException("The email is already in use");
        }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        user.setRole(Role.USER);

        Account account = new Account();
        account.setBalance(new BigDecimal("0.00"));
        account.setUserOwner(user);

        userRepository.save(user);

    }


    public AuthResponseDto login (LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token= jwtUtils.generateJwtToken(authentication);

        return new AuthResponseDto(token);

    }
}
