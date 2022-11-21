package com.example.moview.data.Repository.Estreno

import com.example.moview.data.local.dao.TituloDao
import com.example.moview.data.local.entity.TituloEstreno
import com.example.moview.data.remote.WatchModeApi
import com.example.moview.data.remote.dto.TitleDto
import com.example.moview.data.remote.dto.mapToEntity

class EstrenoRepositoryImpl(
    private val estrenoDao: TituloDao,
    private val api: WatchModeApi
): EstrenoRepository {
    override suspend fun getEstrenos(): Resource<List<TituloEstreno>> {
        val localEstrenos = estrenoDao.getTitulos()
        return try {
            if(localEstrenos.isEmpty()) {
                val remoteEstrenos = api.getEstrenos().titleDtos
                val mappedEstrenos =remoteEstrenos.map { TitleDto -> TitleDto.mapToEntity() }
                estrenoDao.insertAll(mappedEstrenos)
                Resource.Success(mappedEstrenos)
            } else {
                Resource.Success(localEstrenos)
            }
        } catch (ex: Exception) {
            Resource.Error(ex.message ?: "")
        }
    }
}