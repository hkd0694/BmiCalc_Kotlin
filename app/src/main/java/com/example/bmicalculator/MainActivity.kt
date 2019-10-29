package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //코틀린으로 만든 프로젝트에는 기본적으로 'kotlin-android-extensions' 플러그인이 설정이 되어 있기 때문에
        //레이아웃에 배치한 뷰 ID가 자동으로 임포트되어 코틀린 코드에서 바로 사용할 수 있다.
        //java 에선 findViewId를 해줘야 사용 가능!!
        resultButton.setOnClickListener {
            //Java에선 new 연산자를 통해 Intnet를 만들어주지만     (ResultActivity.class)
            //Kotlin에서는 new 연산자를 사용 안해도 된다.          (ResultActivity::class.java)
            /*val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight",weightEditText.text.toStrong())
            intent.putExtra("height",heightEditText.text.toStrong())
            startActivity(intent)
            */
            //하지만 우리가 더 간편하게 Anko Library를 사용하면 코드가 더 짧게 표현할 수 있다.
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }

    }
}
