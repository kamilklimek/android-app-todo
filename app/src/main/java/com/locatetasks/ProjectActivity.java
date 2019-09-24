package com.locatetasks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.task.TaskListFragment;

import java.util.Optional;

public class ProjectActivity extends AppCompatActivity {
    private Integer projectId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        Bundle b = getIntent().getExtras();
        View rootView = getWindow().getDecorView().getRootView();
        if (Optional.ofNullable(b).isPresent()) {
            this.projectId = b.getInt("projectId");
            Optional<ProjectModel> project = DependencyManager.get(ProjectDao.class).findById(this.projectId.longValue());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            TextView projectTitle = findViewById(R.id.project_title);
            LinearLayout linearLayout  = findViewById(R.id.list_of_tasks);

            if (!project.isPresent()) {
                projectTitle.setText("Nie znaleziono projektu o ID: " + projectId);
            } else {
                ProjectModel projectModel = project.get();
                projectTitle.setText(projectModel.getName());

                TaskListFragment projectListFragment = new TaskListFragment(getBaseContext(), projectModel.getId(), rootView);
                linearLayout.addView(projectListFragment, lp);
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(getBaseContext(), MainActivity.class);
        getBaseContext().startActivity(myIntent);
        startActivity(myIntent);
    }
}
