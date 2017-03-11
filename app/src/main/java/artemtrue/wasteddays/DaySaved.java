package artemtrue.wasteddays;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by artemtrue on 11/03/17.
 */
public class DaySaved extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.day_saved);
    }

    public void statsButtonClickedFromSaved(View view) {
        stats_clicked = new Intent(DaySaved.this, StatsScreen.class);
        startActivity(stats_clicked);
    }
}
