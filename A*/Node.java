import java.util.*;
public class Node {
    public Node father = null;
    public int f;
    public int g;
    public int h;
    public HashMap<Integer, Integer> map ;
    public  int t;

    /**
     *
     * @param h
     * @param t:The target of the current state, for example: the water has been poured into the infinite pot,
     *         the target minus the poured water is t
     * @param map
     */
    public Node(int h,int t,HashMap map){

        this.g = 0;
        this.h = h;
        this.t= t;
        this.map =  map;
        this.f = this.g+this.h;
    }


    /**
     *
     * @param father
     * @param g
     * @param map
     * @param t
     */
    public Node(Node father,int g,HashMap map,int t){

        this.g = g+1;
        this.h = h;
        this.t= t;
        this.map = map;
        this.f = this.g+this.h;
        this.father=father;
    }


    public Node getFather() {
        return father;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public HashMap<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(HashMap<Integer, Integer> map) {
        this.map = map;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
