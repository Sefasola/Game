package com.tutorial.main;

import java.awt.Color;
import java.awt.FocusTraversalPolicy;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import org.ietf.jgss.Oid;

import com.tutorial.main.Game.STATE;



public class Menu extends MouseAdapter{
	

	public int gameState_Menu = 0;
	public int gameState_Game = 1;
	public int gameState_Help = 2;	
	public int gameState = -1;
	private Game game;
	private Handler handler;
	private HUD hud;
	private Random r= new Random();
	
	public Menu(Game game, Handler handler, HUD hud) {
		this.game=game;
		this.handler=handler;
		this.hud= hud;
	}

	public void mousePresed(MouseEvent e) {
		
		int mx= e.getX();
		int my= e.getY();
		if(game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState= STATE.Game;
				handler.clearEnemys();
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				
				handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH-50), r.nextInt(Game.HEIGHT-50), ID.BasicEnemy, handler));
			}
			
			//help button
			else if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit button
			else if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}

		}	
	
	
	//back button for help
	if(game.gameState == STATE.Help) {
		if(mouseOver(mx, my, 210, 350, 200, 64)){
			game.gameState= STATE.Menu;
			return;
		}
	}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y ,int width, int height) {
		if(mx > x && mx<x + width) {
			if(my > y && my<y +height) {
				return true;
			}
			else return false;
			
		}else return false;
		
	}
	
	public void tick() {
		
	}
	
	public void render (Graphics g) {
		
		if(game.gameState == STATE.Menu) {
			Font fnt= new Font("arial", 1, 50);
			Font fnt2= new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("menu", 240, 70);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("play", 270, 190);
			
			
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 270, 290);
			
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 270, 390);
		}
		else if(game.gameState == STATE.Help) {
			Font fnt= new Font("arial", 1, 50);
			Font fnt2= new Font("arial", 1, 30);
			Font fnt3= new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("you lost with a score of: ", 50, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
			
		}
		else if(game.gameState == STATE.End) {
			Font fnt= new Font("arial", 1, 50);
			Font fnt2= new Font("arial", 1, 30);
			Font fnt3= new Font("arial", 1, 20);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Game Over", 240, 70);
			
			g.setFont(fnt3);
			g.drawString("you lost with a score of: ", 50, 200);
			
			g.setFont(fnt2);
			g.drawRect(210, 350, 200, 64);
			g.drawString("Back", 270, 390);
			
		}
	}
}
