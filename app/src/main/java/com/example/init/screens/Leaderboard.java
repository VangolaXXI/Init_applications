package com.example.init.screens;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.init.R;
import com.example.init.adapter.LeaderboardAdapter;
import com.example.init.adapter.LeaderboardItem;
import com.example.init.dp.LeaderboardDataLoader;

import java.util.List;

public class Leaderboard extends Fragment {

    private RecyclerView recyclerView;
    private LeaderboardAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        // Создаем экземпляр класса для загрузки данных
        LeaderboardDataLoader dataLoader = new LeaderboardDataLoader(requireContext());

        // Загружаем данные
        List<LeaderboardItem> leaderboardItems = dataLoader.loadLeaderboardItems();

        // Настройка RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new LeaderboardAdapter(leaderboardItems);
        recyclerView.setAdapter(adapter);

        Button btBack = view.findViewById(R.id.bt_back);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackButtonClick();
            }
        });

        return view;
    }

    private void onBackButtonClick() {
        requireActivity().getSupportFragmentManager().popBackStack();
    }
}
