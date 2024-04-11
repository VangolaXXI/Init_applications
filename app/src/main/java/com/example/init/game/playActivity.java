package com.example.init.game;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.init.R;

public class playActivity extends AppCompatActivity {
    static String[] question_list = {
            "Какая компания владеет андроидом?",
            "Какой из них не является языком программирования?",
            "Где вы смотрите это видео?",
            "Какой компании принадлежит Apple?",
            "Какая из следующих языков программирования является компилируемым?",
            "Что из перечисленного относится к объектно-ориентированному программированию (ООП)?",
            "Какая структура данных используется для хранения элементов в порядке 'первый вошел, первый вышел'?",
            "Какой оператор используется в Python для выполнения условных операций?",
            "Какой тип данных используется для хранения дробных чисел в Python?",
            "Какая из следующих конструкций используется для создания цикла в Python?",
            "Какие методы HTTP запросов обычно используются для получения данных?",
            "Как называется процесс превращения исходного кода в исполняемый файл?",
            "Как называется язык разметки, используемый для структурирования веб-страниц?",
            "Какая функция в языке Python используется для вывода текста на экран?",
            "Какой символ обычно используется для обозначения комментариев в большинстве языков программирования?",
            "Какой оператор используется для объединения двух строк в Python?",
            "Какое ключевое слово используется для создания функции в Python?",
            "Какой тип данных в Python используется для хранения неизменяемых коллекций элементов?",
            "Как называется процесс разделения программы на более мелкие, логически связанные части?",
            "Какой оператор используется для выполнения целочисленного деления в Python?",
            "Какой метод используется для добавления нового элемента в конец списка в Python?",
            "Какой из следующих языков программирования обычно используется для разработки мобильных приложений?",
            "Какая конструкция используется для обработки исключений в Python?",
            "Как называется структура данных, которая позволяет хранить данные в формате 'ключ-значение'?"
    };
    String[] choose_list = {
            "Google","Apple","Nokia","Samsung",
            "Java","Kotlin","Notepad","Python",
            "Facebook","Whatsapp","Instagram","Youtube",
            "Google","Apple","Nokia","Samsung",
            "Python" , " JavaScript","C++", " Ruby",
            "Инструкции" , "Функции" , "Классы" , "Переменные",
            "Стек" , "Очередь" , "Массив" , "Список",
            "if" , "switch" , "case" , "select",
            "int" , "str" , "float" , "bool",
            "for" , "loop" , "while" , "repeat",
            "GET" , "POST" , "DELETE" , "PUT",
            "Интерпретация" , "Компиляция" , "Обработка" , "Индексация",
            "Java" , "HTML" , "CSS" , "PHP",
            "print()" , "display()" , " show()" , "log()",
            "//" , "--" , "##" , "/* */",
            "&" , "+" , "*" , "/",
            "define" , "function" , "def" , "create",
            "list" , "tuple" , "set" , "dictionary",
            "Компиляция" , "Разработка" , "Декомпиляция" , "Разделение",
            "/" , "%" , "//" , "**",
            "add()" , "push()" , "append()" , "insert()",
            "Java" , "HTML" , "Ruby" , "Swift",
            "try-except" , "if-else" , "switch-case" , "while",
            "Array" , "List" , "Dictionary" , "Tuple"
    };
    String[] correct_list = { "Google",
            "Notepad",
            "Youtube",
            "Apple",
            "C++",
            "Классы",
            "Стек",
            "if",
            "float",
            "for",
            "GET",
            "Компиляция",
            "HTML",
            "print()",
            " //",
            "+",
            "def",
            "tuple",
            "Разделение",
            "//",
            "append()",
            "Swift",
            "try-except",
            "Dictionary"};

    TextView cpt_question , text_question;
    Button btn_choose1 , btn_choose2 , btn_choose3 , btn_choose4 , btn_next;
    ProgressBar progressBar;
    TextView progressText;

    int currentQuestion =  0  ;
    int scorePlayer =  0  ;
    boolean isclickBtn = false;
    String valueChoose = "";
    Button btn_click;
    CountDownTimer currentTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        cpt_question = findViewById(R.id.cpt_question);
        text_question = findViewById(R.id.text_question);
        btn_choose1 = findViewById(R.id.btn_choose1);
        btn_choose2 = findViewById(R.id.btn_choose2);
        btn_choose3 = findViewById(R.id.btn_choose3);
        btn_choose4 = findViewById(R.id.btn_choose4);
        btn_next = findViewById(R.id.btn_next);
        progressBar = findViewById(R.id.progressBar);
        progressText = findViewById(R.id.progressText);

        remplirData();

        btn_next.setOnClickListener(
                view -> {
                    if (isclickBtn) {
                        isclickBtn = false;

                        if (!valueChoose.equals(correct_list[currentQuestion])) {
                            Toast.makeText(playActivity.this, "Неправильно", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.btn_game_pressed);

                        } else {
                            Toast.makeText(playActivity.this, "Правильно", Toast.LENGTH_LONG).show();
                            btn_click.setBackgroundResource(R.drawable.btn_game_correct);
                            scorePlayer++;
                        }
                        new Handler().postDelayed(() -> {
                            if (currentQuestion != question_list.length - 1) {
                                currentQuestion = currentQuestion + 1;
                                remplirData();
                                valueChoose = "";
                                btn_choose1.setBackgroundResource(R.drawable.button_style);
                                btn_choose2.setBackgroundResource(R.drawable.button_style);
                                btn_choose3.setBackgroundResource(R.drawable.button_style);
                                btn_choose4.setBackgroundResource(R.drawable.button_style);
                                if (currentTimer != null) {
                                    currentTimer.cancel();
                                }
                                startTimer();
                            } else {
                                Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                                intent.putExtra("Результат", scorePlayer);
                                startActivity(intent);
                                finish();
                                currentTimer.cancel();
                            }
                        }, 2000);
                    } else {
                        Toast.makeText(playActivity.this, "Вы должны выбрать один", Toast.LENGTH_LONG).show();
                    }
                }
        );

        findViewById(R.id.image_back).setOnClickListener(
                a -> {
                    if (currentTimer != null) {
                        currentTimer.cancel();
                    }
                    finish();
                }
        );

        startTimer();
    }

    void startTimer() {
        long totalTime = 10 * 1000; // 10 секунд
        currentTimer = new CountDownTimer(totalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secondsRemaining = millisUntilFinished / 1000;
                progressText.setText(secondsRemaining + " сек");
                long totalTimeSeconds = totalTime / 1000;
                long progress = (secondsRemaining * 100) / totalTimeSeconds;
                progressBar.setProgress((int) progress);
            }

            @Override
            public void onFinish() {
                progressBar.setProgress(0);
                Intent intent = new Intent(playActivity.this, ResulteActivity.class);
                intent.putExtra("Результат", scorePlayer);
                startActivity(intent);
                finish();
            }
        };
        currentTimer.start();
    }

    void remplirData(){
        cpt_question.setText((currentQuestion+1) + "/" + question_list.length);
        text_question.setText(question_list[currentQuestion]);
        btn_choose1.setText(choose_list[4 * currentQuestion]);
        btn_choose2.setText(choose_list[4 * currentQuestion+1]);
        btn_choose3.setText(choose_list[4 * currentQuestion+2]);
        btn_choose4.setText(choose_list[4 * currentQuestion+3]);
    }

    public void ClickChoose(View view) {
        btn_click = (Button)view;
        if (isclickBtn) {
            btn_choose1.setBackgroundResource(R.drawable.button_style);
            btn_choose2.setBackgroundResource(R.drawable.button_style);
            btn_choose3.setBackgroundResource(R.drawable.button_style);
            btn_choose4.setBackgroundResource(R.drawable.button_style);
        }
        chooseBtn();
    }

    void chooseBtn(){
        btn_click.setBackgroundResource(R.drawable.btn_game_normal);
        isclickBtn = true;
        valueChoose = btn_click.getText().toString();
    }
}
