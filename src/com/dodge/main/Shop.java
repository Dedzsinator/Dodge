package com.dodge.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Shop extends MouseAdapter {
	
	Handler handler;
	HUD hud;
	private int B1 = 1000;
	private int B2 = 1000;
	private int B3 = 1000;
	
	public Shop(Handler handler, HUD hud) {
		
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g) {
		
		g.setColor(Color.white);
		g.setFont(new Font("arial", 0, 48));
		g.drawString("Bolt", 540, 90);
		
		//BOX 1
		g.setFont(new Font("arial", 0, 12));
		g.drawString("Élet fejlesztés", 310, 220);
		g.drawString("Ár: " + B1, 310, 240);
		g.drawRect(300, 200, 100, 50);
		
		//BOX 2
		g.drawString("Sebesség fejlesztés", 460, 220);
		g.drawString("Ár: " + B2, 460, 240);
		g.drawRect(450, 200, 135, 50);
				
		//BOX 3
		g.drawString("Élet újratöltése", 650, 220);
		g.drawString("Ár: " + B3, 650, 240);
		g.drawRect(640, 200, 100, 50);
		
		g.drawString("Pontszámod: " + hud.getScore(), 1100, 80);
		g.drawString("A szõközzel visszaléphetsz", 520, 600);
	}
	
	public void mousePressed(MouseEvent e) {
		
		int mx = e.getX();
		int my = e.getY();
		
		
		//BOX 1
		if(mx >= 310 && mx <= 410) {
			if(my >= 200 && my <= 250) {
				//SELECTED BOX 1
				if(hud.getScore() >= B1) {
					hud.setScore(hud.getScore() - B1);
					B1 += 1000;
					hud.bounds += 20;
					hud.HEALTH = (100 + (hud.bounds/2));
				}
			}
		}
				
		//BOX 2
		if(mx >= 450 && mx <= 585) {
			if(my >= 200 && my <= 250) {
				//SELECTED BOX 2
				if(hud.getScore() >= B2) {
					hud.setScore(hud.getScore() - B2);
					B2 += 1000;
					handler.spd++;
					
				}
			}
		}
								
		//BOX 3
		if(mx >= 640 && mx <= 740) {
			if(my >= 200 && my <= 250) {
				//SELECTED BOX 3
				if(hud.getScore() >= B3) {
					hud.setScore(hud.getScore() - B3);
					hud.HEALTH = (100 + (hud.bounds/2));
				}
			}
		}
	}
}
