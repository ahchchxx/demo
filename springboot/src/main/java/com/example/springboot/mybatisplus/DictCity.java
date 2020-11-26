package com.example.springboot.mybatisplus;

import lombok.Data;
import java.util.Date;

@Data
public class DictCity {

    private int id;
    private String code;
    private String name;
    private int provinceId;
    private int createUid;
    private Date createDate;
    private int writeUid;
    private Date writeDate;

}