package com.locatetasks.ui.main.project;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.locatetasks.ui.main.model.ProjectModel;

public class ProjectListElement extends LinearLayout {
    public ProjectListElement(Context context, ProjectModel projectModel, int color) {
        super(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        setOrientation(VERTICAL);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(VERTICAL);
        linearLayout.setLayoutParams(lp);
        


        setPadding(16, 16, 16, 16);
        setBackgroundColor(color);

        Button button = new Button(context);
        button.setText(projectModel.getName());
        addView(button, lp);

        TextView textView = new TextView(context);
        textView.setText("Liczba zadań: " + projectModel.getTaskModels().size());
        addView(textView, lp);
    }




}
