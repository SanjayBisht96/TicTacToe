package com.example.abc.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //yellow=1,red=0
    int activePlayer=0;
    boolean gameison=true;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winingPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void drop(View view){
        ImageView image= (ImageView) view;
        int tagcounter=Integer.parseInt(image.getTag().toString());

        if (gamestate[tagcounter]==2&&gameison==true) {
            gamestate[tagcounter]=activePlayer;
            image.setTranslationY(-1000f);
            if (activePlayer == 0) {
                image.setImageResource(R.drawable.red);
                image.animate().translationYBy(1000f).setDuration(500);
                activePlayer = 1;
            } else {
                image.setImageResource(R.drawable.yellow);
                image.animate().translationYBy(1000f).setDuration(1000);
                activePlayer = 0;
            }
        }
        for(int[] winnerPosition:winingPositions) {
            if(gamestate[winnerPosition[0]]==gamestate[winnerPosition[1]]
                    &&gamestate[winnerPosition[1]]==gamestate[winnerPosition[2]]
                    &&gamestate[winnerPosition[2]]!=2)
            {
                gameison=false;
                String winner="Yellow";
                if (gamestate[winnerPosition[0]]==0) {

                    winner="Red";

                }
                TextView messege=(TextView) findViewById(R.id.winnermsg);
                messege.setText(winner + " has won");
                LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);
                layout.setVisibility(View.VISIBLE);

            }
        else {
                boolean gameisover = true;
                for (int count : gamestate)
                {       if (count == 2)
                        gameisover = false;
                }
            if(gameisover)
            {
                TextView messege=(TextView) findViewById(R.id.winnermsg);
                messege.setText("DRAW");
                LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);
                layout.setVisibility(View.VISIBLE);
            }

          }
        }
    }
    public void playagain(View view){
        gameison=true;
        LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
         activePlayer=0;
        //int[] gamestate={2,2,2,2,2,2,2,2,2};
        for(int i=0;i<9;i++)
        gamestate[i]=2;
        GridLayout grid=(GridLayout) findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


            setContentView(R.layout.activity_main);


        LinearLayout layout=(LinearLayout) findViewById(R.id.playagainlayout);
        layout.setVisibility(View.INVISIBLE);
    }
}
