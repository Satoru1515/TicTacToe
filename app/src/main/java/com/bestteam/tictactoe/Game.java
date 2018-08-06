package com.bestteam.tictactoe;

import java.util.Random;

public class Game {

    /* array, variable and contant statement */
    public final int DIFICULT;
    public int player;
    private int[] BOXES;

    //Constructor
    public Game(int dificult , int player){
        DIFICULT = dificult;
        this.player = player;

        BOXES= new int[9];
        for(int i=0; i<9; i++) BOXES[i] = 0;

        COMBINATION = new int[][]{{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    }


    /* This method changed the player turn and check if a player won */
    public int turn(){

        boolean tie = true;

        boolean finalMovement = true;

        for(int i= 0; i<COMBINATION.length;i++) {

            for (int position : COMBINATION[i]) {

                if (BOXES[position]!= player) finalMovement = false;

                if (BOXES[position]==0) tie =false;
            }// internal for


            if (finalMovement) return player;

            finalMovement=true;

        }//Principal for

        if (tie) return 3;

        player++;
        if (player>2) player=1;

        return 0;
    }

    /*This method check the boxes selected by the player*/
    public int twoLines(int playerTurn){

        int box=-1;
        int count=0;

        for(int i= 0; i<COMBINATION.length;i++) {

            for (int position : COMBINATION[i]) {
                if (BOXES[position] == playerTurn) {
                    count++;
                }
                if (BOXES[position] == 0) box=position;
            }

            if (count ==2 && box!= -1) return box;

            box=-1;

            count=0;
        }
        return  -1;
    }


    /* This method make the artificial intelligence */
    public int ia(){
        int box;

        box = twoLines(2);

        if (box != -1) return  box;

        if (DIFICULT > 0) {

            box = twoLines(1);
            if (box != -1) return box;
        }

        if (DIFICULT==2){

            if (BOXES[0]==0) return 0;
            if (BOXES[2]==0) return 2;
            if (BOXES[4]==0) return 4;
            if (BOXES[6]==0) return 6;
        }

        Random random = new Random();
        box = random.nextInt(9);
        return box;
    }

    // This method verify if the play is valid
    public boolean validate(int box){
        if (BOXES[box] !=0){
            return false;
        }
        else{ BOXES[box]=player; return true;}
    }


    /* this array have the combination win */
    private final int[][] COMBINATION;


}
