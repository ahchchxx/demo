package com.example.Composite;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    String name;
    Integer age;
    List<Entity> list;

    Entity(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.list = new ArrayList<>();
    }

    public void add(Entity e) {
        list.add(e);
    }
    public void remove(Entity e){
        if (list.contains(e)) {
            list.remove(e);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Entity> getList() {
        return list;
    }

    public void setList(List<Entity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
