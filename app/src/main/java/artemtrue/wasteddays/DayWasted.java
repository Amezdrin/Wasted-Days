package artemtrue.wasteddays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by artemtrue on 11/03/17.
 */
public class DayWasted extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.day_wasted);
    }

    public void statsButtonClickedFromWasted(View view) {
        stats_clicked = new Intent(DayWasted.this, StatsScreen.class);
        startActivity(stats_clicked);
    }
}