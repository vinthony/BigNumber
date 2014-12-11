package main;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MathematicsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
//        1111111111111 + 1
//        111111111111 + 9
//        111111111+1111111
        Mathematics instance = new Mathematics();
        assertEquals(instance.Add("1","2"),"3");
        assertEquals(instance.Add("10000","1"),"10001");
    }

    @Test
    public void testSub() throws Exception {
        Mathematics instance = new Mathematics();
        assertEquals(instance.Sub("1","2"),"-1");
        assertEquals(instance.Sub("10000","1"),"9999");
    }

    @Test
    public void testMul() throws Exception {
       // Mathematics instance = new Mathematics();
       // System.out.println(instance.Mul(str1, str2));
    }

    @Test
    public void testDiv() throws Exception {
        Mathematics instance = new Mathematics();
    }
}