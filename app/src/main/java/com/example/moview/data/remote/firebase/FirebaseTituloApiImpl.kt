package com.example.moview.data.remote.firebase

import com.example.moview.data.remote.api.TituloApi
import com.example.moview.data.remote.dto.ComentarioDto
import com.example.moview.data.remote.dto.RepartoDto
import com.example.moview.data.remote.dto.TituloDto
import com.example.moview.data.remote.dto.UserDto
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await
import java.util.*

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

    override suspend fun actualizarPuntajeTitulo(
        id: String,
        nuevosDatos: Map<String, MutableList<Boolean>>
    ): Boolean {
        return try {
            val document = db.collection("peliculas").document(id)
            document.update(nuevosDatos)
            true
        } catch (e: Exception) {
            return try {
                val document = db.collection("series").document(id)
                document.update(nuevosDatos)
                true
            } catch (e: Exception) {
                return false
            }
        }
    }

    override suspend fun actualizarComentariosTitulo(
        id: String,
        nuevoComentario: ComentarioDto
    ): Boolean {
        return try {
            val document = db.collection("peliculas").document(id).collection("comentarios")
            document.add(nuevoComentario).await()
            true
        } catch (e: Exception) {
            return try {
                val document = db.collection("series").document(id).collection("comentarios")
                document.add(nuevoComentario).await()
                true
            } catch (e: Exception) {
                return false
            }
        }
    }

    override suspend fun getReparto(id: String): List<RepartoDto>? {
        return try {
            val document = db.collection("peliculas").
                    document(id).collection("comentarios").orderBy("nombre").get().await()
            return document.documents.map { documentSnapshot ->
                documentSnapshot.toObject<RepartoDto>()!!
            }
        }catch (e : Exception){
            return try {
                val document = db.collection("series").
                document(id).collection("comentarios").orderBy("nombre").get().await()
                return document.documents.map { documentSnapshot ->
                    documentSnapshot.toObject<RepartoDto>()!!
                }
            }catch (e : Exception){
                null
            }
            null
        }
    }
}