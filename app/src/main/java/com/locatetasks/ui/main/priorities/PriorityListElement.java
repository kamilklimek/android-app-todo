package com.locatetasks.ui.main.priorities;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.locatetasks.MainActivity;
import com.locatetasks.ProjectActivity;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.TaskModel;

import java.time.format.DateTimeFormatter;

public class PriorityListElement extends LinearLayout {
    public PriorityListElement(Context context, final TaskModel taskModel, int color) {
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
        button.setText("UKOŃCZ ZADANIE");
        button.setOnClickListener(new Listener(this, taskModel));
        addView(button, lp);
    }

    class Listener implements OnClickListener {
        private final LinearLayout linearLayout;
        private TaskModel taskModel;

        Listener(LinearLayout linearLayout, TaskModel taskModel) {
            this.linearLayout = linearLayout;
            this.taskModel = taskModel;
        }

        @Override
        public void onClick(View v) {
            DependencyManager.get(ProjectDao.class).markTaskAsDone(taskModel.getProjectModel().getId(), taskModel.getId());

            Intent myIntent = new Intent(linearLayout.getContext(), MainActivity.class);
            linearLayout.getContext().startActivity(myIntent);
        }
    }




}
