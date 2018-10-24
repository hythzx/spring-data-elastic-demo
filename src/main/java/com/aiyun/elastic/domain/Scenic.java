package com.aiyun.elastic.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;
import java.util.Objects;

@Document(indexName = "scenic", type = "scenic")
public class Scenic implements Serializable {

    @Id
    @Field
    private Long id;

    @Field
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scenic)) return false;
        Scenic scenic = (Scenic) o;
        return Objects.equals(getId(), scenic.getId()) &&
                Objects.equals(getName(), scenic.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Scenic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
