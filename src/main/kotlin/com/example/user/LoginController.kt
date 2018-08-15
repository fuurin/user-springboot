package com.example.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

class LoginController
    @Autowired
    constructor(private val userService: UserService) {

    @RequestMapping("/")
    fun root(): ModelAndView {
        return ModelAndView("/index")
    }

    @RequestMapping("/login/success")
    fun success(): ModelAndView = ModelAndView("/login/success")
}