package artemtrue.wasteddays;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //long date = System.currentTimeMillis();

    //long countdown = 86400000 - date;
    CountDownTimer cTimer = null;

    public void wastedDaysCounter(){
        int wastedDays = 0;
        wastedDays++;
    }

    public void savedDaysCounter(){
        int savedDays = 0;
        savedDays++;
    }

    public void timeStamp(){

        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateString = sdf.format(date);
        timeStamp.setText(dateString);
    }

    public void wastedButtonFollower(View view) {

        final TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);
        final Button saved = (Button) findViewById(R.id.saved_btn);


        /* rl.setBackgroundColor(Color.RED);
        wasted.setEnabled(false);
        wastedDaysCounter();
        saved.setEnabled(false); */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        long timeInFuture = cal.getTimeInMillis();
        long currentTime = System.currentTimeMillis();
        long countdown = timeInFuture - currentTime;

            cTimer = new CountDownTimer(countdown, 1000) {

                public void onTick(long millisUntilFinished) {

                        timeStamp();
                        rl.setBackgroundColor(Color.RED);
                        wasted.setEnabled(false);
                        wastedDaysCounter();
                        saved.setEnabled(false);
                }
                    public void onFinish() {
                        rl.setBackgroundColor(Color.TRANSPARENT);
                        wasted.setEnabled(true);
                        saved.setEnabled(true);
                        timeStamp.setText("It's a new day!");
                    }
            }.start();
    }

    public void savedButtonFollower(View view) {

        final TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        final LinearLayout rl2 = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);
        final Button saved = (Button) findViewById(R.id.saved_btn);

        timeStamp();
        /* rl2.setBackgroundColor(Color.GREEN);
        saved.setEnabled(false);
        savedDaysCounter();
        wasted.setEnabled(false); */
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, +1);
        long timeInFuture = cal.getTimeInMillis();
         long currentTime = System.currentTimeMillis();
         long countdown = timeInFuture - currentTime;

        /**long date = System.currentTimeMillis();
         long countdown = 86400000 - date;*/ //уходит в минус, нужно завтрашний день (00:00) в миллисекунды и из него вычесть текущий (calendar api).
                https://cl.ly/1I0n2J2q1W32
        http://stackoverflow.com/questions/15607500/subtracting-two-days-from-current-date-in-epoch-milliseconds-java


        cTimer = new CountDownTimer(countdown, 1000) {

            public void onTick(long millisUntilFinished) {

                rl2.setBackgroundColor(Color.RED);
                saved.setEnabled(false);
                savedDaysCounter();
                wasted.setEnabled(false);
            }

            public void onFinish() {
                rl2.setBackgroundColor(Color.TRANSPARENT);
                saved.setEnabled(true);
                wasted.setEnabled(true);
                timeStamp.setText("It's a new day!");
            }
        }.start();
    }
}