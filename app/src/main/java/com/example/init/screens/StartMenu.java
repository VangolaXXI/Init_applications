package com.example.init.screens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.init.R;


public class StartMenu extends Fragment {
    private OnStartMenuButtonClicked listener;



    public interface OnStartMenuButtonClicked {
        void onStartMenuButtonClick();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_menu, container, false);

        Button startButton = view.findViewById(R.id.loginButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onStartMenuButtonClick();
                }
            }
        });

        return view;
    }

    public void setOnStartMenuButtonClickListener(OnStartMenuButtonClicked listener) {
        this.listener = listener;
    }

    /**
     * A simple {@link Fragment} subclass.
     * Use the {@link StudentTicket#newInstance} factory method to
     * create an instance of this fragment.
     */
    public static class StudentTicket extends Fragment {

        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        public StudentTicket() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StudentTicket.
         */
        // TODO: Rename and change types and number of parameters
        public static StudentTicket newInstance(String param1, String param2) {
            StudentTicket fragment = new StudentTicket();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }


    }
}
