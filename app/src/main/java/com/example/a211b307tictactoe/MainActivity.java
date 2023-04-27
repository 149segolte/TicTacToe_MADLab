package com.example.a211b307tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean isWinner = false;
    boolean player = true;
    int [][]currState = {{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};

    public void onClick(View view) {
        if (isWinner) {
            return;
        }
        ImageView iv = (ImageView) view;
        String tag = String.valueOf(iv.getTag());
        int row = Integer.parseInt(String.valueOf(tag.charAt(0)));
        int col = Integer.parseInt(String.valueOf(tag.charAt(1)));
        if (currState[row][col] != -1) {
            Toast.makeText(this, "Already set!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            currState[row][col] = player ? 1 : 0;
        }
        if (player) {
            int imageResource = getResources().getIdentifier("@android:drawable/ic_delete", null, getPackageName());
            iv.setImageResource(imageResource);
        } else {
            int imageResource = getResources().getIdentifier("@android:drawable/radiobutton_off_background", null, getPackageName());
            iv.setImageResource(imageResource);
        }
        currState[row][col] = (String.valueOf(player) == "true") ? 1 : 0;
        int result = checkState();
        if (result == 1) {
            Toast.makeText(this, "Player 1 won!", Toast.LENGTH_SHORT).show();
        } else if (result == 0) {
            Toast.makeText(this, "Player 2 won!", Toast.LENGTH_SHORT).show();
        } else if ((result - 2) == 0) {
            Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show();
        } else {
            player = !player;
            return;
        }
        isWinner = true;
    }

    public int checkState() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (currState[i][0] == currState[i][1] && currState[i][1] == currState[i][2]) {
                return currState[i][0];
            }
        }
        // Check cols
        for (int i = 0; i < 3; i++) {
            if (currState[0][i] == currState[1][i] && currState[1][i] == currState[2][i]) {
                return currState[0][i];
            }
        }
        //Check diagonals
        if ((currState[0][0] == currState[1][1] && currState[1][1] == currState[2][2]) || (currState[0][2] == currState[1][1] && currState[1][1] == currState[2][0])) {
            return currState[1][1];
        }
        int totalfree = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currState[i][j] == -1) {
                    totalfree += 1;
                }
            }
        }
        
        return totalfree + 2;
    }

    public void reset(View view) {
        isWinner = false;
        player = true;
        currState = new int[][]{{-1, -1, -1}, {-1, -1, -1}, {-1, -1, -1}};
        TableLayout table = findViewById(R.id.grid);
        int imageResource = getResources().getIdentifier("@android:color/transparent", null, getPackageName());
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3; j++) {
                ImageView iv = (ImageView) row.getChildAt(j);
                iv.setImageResource(imageResource);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}