package com.example.user

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class LoginUser(user: User) :
        org.springframework.security.core.userdetails.User(
                user.email,
                user.password, // encrypted?
                AuthorityUtils.createAuthorityList("ROLE_USER")
        ) {
    var loginUser: User? = null

    init {
        this.loginUser = user
    }
}
