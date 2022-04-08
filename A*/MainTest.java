import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void runtest1() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(1,0);
        Jugs.put(2,0);
        Jugs.put(3,0);
        int steps = Main.run(new int[]{1, 2, 3}, 3,Jugs);
        assertEquals(2, steps);
    }

    @Test
    void runtest2() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(3,0);
        Jugs.put(8,0);
        Jugs.put(17,0);
        int steps = Main.run(new int[]{3, 8, 17}, 260,Jugs);
        assertEquals(33, steps);
    }

    @Test
    void runtest3() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(2,0);
        Jugs.put(5,0);
        Jugs.put(6,0);
        Jugs.put(72,0);
        int steps = Main.run(new int[]{2, 5, 6,72}, 143,Jugs);
        assertEquals(7, steps);
    }

    @Test
    void runtest4() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(2,0);
        Jugs.put(5,0);
        Jugs.put(7,0);
        Jugs.put(8,0);
        Jugs.put(9,0);
        int steps = Main.run(new int[]{2, 5, 7,8,9}, 50,Jugs);
        assertEquals(12, steps);
    }

    @Test
    void runtest5() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(1,0);
        Jugs.put(4,0);
        Jugs.put(10,0);
        Jugs.put(15,0);
        Jugs.put(22,0);
        int steps = Main.run(new int[]{1, 4, 10,15,22}, 181,Jugs);
        assertEquals(19, steps);
    }

    @Test
    void runtest6() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();
        Jugs.put(2,0);
        Jugs.put(3,0);
        Jugs.put(5,0);
        Jugs.put(19,0);
        Jugs.put(121,0);
        Jugs.put(852,0);


        int steps = Main.run(new int[]{2, 3,5,19,121,852}, 11443,Jugs);
        assertEquals(36, steps);
    }

    @Test
    void runtest7() {
        HashMap<Integer, Integer> Jugs = new HashMap<>();

        Jugs.put(2,0);

        int steps = Main.run(new int[]{ 2}, 143,Jugs);
        assertEquals(-1, steps);
    }

    @Test
    void gcdd() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(12);
        test.add(34);
        test.add(56);
        test.add(1);
        int gcdd = Main.gcdd(test);
        assertEquals(1, gcdd);
    }
    @Test
    void gcdd2() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(12);
        test.add(34);
        test.add(56);
        test.add(10);
        int gcdd = Main.gcdd(test);
        assertEquals(2, gcdd);
    }
    @Test
    void gcdd3() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(12);
        test.add(354);
        test.add(56);
        test.add(6);
        int gcdd = Main.gcdd(test);
        assertEquals(2, gcdd);
    }
    @Test
    void gcdd4() {
        ArrayList<Integer> test = new ArrayList<Integer>();
        test.add(1412);
        test.add(34);
        test.add(56);
        test.add(3);
        int gcdd = Main.gcdd(test);
        assertEquals(1, gcdd);
    }


    @Test
    void gcd() {
        int gcd = Main.gcd(12,32);
        assertEquals(4, gcd);
    }



    @Test
    void gcd2() {
        int gcd = Main.gcd(2,365);
        assertEquals(1, gcd);
    }

    @Test
    void gcd3() {
        int gcd = Main.gcd(172,1232);
        assertEquals(4, gcd);
    }

    @Test
    void gcd4() {
        int gcd = Main.gcd(16512,312);
        assertEquals(24, gcd);
    }
}