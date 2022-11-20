package com.example.moview.data.remote.firebase

import com.example.moview.data.remote.api.UserApi
import com.example.moview.data.remote.dto.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class FirebaseUserApiImpl (
    private val db : FirebaseFirestore
        ): UserApi {
    override suspend fun insert(userDto: UserDto) {
            db.collection("users")
            .add(userDto)
            .await()
    }

    override suspend fun getByUser(user: String): List<UserDto>? {
        try {
           val document = db.collection("users")
                .whereEqualTo("user",user).get().await()

            return document.documents.map { documentSnapshot ->
                documentSnapshot.toObject<UserDto>()!!
            }
        }catch (e: Exception){
            return null
        }
    }

    override suspend fun getByEmail(email: String): List<UserDto>? {
        try {
            val document = db.collection("users")
                .whereEqualTo("email",email).get().await()

            return document.documents.map { documentSnapshot ->
                documentSnapshot.toObject<UserDto>()!!
            }
        }catch (e: Exception){
            return null
        }
    }

}