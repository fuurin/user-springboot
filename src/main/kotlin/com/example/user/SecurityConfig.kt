package com.example.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.AuthenticationFailureHandler

// 詳しくは
// https://qiita.com/aikumi/items/256b7892effd5c92a39f
open class SecurityConfig: WebSecurityConfigurerAdapter() {
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers(
                "/**/favicon.ico",
                "/images/**",
                "/css/**",
                "/javascript/**",
                "/webjars/**"
        )
    }

    override fun configure(http: HttpSecurity) {
        // 許可の設定
        http.authorizeRequests()
                .antMatchers("/", "index")
                .permitAll()
                .anyRequest()
                .authenticated()

        // ログイン設定
        http.formLogin()
                .loginProcessingUrl("/users/login")
                .loginPage("/index")
                .failureHandler(AuthenticationFailureHandler())
                .defaultSuccessUrl("/login/success")
                .usernameParameter("email")
                .passwordParameter("password") // encrypted?
                .and()

        @Configuration
        open class AuthenticationConfiguration: GlobalAuthenticationConfigurerAdapter() {
            @Autowired
            var userDetailsService: UserDetailsServiceImpl = UserDetailsServiceImpl()

            @Bean
            fun passwordEncoder(): PasswordEncoder {
                return NoOpPasswordEncoder.getInstance()
            }

            override fun init(auth: AuthenticationManagerBuilder) {
                auth.userDetailsService(userDetailsService)
            }
        }
    }
}