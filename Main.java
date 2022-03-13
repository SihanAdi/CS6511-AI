package Tile_Placement;
import java.io.*;
import java.util.*;
import java.util.LinkedList;


public class Main {

    public static void main(String[] args){
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();

        try{
            BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/Tile_Placement/Landscape.txt"));
            BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/Tile_Placement/Tiles.txt"));
            BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/Tile_Placement/Targets.txt"));
            String til_str, tar_str, ls_row;

            //input landscape
            LinkedList<String> ls_list = new LinkedList<>();
            while ((ls_row = ls.readLine()) != null){
                ls_list.add(ls_row);//get each of rows
            }
            String s = ls_list.get(0);
            int col = 0;
            if (s.length() > 20){
                col = 20;
            }else{
                col = 10;
            }
            landscape = new int[ls_list.size()][col];
            int count = 0;
            for (String str : ls_list){

                int i = 0;
                int index;
                for (i = 0, index = 0; i < col && index < str.length(); i++, index += 2){
                    if (str.charAt(index) != ' '){
                        landscape[count][i] = Integer.valueOf(str.charAt(index) - '0');
                    }else{
                        landscape[count][i] = 0;//0 represent space
                    }

                }
                while (i < col){
                    landscape[count][i] = 0;
                    i++;
                }
                count++;
            }

            //input tiles
            String til_str1 = til.readLine();
            til_str = til_str1.substring(1,til_str1.length() - 1);



            String[] input_til = til_str.split(",");
            for (int i = 0; i < input_til.length; i++){
                if (i == 0){
                    String[] tmp = input_til[i].split("=");
                    tiles.put(tmp[0], Integer.valueOf(tmp[1]));
                }else{
                    String s2 = input_til[i].substring(1);
                    String[] tmp = s2.split("=");
                    tiles.put(tmp[0], Integer.valueOf(tmp[1]));
                }

            }

            //input targets
            LinkedList<String> tar_list = new LinkedList<>();
            while ((tar_str = tar.readLine()) != null){
                tar_list.add(tar_str);//get each of rows
            }
            for (String str : tar_list){
                String[] tmp = str.split(":");
                targets.put(Integer.valueOf(tmp[0]), Integer.valueOf(tmp[1]));
            }

            System.out.println(targets);
            for (int i = 0; i < ls_list.size(); i++){
                for (int j = 0; j < col; j++){
                    System.out.println(landscape[i][j]);
                }
            }

            System.out.println(tiles);

        } catch (IOException e) {
        }

        generate_variables_domain(landscape, tiles, targets);
    }

    //initial variable and domain
    public static boolean generate_variables_domain (int[][] landscape, HashMap<String, Integer> tiles, HashMap<Integer, Integer> targets){
//        int[][] var = new int[landscape.length][landscape[0].length];//initial variable
//        System.out.println(var[0].length);

        //define variable
        //list: index i represents variable xi
        LinkedList<LinkedList<HashMap<Integer, Integer>>> var = new LinkedList<LinkedList<HashMap<Integer, Integer>>>();

        int num = (landscape.length / 4) * (landscape[0].length / 4);
        int num1 = landscape.length / 4;
        int col = 0;
        int row = 0;

        //define the domain of variable
        for (int k = 0; k < num; k++){
            if (k % num1 == 0 && k != 0){
                col = 0;
                row += 4;
            }else{
                if (k != 0) {
                    col += 4;
                }
            }

            LinkedList<HashMap<Integer, Integer>> var_x = new LinkedList<HashMap<Integer, Integer>>();
            //the index of list var_x represents the kind of tiles,
            // i = 2 ->El,
            // i = 1 -> outer boundary,
            // i = 0 -> full_block

            int number_1 = 0;
            int number_2 = 0;
            int number_3 = 0;
            int number_4 = 0;


            //define domain_outer boundary
            for (int i = row + 1; i < row + 3; i++){
                for (int j = col + 1; j < col + 3; j++){
                    switch (landscape[i][j])
                    {
                        case 1 :
                            number_1 += 1;
                            break;
                        case 2 :
                            number_2 += 1;
                            break;
                        case 3 :
                            number_3 += 1;
                            break;
                        case 4 :
                            number_4 += 1;
                            break;
                    }
                }
            }
            HashMap<Integer, Integer> domain_2 = new HashMap<Integer, Integer>();
            domain_2.put(1, number_1);
            domain_2.put(2, number_2);
            domain_2.put(3, number_3);
            domain_2.put(4, number_4);
            domain_2.put(5, 1);//represent OL

            var_x.addLast(domain_2);
            int n1 = number_1;
            int n2 = number_2;
            int n3 = number_3;
            int n4 = number_4;


            //define four types of EL

            //define domain_EL_top_right
            number_1 = n1;
            number_2 = n2;
            number_3 = n3;
            number_4 = n4;
            for (int i = row + 1; i < row + 4; i++){
//                for (int j = col + 1; j < col + 4; j++){
                    switch (landscape[i][col + 3])
                    {
                        case 1 :
                            number_1 += 1;
                            break;
                        case 2 :
                            number_2 += 1;
                            break;
                        case 3 :
                            number_3 += 1;
                            break;
                        case 4 :
                            number_4 += 1;
                            break;
                    }
//                }
            }
            for (int j = col + 1; j < col + 3; j++){
                switch (landscape[row + 3][j])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
            }
            HashMap<Integer, Integer> domain_1_tr = new HashMap<Integer, Integer>();
            domain_1_tr.put(1, number_1);
            domain_1_tr.put(2, number_2);
            domain_1_tr.put(3, number_3);
            domain_1_tr.put(4, number_4);
            domain_1_tr.put(5, 2);//represent EL
            var_x.add(domain_1_tr);


            //define domain_EL_top_left
            number_1 = n1;
            number_2 = n2;
            number_3 = n3;
            number_4 = n4;
            for (int i = row + 1; i < row + 4; i++){
//                for (int j = col + 1; j < col + 4; j++){
                switch (landscape[i][col])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
//                }
            }
            for (int j = col + 1; j < col + 3; j++){
                switch (landscape[row + 3][j])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
            }
            HashMap<Integer, Integer> domain_1_tl = new HashMap<Integer, Integer>();
            domain_1_tl.put(1, number_1);
            domain_1_tl.put(2, number_2);
            domain_1_tl.put(3, number_3);
            domain_1_tl.put(4, number_4);
            domain_1_tl.put(5, 2);
            var_x.add(domain_1_tl);


            //define domain_EL_bottom_left
            number_1 = n1;
            number_2 = n2;
            number_3 = n3;
            number_4 = n4;
            for (int i = row; i < row + 3; i++){
//                for (int j = col + 1; j < col + 4; j++){
                switch (landscape[i][col])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
//                }
            }
            for (int j = col + 1; j < col + 3; j++){
                switch (landscape[row][j])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
            }
            HashMap<Integer, Integer> domain_1_bl = new HashMap<Integer, Integer>();
            domain_1_bl.put(1, number_1);
            domain_1_bl.put(2, number_2);
            domain_1_bl.put(3, number_3);
            domain_1_bl.put(4, number_4);
            domain_1_bl.put(5, 2);
            var_x.add(domain_1_bl);


            //define domain_EL_bottom_right
            number_1 = n1;
            number_2 = n2;
            number_3 = n3;
            number_4 = n4;
            for (int i = row; i < row + 3; i++){
//                for (int j = col + 1; j < col + 4; j++){
                switch (landscape[i][col + 3])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
//                }
            }
            for (int j = col + 1; j < col + 3; j++){
                switch (landscape[row][j])
                {
                    case 1 :
                        number_1 += 1;
                        break;
                    case 2 :
                        number_2 += 1;
                        break;
                    case 3 :
                        number_3 += 1;
                        break;
                    case 4 :
                        number_4 += 1;
                        break;
                }
            }
            HashMap<Integer, Integer> domain_1_br = new HashMap<Integer, Integer>();
            domain_1_br.put(1, number_1);
            domain_1_br.put(2, number_2);
            domain_1_br.put(3, number_3);
            domain_1_br.put(4, number_4);
            domain_1_br.put(5, 2);
            var_x.add(domain_1_br);


            //define domain_full_block
            HashMap<Integer, Integer> domain_3 = new HashMap<Integer, Integer>();
            domain_3.put(1, 0);
            domain_3.put(2, 0);
            domain_3.put(3, 0);
            domain_3.put(4, 0);
            domain_3.put(5,0);
            var_x.addLast(domain_3);

            var.add(var_x);
        }
        System.out.println(var);

        //init tiles
        HashMap<String, Integer> tmp_tiles = new HashMap<>();
        tmp_tiles.put("OUTER_BOUNDARY", 0);
        tmp_tiles.put("EL_SHAPE", 0);
        tmp_tiles.put("FULL_BLOCK", 0);

        System.out.println(tmp_tiles);

        //init targets
        HashMap<Integer, Integer> tmp_targets = new HashMap<>();
        tmp_targets.put(1, 0);
        tmp_targets.put(2, 0);
        tmp_targets.put(3, 0);
        tmp_targets.put(4, 0);
        System.out.println(tmp_targets);

        //record access var
        int[] visited_var = new int[var.size()];
//        for (int i = 0; i < visited.length; i++){
//            System.out.println(visited[i]);
//        }

        if(backtracking(var, landscape, tiles, targets, tmp_tiles, tmp_targets, visited_var)){
            System.out.println("true");
            return true;
        }
        return false;
    }





    public static boolean backtracking (LinkedList<LinkedList<HashMap<Integer, Integer>>> var, int[][] landscape,
                                     HashMap<String, Integer> tiles, HashMap<Integer, Integer> targets,
                                     HashMap<String, Integer> tmp_tiles, HashMap<Integer, Integer> tmp_targets,
                                        int[] visited_var){
        //Is assignment already completed

        boolean assignment = true;

        //check targets
        for (Integer i : targets.keySet()){
            if (targets.get(i) != tmp_targets.get(i)){
                assignment = false;
                break;
            }
        }

        //check tiles
        if (assignment == true){

            if (tiles.get("FULL_BLOCK") != tmp_tiles.get("FULL_BLOCK")){
                assignment = false;

            }
            if (tiles.get("OUTER_BOUNDARY") != tmp_tiles.get("OUTER_BOUNDARY")){
                assignment = false;

            }
            if (tiles.get("EL_SHAPE") != tmp_tiles.get("EL_SHAPE")){
                assignment = false;
            }


        }
        //check assignment
        if (assignment == true){
            System.out.println("true");
            return true;
        }

        boolean OL_is_full = false;
        boolean EL_is_full = false;
        boolean FL_is_full = false;
        for (String str : tiles.keySet()){
            if (tiles.get(str) <= tmp_tiles.get(str)){
                if (str.equals("OUTER_BOUNDARY")){
                    OL_is_full = true;
                }
                if (str.equals("EL_SHAPE")){
                    EL_is_full = true;
                }
                if (str.equals("FULL_BLOCK")){
                    FL_is_full = true;
                }
            }
        }
        if (OL_is_full == true && EL_is_full == true && FL_is_full == true){
            for (Integer i : targets.keySet()){
                if (targets.get(i) != tmp_targets.get(i)){
                    assignment = false;
                    break;
                }
            }
            //check tiles
            if (assignment == true){
                if (tiles.get("FULL_BLOCK") != tmp_tiles.get("FULL_BLOCK")){
                    assignment = false;

                }
                if (tiles.get("OUTER_BOUNDARY") != tmp_tiles.get("OUTER_BOUNDARY")){
                    assignment = false;

                }
                if (tiles.get("EL_SHAPE") != tmp_tiles.get("EL_SHAPE")){
                    assignment = false;
                }
            }
            return assignment;
        }
        boolean is_1_full = false;
        boolean is_2_full = false;
        boolean is_3_full = false;
        boolean is_4_full = false;
        for (int i = 1; i < 5; i++){
            if (targets.get(i) == tmp_targets.get(i)){
                switch (i){
                    case 1:
                        is_1_full = true;
                        break;
                    case 2:
                        is_2_full = true;
                        break;
                    case 3:
                        is_3_full = true;
                        break;
                    case 4:
                        is_4_full = true;
                        break;
                }

            }
        }

        for (Integer i : targets.keySet()){
            if (targets.get(i) < tmp_targets.get(i)){
                return false;
            }
        }
        for (String str : tiles.keySet()){
            if (tiles.get(str) < tmp_tiles.get(str)){
                return false;
            }
        }

        LinkedList<LinkedList<HashMap<Integer, Integer>>> var_tmp = new LinkedList<LinkedList<HashMap<Integer, Integer>>>();
        for (int i = 0; i < var.size(); i++){
            LinkedList<HashMap<Integer, Integer>> list = new LinkedList<HashMap<Integer, Integer>>();
            for (int j = 0; j < var.get(i).size(); j++){
                HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                for (int t = 1; t <= var.get(i).get(j).size(); t++){
                    map.put(t, var.get(i).get(j).get(t));
                }
                list.add(map);
            }
            var_tmp.add(list);
        }

        int [] visited_var_tmp = new int[var.size()];
        //select unassigned var
        //Get the number of values for each variable by looking at its linked list length
        //Find the one with the least number and see if it has been visited;
        //MRV
        int min_var = 10;//Because each variable can have at most five values.
        int index_var = -1;
        for (int i = 0; i < var.size(); i++){
            if (visited_var[i] == 0){
                if (var.get(i).size() < min_var){
                        index_var = i;
                        min_var = var.get(i).size();
                }
            }
            visited_var_tmp[i] = visited_var[i];
        }
        if (index_var == -1) {
            return assignment;
        }

        //For the linked list of each variable, the position of 0 is OUTER_BOUNDARY,
        //and the remaining four positions are the four values of EL_SHAPE.

        LinkedList<HashMap<Integer, Integer>> value_list = (LinkedList<HashMap<Integer, Integer>>) var_tmp.get(index_var).clone();

        int[] visited_val = new int[value_list.size()];

//        //update var
//        LinkedList<LinkedList<HashMap<Integer, Integer>>> tmp_var = (LinkedList<LinkedList<HashMap<Integer, Integer>>>) var.clone();

        while (!value_list.isEmpty()){
            //LCV
            //The order in which the values of the variables are chosen,
            //with preference given to the values that leave neighbors with more choices.
            //In this problem, that is to choose the value with the least number of targets
            // and the one with the largest remaining number of tiles.
            int min_val = 17;//4*4 grid
//            int max_val = 0;
            int index_val = -1;


            for (int i = 0; i < value_list.size(); i++){
                int sum = 0;
                if (visited_val[i] != 0){
                    continue;
                }
                if (EL_is_full){
                    if (value_list.get(i).get(5) == 2){
                        continue;
                    }
                }
                if (OL_is_full){
                    if (value_list.get(i).get(5) == 1){
                        continue;
                    }
                }
                if (FL_is_full){
                    if (value_list.get(i).get(5) == 0){
                        continue;
                    }
                }
                if (is_1_full){
                    if (value_list.get(i).get(1) != 0){
                        continue;
                    }
                }
                if (is_2_full){
                    if (value_list.get(i).get(2) != 0){
                        continue;
                    }
                }if (is_3_full){
                    if (value_list.get(i).get(3) != 0){
                        continue;
                    }
                }if (is_4_full){
                    if (value_list.get(i).get(4) != 0){
                        continue;
                    }
                }
                for (Integer num : value_list.get(i).keySet()){
                    if (num != 5){
                        sum += value_list.get(i).get(num);
                    }

                }
                if (sum < min_val){
                    if (index_val != -1){
                        int tmp_index_val = -1;
                        int tmp_i = -1;
                        if (value_list.get(index_val).get(5) == 1){
                            tmp_index_val = tiles.get("OUTER_BOUNDARY") - tmp_tiles.get("OUTER_BOUNDARY");
                        }
                        else if (value_list.get(index_val).get(5) == 2){
                            tmp_index_val = tiles.get("EL_SHAPE") - tmp_tiles.get("EL_SHAPE");
                        }
                        else if (value_list.get(index_val).get(5) == 0){
                            tmp_index_val = tiles.get("FULL_BLOCK") - tmp_tiles.get("FULL_BLOCK");
                        }

                        if (value_list.get(i).get(5) == 1){
                            tmp_i = tiles.get("OUTER_BOUNDARY") - tmp_tiles.get("OUTER_BOUNDARY");
                        }
                        else if (value_list.get(i).get(5) == 2){
                            tmp_i = tiles.get("EL_SHAPE") - tmp_tiles.get("EL_SHAPE");
                        }
                        else if (value_list.get(i).get(5) == 0){
                            tmp_i = tiles.get("FULL_BLOCK") - tmp_tiles.get("FULL_BLOCK");
                        }
                        if (tmp_i >= tmp_index_val){
                            min_val = sum;
                            index_val = i;
                        }
                    }else{
                        min_val = sum;
                        index_val = i;
                    }

                }
//                if (sum > max_val){
//                    max_val = sum;
//                    index_val = i;
//                }
            }
            if (index_val == -1){
                return assignment;
            }else{
                visited_val[index_val] = 1;
            }
            // update targets and tiles
            // check if min_val is OUTER_BOUNDARY
            //update tiles

//            boolean is_OUTER_BOUNDARY = true;
//            if (value_list.get(index_val).get(5) == 1){
//                is_OUTER_BOUNDARY = false;
//            }
            HashMap<String, Integer> tmp_second_tiles = (HashMap<String, Integer>) tmp_tiles.clone();
            if (value_list.get(index_val).get(5) == 1){
                int tmp = tmp_second_tiles.get("OUTER_BOUNDARY");
                tmp_second_tiles.replace("OUTER_BOUNDARY", (tmp + 1));
            }
            else if (value_list.get(index_val).get(5) == 2){
                int tmp = tmp_second_tiles.get("EL_SHAPE");
                tmp_second_tiles.replace("EL_SHAPE", (tmp + 1));
            }
            else if (value_list.get(index_val).get(5) == 0){
                int tmp = tmp_second_tiles.get("FULL_BLOCK");
                tmp_second_tiles.replace("FULL_BLOCK", (tmp + 1));
            }

            //update targets
            HashMap<Integer, Integer> tmp_second_targets = (HashMap<Integer, Integer>) tmp_targets.clone();
            for (Integer t : tmp_targets.keySet()){
                int tmp1 = tmp_targets.get(t);
                int tmp2 = value_list.get(index_val).get(t);
                tmp_second_targets.replace(t, (tmp1 + tmp2));

            }



            //调用AC3函数传入tmp_second_targets，tmp_second_tiles
            if (AC3(var, tiles, targets, tmp_second_targets, tmp_second_tiles, index_var, visited_var)){
                visited_var[index_var] = 1;
                System.out.println(index_var);
                if (backtracking(var, landscape, tiles, targets, tmp_second_tiles, tmp_second_targets, visited_var)){
                    if (var.get(index_var).get(index_val).get(5) == 1){
                        System.out.println(index_var + "OUTER_BOUNDARY");

                    }
                    else if (var.get(index_var).get(index_val).get(5) == 2){
                        System.out.println(index_var + "EL_SHAPE");

                    }
                    else if (var.get(index_var).get(index_val).get(5) == 0){
                        System.out.println(index_var + "FULL_BLOCK");

                    }
                    System.out.println(var.get(index_var).get(index_val));
                    return true;
                }
            }

            for (Integer i : tmp_targets.keySet()){
                tmp_second_targets.replace(i, tmp_targets.get(i));
            }
            for (String str : tmp_tiles.keySet()){
                tmp_second_tiles.replace(str, tmp_tiles.get(str));
            }
            for (int i = 0; i < visited_var.length; i++){
                visited_var[i] = visited_var_tmp[i];
            }
            var.clear();
            for (int i = 0; i < var_tmp.size(); i++){

                LinkedList<HashMap<Integer, Integer>> list = new LinkedList<HashMap<Integer, Integer>>();
                for (int j = 0; j < var_tmp.get(i).size(); j++){
                    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
                    for (int t = 1; t <= var_tmp.get(i).get(j).size(); t++){
                        map.put(t, var_tmp.get(i).get(j).get(t));
                    }
                    list.add(map);
                }
                var.add(i, list);
//                }else{
//                    for (int j = 0; j < var.get(i).size(); j++){
//                        if (var.get(i).get(j).isEmpty()){
//                            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//                            for (int t = 1; t <= var_tmp.get(i).get(j).size(); t++){
//                                map.put(t, var_tmp.get(i).get(j).get(t));
//                            }
//                            var.get(i).remove(j);
//                            var.get(i).add(j, map);
//                        }else{
//                            for (int t = 1; t <= var_tmp.get(i).get(j).size(); t++){
//                                var.get(i).get(j).put(t, var_tmp.get(i).get(j).get(t));
//                            }
//                        }
//                        if (!var.get(i).contains(var_tmp.get(i).get(j))){
//                            var.get(i).add(j, var_tmp.get(i).get(j));
//                        }
//                        for (int t = 1; t <= var_tmp.get(i).get(j).size(); t++){
//                            var.get(i).get(j).put(t, var_tmp.get(i).get(j).get(t));
//                        }
//                    }
//                    if (var.get(i).size() < var_tmp.get(i).size()){
//                        for (int j = var.get(i).size(); j < var_tmp.get(i).size(); j++){
//                            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
//                            for (int t = 1; t <= var_tmp.get(i).get(j).size(); t++){
//                                map.put(t, var_tmp.get(i).get(j).get(t));
//                            }
//                            var.get(i).add(j, map);
//                        }
//
//                    }
//                }


            }
        }



        return false;
    }

    public static boolean AC3 (LinkedList<LinkedList<HashMap<Integer, Integer>>> var, HashMap<String, Integer> tiles,
                               HashMap<Integer, Integer> targets, HashMap<Integer, Integer> tmp_second_targets,
                               HashMap<String, Integer> tmp_second_tiles, int index_var, int[] visited_var){
        //init linkedlist as queue
        LinkedList<HashMap<Integer, Integer>> queue = new LinkedList<HashMap<Integer, Integer>>();
        LinkedList<HashMap<Integer, Integer>> queue_tmp = new LinkedList<HashMap<Integer, Integer>>();
        for (int i = 0; i < var.size(); i++){
            HashMap<Integer, Integer> arc = new HashMap<Integer, Integer>();
            HashMap<Integer, Integer> arc_tmp = new HashMap<Integer, Integer>();
            for (int j = 0; j < var.size(); j++){
                if (i != j && i != index_var && j != index_var && visited_var[i] == 0 && visited_var[j] == 0){
                    arc.put(j, i);
                    arc_tmp.put(j, i);
                }
            }
            queue.addLast(arc);
            queue_tmp.addLast(arc_tmp);
        }

//        HashMap<Integer, Integer> tmp_revise_targets = (HashMap<Integer, Integer>) tmp_second_targets.clone();
//        HashMap<Integer, Integer> tmp_revise_tiles = (HashMap<Integer, Integer>) tmp_second_tiles.clone();

        while (!queue_tmp.isEmpty()) {

            if (queue_tmp.get(0).isEmpty()){
                queue_tmp.removeFirst();
            }else{
                for (Integer t : queue_tmp.get(0).keySet()) {
                    int xi = t;
                    int xj = queue_tmp.get(0).get(t);
                    if (Revise(var, tiles, targets, tmp_second_targets, tmp_second_tiles, xi, xj)){
                        if (var.get(xi).size() == 0){
                            return false;
                        }
                        if (!queue.get(xi).isEmpty()){
                            queue_tmp.addLast(queue.get(xi));
                        }
                    }
                }
                queue_tmp.removeFirst();

            }

        }

    return true;
    }


    public static boolean Revise(LinkedList<LinkedList<HashMap<Integer, Integer>>> var, HashMap<String, Integer> tiles,
                                 HashMap<Integer, Integer> targets, HashMap<Integer, Integer> tmp_second_targets,
                                 HashMap<String, Integer> tmp_second_tiles, int xi, int xj){
        boolean revised = false;
        boolean check = false;


        for (int i = 0; i < var.get(xi).size(); i++){
            if (var.get(xi).get(i).get(5) == 1){//OL
                if (tmp_second_tiles.get("OUTER_BOUNDARY") + 1 > tiles.get("OUTER_BOUNDARY")){
                    check = false;
                }
            }
            if (var.get(xi).get(i).get(5) == 2){//EL
                if (tmp_second_tiles.get("EL_SHAPE") + 1 > tiles.get("EL_SHAPE")){
                    check = false;
                }
            }
            if (var.get(xi).get(i).get(5) == 0){//FL
                if (tmp_second_tiles.get("FULL_BLOCK") + 1 > tiles.get("FULL_BLOCK")){
                    check = false;
                }
            }
            for (HashMap<Integer, Integer> dj : var.get(xj)){
                if (var.get(xi).get(i).get(1) + dj.get(1) + tmp_second_targets.get(1) <= targets.get(1)
                        && var.get(xi).get(i).get(2) + dj.get(2) + tmp_second_targets.get(2) <= targets.get(2)
                        && var.get(xi).get(i).get(3) + dj.get(3) + tmp_second_targets.get(3) <= targets.get(3)
                        && var.get(xi).get(i).get(4) + dj.get(4) + tmp_second_targets.get(4) <= targets.get(4)){
                    if (var.get(xi).get(i).get(5) == 1){//OL
                        if (tmp_second_tiles.get("OUTER_BOUNDARY") + 1 <= tiles.get("OUTER_BOUNDARY")){
                            if (dj.get(5) == 1) {//OL
                                if (tmp_second_tiles.get("OUTER_BOUNDARY") + 1 + 1 <= tiles.get("OUTER_BOUNDARY")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 2){
                                if (tmp_second_tiles.get("EL_SHAPE") + 1 <= tiles.get("EL_SHAPE")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 0){
                                if (tmp_second_tiles.get("FULL_BLOCK") + 1 <= tiles.get("FULL_BLOCK")) {
                                    check = true;
                                }
                            }
                        }
                    }
                    else if (var.get(xi).get(i).get(5) == 2){
                        if (tmp_second_tiles.get("EL_SHAPE") + 1 <= tiles.get("EL_SHAPE")) {
                            if (dj.get(5) == 1) {//OL
                                if (tmp_second_tiles.get("OUTER_BOUNDARY") + 1 <= tiles.get("OUTER_BOUNDARY")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 2){
                                if (tmp_second_tiles.get("EL_SHAPE") + 1 + 1 <= tiles.get("EL_SHAPE")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 0){
                                if (tmp_second_tiles.get("FULL_BLOCK") + 1 <= tiles.get("FULL_BLOCK")) {
                                    check = true;
                                }
                            }
                        }
                    }
                    else if (var.get(xi).get(i).get(5) == 0){
                        if (tmp_second_tiles.get("FULL_BLOCK") + 1 <= tiles.get("FULL_BLOCK")) {
                            if (dj.get(5) == 1) {//OL
                                if (tmp_second_tiles.get("OUTER_BOUNDARY") + 1 <= tiles.get("OUTER_BOUNDARY")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 2){
                                if (tmp_second_tiles.get("EL_SHAPE") + 1 <= tiles.get("EL_SHAPE")) {
                                    check = true;
                                }
                            }
                            else if (dj.get(5) == 0){
                                if (tmp_second_tiles.get("FULL_BLOCK") + 1 + 1 <= tiles.get("FULL_BLOCK")) {
                                    check = true;
                                }
                            }
                        }
                    }

                }
            }
            if (!check){
                var.get(xi).remove(i);
                revised = true;
            }

        }
        return revised;
    }
}
