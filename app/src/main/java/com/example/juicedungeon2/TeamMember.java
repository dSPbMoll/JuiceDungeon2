package com.example.juicedungeon2;

import java.util.ArrayList;

public class TeamMember {
    private final CharacterType type;
    private int maxHp;
    private final double MAX_HP_GROW_RATE;
    private int hp;
    private int power;

    private final ArrayList<BattleMove> moveSet;

    public TeamMember(CharacterType character, ArrayList<BattleMove> moveSet, double maxHpGrowRate) {
        this.type = character;
        this.moveSet = moveSet;
        this.MAX_HP_GROW_RATE = maxHpGrowRate;
    }

    //======================= GETTERS & SETTERS =======================
    public CharacterType getType() {
        return type;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPower() {
        return this.power;
    }
    public void setPower(int power) {
        this.power = power;
    }

    // ========================= OTHER ==========================
    public void growPower(int growAmmount) {
        this.power = this.power + growAmmount;
        recalculateStats();
    }

    private void recalculateStats() {
        // MaxHp
        int oldMaxHp = this.maxHp;
        this.maxHp = (int) Math.round(this.power * this.MAX_HP_GROW_RATE);
        int maxHpDiff = maxHp - oldMaxHp;

        // Hp
        this.hp = this.hp + maxHpDiff;
    }

}
