package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class TimedSystem extends EntitySystem
{

	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<TimedComponent> tm = ComponentMapper.getFor(TimedComponent.class);
	private GEngine engine;
	
	public TimedSystem(GEngine engine)
	{
		this.engine = engine;
	}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(TimedComponent.class).get());
	}
	
	public void update(float deltatime)
	{
		for(Entity e:entities)
		{
			TimedComponent tc = tm.get(e);
			
			tc.time -= deltatime;
			if(tc.time <= 0)
			{
				engine.dispose(e);
			}
		}
	}

	
}
