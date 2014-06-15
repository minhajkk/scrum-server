package com.scrum

import com.scrum.auth.User

class MyRegisterController {

    def index() {
        render view: '/auth/register'
    }

    def save(){
        User userInstance = new User(params);
        userInstance.validate()
        if (userInstance.hasErrors()) {
            render view:'/auth/register', model: [userInstance: userInstance]
            return
        }

        userInstance.save flush:true

        flash.message = "Well done! You are successfully registered!!"

        redirect(controller: "auth", action: "login")
    }
}
