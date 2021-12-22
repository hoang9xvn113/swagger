package com.example.swagger.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "TODO")
public class TodoEntity {
    @Id
    @Column(name = "TODO_ID",nullable = false)
    private String id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "SELECTED", nullable = false)
    private boolean selected;
}
