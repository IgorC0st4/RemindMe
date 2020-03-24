package br.com.remindme.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.remindme.R;
import br.com.remindme.model.Reminder;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder> {

    private static final String TAG = "ReminderAdapter";
    private List<Reminder> reminders;
    private Context context;

    public ReminderAdapter(List<Reminder> reminders, Context context) {
        this.reminders = reminders;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_reminder, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: called.");
        final Reminder reminder = reminders.get(i);
        viewHolder.txtTitle.setText(reminder.getTitle());
        viewHolder.txtTime.setText("" + reminder.getHour() + ":" + reminder.getMinute());

        viewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + reminder.getTitle());




                Toast.makeText(context, reminder.getTitle(), Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtTime;
        ConstraintLayout constraintLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtTime = itemView.findViewById(R.id.txt_time);
            constraintLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}