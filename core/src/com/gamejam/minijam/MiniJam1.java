package com.gamejam.minijam;

import java.util.Random;

import com.gamejam.engine.*;
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
	GEngine eng;
	Entity Player;
	float speed;
	int layer = 4;
	int gamestate;
	float mx,my;
	Texture bimg;
	
	@Override
	public void create()
	{
		eng = new GEngine();
		eng.sw.setTTF("COMIC.TTF");
		eng.sw.setFontSize(20);
		eng.sw.setColor(Color.BLACK);
		Texture img = new Texture("player.png");
		Entity e = new Entity();
		e.add(new PositionComponent(200,200)).add(new DisplayComponent(img)).add(new VelocityComponent(0,0)).add(new CollisionComponent(0,0,42,42)).add(new StateComponent());
		eng.addEntity(e);
		Player = e;
		eng.jamcam.setFollow(Player);
		mx = my = 0;
		bimg = new Texture("Bullet.png");
		
	}
	
	public void generateBullet(Entity a)
	{
		
		Entity e = new Entity();
		//Update to shoot in the proper direction
		e.add(new TimedComponent(1f)).add(new DisplayComponent(bimg)).add(new VelocityComponent(5,5)).add(new CollisionComponent(0,0,8,8)).add(new PositionComponent(100,100));
		eng.addEntity(e);
		
	}
	
	
	@Override
	public void render() 
	{
		/**
		 * For testing collisions
		 */
		VelocityComponent vc = Player.getComponent(VelocityComponent.class);
		StateComponent sc = Player.getComponent(StateComponent.class);
		PositionComponent pc = Player.getComponent(PositionComponent.class);
		
		
		Vector3 mouse = eng.getMousePosition();
		
		mx = (int)mouse.x;
		my = (int)mouse.y;
		
		double angle = -getTheta(getEntityVector(Player),mouse)+180;
		
		DisplayComponent dc = Player.getComponent(DisplayComponent.class);
		dc.rotation = angle;
		
		String text = "("+mx+","+my+","+angle+")";
		
		Entity e = new Entity();
		e.add(new PositionComponent(mx,my+20)).add(new TextComponent(text,120)).add(new TimedComponent(0.025f));
		eng.addEntity(e);
		
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
		
		if(Gdx.input.isButtonPressed(Buttons.LEFT))
		{
			generateBullet(Player);
		}
		
		
		eng.update(Gdx.graphics.getDeltaTime());
		eng.camera.position.y = pc.y-(eng.camera.position.y/eng.camera.viewportWidth)+21;
		eng.camera.position.x = pc.x-(eng.camera.position.x/eng.camera.viewportHeight)+21;
		eng.camera.update();
		
		//String score = Float.toString(height);
		
		//eng.sw.writeScreen("test", eng.camera.position.x, eng.camera.position.y+20, 0, 100, 100);
	}
	
	public double getTheta(Vector3 a, Vector3 b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		
		return MathUtils.radiansToDegrees * Math.atan2(dx,dy);
		
	}
	
	public Vector3 getEntityVector(Entity a)
	{
		PositionComponent pc = a.getComponent(PositionComponent.class);
		CollisionComponent cc = a.getComponent(CollisionComponent.class);
		
		if(cc==null)
			return null;
		
		Vector3 va = new Vector3();
		va.x = pc.x+(cc.w/2);
		va.y = pc.y+(cc.h/2);
		return va;
	}
	
	
	@Override
	public void dispose()
	{
		eng.dispose();
	}


}
