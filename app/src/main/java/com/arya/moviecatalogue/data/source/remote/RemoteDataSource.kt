package com.arya.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.arya.moviecatalogue.data.source.remote.response.*
import com.arya.moviecatalogue.network.NetworkClient
import com.arya.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    fun getMovie(): LiveData<ApiResponse<List<MovieResult>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<MovieResult>>>()
        NetworkClient.getApiService().getMovie().enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                val result = response.body()?.results
                if (result != null) {
                    resultMovie.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovie onFailure: ${t.message}")
                resultMovie.postValue(ApiResponse.error(t.message.toString(), mutableListOf()))
                EspressoIdlingResource.decrement()
            }

        })
        return resultMovie
    }

    fun getTv(): LiveData<ApiResponse<List<TvResult>>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<List<TvResult>>>()
        NetworkClient.getApiService().getTv().enqueue(object : Callback<TvResponse> {
            override fun onResponse(
                call: Call<TvResponse>,
                response: Response<TvResponse>
            ) {
                val result = response.body()?.results
                if (result != null) {
                    resultTv.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTv onFailure: ${t.message}")
                resultTv.postValue(ApiResponse.error(t.message.toString(), mutableListOf()))
                EspressoIdlingResource.decrement()
            }

        })
        return resultTv
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        NetworkClient.getApiService().getDetailMovie(movieId)
            .enqueue(object : Callback<MovieDetailResponse> {
            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
            ) {
                val result = response.body()
                if (result != null) {
                    resultMovie.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getMovieDetail onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultMovie
    }

    fun getTvDetail(tvId: Int): LiveData<ApiResponse<TvDetailResponse>> {
        EspressoIdlingResource.increment()
        val resultTv = MutableLiveData<ApiResponse<TvDetailResponse>>()
        NetworkClient.getApiService().getDetailTv(tvId)
            .enqueue(object : Callback<TvDetailResponse> {
            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                val result = response.body()
                if (result != null) {
                    resultTv.postValue(ApiResponse.success(result))
                }
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                Log.e("RemoteDataSource", "getTvDetail onFailure: ${t.message}")
                EspressoIdlingResource.decrement()
            }

        })
        return resultTv
    }

}