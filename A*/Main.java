import java.io.*;
import java.util.*;
import java.lang.Math;


public class Main {

    HashMap<Integer, Integer> Jugs;


    public static void main(String[] args) {
        int[] order = null;
        int target = 0;
        int maxv = 0;
        int sumv = 0;
        Main mm = new Main();
        mm.Jugs = new HashMap<Integer, Integer>();
        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/Ai/src/test.txt"));
            String str;
            str = in.readLine();
            String[] input = str.split(",");//PUT THE FIRST LINE TO THE HASH MAP SET THE VALUE TO 0

            for (String value : input) {
                sumv += Integer.parseInt(value);// sum of all the water jugs;
                if (maxv <= Integer.parseInt(value)) {
                    maxv = Integer.parseInt(value); //FIND THE MAX VALUE IN THE ARRAY
                }
            }
            order = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                arr1.add(Integer.parseInt(input[i]));
                order[i] = Integer.parseInt(input[i]);
                mm.Jugs.put(Integer.parseInt(input[i]), 0);
            }
            Arrays.sort(order);

            target = Integer.parseInt(in.readLine());       //put the second line to TARGET
        } catch (IOException e) {
        }





        run(order,target,mm.Jugs);

    }
    /**
     *
     * Drive method
     *
     *
     * @param order：Array of cups sorted by capacity from small to large
     * @param target：target water volume
     * @param Jugs：
     *
     * @return: ans
     *
     *
     */
    public static int run (int[] order,int target,HashMap Jugs) {
        int pre = 0;
        int temp;

        ArrayList<Integer> arr1 = new ArrayList<Integer>();
        //Main mm = new Main();

        for (int i = 0; i < order.length; i++) {
            arr1.add(order[i]);

        }

        int jg = gcdd(arr1);
        if (target % jg != 0) {
            System.out.println("Steps______>" + "-1");
            System.out.println("For those input thses is no answer for this question");
            return -1;
        }
        //reduce the amount of the target
        for (int j = order.length -1; j >= 0; j--){
            temp = target % order[j];
            if (j - 1 >= 0){
                if (order[j - 1] > getSum(j-1,order) && temp >= order[j - 1]){
                    //If the remainder is larger than all the previous cups combined, pour water directly from this cup.
                    int temp1 = temp % order[j - 1];
                    if (temp1 < order[0]){
                        break;
                    }

                    else{
                        pre += target/order[j] * 2;
                        target = temp;


                    }
                }else{
                    if (temp < order[0]){
                        break;
                    }

                    else{
                        pre += target/order[j] * 2;
                        target = temp;
                        break;

                    }
                }
            }else{
                break;
            }

        }




        int ans = astar(target,order,Jugs);
        if(ans != 0){
            ans = pre + ans;
            System.out.println("Steps______>"+ans);
            return ans;
        }

        return -1;
    }
    public static int getSum(int j, int[] order){
        int sum = 0;

        for (int i = 0; i < j; i++){
            sum += order[i];
        }
        return sum;
    }



    /**
     *
     * The current target water volume is divided by the appropriate cup capacity to obtain h.
     *
     * @param j：The currently expanded node.
     * @param order：Array of cups sorted by capacity from small to large
     * @param key1：Cup1
     * @param key2：If key2 == 0, then it is a single cup operation.
     *
     * @return: h
     *
     *
     */
    public static int heuristic (Node j , int[] order,int key1,int key2) {
//        int total = 0;
//        for (int i : j.map.values()) {
//            total += i;
//        }
        int total = j.t;
        if (j.map.get(key1) == 0 || j.map.get(key1) == key1){//When operating with a single cup,
            if (j.t >= key1 && key2 == 0){

                return total/key1;
                // if the current cup capacity is smaller than the target water volume
                // h is the current target water volume divided by the current cup capacity.
            }
        }else {
            //If there is water in the cup, then subtract the current amount of water in the cup from the target water amount,
// and divide by the capacity of the first cup that is less than the target water amount.
            if (j.t >= j.map.get(key1) && key2 == 0){
                total=j.t - j.map.get(key1);
                int index = order.length - 1;
                for (int i = order.length - 1; i >= 0; i--){
                    if (order[i] <= total){

                        index = i;
                        break;

                    }
                }
                int heuristic = total / order[index];
                return heuristic;
            }
        }
        if (key2 != 0){
            if (j.map.get(key1) == 0){
                if (j.t >= j.map.get(key2)){
                    total=j.t - j.map.get(key2);
                    int index = order.length - 1;
                    for (int i = order.length - 1; i >= 0; i--){
                        if (order[i] <= total){
                            //If two cups are poured into each other, if all key1 is poured into key2,
                            // then the target water volume is subtracted from the current water volume in key2,
                            //and divided by the first target water volume minus the current key2 water volume is smaller than the cup capacity.
                            index = i;
                            break;

                        }
                    }
                    int heuristic = total / order[index];
                    return heuristic;
                }
            }
            if (j.map.get(key2) == key2){
                if (j.t >= j.map.get(key1)){
                    total=j.t - j.map.get(key1);
                    int index = order.length - 1;
                    for (int i = order.length - 1; i >= 0; i--){
                        if (order[i] <= total){
//If two cups are poured into each other, if the key1 part is poured into key2,
// then the target water volume is subtracted from the current water volume in key1,
// and divided by the first target water volume minus the current water volume in key1 than the smaller cup capacity.
                            index = i;
                            break;

                        }
                    }
                    int heuristic = total / order[index];
                    return heuristic;
                }
            }
        }
        int index = order.length - 1;
        for (int i = order.length - 1; i >= 0; i--){
            if (order[i] <= total){
                //if (order[i] ==total){
                index = i;
                break;
                //}
//                else{
//                    if (i >0){
//                        index = i - 1;
//                    }
//                    else{
//                        index = i;
//                    }
//
//                    break;
//                }

            }
        }
        int heuristic = total / order[index];



        return heuristic;  //THE ARIM THE SUM OF ALL THE WATER IN THE JUGS
    }

    /**
     * A* algorithm
     * Find the cup with the smallest f from the openlist to expand the new nodes.
     * Find the target node when t=0
     *
     * @param target：Target water volume after preprocess
     * @param order：Sort the cups from smallest to largest
     * @param jugs：Capacity and water volume of each cup
     * @return nodei.g: Length of optimal path
     */
    public static int astar (int target, int[] order, HashMap jugs){
        LinkedList<Node> openlist = new LinkedList<Node>();
        LinkedList<Node> closelist = new LinkedList<Node>();
        //Generate initial node root and add it to openlist.
        Node root = new Node(target,target,jugs);
        openlist.add(root);
        //int counter = 0;
        while (openlist.size()!=0){

            //System.out.println(counter);
            //counter ++;
            Node nodei = null;
            int temp = openlist.get(0).f;
            int k=0;
            //Find the cup with the smallest f from the openlist.
            for (int i = 0; i< openlist.size();i++){

                int now = openlist.get(i).f;
                if(temp>=now){
                    temp=now;
                    nodei = openlist.get(i);
                    k = i;
                }
            }
            openlist.remove(k);

            //Find the target node, output the path
            if(nodei.t==0){
                Node cur = nodei;
                while(cur.father != null){
                    System.out.println(cur.map.entrySet());
                    cur = cur.father;
                }
                return nodei.g;

            }
            //Add the selected node to the closelist
            closelist.add(nodei);
            Node finalNodei = nodei;
            //Pick one cup to change the amount of water
            nodei.map.forEach((key, value) -> {

                //System.out.println(key);
                //HashMap <Integer, Integer> mapp = new HashMap<Integer, Integer>();
                HashMap <Integer, Integer> mapp = (HashMap <Integer, Integer>) finalNodei.map.clone();
                Node nodej = new Node(finalNodei, finalNodei.g,mapp, finalNodei.t );
                //A single jug is empty or full，call expendOneCup1() to expand the new node
                if(value == 0||value == key){
                    expendOneCup1(nodej,key);
                    //if (j.t >= 0) {
                    nodej.h = heuristic(nodej, order,key,0);
                    nodej.f = nodej.g + nodej.h;
                    addlist(openlist, closelist, nodej);
                    //}
                }
                //When a single cup has water but is not full,
                //call expendOneCup2( ) to fill the cup,
                //and call expendOneCup3( ) to empty the cup.
                else {
                    HashMap <Integer, Integer> mapppp = (HashMap <Integer, Integer>) finalNodei.map.clone();
                    Node nodek = new Node(finalNodei, finalNodei.g,mapppp, finalNodei.t);
                    expendOneCup2(nodej,key);
                    expendOneCup3(nodek,key);
                    //if (jj.t >= 0) {
                    nodek.h = heuristic(nodek, order,key,0);
                    nodek.f = nodek.g + nodek.h;
                    addlist(openlist, closelist, nodek);
                    //}
                    nodej.h = heuristic(nodej,order,key,0);
                    nodej.f = nodej.g + nodej.h;
                    addlist(openlist,closelist,nodej);

                }
            });
            //Adjust the water volume of the two water cups to expand the new node
            for (int i = 0;i< order.length;i++){
                for (int j = i+1; j< order.length;j++){
                    HashMap <Integer, Integer> mapp = (HashMap <Integer, Integer>) nodei.map.clone();
                    Node nodel = new Node(nodei, nodei.g,mapp, nodei.t );
//                    if(jjj.map.get(order[i])!= 0 ||  jjj.map.get(order[j])!= 0 ){
//                        if (jjj.map.get(order[i])== order[i] &&  jjj.map.get(order[j])== order[j]){
//                            continue;
//                        }else{
//                            expend3(jjj,order[i],order[j]);
//                            jjj.h = heuristic(jjj);
//                            jjj.f = jjj.g + jjj.h;
//                            addlist(openlist,closelist,jjj);
//                        }
//                    }
                    //j cup pours water into i cup
                    if (nodei.map.get(order[j])!= 0){
                        HashMap <Integer, Integer> mappp = (HashMap <Integer, Integer>) nodei.map.clone();
                        Node nodem = new Node(nodei, nodei.g,mappp, nodei.t );
                        if (nodem.map.get(order[i])== order[i] &&  nodem.map.get(order[j])== order[j]){
                            continue;
                        }
                        else if(nodem.map.get(order[i])< order[i]){
                            expendTwoCups(nodem,order[j],order[i]);
                            nodem.h = heuristic(nodem,order,order[j],order[i]);
                            nodem.f = nodem.g + nodem.h;
                            addlist(openlist,closelist,nodem);
                        }
                    }
                    //i cup pours water into j cup
                    if (nodei.map.get(order[i])!= 0) {
                        if (nodel.map.get(order[i]) == order[i] && nodel.map.get(order[j]) == order[j]) {
                            continue;
                        } else if (nodel.map.get(order[j]) < order[j]) {
                            expendTwoCups(nodel, order[i], order[j]);
                            nodel.h = heuristic(nodel,order,order[i], order[j]);
                            nodel.f = nodel.g + nodel.h;
                            addlist(openlist, closelist, nodel);
                        }
                    }
                }



            }

        }

        return 0;
    }
    /**
     *
     * Empty or fill a cup.
     *
     * @param node：The currently expanded node.
     * @param key：Cup
     *
     *
     */
    public static void expendOneCup1(Node node,int key) {
        if (node.map.get(key) == 0) {
            node.map.put(key,key);
            //node.g += key;

        }
        else if(key ==  node.map.get(key)){

            node.map.put(key,0);
            // node.g += key;

            if (node.t >= key){
                node.t = node.t - key;
            }

        }

    }
    /**
     *
     * Fill a cup that has water but is not full.
     *
     * @param node：The currently expanded node.
     * @param key：Cup
     *
     *
     */
    public static void expendOneCup2 (Node node,int key) {
        //node.g += key - node.map.get(key);
        node.map.put(key,key);


    }
    /**
     *
     * Empty a cup that has water in itself but is not full.
     *
     * @param node：The currently expanded node.
     * @param key：Cup
     *
     *
     */
    public static void expendOneCup3(Node node,int key) {

        //一半倒空
        //node.g += node.map.get(key);

        if (node.t >= node.map.get(key)){
            node.t = node.t - node.map.get(key);
        }
        node.map.put(key,0);

    }

    /**
     *
     * Two cups pour water into each other.
     * cup1 pours water into cup2.
     * @param node：The currently expanded node.
     * @param key1：Cup1
     * @param key2：Cup2
     *
     *
     */
    public static void expendTwoCups(Node node,int key1,int key2) {

        if(node.map.get(key2) +node.map.get(key1) <= key2 ){
            //node.g += key1;
            node.map.put(key2,node.map.get(key2) +node.map.get(key1));
            node.map.put(key1, 0);
        }
        else{
            //node.g += key2 - node.map.get(key2);
            node.map.put(key1, (node.map.get(key2) +node.map.get(key1)-key2));
            node.map.put(key2,key2 );
        }

    }

    /**
     *
     * Add node to openlist
     * @param list：openlist
     * @param closelist：closelist
     * @param node：The latest expanded node
     *
     */
    public static void addlist(LinkedList<Node> list, LinkedList<Node> closelist, Node node){//(0,0)加入open中
        //Each cup water level status in this node exists in closelist and t is also equal
        for(Node closenode:closelist){
            if(node.map.equals(closenode.map) && node.t == closenode.t) return;
        }
        //Traverse each node in the list node
        for(int i = 0; i < list.size(); i++) {
            //If the current node represents a better path, replace the node in the openlist
            if(node.map.equals(list.get(i).map) && node.t == list.get(i).t) {
                if(node.f < list.get(i).f){
                    list.set(i, node);
                    return;
                }
                else {
                    return;
                }
            }
        }
        //If not in closelist or openlist, add node to openlist
        list.add(node);
    }


    public static int gcdd(ArrayList<Integer> number) {
        while(number.size()>1) {//If there is only one number left, it is the last calculated greatest common factor
            int temp = gcd(number.get(0), number.get(1));//Loop out the first two numbers and calculate their greatest common factor
   //The second number to be removed is the number that is now at position 0
            number.remove(0);
            number.remove(0);
            //Add the newly calculated greatest common factor to the beginning of the ArrayList
            number.add(0,temp);
        }
        return number.get(0);//Returns the greatest common factor of this set of numbers
    }

    public static int gcd(int m,int n)
    {   if(n == 0){
        return m;
    }
        int r = m%n;
        return gcd(n,r);
    }


}



