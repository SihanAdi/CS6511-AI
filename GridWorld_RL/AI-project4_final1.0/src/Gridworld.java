import java.util.*;
import org.apache.commons.math3.distribution.EnumeratedIntegerDistribution;
public class Gridworld {
    public int row;
    public int column;
    public int row_win;
    public int col_win;
    public int row_lose;
    public int col_lose;
    public int state_row;
    public int state_col;
    public int[] state = new int[2];
    public boolean certainty;
    public boolean is_end;
    public int[][] grid;

    public Gridworld(int state_row, int state_col){
        this.row = 40;
        this.column = 40;
        this.row_win = 0;
        this.col_win = 39;
        this.row_lose = 1;
        this.col_lose = 39;
        this.state_col = state_col;
        this.state_row = state_row;
        this.state[0] = this.state_row;
        this.state[1] = this.state_col;
        this.certainty = false;
        this.is_end = false;
        this.grid = new int[row][column];
        for (int i = 0; i < this.row; i++){
            for (int j  = 0; j < this.column; j++) {
                grid[i][j] = 0;
            }
        }
        //grid[1][2] = -1;//Indicates an obstacle
    }

    public double reward(){
        if (this.state_row == this.row_win && this.state_col == this.col_win){
            return 10.0;
        }
        else if (this.state_row == this.row_lose && this.state_col == this.col_lose){
            return -10.0;
        }else{
            return 0.0;
        }
    }

    public void check_end(){
        if ((this.state_row == this.row_win && this.state_col == this.col_win) || (this.state_row == this.row_lose && this.state_col == this.col_lose)){
            this.is_end = true;
        }

    }

    public String choose_random_move(String action){
        if (action == "up"){
            String[] act = {"up", "left", "right"};
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2};
            double[] probability = {0.8, 0.1, 0.1};
            dist = new EnumeratedIntegerDistribution(num, probability);
            int idx = dist.sample();
            return act[idx];
        }
        else if (action == "down"){
            String[] act = {"down", "left", "right"};
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2};
            double[] probability = {0.8, 0.1, 0.1};
            dist = new EnumeratedIntegerDistribution(num, probability);
            int idx = dist.sample();
            return act[idx];
        }
        else if (action == "left"){
            String[] act = {"left", "up", "down"};
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2};
            double[] probability = {0.8, 0.1, 0.1};
            dist = new EnumeratedIntegerDistribution(num, probability);
            int idx = dist.sample();
            return act[idx];
        }
        else if (action == "right"){
            String[] act = {"right", "up", "down"};
            EnumeratedIntegerDistribution dist;
            int[] num = {0,1,2};
            double[] probability = {0.8, 0.1, 0.1};
            dist = new EnumeratedIntegerDistribution(num, probability);
            int idx = dist.sample();
            return act[idx];
        }
        return "";
    }

    public int[] next_position(String action){
        int[] next = new int[2];
        if (this.certainty){
            if (action == "up"){
                next[0] = this.state[0] - 1;
                next[1] = this.state[1];
            }
            else if (action == "down"){
                next[0] = this.state[0] + 1;
                next[1] = this.state[1];
            }
            else if (action == "left"){
                next[0] = this.state[0];
                next[1] = this.state[1] - 1;
            }
            else if (action == "right"){
                next[0] = this.state[0];
                next[1] = this.state[1] + 1;
            }
            this.certainty = false;
        }else {
            action = this.choose_random_move(action);
            this.certainty = true;
            next = this.next_position(action);
        }

        //Check availability
        if (next[0] >= 0 && next[0] < this.row){
            if (next[1] >= 0 && next[1] < this.column){
                if (next[0] == 1 && next[1] == 2){
                    return this.state;
                }else{
                    return next;
                }
            }
        }
        return this.state;
    }

    public void show(){
        this.grid[state[0]][state[1]] = 1;
        for (int i = 0; i < this.state_row; i++){
            String output = "| ";
            for (int j  = 0; j < this.state_col; j++){
                String str = "";
                if (this.grid[i][j] == 1){
                    str = "*";
                }
                else if (this.grid[i][j] == -1){
                    str = "@";
                }
                else if (this.grid[i][j] == 0){
                    str = "0";
                }
                output += str + " | ";
            }
            System.out.println(output);
        }
    }
}
