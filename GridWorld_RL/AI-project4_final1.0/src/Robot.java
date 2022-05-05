import java.math.BigDecimal;
import java.util.*;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
import java.io.IOException;
import java.io.*;
import java.lang.Math;
import org.apache.commons.math3.util.Pair;
import com.alibaba.fastjson.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Robot {
    public LinkedList<Pair<Pair<Integer,Integer>, String>> state;
    public String[] actions = {"up", "down", "left", "right"};
    public Gridworld GridWorld;
    public boolean is_end;
    public double lost_way;
    public double learning_rate;
    public double discount;
    private int TEAM_ID = 1296;
    public int x;
    public int y;
    private Requests requests;
    public HashMap<Pair<Pair<Integer,Integer>, String>, Double> Q;

    Pair<Pair<Integer,Integer>, String> pairtest ;

    public Robot(int x, int y, HashMap<Pair<Pair<Integer,Integer>, String>, Double> Q){
        this.state = new LinkedList<Pair<Pair<Integer,Integer>, String>>();
        this.x = x;
        this.y = y;
        this.GridWorld = new Gridworld(x, y);
        this.is_end = this.GridWorld.is_end;
        this.lost_way = 0.2;
        this.learning_rate = 0.3;
        this.discount = 0.9;
        this.requests = new Requests();
        this.Q = Q;
        for (int i = 0; i < this.GridWorld.row; i++){
            for (int j  = 0; j < this.GridWorld.column; j++){
                Pair<Integer, Integer> pair1 = new Pair<>(i, j);
                for (String act : this.actions){
                    Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, act);
                    if (!this.Q.containsKey(pair2)){
                        this.Q.put(pair2, 0.0);
                    }
                }
            }
        }
    }

    public String choose_action(){
        double next_max = Integer.MIN_VALUE;//这改了
        String action = "";
        Random r = new Random();
        double tmp = r.nextDouble();
        if (tmp <= this.learning_rate){
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2,3};
            dist = new EnumeratedIntegerDistribution(num);
            int idx = dist.sample();
            action = this.actions[idx];
        }else {
            for (String act : this.actions){
                int[] current_state = new int[2];
                current_state[0] = this.GridWorld.state[0];
                current_state[1] = this.GridWorld.state[1];
                Pair<Integer, Integer> pair1 = new Pair<>(current_state[0], current_state[1]);
                Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, act);
                //double next1 = reward;
                double next = this.Q.get(pair2);
                if (next >= next_max && !state.contains(pair2)){
                    action = act;
                    next_max = next;
                }

            }
        }
        if (action == ""){
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2,3};
            dist = new EnumeratedIntegerDistribution(num);
            int idx = dist.sample();
            action = this.actions[idx];
        }
        return action;
    }
    public String choose_action0(){
        double next_max = Integer.MIN_VALUE;//这改了
        String action = "";
        Random r = new Random();
        double tmp = r.nextDouble();
        if (tmp <= this.learning_rate){
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2,3};
            dist = new EnumeratedIntegerDistribution(num);
            int idx = dist.sample();
            action = this.actions[idx];
        }else {
            for (String act : this.actions){
                int[] current_state = new int[2];
                current_state[0] = this.GridWorld.state[0];
                current_state[1] = this.GridWorld.state[1];
                Pair<Integer, Integer> pair1 = new Pair<>(current_state[0], current_state[1]);
                Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, act);
                //double next1 = reward;
                double next = this.Q.get(pair2);
                if (next >= next_max && !state.contains(pair2)){
                    action = act;
                    next_max = next;
                }
            }
        }
        if (action == ""){
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2,3};
            dist = new EnumeratedIntegerDistribution(num);
            int idx = dist.sample();
            action = this.actions[idx];
        }
        return action;
    }

    public Gridworld operation(String action, int x, int y){
        int[] position = new int[2];
        position[0] = x;
        position[1] = y;
        //position = this.GridWorld.next_position(action);
        return new Gridworld(position[0],position[1]);
    }

    public void reassociation (){
        this.state = new LinkedList<Pair<Pair<Integer,Integer>, String>>();
        this.GridWorld = new Gridworld(this.x, this.y);
        this.is_end = this.GridWorld.is_end;
    }

    public void Execute(int rounds, int count) throws Exception {
        int i = 0;
        double end_reward = 0.0;
        while (i < rounds){
            if (this.GridWorld.is_end){
                System.out.println("reward change to end_reward:"+ end_reward);
                double reward = end_reward;

                for (String act : this.actions){
                    Pair<Integer, Integer> pair1 = new Pair<>(this.GridWorld.state[0], this.GridWorld.state[1]);
                    Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, act);
                    this.Q.replace(pair2, reward);
                }
                System.out.println("This is end "+reward);

//                try {
                    for (int j = this.state.size() - 2; j >= 0; j--) {
                        pairtest = state.get(j);
                        double current_val_Q = this.Q.get(this.state.get(j));
                        reward = current_val_Q + this.lost_way * (this.discount * reward - current_val_Q);
//                        String tmp = String.format("%f", reward);
                        this.Q.replace(this.state.get(j), reward);
                    }
//                }
//                catch (Exception e){
//                    System.out.println("Find error on :"+ pairtest.toString());
//                }
                this.reassociation();
                i++;
//                HashMap<String, String> params3 = new HashMap<String, String>();
//                params3.put("type", "enter");
//                params3.put("worldId", "1");
//                params3.put("teamId", "1296");
//                String result3 = requests.post(params3);
//                JSONObject parsedResult3 = JSON.parseObject(result3);
//                if (!parsedResult3.getString("code").equals("OK")) {
//                    throw new Exception("ERROR Enter: " + result3);
//                } else {
////            System.out.println("Success Enter world:" + result3);
////            String states = parsedResult3.getString("state");
////            String[] state = states.split(":");
////            int x, y;
////            x = Integer.parseInt(state[0]);
////            y = Integer.parseInt(state[2]);
//
//                    Robot robot = new Robot(0, 0, this.Q);
//                    System.out.println("initial " + robot.Q);
//                    robot.Execute(1);
//                    System.out.println("latest " + robot.Q);
//                }
            }else{
                double reward;
                String action;
                if (count != 0){
                    action = this.choose_action();
                }else{
                    action = this.choose_action0();
                }

                String move = "";

                // Make a move
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("type", "move");
                params.put("teamId", Integer.toString(this.TEAM_ID));
                if(action.equals("up")){
                    move = "N";
                }
                else if(action.equals("down")){
                    move = "S";
                }
                else if(action.equals("left")){
                    move = "W";
                }
                else if(action.equals("right")){
                    move = "E";
                }
                params.put("move", move);
                params.put("worldId", Integer.toString(4));
                String result = this.requests.post(params);

                JSONObject parsedResult = JSON.parseObject(result);
                int x, y;
                if (!parsedResult.getString("code").contains("OK")) {
                    throw new Exception("ERROR: making move: " + result);
                } else {
                    System.out.println("Success Making move!" + result);
                    System.out.println("i = " + count);
                    String newstates = parsedResult.getString("newState");
                    end_reward = Double.parseDouble(parsedResult.getString("reward"));
                    if(newstates != null) {
                        JSONObject temp = JSON.parseObject(parsedResult.getString("newState").toString());
                        x = Integer.parseInt(temp.get("x").toString());
                        y = Integer.parseInt(temp.get("y").toString());
                        reward = Double.parseDouble(parsedResult.getString("reward"));
                        Pair<Integer, Integer> pair1 = new Pair<>(this.GridWorld.state[0], this.GridWorld.state[1]);
                        Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, action);
                        Q.replace(pair2, reward);
                        this.state.add(pair2);
                        System.out.println("current state {" + this.GridWorld.state[0] + ", " + this.GridWorld.state[1] + "} action is " + action);
                        this.GridWorld = this.operation(action, y, x);
                        //throw new Exception("state is null!");
                    }
//                    JSONObject temp = JSON.parseObject(parsedResult.getString("newState").toString());
                    //String states = parsedResult4.getString("state");
//            if(Objects.equals(states, "")) {
//                throw new Exception("state is null!");
//            }
//                    x = Integer.parseInt(temp.get("x").toString());
//                    y = Integer.parseInt(temp.get("y").toString());
                    //String[] newstate = newstates.split(":");
                    //x = Integer.parseInt(newstate[0]);
                    //y = Integer.parseInt(newstate[1]);

                    if(parsedResult.getString("newState") == null) {

                        reward = Double.parseDouble(parsedResult.getString("reward"));
                        Pair<Integer, Integer> pair1 = new Pair<>(this.GridWorld.state[0], this.GridWorld.state[1]);
                        Pair<Pair<Integer,Integer>, String> pair2 = new Pair<>(pair1, action);
                        Q.replace(pair2, reward);
                        this.state.add(pair2);
                        System.out.println("current state {" + this.GridWorld.state[0] + ", " + this.GridWorld.state[1] + "} action is " + action);
//                        this.GridWorld = this.operation(action, y, x);
                        this.GridWorld.is_end = true;
                    }
                    else{
                        this.GridWorld.is_end = false;
                    }
                }


                //this.GridWorld = this.operation(action, x, y);
                //TimeUnit.SECONDS.sleep(15);

//                this.GridWorld.check_end();
//                System.out.println("next state " + this.GridWorld.state[0] + ", " + this.GridWorld.state[1]);
//                this.is_end = this.GridWorld.is_end;
            }
        }
    }
}

