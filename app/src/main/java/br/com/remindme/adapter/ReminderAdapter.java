package br.com.remindme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.remindme.R;
import br.com.remindme.model.Reminder;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ReminderViewHolder> {

    private List<Reminder> mList;

    public ReminderAdapter(List<Reminder> list){
        this.mList = list;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_reminder, viewGroup, false);
        return new ReminderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder viewHolder, int i) {
        Reminder reminder = mList.get(i);
        viewHolder.txtTitle.setText(reminder.getTitle());
        viewHolder.txtTime.setText(""+reminder.getHour()+":"+reminder.getMinute());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected static class ReminderViewHolder  extends RecyclerView.ViewHolder{

        protected TextView txtTitle;
        protected TextView txtTime;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtTime = itemView.findViewById(R.id.txt_time);
        }
    }
}