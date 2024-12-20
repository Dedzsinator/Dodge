package com.dodge.main;

import java.awt.Graphics;
import java.awt.Color;

public class HUD {
	
	
	public int bounds = 0;
    public static int HEALTH = 100;
    private int greenValue = 255;
    
    private int score = 0;
    private int level = 1;

    public void tick() {

        HEALTH = Game.clamp(HEALTH, 0, 100 + (bounds / 2));
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = HEALTH*2;
        
        score++;

    }

    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200 + bounds, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200 + bounds, 32);

        g.drawString("Pontsz�m: " + score, 15, 64);
        g.drawString("Szint: " + level, 15, 80);
        g.drawString("Sz�k�z a Bolthoz", 15, 94);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}


