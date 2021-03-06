package com.gupaoedu.mybatis.my;

//import lombok.Data;

public class Test {
    private Integer id;
    private Integer nums;
    private String name;

    public Test(Integer id, Integer nums, String name) {
        this.id = id;
        this.nums = nums;
        this.name = name;
    }
    public Test() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", nums=" + nums +
                ", name='" + name + '\'' +
                '}';
    }
}