package main;

import org.junit.Before;
import org.junit.Test;

public class MathematicsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        String str1 = "1";
        String str2 = "2";
//        1111111111111 + 1
//        111111111111 + 9
//        111111111+1111111
//        Mathematics instance = new Mathematics();
//        System.out.println(instance.Add(str1,str2));
    }

    @Test
    public void testSub() throws Exception {
        String str1 = "100";
        String str2 = "23";

        Mathematics instance = new Mathematics();
        System.out.println(instance.Sub(str1, str2));
    }

    @Test
    public void testMul() throws Exception {
        String str1 = "100";
        String str2 = "2";
       // Mathematics instance = new Mathematics();
       // System.out.println(instance.Mul(str1, str2));
    }

    @Test
    public void testDiv() throws Exception {
        String str1 = "100";
        String str2 = "23";
        Mathematics instance = new Mathematics();
        System.out.println(instance.Div(str1, str2));
    }
}