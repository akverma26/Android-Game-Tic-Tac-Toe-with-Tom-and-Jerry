package com.ankitkumar.tictoctoeandtomandjerry;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    int turn = 0,steps=0,isGameOn=1; //Jerry Turn if 0 else Tom Turn and Steps is counter for number of turns
    int[] game = {2,2,2,2,2,2,2,2,2}; //2->Not Played, 0->Played by Jerry, 1->Played by Tom
    String[] player = {"Jerry","Tom"}; //Default Player Name
    int[][] winningConditions = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}}; //All Winning Conditions Grid Number

    public void gameOver(String msg){ //GameOver Msg
        LinearLayout gameover = (LinearLayout)findViewById(R.id.gameOver);
        TextView text = (TextView)findViewById(R.id.winnerMsg);
        text.setText(msg);
        gameover.setVisibility(View.VISIBLE);
    }

    public void appear(View view) {//Appearance of Images on Grid Click and Game is Continue
        ImageView grid = (ImageView) view;
        int index = Integer.parseInt(view.getTag().toString());
        if (game[index] == 2 && isGameOn == 1) {
            if (turn == 0) {
                grid.setImageResource(R.drawable.jerry1);
                turn = 1;
                game[index] = 0;
            } else {
                grid.setImageResource(R.drawable.tom1);
                turn = 0;
                game[index] = 1;
            }
            steps++;
            grid.setAlpha(0f);
            grid.animate().alpha(1f).setDuration(1000);
        } else if (isGameOn == 1) {
            Toast.makeText(this, "Tap on un-Played Grid !!!", Toast.LENGTH_SHORT).show();
        }

        for (int[] comp : winningConditions) { //Checking for winner
            if (game[comp[0]] == game[comp[1]] && game[comp[1]] == game[comp[2]] && game[comp[0]] != 2) {
                steps = 0;
                isGameOn = 0;
                gameOver("Congratulations " + player[game[comp[0]]] + " !!!\nYou are Winner !!!");
            }

        }
        if(isGameOn==1 && steps==9) gameOver("Match Drawn !!!");
    }

    public void playAgain(View view){ //Play Again Pressed
        LinearLayout gameover = (LinearLayout)findViewById(R.id.gameOver);
        gameover.setVisibility(View.INVISIBLE);
        for(int i=0;i<9;i++) game[i]=2; isGameOn=1;
        GridLayout grid=(GridLayout)findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView img = (ImageView)grid.getChildAt(i);
            img.setImageResource(R.drawable.b1);
            img.setAlpha(0f);
            img.animate().alpha(1f).setDuration(1000);
        }
    }

    public void setPlayerJerry(View view){ //Player Name Setting
        if(!((EditText)findViewById(R.id.jerryName)).getText().toString().isEmpty())
            Toast.makeText(this, "Player Name has been Set !!!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Player Name is set to "+player[0], Toast.LENGTH_SHORT).show();
    }
    public void setPlayerTom(View view){
        if(!((EditText)findViewById(R.id.tomName)).getText().toString().isEmpty())
            Toast.makeText(this, "Player Name has been Set !!!", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Player Name is set to "+player[1], Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = (GridLayout)findViewById(R.id.grid);
        int length =(double) grid.getHeight()<grid.getWidth()?grid.getHeight():grid.getWidth();
        length=length/3;
        for(int i=0;i<grid.getChildCount();i++){
            ImageView img = (ImageView)grid.getChildAt(i);
            img.setImageResource(R.drawable.b1);
            img.setAlpha(0f);
            img.animate().alpha(1f).setDuration(2000);
            img.getLayoutParams().height=length;
            img.getLayoutParams().width=length;
        }
    }
}
