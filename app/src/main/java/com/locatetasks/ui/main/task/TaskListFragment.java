package com.locatetasks.ui.main.task;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.locatetasks.R;
import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.TaskModel;
import com.locatetasks.ui.main.priorities.PriorityListElement;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TaskListFragment extends LinearLayout {
    private Long projectId;

    public TaskListFragment(Context context, Long projectId, View rootView) {
        super(context);
        this.projectId = projectId;

        final LinearLayout projectList =  new LinearLayout(rootView.getContext());
        projectList.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        int rgb = Color.rgb(7, 186, 73);
        int rgb2 = Color.rgb(196, 10, 50);
        boolean flag = true;

        List<TaskModel> all = DependencyManager.get(ProjectDao.class).findById(projectId).get().getTaskModels();
        for (TaskModel model : all) {
            if (!model.isDone()) {
                PriorityListElement element = new PriorityListElement(rootView.getContext(), model, flag ? rgb : rgb2);
                projectList.addView(element, lp);
                flag = !flag;
            }
        }

        addView(projectList);
    }
}