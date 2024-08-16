package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class User {
    private int id;
    private String name;
    private Timestamp creationDate = new Timestamp(System.currentTimeMillis());
}
