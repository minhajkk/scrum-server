package com.scrum

import com.scrum.auth.User

class MyRegisterController {

    def index() {
    }

    def creatingUser(){
        User user = new User();

        user.username = "minhaj"
        user.password = "minhaj123#"
        //user.email = "myemail@dontexist.com"
        user.accountExpired = false
        println("User: ${user}");
        user.save();

        println("saved...");
        println("this is printed");
        render (text: "test")
    }
}
