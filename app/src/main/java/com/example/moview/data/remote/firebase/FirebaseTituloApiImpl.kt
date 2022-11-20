package com.example.moview.data.remote.firebase

import com.example.moview.data.remote.api.TituloApi
import com.example.moview.data.remote.dto.TituloDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

class FirebaseTituloApiImpl(
    private val db : FirebaseFirestore
): TituloApi {
    override suspend fun getById(id: String): TituloDto? {
        return try {
            val document = db.collection("peliculas").document(id).get().await()
            document?.toObject<TituloDto>()
        } catch (e: Exception) {
            return try {
                val document = db.collection("series").document(id).get().await()
                document?.toObject<TituloDto>()
            } catch (e: Exception) {
                return null
            }
        }
    }

    override suspend fun getByType(type: String): List<TituloDto>? {
        return try {
            val res = db.collection(type).orderBy("title").get().await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<TituloDto>()!!
            }

        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getPeliculasByGender(genero: String): List<TituloDto>? {
        return try {
            val res = db.collection("peliculas").whereArrayContains("generos", genero).get().await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<TituloDto>()!!
            }
        } catch (e: Exception) {
            return null
        }
    }

    override suspend fun getSeriesByGender(genero: String): List<TituloDto>? {
        return try {
            val res = db.collection("series").whereArrayContains("generos", genero).get().await()

            res?.documents?.map { documentSnapshot ->
                documentSnapshot.toObject<TituloDto>()!!
            }
        } catch (e: Exception) {
            return null
        }
    }
}