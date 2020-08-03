package com.example.hiltpractice.repository

import com.example.hiltpractice.local.BlogDao
import com.example.hiltpractice.local.CacheMapper
import com.example.hiltpractice.model.Blog
import com.example.hiltpractice.remote.BlogRetrofit
import com.example.hiltpractice.remote.NetworkMapper
import com.example.hiltpractice.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper
) {
    suspend fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        try {
            val networkBlog = blogRetrofit.getAll()
            val blogs = networkMapper.mapFromEntityList(networkBlog)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}