package com.example.jsEval;

import cn.hutool.script.ScriptUtil;
import javax.script.CompiledScript;

public class JsEvalTest {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        // 1, define some variables or obj
        initInfo(sb);

        // 2, pre-define some functions
        sb.append("function max(val1, val2) {return Math.max(val1, val2)};");

        // define function body
        funBody(sb);
        // call that function in js
        sb.append("fun();");

        // ★ execute
        eval(sb);
    }

    private static void initInfo(StringBuilder sb) {
        sb.append("var ee = {'name': 'Tom', 'age': 1, 'birthday': '2020-12-12', 'salary': 10000};");

        // convert from java bean to js object
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("name", "Tom");
        // map.put("age", 1);
        // map.put("birthday", "2020-12-12");
        // map.put("salary", 10000);
        // sb.append("var ee = " + new Gson().toJson(map) + ";");

        sb.append("var er = {'name': 'ET', 'type': 2};");
    }

    private static void funBody(StringBuilder sb) {
        // ------------------------------- start to define a function
        sb.append("function fun() {");
        sb.append(" var result = '';// 定义为 null 时，返回值会报错 java.lang.NullPointerException\n");

        // test - 1.  class java.lang.Double
        // sb.append(" if(er.type == 1){");
        // sb.append("     result = ee.salary * 0.82225565; ");
        // sb.append("     result++\n");
        // sb.append(" }");
        // sb.append(" else{result = 1211;};");

        // test - 2.  class java.lang.Integer
        // sb.append(" er.type = 2\n");
        sb.append(" result = ee.salary * er.type + 8;");

        // test - 3.  class java.lang.String
        // sb.append(" result = er.name + ', ' + ee.name;");

        // test - 4.  max library fun
        // sb.append(" result = Math.max(1, 2);");
        // sb.append(" result = max(ee.salary, er.type);");

        // test - 5.
        // xxx

        sb.append(" return result;");
        sb.append("}");
        // ------------------------------- end of the definition
    }

    private static void eval(StringBuilder sb) {
        try {
            CompiledScript compile = ScriptUtil.compile(sb.toString());
            Object ret = compile.eval();
            System.out.println(ret.getClass().toString());
            System.out.println(ret);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

// basic usage example:
//     sb.append("var a = 1;");
//     sb.append("var b = 2;");
//     sb.append("var result = a + b;");
//     sb.append("print(result);");
