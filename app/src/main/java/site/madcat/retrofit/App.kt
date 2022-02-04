package site.madcat.retrofit

import android.app.Application
import android.content.Context
import site.madcat.retrofit.data.RetrofitRepoUseIml
import site.madcat.retrofit.domain.RetroRepoUseCase

class App: Application() {
    val githubRepoUsecase: RetroRepoUseCase by lazy {  RetrofitRepoUseIml() }

}


val Context.app
    get() = applicationContext as App