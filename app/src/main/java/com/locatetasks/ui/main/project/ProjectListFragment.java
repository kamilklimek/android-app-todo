package com.locatetasks.ui.main.project;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.locatetasks.R;
import com.locatetasks.ui.main.ViewTabs;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProjectListFragment extends Fragment {
    private ViewGroup container;
    private LayoutInflater inflater;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.inflater = inflater;
        this.container = container;
        return addProjects();
    }

    private View addProjects() {
        View root = inflater.inflate(R.layout.project_list_fragment, container, false);
        final LinearLayout projectList = root.findViewById(R.id.project_list_fragment);
        projectList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int rgb = Color.rgb(7, 186, 73);
        int rgb2 = Color.rgb(196, 10, 50);
        boolean flag = true;

        List<ProjectModel> all = DependencyManager.get(ProjectDao.class).findAll();
        for (ProjectModel model : all) {
            ProjectListElement projectListElement = new ProjectListElement(root.getContext(), model, flag ? rgb : rgb2);
            flag = !flag;
            projectList.addView(projectListElement, lp);
        }
        return root;
    }

    @Override
    public void onResume() {
        this.addProjects();
        super.onResume();
    }
}