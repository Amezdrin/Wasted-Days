package artemtrue.wasteddays;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by artemtrue on 11/03/17.
 */
public class MainActivity extends AppCompatActivity {

    Intent no_clicked;
    Intent yes_clicked;
    Intent stats_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.activity_main);
    }

    public void yesButtonClicked(View view) {
        yes_clicked = new Intent(MainActivity.this, DaySaved.class);
        startActivity(yes_clicked);
    }

    public void noButtonClicked(View view) {
        no_clicked = new Intent(MainActivity.this, DayWasted.class);
        startActivity(no_clicked);
    }

    public void statsButtonClicked(View view) {
        stats_clicked = new Intent(MainActivity.this, StatsScreen.class);
        startActivity(stats_clicked);
    }
}