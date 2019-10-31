# Kotlin Study (1/9) - 2019/10/29

Kotlin을 공부하기 위해 간단한 앱부터 복잡한 앱까지 만들어 봄으로써 Kotlin에 대해 하기!

총 9개의 앱 중 첫 번째 앱

프로젝트명 : BmiCalculator

기능

* 키와 몸무게를 입력하고 결과 버튼을 누르면 다른 화면에서 비만도 결과를 문자와 그림으로 보여준다.
  
* 마지막에 입력했던 키와 몸무게는 자동으로 입력된다.

핵심 구성요소

* ConstraintLayout : 제약에 따라 뷰를 배치하는 레이아웃이다.
  
* Intent : 화면을 전환에 사용하고, 간단한 데이터를 전달할 수 있다.
  
* SharedPreferences : 간단한 데이터를 저장할 수 있다.

라이브러리 설명

* Anko : 인텐트, 다이얼로그, 로그 등을 구현하는 데 도움이 되는 라이브러리


# Anko

Anko 라이브러리는 코틀린을 개발한 잿브레인에서 개발한 라이브러리로 코드 작성을 좀더 편리하게 할 수있도록 도와준다.

Anko 라이브러리는 총 4가지의 라이브러리로 구성된다.

* AnKo Commons
  
* AnKo Layouts
  
* AnKo SQLite
  
* AnKo Coroutines


AnKo 라이브러리의 깃허브 ( https://github.com/Kotlin/anko )


이 4가지의 라이브러리 중 인텐트, 다이얼로그, 로그 등을 구현하는데 사용하는 AnKo Commons를 사용할 예정이다.

이 라이브러리를 사용하려면 build.gradle (Module) 에 추가해준다

```java
implementation "org.jetbrains.anko:anko:$anko_version"
```

또한, build.gradle (Project) 에도 이와 같이 추가한다.

```java

buildscript {
    ext.anko_version='0.10.5'
}

```

## 사용 예제

```kotlin
package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*;
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultButton.setOnClickListener {
            //Java에선 new 연산자를 통해 Intnet를 만들어주지만     (ResultActivity.class)
            //Kotlin에서는 new 연산자를 사용 안해도 된다.          (ResultActivity::class.java)
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("weight",weightEditText.text.toStrong())
            intent.putExtra("height",heightEditText.text.toStrong())
            startActivity(intent)
            
            //하지만 우리가 더 간편하게 Anko Library를 사용하면 코드가 더 짧게 표현할 수 있다.
            startActivity<ResultActivity>(
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }

    }

}

```

버튼을 누르는 이벤트 안에 있는 두 가지의 인텐트 코드는 둘다 동일한 코드로 아래에 Anko Library를 사용하여 intent를 사용한 경우이다.

또한, Toast 메시지를 구현할 경우에도 Anko Library를 사용하면 훨씬 간단하게 구현 할 수 있다.

```kotlin

//Toast 메시지를 사용한 것
//원래 표시할때는 아래의 코드와 같지만
Toast.makeText(this,"$bmi",Toast.Length_SHORT).show()
//Anko 라이브러리를 사용하면 아래 코드처럼 정말 간단하게 Toast 메시지를 추가할 수 있다.
toast("$bmi")

```

## BmiCalc_Kotlin을 통해 배운 것들

### When

when문은 Java의 switch문에 대응하는 것으로 사용방법이 다양하다. 값이 하나인 경우는 물론 콤마(,)나 in 연산자로 값의 범위를 자유롭게 지정하는 것이 특징이다.

```kotlin

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

```

### 'kotlin-android-extensions'

Android Java와는 다르게 Android Kotlin에서는 레이아웃에 배치한 뷰 ID가 자동으로 임포트되어 코틀린 코드에서 바로 사용할 수가 있다. 이러한 이유는 코틀린 프로젝트에는 기본적으로 'kotlin-android-extensions' 플러그인이 설정되어 있기 때문이다.

Java인 경우
```java

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        
    }


}



```

Kotlin인 경우

```Kotlin

import kotlinx.android.synthetic.main.activity_main.*;

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //resultButton에 대해 java처럼 findViewId를 안해줘도 된다!
        resultButton.setOnClickListener {

        }

    }
```