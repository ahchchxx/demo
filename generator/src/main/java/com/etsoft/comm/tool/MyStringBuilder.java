package com.etsoft.comm.tool;

public class MyStringBuilder{
	StringBuilder sb = new StringBuilder();
    public StringBuilder append(Object obj) {
        return append(String.valueOf(obj));
    }
    public StringBuilder appendLine(Object obj) {
        return append(String.valueOf(obj)).append("\n");
    }
    public StringBuilder append(String str) {
    	return sb.append(str);
    }
    public StringBuilder appendRepeat(String str, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(str);
        }
    	return sb;
    }
    public String toString(){
    	return sb.toString();
    }
}
