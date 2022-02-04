package site.madcat.retrofit.domain

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import site.madcat.retrofit.domain.RetroFitGithubEntity

interface RetroRepoUseCase {

    @WorkerThread
    @Throws(Throwable::class)
    fun getReposForUserSync(userName: String): List<RetroFitGithubEntity>

    fun getReposForUser(userName: String): LiveData<List<RetroFitGithubEntity>>

    fun getReposForUserAsync(
        userName: String,
        onSuccess: (List<RetroFitGithubEntity>) -> Unit,
        onError: (Throwable) -> Unit
    )

}