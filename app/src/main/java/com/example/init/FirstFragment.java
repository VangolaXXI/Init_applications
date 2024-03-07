package com.example.init;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
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
import androidx.fragment.app.Fragment;

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
                        } else {
                            passwordEditText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
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
        // Предполагается, что у вас есть корректный логин и пароль
        String correctUsername = "admin";
        String correctPassword = "admin";

        if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
            if (listener != null) {
                listener.onFirstFragmentButtonClick();
            }
        } else {
            // Если логин или пароль неправильные, отобразить сообщение об ошибке
            Toast.makeText(getContext(), "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
        }
    }

    public void setOnFirstFragmentButtonClickListener(OnFirstFragmentButtonClicked listener) {
        this.listener = listener;
    }
}
