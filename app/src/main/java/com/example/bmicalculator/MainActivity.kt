package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //이전에 입력한 값을 읽어오기
        loadData()

        //코틀린으로 만든 프로젝트에는 기본적으로 'kotlin-android-extensions' 플러그인이 설정이 되어 있기 때문에
        //레이아웃에 배치한 뷰 ID가 자동으로 임포트되어 코틀린 코드에서 바로 사용할 수 있다.
        //java 에선 findViewId를 해줘야 사용 가능!!
        resultButton.setOnClickListener {

            //버튼을 눌렀을 경우 그 값을 마지막에 저장 시켜 놓는다.
            saveData(weightEditText.text.toString().toInt()
            , heightEditText.text.toString().toInt())

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

    //SharedPreference이란 앱자체에서 간단하게 데이터를 저장시켜서 이용하는 것이다.
    private fun saveData(weight:Int, height:Int){
        val pref = this.getSharedPreferences("prefs",0)
        val edit = pref.edit()
        edit.putInt("KEY_WEIGHT",weight).putInt("KEY_HEIGHT",height).apply()
    }

    //SharePreference에 저장한 데이터를 불러오는 함수
    private fun loadData(){
        val pref = this.getSharedPreferences("prefs",0)
        val weight = pref.getInt("KEY_WEIGHT",0)
        val height = pref.getInt("KEY_HEIGHT",0)
        if(weight != 0 && height != 0){
            weightEditText.setText(weight.toString())
            heightEditText.setText(height.toString())
        }
    }
}
