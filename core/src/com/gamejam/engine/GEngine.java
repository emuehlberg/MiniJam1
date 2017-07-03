package com.gamejam.engine;

import java.util.LinkedList;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GEngine
{
	private EngineDebugger dbgr = new EngineDebugger();
	public Engine engine;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private int NextID;
	public JamCam jamcam;
	public ScreenWriter sw;
	
	public GEngine()
	{
		batch = new SpriteBatch();
		System.out.println("-Initializing Engine-");
		NextID = 0;
		engine = new Engine();
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		jamcam = new JamCam(camera);
		engine.addSystem(new TimedSystem(this));
		engine.addSystem(new MovementSystem());
		engine.addSystem(new PhysicsSystem());
		engine.addSystem(new CollisionSystem());
		engine.addSystem(new DisplaySystem(this)); //Keep this system last
		sw = new ScreenWriter(batch);
		System.out.println("-Engine Initialized-");
	}

	public void update(float deltaTime)
	{
		engine.update(deltaTime);
		camera.update();
	}
	
	private int getNextID()
	{
		NextID += 1;
		return NextID;
	}
	
	public void addEntity(Entity e)
	{
		e.add(new IDComponent(getNextID()));
		engine.addEntity(e);
	}
	
	public void removeEntity(Entity e)
	{
		engine.removeEntity(e);
	}
	
	public void printVars()
	{
		System.out.println("Last ID: "+NextID);
		for(Entity e:engine.getEntities())
		{
			IDComponent id = e.getComponent(IDComponent.class);
			if(id.ID!=1)
				continue;
			System.out.println("Entity: "+id.ID);
			for(Component c:e.getComponents())
			{
				
				dbgr.Inspect(c);
			}
		}
	}
	
	public void WriteScreen(String text, float x, float y, float width)
	{
		Entity e = new Entity();
		e.add(new PositionComponent(x,y)).add(new TextComponent(text, width));
		this.addEntity(e);
	}
	
	public void dispose(Entity e)
	{
		engine.removeEntity(e);
		DisplayComponent dc = e.getComponent(DisplayComponent.class);
		if(dc != null)
			dc.texreg.getTexture().dispose();
	}
	
	public void dispose()
	{
		System.out.println("-Shutting Down-");
		System.out.println("-Disposing Graphical Assets-");
		for(Entity e:engine.getEntities())
		{
			DisplayComponent dc = e.getComponent(DisplayComponent.class);
			if(dc != null)
			{
				dc.texreg.getTexture().dispose();
			}
		}
		System.out.println("-Disposing Batch-");
		batch.dispose();
		System.out.println("-Exiting-");
	}
	
}
