package gamedesign.mathquizz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.os.CountDownTimer;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button yes;
    Button no;
    Button newGame;
    TextView num1;
    TextView num2;
    TextView answer;
    TextView score;
    ProgressBar progressBar;
    CountDownTimer mCountDownTimer;
    int i=0;    //for the timer
    int sc = 0; //for score


    Random r = new Random();
    int random1;
    int random2;
    int randomAns;

    boolean new_game = false; //to track new game progress.
    boolean finished = true;  // to track the timer.
    boolean isCounterRunning  = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        num1 = (TextView)findViewById(R.id.num1);
        num2= (TextView)findViewById(R.id.num2);
        answer= (TextView)findViewById(R.id.answer) ;
        score = (TextView)findViewById(R.id.score);


        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBar.setProgress(i);

        mCountDownTimer = new CountDownTimer(4000,900) {

            @Override
            public void onTick(long millisUntilFinished) {

                System.out.println("tick");
                i++;
                progressBar.setProgress((int)i*100/(4000/1000));

            }

            @Override
            public void onFinish() {

                isCounterRunning = false;
                progressBar.setProgress(0);

                new_game = false;
                sc = 0;
                num1.setText(Integer.toString(0));
                num2.setText(Integer.toString(0));
                answer.setText(Integer.toString(0));
                score.setText("0");
               i=0;

            }
        };





        newGame = (Button)findViewById(R.id.newGame);
        newGame.setOnClickListener(new View.OnClickListener(){
           @Override

            public void onClick(View v){
               if( mCountDownTimer != null) {
                   i=0;
                   progressBar.setProgress(i); // i hate this buggy i >_<
               mCountDownTimer.cancel();
               mCountDownTimer.start();




               new_game = true;
               random1=r.nextInt(5)+1;
               random2=r.nextInt(5)+1;
               randomAns=r.nextInt(10)+1;
               num1.setText(Integer.toString(random1));
               num2.setText(Integer.toString(random2));
               answer.setText(Integer.toString(randomAns));
               score.setText("0");}


            }

        });
        yes = (Button)findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new_game && (random1 + random2 == randomAns)){
                    if( mCountDownTimer != null) {
                        i = 0;
                        progressBar.setProgress(i); // i hate this buggy i >_<
                        sc += 1;
                        score.setText(Integer.toString(sc));
                        random1 = r.nextInt(5) + 1;
                        random2 = r.nextInt(5) + 1;
                        randomAns = r.nextInt(10) + 1;
                        num1.setText(Integer.toString(random1));
                        num2.setText(Integer.toString(random2));
                        answer.setText(Integer.toString(randomAns));
                    }


                }else if(new_game && (random1 + random2 != randomAns)){
                    new_game = false;

                    mCountDownTimer.cancel();
                    i=0;
                    progressBar.setProgress(0);

                    sc = 0;
                    num1.setText(Integer.toString(0));
                    num2.setText(Integer.toString(0));
                    answer.setText(Integer.toString(0));
                    score.setText("0");
                    // a pop up window also  must appear and show some kind of kinetic
                }
            }
        });

        no = (Button)findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(new_game && (random1 + random2 != randomAns)){

                   if( mCountDownTimer != null) {
                       i=0;
                       progressBar.setProgress(i); // i hate this buggy i >_<

                       sc += 1;
                       score.setText(Integer.toString(sc));
                       random1 = r.nextInt(5) + 1;
                       random2 = r.nextInt(5) + 1;
                       randomAns = r.nextInt(5) + 1;
                       num1.setText(Integer.toString(random1));
                       num2.setText(Integer.toString(random2));
                       answer.setText(Integer.toString(randomAns));
                       mCountDownTimer.cancel();
                       mCountDownTimer.start();
                   }



                }else if(new_game && (random1 + random2 == randomAns)){


                    mCountDownTimer.cancel();
                    i=0;
                    progressBar.setProgress(0);
                    new_game = false;
                    sc = 0;
                    num1.setText(Integer.toString(0));
                    num2.setText(Integer.toString(0));
                    answer.setText(Integer.toString(0));
                    score.setText("0");
                    // a pop up window also  must appear and show some kind of kinetic
                }
            }
        });

    }
}
