package com.example.next;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {
    private final List<Lesson> lessons;
    private final LayoutInflater inflater;
    private final LearningInterface learningInterface;
    private int selectedPosition = -1; // For tracking the selected lesson

    public LessonAdapter(Context context, List<Lesson> lessons, LearningInterface learningInterface) {
        this.inflater = LayoutInflater.from(context);
        this.lessons = lessons;
        this.learningInterface = learningInterface;
    }

    @Override
    public LessonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.lesson_item, parent, false);
        return new LessonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(LessonViewHolder holder, int position) {
        Lesson lesson = lessons.get(position);
        holder.lessonTitle.setText(lesson.getTitle());

        // Show the start button only for the selected item
        holder.startLessonButton.setVisibility(position == selectedPosition ? View.VISIBLE : View.GONE);

        // Click listener for the item view to show the start button
        holder.itemView.setOnClickListener(view -> {
            selectedPosition = position; // Update the selected position
            notifyDataSetChanged(); // Refresh the list to apply the visibility change
        });

        // Click listener for the start button to perform the action
        holder.startLessonButton.setOnClickListener(view -> {
            learningInterface.onLessonButtonClicked(lesson); // Perform the action when the start button is clicked
        });
    }


    @Override
    public int getItemCount() {
        return lessons.size();
    }

    public static class LessonViewHolder extends RecyclerView.ViewHolder {
        final TextView lessonTitle;
        final Button startLessonButton;

        public LessonViewHolder(View itemView) {
            super(itemView);
            lessonTitle = itemView.findViewById(R.id.lessonTitle);
            startLessonButton = itemView.findViewById(R.id.startLessonButton);
        }
    }
}
