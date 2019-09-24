package com.locatetasks.ui.main.project;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.locatetasks.AddModelActivity;
import com.locatetasks.ProjectActivity;
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
        button.setOnClickListener(new Listener(this, projectModel.getId()));
        addView(button, lp);

        TextView textView = new TextView(context);
        textView.setText("Liczba zadań: " + projectModel.getTaskModels().size());
        addView(textView, lp);
    }

    class Listener implements OnClickListener {
        private ProjectListElement projectListElement;
        private final Long projectId;

        public Listener(ProjectListElement projectListElement, Long projectModelId) {
            this.projectListElement = projectListElement;
            this.projectId = projectModelId;
        }

        @Override
        public void onClick(View v) {
            Intent myIntent = new Intent(projectListElement.getContext(), ProjectActivity.class);
            projectListElement.getContext().startActivity(myIntent);
            Bundle b = new Bundle();
            b.putInt("projectId", this.projectId.intValue()); //Your id
            myIntent.putExtras(b);
            projectListElement.getContext().startActivity(myIntent);
        }
    }




}
