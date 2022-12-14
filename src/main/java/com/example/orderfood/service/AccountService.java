package com.example.orderfood.service;

import com.example.orderfood.entity.Account;
import com.example.orderfood.entity.Credential;
import com.example.orderfood.entity.Food;
import com.example.orderfood.entity.dto.AccountLoginDto;
import com.example.orderfood.entity.dto.AccountRegisterDto;
import com.example.orderfood.repository.AccountRepository;
import com.example.orderfood.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    final AccountRepository accountRepository;
    final PasswordEncoder passwordEncoder;

    public Account register(AccountRegisterDto accountRegisterDto) throws Exception {
        List<Account> optionalAccount =
                accountRepository.findAccountsByPhoneOrEmailOrUsername(accountRegisterDto.getPhone(),accountRegisterDto.getEmail(), accountRegisterDto.getUsername());
        if (optionalAccount.size() > 0) {
            throw new Exception("Account exist");
        }


        Account account = Account.builder()
                .username((accountRegisterDto.getUsername()))
                .passwordHash(passwordEncoder.encode(accountRegisterDto.getPassword()))
                .email(accountRegisterDto.getEmail())
                .phone(accountRegisterDto.getPhone())
//                .role(accoutRegisterDto.getRole())
                .role(2)
                .build();
        return accountRepository.save(account);

    }

    public Credential login(AccountLoginDto accountLoginDto) {
        Optional<Account> optionalAccount
                = accountRepository.findAccountByUsername(accountLoginDto.getUsername());
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("User is not found");
        }
        Account account = optionalAccount.get();
        boolean isMatch = passwordEncoder.matches(accountLoginDto.getPassword(), account.getPasswordHash());
        if (isMatch) {
            int expiredAfterDay = 7;
            String accessToken =
                    JwtUtil.generateTokenByAccount(account, expiredAfterDay = 24 * 60 * 60 * 1000);
            String refreshToken =
                    JwtUtil.generateTokenByAccount(account, 14 * 24 * 60 * 60 * 1000);
            Credential credential = new Credential();
            credential.setAccessToken(accessToken);
            credential.setRefreshToken(refreshToken);
            credential.setExpiredAt(expiredAfterDay);
            credential.setScope("basic_information");
            return credential;
        } else {
            throw new UsernameNotFoundException("Password is not match");

        }

    }

    public void getInformation() {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> optionalAccount = accountRepository.findAccountByUsername(username);
        if (!optionalAccount.isPresent()) {
            throw new UsernameNotFoundException("Username is not found");
        }
        Account account = optionalAccount.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority =
                new SimpleGrantedAuthority(account.getRole() == 1 ? "ADMIN" : "USER");
        authorities.add(simpleGrantedAuthority);
        return new User(account.getUsername(), account.getPasswordHash(), authorities);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    public Account save(Account account) {
        return accountRepository.save(account);
    }

}
