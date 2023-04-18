package com.example.implementations

import com.example.repository.DogArticlesRepository
import com.example.data.remote.DogApiFactsService
import com.example.data.remote.DogApiImgService
import javax.inject.Inject

class DogArticlesRepositoryImpl @Inject constructor(
    private val dogApiFactsService: DogApiFactsService,
    private val dogApiImgService : DogApiImgService
) : DogArticlesRepository {

    override suspend fun getDogFact() = dogApiFactsService.getDogFact()
    override suspend fun getDogImg() = dogApiImgService.getDogImg()

}