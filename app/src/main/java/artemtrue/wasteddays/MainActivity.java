package artemtrue.wasteddays;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
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

    Boolean dayIsSaved = false;
    Boolean dayIsWasted = false;
    Calendar calendar2 = Calendar.getInstance();
    Date today;
    Date tomorrow;
    int wastedDays = 0;
    int savedDays = 0;
    int restoreWastedDays = 0;
    int restoreSavedDays = 0;
    int i = 0;
    MyTask mt;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
    String out1;
    String out2;
    String out1i;
    String TAG = "Date today";
    String TAG1 = "Date tomorrow";
    String TAG2 = "current i value";
    public static final String WD = "WastedDAYS";
    public static final String SD = "SavedDAYS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);

        /**выводит количество дней при запуске приложения*/
        TextView numberOfWastedDays = (TextView) findViewById(R.id.wasted_number);
        TextView numberOfSavedDays = (TextView) findViewById(R.id.saved_number);

        SharedPreferences prefs = getSharedPreferences("sharedPref", MODE_PRIVATE);
        int restoredWDValue = prefs.getInt(WD, restoreWastedDays);
        int restoredSDValue = prefs.getInt(SD, restoreSavedDays);
        if (restoredWDValue != 0 && restoredSDValue != 0) {
            wastedDays = prefs.getInt(WD, restoreWastedDays);
            savedDays = prefs.getInt(SD, restoreSavedDays);
        }
        else{
            wastedDays = prefs.getInt(WD, 0);
            savedDays = prefs.getInt(SD, 0);
        }
        numberOfWastedDays.setText(Integer.toString(wastedDays));
        numberOfSavedDays.setText(Integer.toString(savedDays));
    }

    public void DaysCounter(){ //счётчик дней для wasted и saved
        TextView numberOfWastedDays = (TextView) findViewById(R.id.wasted_number);
        TextView numberOfSavedDays = (TextView) findViewById(R.id.saved_number);

        if(dayIsWasted == true) {
            wastedDays = wastedDays + 1;
            restoreWastedDays = wastedDays;
            numberOfWastedDays.setText(Integer.toString(wastedDays));
        }

        if(dayIsSaved == true) {
            savedDays = savedDays + 1;
            restoreSavedDays = savedDays;
            numberOfSavedDays.setText(Integer.toString(savedDays));
        }
    }

    public void commonUIChanger(){ //меняет UI на wasted или saved
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);
        final Button saved = (Button) findViewById(R.id.saved_btn);

        if(dayIsWasted == true){
            rl.setBackgroundColor(Color.RED);
            wasted.setEnabled(false);
            saved.setEnabled(false);
        }

        if(dayIsSaved == true){
            rl.setBackgroundColor(Color.GREEN);
            saved.setEnabled(false);
            wasted.setEnabled(false);
        }
    }

    public void resetUIToDefault(){ //меняет UI на исходный

        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        final LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);
        final Button saved = (Button) findViewById(R.id.saved_btn);
        dayIsSaved = false;
        dayIsWasted = false;
        rl.setBackgroundColor(Color.TRANSPARENT);
        timeStamp.setText("It's a new day!");
        wasted.setEnabled(true);
        saved.setEnabled(true);

    }

    public void todayTimeStamp(){ //ставит сегодняшнюю дату

        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        long date = System.currentTimeMillis();
        String dateString = sdf.format(date);
        timeStamp.setText(dateString);
    }

    public void getTodayDay(){  //получает сегодняшнюю дату и время из календаря

        Calendar calendar = Calendar.getInstance();
        today = calendar.getTime();
        Log.w(TAG,sdf.format(today));
    }

    public void getTomorrowDay(){ //получает завтрашнюю дату с временем 00:00:00

        calendar2.set(Calendar.HOUR_OF_DAY, 0);
        calendar2.set(Calendar.MINUTE, 0);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.MILLISECOND, 0);
        calendar2.add(Calendar.DAY_OF_YEAR, 1);
        tomorrow = calendar2.getTime();
        Log.w(TAG1,sdf.format(tomorrow));
    }

    public int compareDates(){ //сравнивает даты

        i = tomorrow.compareTo(today);
        out1 = today.toString();
        out2 = tomorrow.toString();
        out1i = ("" + i);
        Log.w(TAG,out1);
        Log.w(TAG1, out2);
        Log.w(TAG2, out1i);
        return i;
    }

    class MyTask extends AsyncTask<Void, Void, Void> { //сравнение сегодняшней и завтрашней дат

        @Override
        protected void onPreExecute() { //получение дат
            super.onPreExecute();
            getTodayDay();
            getTomorrowDay();
        }

        @Override
        protected Void doInBackground(Void... params) { //мониторинг наступления завтрашнего дня
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
        protected void onPostExecute(Void result) { //по наступлении дня UI возращается к исходному
            super.onPostExecute(result);
            getTodayDay();
            compareDates();
            resetUIToDefault();
        }
    }

    /** TODO - handler for back button; - button to reset values; - statistic */
    
    public void saveWastedDays() {

        SharedPreferences.Editor editor = getSharedPreferences("sharedPref", MODE_PRIVATE).edit();
        editor.putInt(WD, restoreWastedDays);
        editor.commit();
    }

    public void saveSaveDays() {

        SharedPreferences.Editor editor = getSharedPreferences("sharedPref", MODE_PRIVATE).edit();
        editor.putInt(SD, restoreSavedDays);
        editor.commit();
    }

    public void onClickListener(View view) {

        switch (view.getId()) {
            case R.id.wasted_btn:
                dayIsWasted = true;
                DaysCounter();
                todayTimeStamp();
                commonUIChanger();
                mt = new MyTask();
                mt.execute();
                saveWastedDays();
                break;

            case R.id.saved_btn:
                dayIsSaved = true;
                DaysCounter();
                todayTimeStamp();
                commonUIChanger();
                mt = new MyTask();
                mt.execute();
                saveSaveDays();
                break;
        }
    }
}