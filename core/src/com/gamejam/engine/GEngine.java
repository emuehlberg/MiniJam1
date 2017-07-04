package com.gamejam.engine;

import java.util.LinkedList;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gamejam.engine.components.CollisionComponent;
import com.gamejam.engine.components.DisplayComponent;
import com.gamejam.engine.components.IDComponent;
import com.gamejam.engine.components.PositionComponent;
import com.gamejam.engine.components.TextComponent;
import com.gamejam.engine.components.TimedComponent;
import com.gamejam.engine.components.VelocityComponent;
import com.gamejam.engine.components.systems.CollisionSystem;
import com.gamejam.engine.components.systems.DisplaySystem;
import com.gamejam.engine.components.systems.MovementSystem;
import com.gamejam.engine.components.systems.TimedSystem;

public class GEngine
{
	private EngineDebugger dbgr = new EngineDebugger();
	public Engine engine;
	public OrthographicCamera camera;
	public SpriteBatch batch;
	private int NextID;
	public JamCam jamcam;
	public ScreenWriter sw;
	public com.gamejam.engine.assets.AssetManager am;
	
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
		//engine.addSystem(new PhysicsSystem());
		engine.addSystem(new CollisionSystem());
		engine.addSystem(new DisplaySystem(this)); //Keep this system last
		sw = new ScreenWriter(batch);
		am = new com.gamejam.engine.assets.AssetManager();
		System.out.println("-Engine Initialized-");
	}

	public void updateCamera()
	{
		jamcam.update();
	}
	
	public void update(float deltaTime)
	{
		engine.update(deltaTime);
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
	
	public Texture getTextureAsset(String assetname)
	{
		return (Texture)am.getAsset(assetname);
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
	
	public float getTheta(Vector3 a, Vector3 b)
	{
		double dx = a.x - b.x;
		double dy = a.y - b.y;
		
		return (float) (MathUtils.radiansToDegrees * Math.atan2(dx,dy));
		
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
	
	
	public void generateBullet(Entity a, Vector3 direction, float bulletspeed)
	{
		
		PositionComponent pc = a.getComponent(PositionComponent.class);
		DisplayComponent dc = a.getComponent(DisplayComponent.class);
		
		float x = (float) (21*-Math.sin(dc.rotation*MathUtils.degreesToRadians)+pc.x+20);
		float y = (float) (21*Math.cos(dc.rotation*MathUtils.degreesToRadians)+pc.y+20);

		float dx = (float) -(bulletspeed * Math.sin(dc.rotation*MathUtils.degreesToRadians));
		float dy = (float) (bulletspeed * Math.cos(dc.rotation*MathUtils.degreesToRadians));
		
		Entity e = new Entity();
		//Update to shoot in the proper direction
		e.add(new TimedComponent(.25f)).add(new DisplayComponent((Texture)am.getAsset("bullet.png"))).add(new VelocityComponent(dx,dy)).add(new PositionComponent(x,y));
		addEntity(e);
		
	}
	
	public void dispose(Entity e)
	{
		engine.removeEntity(e);
	}
	
	public Vector3 getMousePosition()
	{
		return camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
	public void dispose()
	{
		System.out.println("-Shutting Down-");
		System.out.println("-Disposing Graphical Assets-");
		try
		{
			am.dispose();
		}
		catch (Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		System.out.println("-Disposing Batch-");
		batch.dispose();
		System.out.println("-Exiting-");
	}
	
}
