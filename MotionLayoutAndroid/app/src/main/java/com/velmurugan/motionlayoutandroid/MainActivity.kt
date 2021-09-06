package com.velmurugan.motionlayoutandroid

import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.motion.widget.MotionLayout

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<AppCompatButton>(R.id.button1).show()

        Handler().postDelayed(
            Runnable {
                findViewById<MotionLayout>(R.id.motionLayout).transitionToEnd()
            }
        , 3000)

        val motionLayout = findViewById<MotionLayout>(R.id.motionLayout)
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener{
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {

            }

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {

            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {

                when(p1) {

                    R.id.end -> {
                        motionLayout.setTransition(R.id.button_trans)
                        motionLayout.transitionToEnd()
                    }

                    R.id.button_end -> {
                        motionLayout.setTransition(R.id.trsn3)
                        motionLayout.postDelayed( Runnable {
                            motionLayout.transitionToEnd()
                        }
                            , 2000
                        )
                    }
                }

            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {

            }
        })


    }



    fun AppCompatButton.show() {
        Toast.makeText(this@MainActivity, "Message", Toast.LENGTH_SHORT).show()
    }

}