package com.example.moview.viewModels

import androidx.lifecycle.ViewModel


class UsersViewModel : ViewModel(){

    private var _user = ""
    private var _email = ""
    private var _pasword =""
    private var _critico = true
    private var _perfil = ""

    val user: String get() = _user
    val email: String get() = _email
    val pasword: String get() = _pasword
    val critico: Boolean get() = _critico
    val perfil: String get() = _perfil

    fun asignarValores(user:String,email: String, pasword: String, critico:Boolean, perfil:String ){
        _user = user
        _email = email
        _pasword = pasword
        _critico = critico
        _perfil = perfil
    }

}