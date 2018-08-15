package com.example.user

import javax.persistence.*

@Entity
@Table(name="users")
class User (
    @Id @GeneratedValue var id: Int? = 0,
    @Column(nullable = false) var email: String = "",
    @Column(nullable = false) var password: String = ""
)