package com.example.juicedungeon2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DungeonActivity extends AppCompatActivity {
    private TeamMember[] team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        // Recuperamos la lista usando la misma llave
        ArrayList<CharacterType> team = (ArrayList<CharacterType>) getIntent().getSerializableExtra("SELECTED_TEAM");

    }

    //privtae
}
