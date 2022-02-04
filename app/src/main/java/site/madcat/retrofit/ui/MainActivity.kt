package site.madcat.retrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import site.madcat.retrofit.R
import site.madcat.retrofit.app
import site.madcat.retrofit.databinding.ActivityMainBinding
import site.madcat.retrofit.domain.RetroRepoUseCase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val githubRepoEntity: RetroRepoUseCase by lazy { app.githubRepoUsecase }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadButton.setOnClickListener {
            showProgress(true)

            val userName = binding.nameEditText.text.toString()


            githubRepoEntity.getReposForUserAsync(
                userName,
                onSuccess = {
                    val sb = StringBuilder()
                    it.forEach {
                        sb.appendLine(it.toString())
                    }
                    runOnUiThread {
                        binding.resTextView.text = sb.toString()
                        showProgress(false)
                    }
                },
                onError = {
                    runOnUiThread {
                        Toast.makeText(this, "Ошибка ${it.message}", Toast.LENGTH_SHORT).show()
                        showProgress(false)
                    }
                }
            )


        }





    }










        private fun showProgress(show: Boolean) {
            binding.progressBar.isVisible = show
            binding.resTextView.isVisible = !show
            binding.loadButton.isEnabled = !show
            binding.nameEditText.isEnabled = !show
        }
}