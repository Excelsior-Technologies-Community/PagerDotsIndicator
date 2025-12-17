package com.ext.pagerdotsindicator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ext.pagerdots.PagerDotsIndicator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val dots = findViewById<PagerDotsIndicator>(R.id.dotsIndicator)
        val dots1 = findViewById<PagerDotsIndicator>(R.id.dotsIndicator1)
        val dots2 = findViewById<PagerDotsIndicator>(R.id.dotsIndicator2)
        val dots3 = findViewById<PagerDotsIndicator>(R.id.dotsIndicator3)
        val dots4 = findViewById<PagerDotsIndicator>(R.id.dotsIndicator4)
        val dots5 = findViewById<PagerDotsIndicator>(R.id.dotsIndicator5)


        dots.setDotCount(4)
        dots1.setDotCount(6)
        dots2.setDotCount(3)
        dots3.setDotCount(5)
        dots4.setDotCount(5)
        dots5.setDotCount(3)



        dots.postDelayed({ dots.selectDot(1) }, 1000)
        dots.postDelayed({ dots.selectDot(2) }, 2000)
        dots.postDelayed({ dots.selectDot(3) }, 3000)

        dots1.postDelayed({ dots1.selectDot(1) }, 1000)
        dots1.postDelayed({ dots1.selectDot(2) }, 2000)
        dots1.postDelayed({ dots1.selectDot(3) }, 3000)
        dots1.postDelayed({ dots1.selectDot(4) }, 4000)
        dots1.postDelayed({ dots1.selectDot(5) }, 5000)

        dots2.postDelayed({ dots2.selectDot(1) }, 1000)
        dots2.postDelayed({ dots2.selectDot(2) }, 2000)
        dots2.postDelayed({ dots2.selectDot(3) }, 3000)

        dots3.postDelayed({ dots3.selectDot(1) }, 1000)
        dots3.postDelayed({ dots3.selectDot(2) }, 2000)
        dots3.postDelayed({ dots3.selectDot(3) }, 3000)


        dots4.postDelayed({ dots4.selectDot(1) }, 1000)
        dots4.postDelayed({ dots4.selectDot(2) }, 2000)
        dots4.postDelayed({ dots4.selectDot(3) }, 3000)
        dots4.postDelayed({ dots4.selectDot(4) }, 4000)

        dots5.postDelayed({ dots5.selectDot(1) }, 1000)
        dots5.postDelayed({ dots5.selectDot(2) }, 2000)
        dots5.postDelayed({ dots5.selectDot(3) }, 3000)

    }
}