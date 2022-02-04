package site.madcat.retrofit.data

import androidx.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import site.madcat.retrofit.data.retrofit.RetrofitRepoApi
import site.madcat.retrofit.domain.RetroFitGithubEntity
import site.madcat.retrofit.domain.RetroRepoUseCase


private const val BASE_URL = "https://api.github.com/"


class RetrofitRepoUseIml :RetroRepoUseCase{

    private val retrofit = Retrofit.Builder()                                          //создание retrofit"а
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var api: RetrofitRepoApi = retrofit.create(RetrofitRepoApi::class.java)




    override fun getReposForUserSync(userName: String): List<RetroFitGithubEntity> {
        TODO("Not yet implemented")
    }

    override fun getReposForUser(userName: String): LiveData<List<RetroFitGithubEntity>> {
        TODO("Not yet implemented")
    }

    override fun getReposForUserAsync(
        userName: String,
        onSuccess: (List<RetroFitGithubEntity>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        api.loadReposByUser(userName).enqueue(object : Callback<List<RetroFitGithubEntity>> {
            override fun onResponse(
                call: Call<List<RetroFitGithubEntity>>,
                response: Response<List<RetroFitGithubEntity>>
            ) {
                if (response.isSuccessful) {
                    onSuccess(response.body() ?: throw IllegalStateException("null result"))
                } else {
                    onError(Throwable("unknown error"))
                }
            }

            override fun onFailure(call: Call<List<RetroFitGithubEntity>>, t: Throwable) {
                onError(t)
            }
        })
    }

}