package edu.temple.coroutineconversion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    //(Refactor to replace Thread code with coroutines)

    lateinit var cakeImageView: ImageView

    private val scope = CoroutineScope(Job() + Dispatchers.Default)

    /*val handler = Handler(Looper.getMainLooper(), Handler.Callback {
        cakeImageView.alpha = it.what / 100f
        true
    })*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cakeImageView = findViewById(R.id.imageView)

        findViewById<Button>(R.id.revealButton).setOnClickListener{
            scope.launch{reveal()}
        }
    }
    private suspend fun reveal(){
        repeat(100) {
            //handler.sendEmptyMessage(it) //replace handler with withContext
            withContext(Dispatchers.Main){
                cakeImageView.alpha = it / 100f
            }
            delay(40)
            //Thread.sleep(40)
        }
    }
}