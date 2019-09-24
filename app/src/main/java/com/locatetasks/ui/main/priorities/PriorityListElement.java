package com.locatetasks.ui.main.priorities;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;

import java.time.format.DateTimeFormatter;

public class PriorityListElement extends LinearLayout {
    public PriorityListElement(Context context, TaskModel taskModel, int color) {
        super(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(20, 20, 20, 20);
        setOrientation(VERTICAL);

        setPadding(16, 16, 16, 16);
        setBackgroundColor(color);
        TextView name = new TextView(context);
        name.setText("Nazwa zadania: " + taskModel.getName());
        addView(name, lp);

        TextView desc = new TextView(context);
        desc.setText("Data wykonania: " + taskModel.getExecutionOffsetDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        addView(desc, lp);


        Button button = new Button(context);
        button.setText("Przejdź do zadania");
        addView(button, lp);
    }




}
