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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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

        LinearLayout rl = (LinearLayout) findViewById(R.id.background);
        Button wasted = (Button) findViewById(R.id.wasted_btn);
        Button saved = (Button) findViewById(R.id.saved_btn);
        timeStamp();
        rl.setBackgroundColor(Color.RED);
        wasted.setEnabled(false);
        wastedDaysCounter();
        saved.setEnabled(false);
    }

    public void savedButtonFollower(View view) {

        LinearLayout rl2 = (LinearLayout) findViewById(R.id.background);
        Button wasted = (Button) findViewById(R.id.wasted_btn);
        Button saved = (Button) findViewById(R.id.saved_btn);
        rl2.setBackgroundColor(Color.GREEN);
        timeStamp();
        saved.setEnabled(false);
        savedDaysCounter();
        wasted.setEnabled(false);
    }


}