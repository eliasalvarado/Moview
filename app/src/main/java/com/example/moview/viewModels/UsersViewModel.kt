package com.example.moview.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class UsersViewModel : ViewModel() {

    private val _user = MutableLiveData<String>("")
    val user : LiveData<String> = _user
    private val _email = MutableLiveData<String>("")
    val email : LiveData<String> = _email
    private val _pasword = MutableLiveData<String>("")
    val pasword : LiveData<String> = _pasword
    private val _critico = MutableLiveData<Boolean>(true)
    val critico : LiveData<Boolean> = _critico
    private val _perfil = MutableLiveData<String>("")
    val perfil : LiveData<String> = _perfil

    fun setUser(user: String, email: String, pasword: String, critico: Boolean, perfil:String){
        _user.value = user
        _email.value = email
        _pasword.value = pasword
        _critico.value = critico
        _perfil.value = perfil
    }
    fun setName(user:String){
        _user.value = user
    }
    fun getUser():String{
        return _user.value.toString()
    }
    fun getProfile():String{
        return  _perfil.value.toString()
    }
    fun getCritico():Boolean{
        return _user.value.isNullOrBlank()
    }
}