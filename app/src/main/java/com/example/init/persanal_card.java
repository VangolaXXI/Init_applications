package com.example.init;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class persanal_card extends AppCompatActivity {

    private ImageView imageView1, imageView3,imageView4; // Переменная для хранения ссылки на ImageView
    private Drawable originalIcon1, originalIcon3,originalIcon4;
    private Drawable currentIcon1, currentIcon3,currentIcon4;

    private Main_menu mainMenu = new Main_menu();
    private FirstFragment firstFragment = new FirstFragment();
    private News news = new News();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persanal_card);
        originalIcon1 = getResources().getDrawable(R.drawable.home_activ);
        originalIcon3 = getResources().getDrawable(R.drawable.news_activ);
        originalIcon4 = getResources().getDrawable(R.drawable.login_activ);
        imageView1 = findViewById(R.id.icon1); // Инициализация imageView1
        imageView3 = findViewById(R.id.icon3);
        imageView4 = findViewById(R.id.icon4);

        imageView1.setImageDrawable(originalIcon1);
        // Сохранение оригинальных иконок
        currentIcon1 = imageView1.getDrawable();
        currentIcon3 = imageView3.getDrawable();
        currentIcon4 = imageView4.getDrawable();

        setNewFragment(firstFragment);
    }

    public void onLinearLayoutClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.layout1) {
            setNewFragment(firstFragment);
            imageView1.setImageDrawable(originalIcon1);
            imageView3.setImageDrawable(currentIcon3);
            imageView4.setImageDrawable(currentIcon4);
//        } else if (viewId == R.id.layout2) {
//            Intent intent = new Intent(this, );
//            startActivity(intent);
//            finish();
        } else if (viewId == R.id.layout3) {
            setNewFragment(news);
            imageView1.setImageDrawable(getResources().getDrawable(R.drawable.home));
            imageView3.setImageDrawable(originalIcon3);
            imageView4.setImageDrawable(currentIcon4);

        } else if (viewId == R.id.layout4) {
            setNewFragment(mainMenu);
            imageView1.setImageDrawable(getResources().getDrawable(R.drawable.home));
            imageView3.setImageDrawable(currentIcon3);
            imageView4.setImageDrawable(originalIcon4);
        }
    }

    private void setNewFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framelayout, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
