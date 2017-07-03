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
import com.gamejam.engine.enums.Facing;

public class MiniJam1 extends ApplicationAdapter
{
	GEngine eng;
	Entity Player;
	float speed;
	int jumpcount;
	float height;
	int layer = 4;
	int gamestate;
	
	@Override
	public void create()
	{
		speed = 0.1f;
		jumpcount = 0;
		height = 0;
		gamestate = 1;
		eng = new GEngine();
		eng.sw.setTTF("COMIC.TTF");
		eng.sw.setFontSize(20);
		eng.sw.setColor(Color.BLACK);
		Texture img = new Texture("player.png");
		Entity e = new Entity();
		e.add(new PositionComponent(200,200)).add(new DisplayComponent(img)).add(new VelocityComponent(0,0)).add(new CollisionComponent(0,0,24,24)).add(new PhysicsComponent(0,-10)).add(new StateComponent());
		eng.addEntity(e);
		Player = e;
		eng.jamcam.setFollow(Player);
		Entity e2;
		for(int x = 0; x<40; x++)
		{			
			e2 = new Entity();
			e2.add(new PositionComponent(16*x,0)).add(new DisplayComponent(new Texture("tile.png"))).add(new CollisionComponent(16*x,0,16,16));
			eng.addEntity(e2);
		}
		
	}
	
	public void restartlevel()
	{
		for(Entity e:eng.engine.getEntities())
		{
			DisplayComponent dc = e.getComponent(DisplayComponent.class);
			dc.texreg.getTexture().dispose();
			
		}
		eng.engine.removeAllEntities();
		
		Texture img = new Texture("player.png");
		
		Entity e = new Entity();
		e.add(new PositionComponent(200,200)).add(new DisplayComponent(img)).add(new VelocityComponent(0,0)).add(new CollisionComponent(0,0,24,24)).add(new PhysicsComponent(0,-10)).add(new StateComponent());
		eng.addEntity(e);
		Player = e;
		eng.jamcam.setFollow(Player);
		Entity e2;
		for(int x = 0; x<40; x++)
		{			
			e2 = new Entity();
			e2.add(new PositionComponent(16*x,0)).add(new DisplayComponent(new Texture("tile.png"))).add(new CollisionComponent(16*x,0,16,16));
			eng.addEntity(e2);
		}
		
		speed = 0.1f;
		jumpcount = 0;
		height = 0;
		gamestate = 1;
		
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
		
		if(vc.vy == 0)
		{
			sc.state.jumptime = 0;
			height = pc.y;
			sc.state.Jumping = false;
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
		{
			sc.state.facing=Facing.Left;
			sc.state.Moving=true;
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
		{
			sc.state.facing=Facing.Right;
			sc.state.Moving=true;
		}
		else
			sc.state.Moving=false;
		if((Gdx.input.isKeyPressed(Keys.SPACE)||Gdx.input.isKeyPressed(Keys.W)) && sc.state.jumptime < 5)
		{
			sc.state.Jumping=true;
			if(sc.state.jumptime == 0)
			{
				vc.vy+=5f;
			}
			sc.state.jumptime+=Gdx.graphics.getDeltaTime();
			jumpcount++;
			speed+=0.2;
		}

		if(Gdx.input.isKeyJustPressed(Keys.SHIFT_LEFT) && vc.vy == 0)
		{
			System.out.println("NEW HEIGHT: "+height);
			generatePlatforms(layer);
			layer+=4;
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.F1))
		{
			Entity e = new Entity();
			e.add(new TimedComponent(10)).add(new PositionComponent(100,100)).add(new TextComponent("Test",100));
			eng.addEntity(e);
		}
			
		eng.update(Gdx.graphics.getDeltaTime());
		
		if(pc.y <= eng.camera.position.y-100)
		{
			//Game over
			gamestate = 2;
		}
		
		eng.camera.position.y += speed * Gdx.graphics.getDeltaTime();
		speed += 0.1*Gdx.graphics.getDeltaTime();
		
		//String score = Float.toString(height);
		
		//eng.sw.writeScreen("test", eng.camera.position.x, eng.camera.position.y+20, 0, 100, 100);
	}
	
	public void generatePlatforms(int layer)
	{
		for(int x=0;x<40;x++)
		{
			int size = platformSize();
			if(size!=0)
			{
				for(int z = 0; z<size; z++)
				{		
					Entity e2 = new Entity();
					e2.add(new PositionComponent(16*(x+z),16*layer)).add(new DisplayComponent(new Texture("tile.png"))).add(new CollisionComponent(16*(x+z),16*layer,16,16));
					eng.addEntity(e2);
				}
				x=x+size+4;
			}
		}
		
		speed += 0.1;
		
		
	}
	
	public int platformSize()
	{
		Random rand = new Random();
		return rand.nextInt(5);
	}
	
	@Override
	public void dispose()
	{
		eng.dispose();
	}


}
