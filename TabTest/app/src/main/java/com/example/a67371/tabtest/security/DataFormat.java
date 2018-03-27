package com.example.a67371.tabtest.security;

public class DataFormat {
    public static boolean isEmail(String str){
        String regex="\\w+@\\w+\\.\\w{2,3}";
        return str.matches(regex);
    }
    public static boolean isPhone(String str){
        String regex="^((13[0-9])|(15[0-9])|(18[0-9]))\\d{8}";
        return str.matches(regex);
    }
    public static boolean isName(String str){
        String regex="^[a-zA-Z0-9_]{1,20}$";
        return str.matches(regex);
    }
    public static boolean isPassword(String str){
        String regex="^[a-zA-Z0-9]{6,18}$";
        return str.matches(regex);
    }
    public static boolean isBirthday(String str){
        String regex="(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
        return str.matches(regex);
    }
    public static boolean isContent(String str){
        String regex="^.{0,199}$";
        return str.matches(regex);
    }
    public static boolean isSex(String str){
        if(str=="男" || str=="女")return true;
        else return false;
    }
    public static void main(String[] args) {
        String test="1";
        boolean a=isSex(test);
        System.out.println(a);
    }

}
