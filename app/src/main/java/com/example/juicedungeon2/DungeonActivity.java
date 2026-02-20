package com.example.juicedungeon2;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DungeonActivity extends AppCompatActivity {
    private TeamMember[] team;
    private HashMap<CharacterType, ArrayList<BattleMove>> defaultMoveSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dungeon);

        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.hide(WindowInsetsCompat.Type.systemBars());

        ArrayList<CharacterType> receivedTeam = (ArrayList<CharacterType>) getIntent().getSerializableExtra("SELECTED_TEAM");

        if (receivedTeam != null) {
            Toast.makeText(this, "Equipo recibido: " + receivedTeam.size(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error: El equipo llegó vacío", Toast.LENGTH_SHORT).show();
        }

        this.team = new TeamMember[receivedTeam.size()];
        initDefaultMoveSets();
        buildTeam(receivedTeam);
        addTeamToScene();
    }

    private void initDefaultMoveSets() {
        this.defaultMoveSets = new HashMap<>();
        defaultMoveSets.put(CharacterType.MACIA, new ArrayList<>());
        defaultMoveSets.put(CharacterType.WIZARD, new ArrayList<>());
        defaultMoveSets.put(CharacterType.KNIGHT, new ArrayList<>());

        //MACIA
        defaultMoveSets.get(CharacterType.MACIA).add(BattleMove.TEACH);

        //WIZARD
        defaultMoveSets.get(CharacterType.WIZARD).add(BattleMove.FIREBALL);
        defaultMoveSets.get(CharacterType.WIZARD).add(BattleMove.UNI_HEAL);
        defaultMoveSets.get(CharacterType.WIZARD).add(BattleMove.MULTI_HEAL);

        //KNIGHT
        defaultMoveSets.get(CharacterType.KNIGHT).add(BattleMove.STRIKE);
        defaultMoveSets.get(CharacterType.KNIGHT).add(BattleMove.DEFEND);
    }

    private void buildTeam(ArrayList<CharacterType> rawTeam) {
        int counter = 0;
        for (CharacterType character : rawTeam) {
            TeamMember member = null;
            ArrayList<BattleMove> moveSet = defaultMoveSets.get(character);

            switch (character) {
                case MACIA:
                    member = new TeamMember(CharacterType.MACIA, moveSet, 1.2);
                    break;
                case WIZARD:
                    member = new TeamMember(CharacterType.WIZARD, moveSet, 1.5);
                    break;
                case KNIGHT:
                    member = new TeamMember(CharacterType.KNIGHT, moveSet, 2.2);
                    break;
            }

            team[counter] = member;
            counter++;
        }
    }

    private void addTeamToScene() {

    }
}
