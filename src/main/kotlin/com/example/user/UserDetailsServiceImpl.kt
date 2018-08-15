package com.example.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsServiceImpl: UserDetailsService {
    @Autowired
    lateinit var userService: UserService

    override fun loadUserByUsername(email: String): UserDetails {
        // 認証を行うユーザ情報を格納する
        var user: User? = null

        try {
            user  = userService.findByEmail(email)
        } catch (e: Exception) {
            throw UsernameNotFoundException("can't get User")
        }

        if(user == null) {
            throw UsernameNotFoundException("User does not exists")
        }

        return LoginUser(user)
    }
}