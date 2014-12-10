package main;


import java.util.ArrayList;

public class Mathematics {

    private String zero = "0";
    public static void main(String [] args)
    {

    }

    //加法运算
    public  String Add(String s1, String s2, String ret)
    {
        int carry = 0;  //进位
        if(s1.equals("")||s2.equals("")) return "";
        if((!isNum(s1))||(!isNum(s2))) return "error";

//        简化操作
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);
        String longer = ns1.length()>ns2.length() ? ns1 : ns2;
        String shorter = ns1.length()>ns2.length() ? ns2 : ns1;
        String num = "";//保存数据的位置
        int n = shorter.length();
        int x = 0;//进位
        int y = 0;//剩余
//      如果两个负数，去掉符号之后相加
        if(isNegative(ns1)&&isNegative(ns2))
            Add(removeSign(ns1),removeSign(ns2),null);
//        如果存在一个负数，当减法处理
        if(isNegative(ns2)||isNegative(ns2))
            Sub(ns1,ns2,null);

        String prev = longer.substring(0,longer.length()-shorter.length());
        String longer2 = longer.substring(prev.length());
        while(n > 0) {
//            迭代
            int temp = getInt(longer2.charAt(n - 1)) + getInt(shorter.charAt(n - 1)) + carry;
            x = temp / 10;//进位
            y = temp % 10;//剩余位
            if (x > 0 && temp >= 10)
                carry = x;//存在进位
            else
                carry = 0;
            num = Integer.toString(y) + num;
            n--;
        }
        if(carry>0)
            return Add(prev,Integer.toString(x),null)+num;
        else
            return prev+num;
    }


    //减法运算
    public  boolean Sub(String s1, String s2, String ret) {
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);

        return true;
    }

    //乘法运算
    public  boolean Mul(String s1, String s2, String ret)
    {
        return true;
    }

    //除法运算
    public  boolean Div(String s1, String s2, String ret)
    {
        return true;
    }

    /*
    * 功能方法 判断输入是否合法
    * #1 允许符号【+,-,.】
    * #2 +/- 只能在句首
    * */
    private  boolean isNum(String s1)
    {
        return s1.matches("^[-|+]*[0-9/.]+");
    }

    /*
        功能方法 将数字精简
        #1 数字前面多余的0
        #2 正负号返回真实的符号
    */
    private String legalify(String s){
        //#2
        int num = 0;
        for (int i = 0 ; i<s.length();i++) {
            if (String.valueOf(s.charAt(i)).equals("-"))
                num++;
            if (String.valueOf(s.charAt(i)).matches("[0-9]")) {
                s = s.substring(i);
                break;
            }
        }
        //偶数- 返回正
        String sub =  num%2 != 0 ? "-":"";

        //#1
        for(int i = 0 ; i<s.length();i++){
            if(!String.valueOf(s.charAt(i)).equals(zero)){
                return sub+s.substring(i);
            }
        }
        return zero;
    }
    private Boolean isNegative(String s){
        return s.matches("[-]");
    }
    private String removeSign(String s){
        for (int i = 0;i<s.length();i++){
            if(String.valueOf(s.charAt(i)).matches("[0-9]")){
                return s.substring(i);
            }
        }
        return "";
    }
/*
*   将长的数分为两部分，每部分进行分治求和，如果有进位则进位。
* */
//    private String divideAdd(String longer,String shorter){
//        if(longer.length()<10){
//            return String.valueOf(Integer.parseInt(longer)+Integer.parseInt(shorter));
//        }else{
//
//        }
//    }
    private int getInt(char c){
        return Integer.parseInt(String.valueOf(c));
    }
    private void log(String c){
        System.out.println(c);
    }
    private void log(int c){
        System.out.println(c);
    }
}
