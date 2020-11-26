package com.example.springboot.config;

//public enum ErrorCodeMsg  {
//    ParamError("ParamError", 0);
//}

public class ErrorCodeMsg  {
//    public static class CodeMsg {
//        private String Code;
//        private String Msg;
//
//        public static CodeMsg GetCodeMsg(String code, String msg) {
//            CodeMsg codeMsg = new CodeMsg();
//            codeMsg.setCode(code);
//            codeMsg.setMsg(msg);
//            return codeMsg;
//        }
//
//        public String getCode() {
//            return Code;
//        }
//        public void setCode(String code) {
//            Code = code;
//        }
//        public String getMsg() {
//            return Msg;
//        }
//        public void setMsg(String msg) {
//            Msg = msg;
//        }
//    }
//
//    public static CodeMsg ParamError = CodeMsg.GetCodeMsg("008", "Param Error");
//    public static CodeMsg LanError = CodeMsg.GetCodeMsg("002", "Lan Error");


    public static RetBean ParamError = RetBean.GetBean("008", "Param Error");
    public static RetBean LanError = RetBean.GetBean("002", "Lan Error");

    public enum ResultCode {
        SUCESS("200", "Sucess"),
        FAILD("500", "Error"),
        ParamError("601", "Param Error");

        private String code;
        private String msg;
        ResultCode(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }
}
