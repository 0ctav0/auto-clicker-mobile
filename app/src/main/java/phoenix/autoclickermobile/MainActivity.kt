package phoenix.autoclickermobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.startBtn).setOnClickListener {
            val svc = Intent(this, Overlay::class.java)
            startService(svc)
            finish()

        }
    }

    fun onClickStartBtn() {
//        Log.i(MainActivity::class.simpleName, "Hey")
//        Toast.makeText(this, "Clicked on btn", Toast.LENGTH_LONG).show()
    }
}