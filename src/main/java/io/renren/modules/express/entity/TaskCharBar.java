package io.renren.modules.express.entity;

import lombok.Data;

@Data
public class TaskCharBar {
    private Integer [] collect;
    private Integer [] send;
    private String [] month;
}
