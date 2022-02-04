package site.madcat.retrofit.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import site.madcat.retrofit.domain.RetroFitGithubEntity

interface RetrofitRepoApi {
    @GET("users/{user}/repos")
    fun loadReposByUser(@Path("user") userName: String): Call<List<RetroFitGithubEntity>>
}