package artemtrue.wasteddays;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    int wastedDays = 0;
    int savedDays = 0;

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

    public void savedDaysCounter(){
        savedDays = savedDays + 1;
        TextView numberOfSavedDays = (TextView) findViewById(R.id.saved_number);
        numberOfSavedDays.setText(Integer.toString(savedDays));
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

        wastedDaysCounter();
        timeStamp();

        rl.setBackgroundColor(Color.RED);
        wasted.setEnabled(false);
        saved.setEnabled(false);
    }

    public void savedButtonFollower(View view) {

        final TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        final LinearLayout rl2 = (LinearLayout) findViewById(R.id.background);
        final Button wasted = (Button) findViewById(R.id.wasted_btn);
        final Button saved = (Button) findViewById(R.id.saved_btn);

        savedDaysCounter();
        timeStamp();

        rl2.setBackgroundColor(Color.GREEN);
        saved.setEnabled(false);
        wasted.setEnabled(false);

    }
}