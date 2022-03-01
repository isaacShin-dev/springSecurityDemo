package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties.Build;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService{
    //UserDetailsService 의 역할 -> 유저네임을 받아와서 UserDetails 에 담아주는 역할 
    @Autowired AccountRepository accountRepository;

    //{noop}123 -> noop 에는 암호화 알고리즘 이름이 들어감 -> 스프링 세큐리티는 이러한 형식의 비밀번호를 요구함. 

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);

        if(account == null){
            throw new UsernameNotFoundException(username);
        }
            // User는 Spring security에서 제공하는 객체이다. 
            //builder 를 통해 UserDetails 객체를 만들 수 있다. 
        return User.builder()
                        .username(account.getUsername())
                        .password(account.getPassword())
                        .roles(account.getRole())
                        .build();  // return UserDetails 
    }

    public Account createAccount(Account account) {
        account.encodePassword();
        return accountRepository.save(account);
    }
    
    
}
