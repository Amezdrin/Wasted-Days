package artemtrue.wasteddays;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    int wastedDays = 0;
    Calendar today;
    Calendar tomorrow;
    MyTask mt;
    long todayInMillis;
    long tomorrowInMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void wastedDaysCounter(){
        wastedDays = wastedDays + 1;
        TextView numberOfWastedDays = (TextView) findViewById(R.id.wasted_number);
        numberOfWastedDays.setText(Integer.toString(wastedDays));
    }

    public void makeUIWasted(){
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);

        rl.setBackgroundColor(Color.RED);
        wasted.setEnabled(false);
    }

    public void makeUIOrigin(){
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);

        rl.setBackgroundColor(Color.TRANSPARENT);
        wasted.setEnabled(true);
    }

    public void todayTimeStamp(){
        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateString = sdf.format(date);
        timeStamp.setText(dateString);
    }

    /**TODO посмотреть правильность дат в календаре, цикл работает но за 1 секунду, как-будто разница между завтра и сегодня всегда 0*/
    public void getTodayDay(){
        today.getInstance();
        today.set(Calendar.DATE, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        todayInMillis = today.getTimeInMillis();
        tomorrow = today;
    }

    public void getTomorrowDay(){
        tomorrow.set(Calendar.DATE, 1);
        tomorrowInMillis = tomorrow.getTimeInMillis();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                getTomorrowDay();
                while(todayInMillis <= tomorrowInMillis) {
                   getTodayDay();
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            makeUIOrigin();
        }
    }

    public void wastedButtonFollower(View view) {

        wastedDaysCounter();
        todayTimeStamp();
        makeUIWasted();
        mt = new MyTask();
        mt.execute();
    }
}