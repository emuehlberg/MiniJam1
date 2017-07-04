package com.gamejam.minijam;

import java.util.Random;

import com.gamejam.engine.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ai.GdxLogger;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
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
		Entity e2;
		mx = my = 0;
		
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
		
		double angle = getTheta(getEntityVector(Player), mouse);
		
		DisplayComponent dc = Player.getComponent(DisplayComponent.class);
		dc.rotation = angle;
		
		String text = "("+mx+","+my+","+angle+")";
		
		Entity e = new Entity();
		e.add(new PositionComponent(mx,my+20)).add(new TextComponent(text,120)).add(new TimedComponent(0.025f));
		eng.addEntity(e);
		
		
		if(vc.vy == 0)
		{
			sc.state.jumptime = 0;
			sc.state.Jumping = false;
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
		{
			//move left
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
		{
			//move right
		}
		else
			sc.state.Moving=false;
		
		if(Gdx.input.isKeyPressed(Keys.UP)||Gdx.input.isKeyPressed(Keys.W))
		{
			//move forward
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)||Gdx.input.isKeyPressed(Keys.S))
		{
			//move backwards
		}
		
			
		eng.update(Gdx.graphics.getDeltaTime());
		//eng.camera.position.y += pc.y-(eng.camera.position.y/eng.camera.viewportWidth);
		//eng.camera.position.x += pc.x-(eng.camera.position.x/eng.camera.viewportHeight);
		
		//String score = Float.toString(height);
		
		//eng.sw.writeScreen("test", eng.camera.position.x, eng.camera.position.y+20, 0, 100, 100);
	}
	
	public double getTheta(Vector3 a, Vector3 b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		
		return Math.atan2(dy,dx);
		
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
