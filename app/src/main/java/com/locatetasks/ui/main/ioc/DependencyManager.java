package com.locatetasks.ui.main.ioc;

import com.locatetasks.ui.main.dao.ProjectDao;
import com.locatetasks.ui.main.priorities.PrioritiesService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public final class DependencyManager {
    private final static Map<Class, Object> dependencies = new HashMap<>();

    static {
        dependencies.put(ProjectDao.class, new ProjectDao());
        dependencies.put(PrioritiesService.class, new PrioritiesService());
    }

    public static <T> T get(final Class<T> c) {
        Optional<Object> o = Optional.ofNullable(dependencies.get(c));

        return o.map(new Function<Object, T>() {
            @Override
            public T apply(Object o) {
                return c.cast(o);
            }
        }).orElseThrow(new Supplier<RuntimeException>() {
            @Override
            public RuntimeException get() {
                return new RuntimeException("Couldnt find dependency for class: " + c.getName());
            }
        });
    }
}
