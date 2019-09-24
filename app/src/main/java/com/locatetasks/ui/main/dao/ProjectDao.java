package com.locatetasks.ui.main.dao;

import android.util.Log;

import com.locatetasks.ui.main.model.ProjectModel;
import com.locatetasks.ui.main.model.TaskModel;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public final class ProjectDao implements Dao<ProjectModel, Long>{
    private final static Map<Long, ProjectModel> projects = new HashMap<>();
    private static Long CURRENT_MODEL_ID = 0L;
    static {
        TaskModel zrobić_zakupy1 = new TaskModel(
                "Zrobić zakupy", "Kupić mleko, jajka", OffsetDateTime.now()
        );
        zrobić_zakupy1.setId(0L);
        ProjectModel zrobić_zakupy = new ProjectModel("Projekcik 1", Collections.singletonList(zrobić_zakupy1));
        zrobić_zakupy1.setProjectModel(zrobić_zakupy);
        zrobić_zakupy.setId(CURRENT_MODEL_ID);
        projects.put(CURRENT_MODEL_ID, zrobić_zakupy);
        CURRENT_MODEL_ID++;
        ProjectModel value = new ProjectModel("Projekcik 5", Collections.<TaskModel>emptyList());
        value.setId(CURRENT_MODEL_ID);
        projects.put(CURRENT_MODEL_ID, value);
        CURRENT_MODEL_ID++;
    }

    @Override
    public ProjectModel save(ProjectModel model) {
        model.setId(CURRENT_MODEL_ID);
        CURRENT_MODEL_ID += 1;
        projects.put(model.getId(), ProjectModel.of(model));
        return model;
    }

    @Override
    public Optional<ProjectModel> findById(Long id) {
        return Optional.ofNullable(projects.get(id)).map(new Function<ProjectModel, ProjectModel>() {
            @Override
            public ProjectModel apply(ProjectModel projectModel) {
                return ProjectModel.of(projectModel);
            }
        });
    }

    @Override
    public List<ProjectModel> findAll() {
        List<ProjectModel> models = new LinkedList<>();
        for (ProjectModel model : projects.values()) {
            models.add(ProjectModel.of(model));
        }
        return models;
    }

    public void markTaskAsDone(Long projectId, final Long taskId) {
        Optional.ofNullable(projects.get(projectId))
                .ifPresent(new Consumer<ProjectModel>() {
                    @Override
                    public void accept(ProjectModel projectModel) {
                        projectModel.getTaskModels().get(taskId.intValue())
                                .setDone(true);
                    }
                });
    }

    @Override
    public boolean remove(Long id) {
        return Optional.ofNullable(projects.remove(id)).isPresent();
    }

    public void addTask(Long projectId, final TaskModel taskModel) {
        Optional.ofNullable(projects.get(projectId))
                .ifPresent(new Consumer<ProjectModel>() {
                    @Override
                    public void accept(ProjectModel projectModel) {
                        projectModel.getTaskModels().add(taskModel);
                    }
                });
    }
}
