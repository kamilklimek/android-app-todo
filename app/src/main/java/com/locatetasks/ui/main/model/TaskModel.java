package com.locatetasks.ui.main.model;

import java.time.OffsetDateTime;
import java.util.Objects;

public final class TaskModel extends AbstractModel {
    private final String description;
    private final OffsetDateTime executionOffsetDateTime;
    private ProjectModel projectModel;
    public TaskModel(String name, String description, OffsetDateTime executionOffsetDateTime) {
        super(name);
        this.description = description;
        this.executionOffsetDateTime = executionOffsetDateTime;
    }


    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }

    public static TaskModel of(TaskModel taskModel) {
        return new TaskModel(taskModel.getName(), taskModel.getDescription(), taskModel.getExecutionOffsetDateTime());
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getExecutionOffsetDateTime() {
        return executionOffsetDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TaskModel taskModel = (TaskModel) o;
        return Objects.equals(description, taskModel.description) &&
                Objects.equals(executionOffsetDateTime, taskModel.executionOffsetDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, executionOffsetDateTime);
    }
}
