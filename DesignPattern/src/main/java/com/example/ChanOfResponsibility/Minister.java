package com.example.ChanOfResponsibility;

import java.util.ArrayList;
import java.util.List;

public class Minister {
    public static void main(String[] args) {
        Emperor emperor = Emperor.getInstance();

        List<Official> list = new ArrayList<>();
        list.add(new AnHuiOfficial());
        list.add(new ShOfficial());

        // start to serve
        for (Official official : list) {
            official.serve(emperor);
        }

    }
}
