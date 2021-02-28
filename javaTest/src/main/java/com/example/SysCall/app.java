package com.example.SysCall;

/**
 * system call example
 */
public class app {
    public static void main(String[] args) throws Exception {
        // Test 1: create a folder in linux OS
        // String cmdStr = "mkdir /tmp/hello";

        // Test 2: open calculation in windows OS
        String cmdStr = "calc.exe"; // "cmd /c dir"

        Process process = Runtime.getRuntime().exec(cmdStr);

        // get return from sys call
        // InputStream in = process.getInputStream();
        // while (in.read() != -1) {
        //     System.out.println(in.read());
        // }
        // in.close();
        // process.waitFor();
    }
}
