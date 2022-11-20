package com.example.moview.data.remote.firebase

import com.example.moview.data.remote.api.ImageApi
import com.example.moview.data.remote.dto.ImageDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirebaseImageApiImpl(
    private val db : FirebaseFirestore
) :ImageApi {
    override suspend fun getById(id: String): ImageDto {

        val document = db.collection("imagenes").document(id).get().await()
        return document.toObject<ImageDto>()!!

    }
}