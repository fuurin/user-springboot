package com.example.user

import org.springframework.beans.factory.annotation.Autowired

open class UserService
    @Autowired constructor(private val userRepository: UserRepository) {

    /**
     * emailでUserテーブルを検索
     * @return User
     */

    fun findByEmail(email: String): User = userRepository.findByEmail(email)
}