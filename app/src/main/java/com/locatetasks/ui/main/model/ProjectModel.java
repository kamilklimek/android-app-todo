package com.locatetasks.ui.main.model;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class ProjectModel extends AbstractModel {
    private final List<TaskModel> taskModels;
    public ProjectModel(String name, List<TaskModel> taskModels) {
        super(name);
        this.taskModels = taskModels;
    }

    public List<TaskModel> getTaskModels() {
        return taskModels;
    }

    public static ProjectModel of(ProjectModel projectModel) {
        ProjectModel newModel = new ProjectModel(projectModel.getName(), getTaskModelsCopy(projectModel.getTaskModels()));
        newModel.setId(projectModel.getId());
        return newModel;
    }

    private static List<TaskModel> getTaskModelsCopy(List<TaskModel> taskModels) {
        List<TaskModel> newTasksModel = new LinkedList<>();
        for (TaskModel taskModel : taskModels) {
            newTasksModel.add(TaskModel.of(taskModel));
        }

        return newTasksModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProjectModel that = (ProjectModel) o;
        return Objects.equals(taskModels, that.taskModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), taskModels);
    }

    @Override
    public String toString() {
        return super.toString() + "ProjectModel{" +
                "taskModels=" + taskModels +
                '}';
    }
}
