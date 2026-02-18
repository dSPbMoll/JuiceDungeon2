package com.example.juicedungeon2;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class TavernActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Asign the screen design
        setContentView(R.layout.activity_tavern);
        // Hide system action bar
        WindowInsetsControllerCompat controller =
                WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        // Make it so the system action bar appears if the used slides
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );
        // Hide everything (Status Bar & Navigation Bar)
        controller.hide(WindowInsetsCompat.Type.systemBars());

        TavernScreen tavern = findViewById(R.id.tavern);
        LinearLayout teamContainer = findViewById(R.id.teamLL);
        tavern.setTeamLayout(teamContainer);
    }
}