package artemtrue.wasteddays;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    int wastedDays = 0;
    //Calendar calendar = Calendar.getInstance();
    Calendar calendar2 = Calendar.getInstance();
    //Calendar tomorrow = new GregorianCalendar();
    MyTask mt;
    long todayInMillis;
    long tomorrowInMillis;
    Date today;
    Date tomorrow;
    String out1;
    String out2;
    String out1i;
    String out2i;
    boolean ass = false;
    int i = 0;
    int i2 = 0;
    private static final String TAG = "Date today";
    private static final String TAG1 = "Date tomorrow";
    private static final String TAG2 = "current i value";
    private static final String TAG3 = "current i2 value";
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

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
        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);

        rl.setBackgroundColor(Color.TRANSPARENT);
        timeStamp.setText("It's a new day!");
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
        /*today.getInstance();
        today.set(Calendar.DATE, 0);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        todayInMillis = today.getTimeInMillis();
        tomorrow = today;
        tomorrow.set(Calendar.DATE, 1);*/

        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();
        Log.w(TAG,sdf.format(today));

    }

    public void getTomorrowDay(){
       // tomorrow.set(Calendar.DATE, 1);
        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        calendar2.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar2.getTime();
        Log.w(TAG1,sdf.format(tomorrow));
    }

    public int compareDates(){

        //ass = today.equals(tomorrow);
        i = tomorrow.compareTo(today);
        //i2 = today.compareTo(tomorrow);
        //int i3 = today.compareTo(today);
        out1 = today.toString();
        out2 = tomorrow.toString();
        out1i = ("" + i);
        //out2i = ("" + i2);
        //String out3i = ("" + i3);
        Log.w(TAG,out1);
        Log.w(TAG1, out2);
        Log.w(TAG2, out1i);
        //Log.w(TAG3, out2i);
        //Log.w(TAG, out3i);
        return i;
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            getTodayDay();
            getTomorrowDay();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                compareDates();
                while(i>0) {
                    getTodayDay();
                    compareDates();
                }
            } catch (Exception e) {

            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            getTodayDay();
            compareDates();
            makeUIOrigin();
            //ass = true;
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