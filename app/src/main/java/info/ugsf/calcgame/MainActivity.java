package info.ugsf.calcgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    TextView textViewQ, textViewA;
    Button button1, button2, button3;
    ImageView mImageStar;
    int mArg1, mArg2;
    int mA1, mA2, mA3;    // ボタンの値
    int mScore = 0;       // 連続正解数
    Random mRnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRnd = new Random();
        textViewQ = findViewById(R.id.textViewQ);
        textViewA = findViewById(R.id.textViewA);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        mImageStar = findViewById(R.id.imageStar);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        nextQuestion();
    }

    public void nextQuestion() {
        mArg1 = mRnd.nextInt(10);
        mArg2 = mRnd.nextInt(10);
        textViewQ.setText(mArg1 + " + " + mArg2 + " = ?");
        do {
            mA1 = mRnd.nextInt(19);
            mA2 = mRnd.nextInt(19);
            mA3 = mRnd.nextInt(19);
            int r = mRnd.nextInt(3);  // どのボタンが正解か決める変数
            if (r == 0) mA1 = mArg1 + mArg2;
            if (r == 1) mA2 = mArg1 + mArg2;
            if (r == 2) mA3 = mArg1 + mArg2;
            button1.setText("" + mA1);
            button2.setText("" + mA2);
            button3.setText("" + mA3);
        } while (mA1 == mA2 || mA2 == mA3 || mA3 == mA1);
    }

    @Override
    public void onClick(View v) {
        int a = 0;
        if (v == button1) a = mA1;
        if (v == button2) a = mA2;
        if (v == button3) a = mA3;
        if (mArg1 + mArg2 == a) {
            textViewA.setText("正解！");
            mScore++;
            if (mScore == 10) {
                mImageStar.setVisibility(View.VISIBLE);
                Toast.makeText(this, "10 combo!", Toast.LENGTH_SHORT).show();
            }
        } else {
            textViewA.setText("残念！");
            mImageStar.setVisibility(View.INVISIBLE);
            mScore = 0;
        }
        nextQuestion();
    }
}
