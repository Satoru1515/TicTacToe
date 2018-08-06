package com.bestteam.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Variable statements
    private  int players;
    private int[] BOXES;
    private Game game;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Initilize Array
        BOXES = new int [9];

        BOXES[0] = R.id.a1;
        BOXES[1] = R.id.a2;
        BOXES[2] = R.id.a3;
        BOXES[3] = R.id.b1;
        BOXES[4] = R.id.b2;
        BOXES[5] = R.id.b3;
        BOXES[6] = R.id.c1;
        BOXES[7] = R.id.c2;
        BOXES[8] = R.id.c3;
    }


     // This function is called when the player touch to Play
    public void play(View view){
        ImageView imageView;

        for (int eachBoxes : BOXES){
            imageView = findViewById(eachBoxes);
            imageView.setImageResource(R.drawable.casilla);
        }
         players=1;

        if(view.getId() == R.id.circle){
            players=2;
        }

        RadioGroup configDificult = findViewById(R.id.configId);

        int id =configDificult.getCheckedRadioButtonId();
        int dificult = 0;

        if(id== R.id.normal){ dificult=1;}
        else if(id == R.id.imposible) {dificult=2;}


        game = new Game(dificult, players);

        findViewById(R.id.onePlayer).setEnabled(false);
        findViewById(R.id.configId).setAlpha(0);
        findViewById(R.id.circle).setEnabled(false);
    }

    // This function is called when the player touch a Square
    public  void touch(View view){

        if (game==null){
            return;
        }
        int box=0;

        for (int i = 0; i < 9; i++){
            if(BOXES[i] == view.getId()) {box = i; break;}
        }

        /*Toast.makeText(this, "this " + box , Toast.LENGTH_SHORT).show();*/

        if (!game.validate(box)){return;}
        mark(box);
        int result = game.turn();

        if (result >0) { gg(result); return;}

        box = game.ia();
        while (!game.validate(box)) {
            box = game.ia();
        }
        mark(box);
        result = game.turn();

        if (result >0) {
            gg(result);

        }
    }

    private void gg(int result) {

        String message;

        if (result==1) message = "Player 1 Win!";
        else if (result==2) message = "Player 2 Win!";
        else message = "Tie";

        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);

        toast.show();

        game=null;

        findViewById(R.id.onePlayer).setEnabled(true);
        findViewById(R.id.configId).setAlpha(1);
        findViewById(R.id.circle).setEnabled(false);
    }



    //This function mark the square

    private void mark(int box){
        ImageView imageView;

        imageView = findViewById(BOXES[box]);

        if(game.player==1){ imageView.setImageResource(R.drawable.aspa);}
        else{ imageView.setImageResource(R.drawable.circulo);}
    }


}
