package br.com.atividade.sistemabancario.Security.Service;

import br.com.atividade.sistemabancario.repositorys.AccountRepository;
import br.com.atividade.sistemabancario.repositorys.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;


}
