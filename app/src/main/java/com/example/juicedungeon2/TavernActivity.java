package com.example.juicedungeon2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;

public class TavernActivity extends AppCompatActivity {
    private TavernScreen tavernScreen;

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

        this.tavernScreen = findViewById(R.id.tavern);
        LinearLayout teamContainer = findViewById(R.id.teamLL);
        tavernScreen.setTeamLayout(teamContainer);
    }

    public void enterDungeon(View view) {
        ArrayList<CharacterType> team = this.tavernScreen.getTeam();
        if (team.isEmpty()) {
            return;
        }

        Intent intent = new Intent(TavernActivity.this, DungeonActivity.class);
        intent.putExtra("SELECTED_TEAM", team);

        startActivity(intent);
        finish();
    }
}