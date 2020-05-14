package com.cateno.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class Model implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", unique = true)
    private String id;

    private Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    public String getId() {
        return id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
