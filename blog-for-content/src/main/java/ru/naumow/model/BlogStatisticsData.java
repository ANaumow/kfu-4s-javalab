package ru.naumow.model;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.HashMap;
import java.util.Map;

@Component
@ApplicationScope
public class BlogStatisticsData {

    private final Map<String, Long> stats;

    public BlogStatisticsData() {
        stats = new HashMap<>();
    }

    public void onVisit(String blogAlias) {
        stats.put(blogAlias, stats.getOrDefault(blogAlias, 0L) + 1);
        System.out.println(stats);
    }

}
