package com.example.init.dp;
import android.content.Context;

import com.example.init.R;
import com.example.init.adapter.LeaderboardItem;
import java.util.ArrayList;
import java.util.List;

public class LeaderboardDataLoader {

    private Context context;

    public LeaderboardDataLoader(Context context) {
        this.context = context;
    }

    public List<LeaderboardItem> loadLeaderboardItems() {
        List<LeaderboardItem> leaderboardItems = new ArrayList<>();

        // Загрузка данных из ресурсов
        String[] numbersArray = context.getResources().getStringArray(R.array.item_numbers);
        String[] usernamesArray = context.getResources().getStringArray(R.array.item_usernames);
        String[] pinsArray = context.getResources().getStringArray(R.array.item_pins);

        // Создание элементов списка из загруженных данных
        for (int i = 0; i < numbersArray.length; i++) {
            int number = Integer.parseInt(numbersArray[i]);
            String username = usernamesArray[i];
            String pin = pinsArray[i];
            leaderboardItems.add(new LeaderboardItem(number, username, pin));
        }

        return leaderboardItems;
    }
}
