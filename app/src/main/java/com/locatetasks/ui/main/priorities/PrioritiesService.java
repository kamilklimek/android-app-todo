package com.locatetasks.ui.main.priorities;

import android.util.Log;

import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.ioc.DependencyManager;
import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;

public class PrioritiesService {
    private final static ProjectDao projectDao = DependencyManager.get(ProjectDao.class);

    public List<TaskModel> getTasksTodoToday() {
        List<ProjectModel> projectModels = projectDao.findAll();
        List<TaskModel> priorities = new LinkedList<>();

        OffsetDateTime now = OffsetDateTime.now();

        for (ProjectModel projectModel : projectModels) {
            for (TaskModel task : projectModel.getTaskModels()) {
                OffsetDateTime executionOffsetDateTime = task.getExecutionOffsetDateTime();
                if (executionOffsetDateTime.getDayOfYear() == now.getDayOfYear() && executionOffsetDateTime.getYear() == now.getYear()
                && !task.isDone()) {
                    TaskModel newModel = TaskModel.of(task);
                    newModel.setProjectModel(projectModel);
                    priorities.add(newModel);
                }
            }
        }

        return priorities;
    }
}
