package com.example.orderfood.config;

import com.example.orderfood.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MySercurityConfig extends WebSecurityConfigurerAdapter {
    final AccountService accountService;
    final PasswordEncoder passwordEncoder;

    @Bean

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        MyAuthenticationFilter authenticationFilter = new MyAuthenticationFilter(authenticationManagerBean());
        authenticationFilter.setFilterProcessesUrl("/api/v1/accounts/login");
        http.cors().and().csrf().disable();
        http.authorizeRequests().antMatchers("/api/v1/accounts/*").permitAll();
        http.authorizeRequests().antMatchers("api/v1/user/*").hasAnyAuthority("USER","ADMIN");
        http.authorizeRequests().antMatchers("api/v1/foods/*").hasAnyAuthority("ADMIN","USER");
        http.authorizeRequests().antMatchers("api/v1/categories/*").hasAnyAuthority("ADMIN","USER");
        http.authorizeRequests().antMatchers("api/v1/admin/*").hasAnyAuthority("ADMIN");
        http.addFilterBefore(
                new MyAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
