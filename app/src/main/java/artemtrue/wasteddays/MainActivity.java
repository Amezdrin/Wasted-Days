package artemtrue.wasteddays;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void timeStamp(){

        TextView timeStamp = (TextView) findViewById(R.id.currentDate_txtView);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        String dateString = sdf.format(date);
        timeStamp.setText(dateString);
    }

    public void wastedButtonFollower(View view) {

        View bg = findViewById(R.id.background);
        Button wasted = (Button) findViewById(R.id.wasted_btn);
        //wasted.setBackgroundColor(0xB0171F);
        wasted.setEnabled(false);
        timeStamp();
        bg.setBackgroundColor(0xB0171F);
        //wasted.setClickable(false);
    }
}
