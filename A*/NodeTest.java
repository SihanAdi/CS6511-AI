import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {
    int f,g,h,t = 66;
    Node fathernode = new Node(1,3,new HashMap());
    HashMap <Integer,Integer>map = new HashMap<>();
    Node node = new Node(fathernode,g,map,t);


    @Test
    void getFather() {
        assertEquals(fathernode, node.getFather());
    }

    @Test
    void getF() {
        node.setF(f);
        assertEquals(f, node.getF());
    }

    @Test
    void getG() {
        assertEquals(1, node.getG());
    }

    @Test
    void getH() {
        node.setH(h);
        assertEquals(h, node.getH());
    }

    @Test
    void getMap() {
        assertEquals(map, node.getMap());
    }

    @Test
    void getT() {
        assertEquals(t, node.getT());
    }
}