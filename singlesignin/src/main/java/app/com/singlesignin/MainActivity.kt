package app.com.singlesignin

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val uri = "smsto:"+"Hello"
            var intent = Intent("SEND_SMS_ACTION", Uri.parse(uri))
            startActivity(intent)
        }
    }
}
