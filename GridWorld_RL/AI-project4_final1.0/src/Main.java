import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

import com.alibaba.fastjson.*;
import org.apache.commons.math3.util.Pair;

public class Main {

    public static void main(String[] args) throws Exception {

        HashMap<Pair<Pair<Integer, Integer>, String>, Double> Q = new HashMap<Pair<Pair<Integer, Integer>, String>, Double>();
        //Robot robot;

        Requests requests = new Requests();
//
        HashMap<String, String> params3 = new HashMap<String, String>();
        params3.put("type", "enter");
        params3.put("worldId", "4");
        params3.put("teamId", "1296");
        String result3 = requests.post(params3);
        JSONObject parsedResult3 = JSON.parseObject(result3);
        if (!parsedResult3.getString("code").equals("OK")) {
            throw new Exception("ERROR Enter: " + result3);
        } else {
//            System.out.println("Success Enter world:" + result3);
//            String states = parsedResult3.getString("state");
//            String[] state = states.split(":");
//            int x, y;
//            x = Integer.parseInt(state[0]);
//            y = Integer.parseInt(state[2]);
            Q = new HashMap<Pair<Pair<Integer, Integer>, String>, Double>();

            Robot robot = new Robot(0, 0, Q);
            System.out.println("initial " + robot.Q);
            int count = 0;
            int rounds = 2;
            while (count < rounds) {
                robot.Execute(1, count);

                if (count == rounds - 1) break;

                HashMap<String, String> params4 = new HashMap<String, String>();
                params4.put("type", "enter");
                params4.put("worldId", "4");
                params4.put("teamId", "1296");
                String result4 = requests.post(params4);
                JSONObject parsedResult4 = JSON.parseObject(result4);
                if (!parsedResult4.getString("code").equals("OK")) {
                    throw new Exception("ERROR Enter: " + result4);
                } else {
//            System.out.println("Success Enter world:" + result3);
//            String states = parsedResult3.getString("state");
//            String[] state = states.split(":");
//            int x, y;
//            x = Integer.parseInt(state[0]);
//            y = Integer.parseInt(state[2]);

                    robot.x = 0;
                    robot.y = 0;
                    System.out.println("initial " + robot.Q);
                    //robot.Execute(1);
                    System.out.println("latest " + robot.Q);
                }

                count++;
            }
            System.out.println("latest " + robot.Q);
        }

//        HashMap<String, String> params4 = new HashMap<String, String>();
//        params4.put("type", "score");
//        params4.put("teamId", Integer.toString(1296));
//        String result4 = requests.get(params4);
//        JSONObject parsedResult4 = JSON.parseObject(result4);
//        if (!parsedResult4.getString("code").equals("OK")) {
//            throw new Exception("ERROR Enter: " + result4);
//        } else {
//            System.out.println("Success Enter world:" + result4);
//        }

//        HashMap<String, String> params4 = new HashMap<String, String>();
//        params4.put("type", "location");
//        params4.put("teamId", Integer.toString(1296));
//        String result4 = requests.get(params4);
//        JSONObject parsedResult4 = JSON.parseObject(result4);
//        if (!parsedResult4.getString("code").equals("OK")) {
//            throw new Exception("ERROR Enter: " + result4);
//        } else {
//            System.out.println("Success Enter world:" + result4);
//            String states = parsedResult4.getString("state");
//            String[] state = states.split(":");
////            if(Objects.equals(states, "")) {
////                state[0] = "0";
////                state[1] = "0";
////                //throw new Exception("state is null!");
////            }
//            Q = new HashMap<Pair<Pair<Integer,Integer>, String>, Double>();
//            int x = 0, y = 0;
//            x = Integer.parseInt(state[0]);
//            y = Integer.parseInt(state[1]);
//            Robot robot = new Robot(x,y, Q);
//            System.out.println("initial " + robot.Q);
//            int count = 0;
//            int rounds = 2;
//            while(count < rounds){
//                robot.Execute(1, count);
//                if(count == rounds - 1) break;
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
//                    robot.x = 0;
//                    robot.y = 0;
//                    System.out.println("initial " + robot.Q);
//                    //robot.Execute(1);
//                    System.out.println("latest " + robot.Q);
//                }
//
//                count++;
//            }
//            //robot.Execute(1);
//            System.out.println("latest " + robot.Q);
//        }
//

//        HashMap<String, String> params4 = new HashMap<String, String>();
//        params4.put("type", "runs");
//        params4.put("teamId", Integer.toString(1296));
//        params4.put("count", Integer.toString(100));
//        String result4 = requests.get(params4);
//        JSONObject parsedResult4 = JSON.parseObject(result4);
//        if (!parsedResult4.getString("code").equals("OK")) {
//            throw new Exception("ERROR Enter: " + result4);
//        } else {
//            System.out.println("Success Enter world:" + result4);
//            String states = parsedResult4.getString("state");
//            if (Objects.equals(states, "")) {
//                throw new Exception("state is null!");
//            }
//        }


//            String[] state = states.split(":");
//            int x, y;
//            x = Integer.parseInt(state[0]);
//            y = Integer.parseInt(state[1]);
//            Robot robot = new Robot(x,y,Q);
//            System.out.println("initial " + robot.Q);
//            robot.Execute(1);
//            System.out.println("latest " + robot.Q);
//    }
//        //创建世界
//        HashMap<String, String> params4 = new HashMap<String, String>();
//        params4.put("teamId", "1296");
//        params4.put("otp", "5712768807");
//        String result4 = requests.get(params4);
//        JSONObject parsedResult4 = JSON.parseObject(result4);
//        if (!parsedResult4.getString("code").equals("OK")) {
//            throw new Exception("ERROR Enter: " + result4);
//        } else {
//            System.out.println("Success reset:" + result4);
//        }

        /*
        Scanner myObj = new Scanner(System.in);
        System.out.println("Have you already created a game? (Y/N)");
        String yn = myObj.nextLine();

        if (yn.toLowerCase().equals("y")) {

            System.out.println("Please enter the game id, board size, target and priority of our team (1 or 2) to join in (each element separated by space): ");
            String[] inputSplit = myObj.nextLine().split(" ");
            String gameId = inputSplit[0].trim();
            int boardSize = Integer.parseInt(inputSplit[1]), target = Integer.parseInt(inputSplit[2]), priority = Integer.parseInt(inputSplit[3]);
            if (priority == 1) {
                Logic logic = new Logic(Integer.parseInt(gameId), boardSize, target, "O", "X");
                logic.start();
            } else if (priority == 2) {
                Logic logic = new Logic(Integer.parseInt(gameId), boardSize, target, "X", "O");
                logic.start();
            }
        } else if (yn.toLowerCase().equals("n")) {
            System.out.println("Please enter the team id of opponent, board size, target and priority of our team (1 or 2) to create a game (each element separated by space): ");
            String[] inputSplit = myObj.nextLine().split(" ");
            String opponentTeamId = inputSplit[0].trim();
            int boardSize = Integer.parseInt(inputSplit[1]), target = Integer.parseInt(inputSplit[2]), priority = Integer.parseInt(inputSplit[3]);
            int gameId = createGame(opponentTeamId, boardSize, target, priority);
            Requests requests = new Requests();

//            HashMap<String, String> params5 = new HashMap<String, String>();
//            params5.put("type", "member");
//            params5.put("teamId", Integer.toString(1291));
//            params5.put("usrId", Integer.toString(1110));
//            String result5 = requests.post(params5);
//            JSONObject parsedResult5 = JSON.parseObject(result5);
//            if (!parsedResult5.getString("code").equals("OK")) {
//                throw new Exception("ERROR in add member: " + result5);
//            } else {
//                System.out.println("Success add member" + result5);
//            }

//            HashMap<String, String> params2 = new HashMap<String, String>();
//            params2.put("type", "team");
//            params2.put("teamId", Integer.toString(1291));
//            //Requests requests = new Requests();
//            String result2 = requests.get(params2);
//
//            JSONObject parsedResult2 = JSON.parseObject(result2);
//            if (!parsedResult2.getString("code").contains("OK")) {
//                throw new Exception("ERROR: GET team member: " + result2);
//            } else {
//                System.out.println("GET team member success" + result2);
//            }

            if (priority == 1) {
                Logic game = new Logic(gameId, boardSize, target, "O", "X");
                game.start();
            } else if (priority == 2) {
                Logic game = new Logic(gameId, boardSize, target, "X", "O");
                game.start();
            }

         */
    }

//    public static int createGame(String opponentTeamId, int boardSize, int target, int priority) throws Exception {
//
//
//        Requests requests = new Requests();
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("type", "game");
//        if (priority == 1) {
//            // Move first
//            params.put("teamId1", Integer.toString(1296));
//            params.put("teamId2", opponentTeamId);
//        } else if (priority == 2) {
//            // Move second
//            params.put("teamId1", opponentTeamId);
//            params.put("teamId2", Integer.toString(1296));
//        }
//        params.put("gameType", "TTT");
//        params.put("boardSize", Integer.toString(boardSize));
//        params.put("target", Integer.toString(target));
//        String result = requests.post(params);
//
//        JSONObject parsedResult = JSON.parseObject(result);
//        if (!parsedResult.getString("code").equals("OK")) {
//            throw new Exception("ERROR in game creation: " + result);
//        } else {
//            System.out.println("New game " + Integer.toString(parsedResult.getInteger("gameId")));
//            return parsedResult.getInteger("gameId");
//        }
//    }
}
