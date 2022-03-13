package test.Tile_Placement;

import Tile_Placement.Main;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {


    @Test
    void runtest1() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Lanscape1"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles1"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets1"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest2() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes2"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles2"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets2"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }

    @Test
    void runtest3() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes3"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles3"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets3"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest4() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes4"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles4"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets4"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest5() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes5"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles5"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets5"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest6() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes6"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles6"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets6"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest7() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes7"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles7"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets7"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest8() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes8"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles8"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets8"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest9() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes9"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles9"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets9"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest10() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes10"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles10"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets10"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest11() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes11"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles11"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets11"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest12() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes12"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles12"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets12"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest13() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes13"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles13"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets13"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest14() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes14"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles14"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets14"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest15() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes15"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles15"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets15"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest16() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes16"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles16"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets16"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest17() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes17"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles17"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets17"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }
    @Test
    void runtest18() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes18"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles18"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets18"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }

    @Test
    void runtest19() throws IOException {
        int[][] landscape = null;
        HashMap<String, Integer> tiles = new HashMap<>();
        HashMap<Integer, Integer> targets = new HashMap<>();
        BufferedReader ls = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Landscapes19"));
        BufferedReader til = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Tiles19"));
        BufferedReader tar = new BufferedReader(new FileReader("/Users/adisihansun/IdeaProjects/AI_CSP_Tile_Placement/src/test/Tile_Placement/Targets19"));
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



        boolean assignment = Main.generate_variables_domain(landscape, tiles, targets);
        assertEquals(true, assignment);
    }


}
