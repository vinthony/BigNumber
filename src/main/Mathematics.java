package main;


import java.util.ArrayList;

public class Mathematics {

    private String zero = "0";
    public static void main(String [] args)
    {

    }

    //加法运算
    public  String Add(String s1, String s2)
    {
        if(s1.equals("0")) return s2;
        if(s2.equals("0")) return s1;
        if(s1.equals("")||s2.equals("")) return s1+s2;
        if((!isNum(s1))||(!isNum(s2))) return "error";

//        简化操作
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);
        String longer = ns1.length()>ns2.length() ? ns1 : ns2;
        String shorter = ns1.length()>ns2.length() ? ns2 : ns1;
        String num = "";//保存数据的位置
        int carry = 0;  //进位
        int n = shorter.length();
        int x = 0;//进位
        int y = 0;//剩余
//      如果两个负数，去掉符号之后相加
        if(isNegative(ns1)&&isNegative(ns2))
           return "-"+Add(removeSign(ns1),removeSign(ns2));
//        如果存在一个负数，当减法处理
        if(isNegative(ns1)||isNegative(ns2)){
            if(isNegative(ns1)) return Sub(ns2,removeSign(ns1));
            if(isNegative(ns2)) return Sub(ns1,removeSign(ns2));
        }

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
            return Add(prev,Integer.toString(x))+num;
        else
            return prev+num;
    }


    //减法运算
    public  String Sub(String s1, String s2) {
        if(s1.equals("")||s2.equals("")) return s1+s2;
        if((!isNum(s1))||(!isNum(s2))) return "error";
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);
//      -a-b = -(a+b)
        if(isNegative(ns1)&&(!isNegative(ns2))) return "-"+Add(removeSign(s1),removeSign(s2));
//        a-(-b) = a+b
        if(isNegative(ns2)) return Add(s1,removeSign(s2));
//       -a-(-b) = b-a = -(a-b)
        if(isNegative(ns1)&&isNegative(ns2)) return Sub(s2, s1);
//        处理 a-b a>b ? a< b
        String bigger = max(ns1,ns2);
        String smaller = min(ns1, ns2);
        String prefix = bigger.equals(ns1) ? "" :"-";
        String prev = bigger.substring(0,bigger.length()-smaller.length());
        String bigger2 = bigger.substring(prev.length());
        int n = smaller.length();
        int borrowed = 0;
        int num;
        String str = "";
        while(n>0){
            int temp = getInt(bigger2.charAt(n-1)) - getInt(smaller.charAt(n-1))-borrowed;
            if(temp<0){//如果不够减
                num = temp + 10;
                borrowed=1;
            }else{
                num = temp;
            }
            str = num+str;
            n--;
        }
        //修正prev 当prev为个位数时，直接得出结果
        if(borrowed!=0&&prev.length()>0){
            if(prev.length() == 1)
                return legalify(prefix+(Integer.parseInt(prev)-borrowed)+str);
            else
                return legalify(prefix+Sub(prev,Integer.toString(borrowed))+str);
        }
        else
            return legalify(prefix+prev+str);
    }

    //乘法运算
    public  String Mul(String s1, String s2)
    {
        if((!isNum(s1))||(!isNum(s2))) return "error";
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);
        String prefix = (isNegative(ns1)&&!isNegative(ns2))||(!isNegative(ns1)&&isNegative(ns2)) ? "-":"";
        String n1 = removeSign(ns1);
        String n2 = removeSign(ns2);
        if( n1.equals("0") || n2.equals("0")) return "0";
        String finalStr = "";
        int temp;
        int m = 0;
        int n;
//        转换成一位数乘法 取集合
        for (int i = n1.length()-1; i >= 0 ;i--){//200
            String str = "";
            for (int j =n2.length()-1 ;j >= 0;j--){//10
                temp = getInt(n2.charAt(j))*getInt(n1.charAt(i))+m;
                log(temp);
                 m = temp/10;//进位
                 n = temp%10;//本位
                //如果是单个
                if(n1.length()==1)
                 str = m + Integer.toString(n) + str;
                else
                 str = Integer.toString(n) +str;
            }
            str = str +produce("0",n1.length()-1-i);
            finalStr = Add(legalify(str),legalify(finalStr));
        }

        return prefix+finalStr;
    }

    //除法运算
    public  String Div(String s1, String s2)
    {
        if(s2.equals("0")) return "error";//除数不能为0
        if((!isNum(s1))||(!isNum(s2))) return "error";
        String ns1 = legalify(s1);
        String ns2 = legalify(s2);
        String prefix = (isNegative(ns1)&&!isNegative(ns2))||(!isNegative(ns1)&&isNegative(ns2)) ? "-":"";
        String n1 = removeSign(ns1);
        String n2 = removeSign(ns2);
        String re = "";
        String t = "";
        int h = 0;
        t =  n1.substring(h,h+n2.length());
        while(h<n1.length()){
            if(max(t,n2).equals(t)){
                //n1>n2
                int k = 0;
                while(max(t,n2).equals(t)){//n1 > n2
                    t = Sub(t,n2);
                    k++;
                }
                re += k;
                try {
                    t = t + n1.substring(h+n2.length(),h+n2.length()+1);
                }catch (Exception e){
                    t = t + "0";
                    break;
                }
                //迭代
            }else{
               re += "0";
                try {
                    t = t + n1.substring(h+n2.length(),h+n2.length()+1);
                }catch (Exception e){
                    t = t + "0";
                    break;
                }
           }
           h++;
        }
        return prefix+legalify(re);
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
    private String divLegal(String s){
        for(int i = s.length()-1 ; i >= 0;i--){
            if(!String.valueOf(s.charAt(i)).equals(zero)){
                return s.substring(0,i);
            }
        }
        return zero;
    }
    private Boolean isNegative(String s){
        return s.matches("[-][0-9]+");
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
    private String max(String s1,String s2){
        s1 = legalify(s1);
        s2 = legalify(s2);
        if(isNegative(s1)&&!isNegative(s2)) return s2;
        if(isNegative(s2)&&!isNegative(s1)) return s1;
       if(s1.length() != s2.length()) return s1.length()>s2.length()? s1 : s2;
       for (int i = 0; i<s1.length();i++){
           if(getInt(s1.charAt(i))-getInt(s2.charAt(i))!=0)
               return getInt(s1.charAt(i))>getInt(s2.charAt(i))? s1 : s2;
       }
        return s1;
    }
    private String min(String s1,String s2){
        return max(s1,s2).equals(s1) ? s2 : s1;
    }
    private String produce(String o,int num){
        String str = "" ;
        while(num>0){
            str += o;
            num--;
        }
        return str;

    }

}
