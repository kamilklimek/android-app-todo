package com.locatetasks.ui.main.priorities;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.locatetasks.R;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;
import com.locatetasks.ui.main.project.ProjectListElement;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PrioritiesListFragment extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.priorities_list_fragment, container, false);
        final LinearLayout projectList = root.findViewById(R.id.priorities_list_fragment);
        projectList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        int rgb = Color.rgb(7, 186, 73);
        int rgb2 = Color.rgb(196, 10, 50);
        boolean flag = true;

        List<TaskModel> all = DependencyManager.get(PrioritiesService.class).getTasksTodoToday();
        for (TaskModel model : all) {
            PriorityListElement element = new PriorityListElement(root.getContext(), model, flag ? rgb : rgb2);
            projectList.addView(element, lp);
            flag = !flag;
        }
        return root;
    }
}