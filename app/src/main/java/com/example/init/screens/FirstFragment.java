package com.example.init.screens;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.init.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class FirstFragment extends Fragment {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private ImageView passwordVisibilityToggle;

    private OnFirstFragmentButtonClicked listener;

    public interface OnFirstFragmentButtonClicked {
        void onFirstFragmentButtonClick();
    }

    @SuppressLint({"WrongViewCast", "ClickableViewAccessibility"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        usernameEditText = view.findViewById(R.id.usernameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginButton = view.findViewById(R.id.loginButton1);


        usernameEditText.setTextAppearance(getContext(), R.style.Boldstyle);
        passwordEditText.setTextAppearance(getContext(), R.style.Boldstyle);
        passwordEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        usernameEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    passwordEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });


        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Выполняем действие, когда нажата клавиша Enter
                    performLogin();
                    return true; // Возвращаем true, чтобы указать, что событие было обработано
                }
                return false; // Возвращаем false, чтобы продолжить передавать событие дальше
            }
        });
        final Drawable[] drawables = passwordEditText.getCompoundDrawablesRelative();

        Drawable visibleIcon = getResources().getDrawable(R.drawable.panda_open);
        Drawable hiddenIcon = getResources().getDrawable(R.drawable.panda_closed);


        passwordEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (passwordEditText.getRight() - passwordEditText.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Save text style
                        float textSize = passwordEditText.getTextSize();
                        Typeface typeface = passwordEditText.getTypeface();

                        // Toggle password visibility
                        if (passwordEditText.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
                            passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], hiddenIcon, drawables[3]);
                        } else {
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], visibleIcon, drawables[3]);
                        }

                        // Restore text style
                        passwordEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
                        passwordEditText.setTypeface(typeface);

                        // Move cursor to the end of the text
                        passwordEditText.setSelection(passwordEditText.getText().length());
                        return true;
                    }
                }
                return false;
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        return view;
    }


    private void performLogin() {
        String enteredUsername = usernameEditText.getText().toString();
        String enteredPassword = passwordEditText.getText().toString();
        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference userPhotosRef = storageRef.child("user_photos");

        // Ссылка на вашу коллекцию в Firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users")
                .whereEqualTo("username", enteredUsername) // Ищем пользователя по имени
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String correctPassword = document.getString("password");
                                // Сравниваем введенный пароль с корректным паролем из базы данных
                                if (enteredPassword.equals(correctPassword)) {
                                    // Успешный вход
                                    if (listener != null) {
                                        // Получаем остальные данные из документа
                                        String fullname = document.getString("Fullname");
                                        String group = document.getString("group");
                                        String birthday = document.getString("birthday");
                                        String email = document.getString("email");
                                        String inn = document.getString("INN");
                                        String username = document.getString("username");
                                        String photoUrl = document.getString("photoUrl");



                                        SharedPreferences sp = requireContext().getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sp.edit();

                                        // Сохраняем данные в SharedPreferences
                                        if (fullname != null) {
                                            editor.putString("Fullname", fullname);
                                        }
                                        if (group != null) {
                                            editor.putString("group", group);
                                        }
                                        if (birthday != null) {
                                            editor.putString("birthday", birthday);
                                        }
                                        if (email != null) {
                                            editor.putString("email", email);
                                        }
                                        if (inn != null) {
                                            editor.putString("INN", inn);
                                        }
                                        if (photoUrl != null) {
                                            editor.putString("photoUrl", photoUrl);
                                        }
                                        if (username != null) {
                                            editor.putString("username", username);
                                        }

// Фиксируем изменения
                                        editor.commit();



                                        listener.onFirstFragmentButtonClick();
                                        // Очистить поля ввода после успешного входа
                                        usernameEditText.setText("");
                                        passwordEditText.setText("");
                                    }
                                    return; // Завершаем цикл, если найден корректный пользователь
                                }
                            }
                            // Если не найдено совпадение пароля для введенного имени пользователя
                            Toast.makeText(getContext(), "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                            // Изменяем цвет текста на красный
                            passwordEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.incorrect));
                            usernameEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.incorrect));
                            loginButton.setBackgroundResource(R.drawable.btn_incorrect);
                        } else {
                            // Пользователь с таким именем не найден
                            Toast.makeText(getContext(), "Пользователь не найден", Toast.LENGTH_SHORT).show();
                            // Изменяем цвет текста на красный
                            usernameEditText.setTextColor(ContextCompat.getColor(getContext(), R.color.incorrect));
                            loginButton.setBackgroundResource(R.drawable.btn_incorrect);
                        }
                    }
                });

    }
    public void setOnFirstFragmentButtonClickListener(OnFirstFragmentButtonClicked listener) {
        this.listener = listener;
    }
}
