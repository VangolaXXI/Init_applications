package com.example.init.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.init.R; // Импортируем правильный R класс
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private List<LeaderboardItem> leaderboardItems;

    public LeaderboardAdapter(List<LeaderboardItem> leaderboardItems) {
        this.leaderboardItems = leaderboardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leaderboard_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LeaderboardItem item = leaderboardItems.get(position);
        holder.tvNumber.setText(String.valueOf(item.getNumber()));
        holder.tvUsername.setText(item.getUsername());
        holder.tvPin.setText(item.getPin());
    }

    @Override
    public int getItemCount() {
        return leaderboardItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumber;
        TextView tvUsername;
        TextView tvPin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNumber = itemView.findViewById(R.id.tv_number); // Обновляем ссылки на идентификаторы
            tvUsername = itemView.findViewById(R.id.tv_username); // Обновляем ссылки на идентификаторы
            tvPin = itemView.findViewById(R.id.tv_pin); // Обновляем ссылки на идентификаторы
        }
    }
}
