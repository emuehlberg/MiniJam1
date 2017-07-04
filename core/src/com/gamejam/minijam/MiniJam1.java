package com.gamejam.minijam;

import java.util.Random;

import com.gamejam.engine.*;
import com.gamejam.engine.assets.TextureAsset;
import com.gamejam.engine.components.CollisionComponent;
import com.gamejam.engine.components.DisplayComponent;
import com.gamejam.engine.components.PositionComponent;
import com.gamejam.engine.components.StateComponent;
import com.gamejam.engine.components.TextComponent;
import com.gamejam.engine.components.TimedComponent;
import com.gamejam.engine.components.VelocityComponent;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.GdxLogger;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.gamejam.engine.enums.Facing;

public class MiniJam1 extends ApplicationAdapter
{
	Player p1;
	GEngine eng;
	int gamestate;
	float mx,my;
	
	
	@Override
	public void create()
	{
		eng = new GEngine();
		eng.sw.setTTF("COMIC.TTF");
		eng.sw.setFontSize(20);
		eng.sw.setColor(Color.BLACK);
		eng.am.addAsset(new TextureAsset("player.png", Color.WHITE));
		Entity e = new Entity();
		e.add(new PositionComponent(1000,1000)).add(new DisplayComponent((Texture)eng.am.getAsset("player.png"))).add(new VelocityComponent(0,0)).add(new CollisionComponent(0,0,42,42)).add(new StateComponent());
		eng.addEntity(e);
		p1 = new Player(e, eng);
		eng.jamcam.setFollow(p1.self);
		mx = my = 0;
		eng.am.addAsset(new TextureAsset("bullet.png", Color.WHITE));
		Gdx.input.setInputProcessor(p1);
		eng.am.addAsset(new TextureAsset("tile.png"));
		eng.am.addAsset(new TextureAsset("enemy.png", Color.WHITE));
		buildRoom();
		Entity en = new Entity();
		en.add(new DisplayComponent(eng.getTextureAsset("enemy.png"))).add(new PositionComponent(500,500));
		eng.addEntity(en);
	}
	
	
	public void buildRoom()
	{
		int roomsize = 50;
		int tilesize = 40; //square
		for(int y = 0; y < roomsize;y++)
		{
			for(int x = 0; x< roomsize;x++)
			{
				if(y==0||y==roomsize-1)
				{
					Entity e = new Entity();
					e.add(new DisplayComponent((Texture)eng.am.getAsset("tile.png"))).add(new PositionComponent(x*tilesize,y*tilesize)).add(new CollisionComponent(x*tilesize,y*tilesize,tilesize,tilesize));
					eng.addEntity(e);
				}
				else
				{
					if(x==0||x==roomsize-1)
					{
						Entity e = new Entity();
						e.add(new DisplayComponent((Texture)eng.am.getAsset("tile.png"))).add(new PositionComponent(x*tilesize,y*tilesize)).add(new CollisionComponent(x*tilesize,y*tilesize,tilesize,tilesize));
						eng.addEntity(e);
					}
				}
			}
		}
	}
	
	@Override
	public void render() 
	{
		/**
		 * For testing collisions
		 */
		VelocityComponent vc = p1.self.getComponent(VelocityComponent.class);
		StateComponent sc = p1.self.getComponent(StateComponent.class);
		PositionComponent pc = p1.self.getComponent(PositionComponent.class);

		
		/*
		 * Update facing
		 */
		p1.mouse = eng.getMousePosition();
		
		mx = (int)p1.mouse.x;
		my = (int)p1.mouse.y;
		
		float angle = -eng.getTheta(eng.getEntityVector(p1.self),p1.mouse)+180;
		
		DisplayComponent dc = p1.self.getComponent(DisplayComponent.class);
		dc.rotation = angle;
		
		/*
		 * Handle movement
		 */
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
		{
			//move left
			vc.vx = -5;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
		{
			//move right
			vc.vx = 5;
		}
		else
		{
			vc.vx = 0;
		}
		
		if(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W))
		{
			//move forward
			vc.vy = 5;
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S))
		{
			//move backwards
			vc.vy = -5;
		}
		else
		{
			vc.vy = 0;
		}
		
		eng.update(Gdx.graphics.getDeltaTime());

	}
	
	
	@Override
	public void dispose()
	{
		eng.dispose();
	}


}
