//package com.example.springsecurityapplication.security;
//
//import com.example.springsecurityapplication.services.PersonDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {
//
//    private final PersonDetailsService personDetailsService;
//
//    @Autowired
//    public AuthenticationProvider(PersonDetailsService personDetailsService) {
//        this.personDetailsService = personDetailsService;
//    }
//
//    // Логика по аутентификации в приложении
//    // За нас спринг секьюрити сам возьмет объект из формы и передаст его сюда
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        // Получаем логин с формы аутентификации
//        String login = authentication.getName();
//
//        // Получаем пароль с формы аутентификации
//        String password = authentication.getCredentials().toString();
//
//        // Т.к. данный метод возвращем объект интерфейса UserDetail то и объект мы создаем через интерфейс
//        UserDetails person = personDetailsService.loadUserByUsername(login);
//
//        // Если пароль с формы аутентификации не равен паролю пользователя из бд, который найден по логину
//        if(!password.equals(person.getPassword())){
//            throw new BadCredentialsException("Не корректный пароль");
//        }
//
//        // Возвращаем объект аутентификации. В данном объекте будет лежать пользователь, который аутентифицировалься, его пароль, его права доступа(список ролей )
//        return new UsernamePasswordAuthenticationToken(person, password, Collections.emptyList());
//
//
//
//
//
//
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
