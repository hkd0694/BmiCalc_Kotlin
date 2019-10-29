package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import org.jetbrains.anko.toast

class ResultActivity : AppCompatActivity() {

    /*
    * Up Navigation
    * 화면 상단 왼쪽에 뒤로가기 버튼을 만들기 위해 AndroidManifest.xml 파일에
    * android:name=".ResultActivity" 인 <activity>로 가 <android:parentActivityName=".MainActivity"/> 를 추가
    * 추가해주면 ResultActivity 화면 상단에 뒤로가기 버튼이 추가되어있다. (초간단)...
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        //Java에서는 getIntent()를 통해 intent 정보를 받아와 getStringExtra() 함수를 부르지만
        //Kotlin에서는 getter() setter()가 자동으로 호출되기 때문에 intent.getStringExtra() 함수를 바로 호출할 수 있다.
        val weight = intent.getStringExtra("weight").toInt()
        val height = intent.getStringExtra("height").toInt()

        //Bmi 계산하는 방법
        val bmi = weight / Math.pow(height / 100.0,2.0)

        //when은 Java에서 사용하는 switch문을 코틀린에서는 when으로 사용 한다.
        //switch문에서는 나머지를 표현할 때 default를 사용하지만
        //when 문에서는 else 를 사용한다.
        when {
            bmi >= 35 -> resultTextView.text = "고도 비만"
            bmi >= 30 -> resultTextView.text = "2단계 비만"
            bmi >= 25 -> resultTextView.text = "1단계 비만"
            bmi >= 23 -> resultTextView.text = "과체중"
            bmi >= 18.5 -> resultTextView.text = "정상"
            else -> resultTextView.text = "저체중"
        }

        when {
            bmi >= 23 -> imageView.setImageResource(R.drawable.ic_sentiment_very_dissatisfied_black_24dp)
            bmi >=18.5 -> imageView.setImageResource(R.drawable.ic_sentiment_satisfied_black_24dp)
            else -> imageView.setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
        }

        //Toast 메시지를 사용한 것
        //원래 표시할때는 Toast.makeText(this,"$bmi",Toast.Length_SHORT).show()를 사용하지만
        //Anko 라이블리를 사용하면 아래 코드처럼 정말 간단하게 Toast 메시지를 추가할 수 있다.
        toast("$bmi")

    }
}
