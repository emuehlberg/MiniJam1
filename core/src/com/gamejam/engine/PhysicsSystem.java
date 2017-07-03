package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class PhysicsSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<PhysicsComponent> pm = ComponentMapper.getFor(PhysicsComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	
	public PhysicsSystem(){}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(VelocityComponent.class,PhysicsComponent.class).get());
	}
	
	public void update(float deltatime)
	{
	
		for(Entity e:entities)
		{
			VelocityComponent vc = e.getComponent(VelocityComponent.class);
			PhysicsComponent pc = e.getComponent(PhysicsComponent.class);
			
			vc.vx = vc.vx + (pc.ax*deltatime);
			vc.vy = vc.vy + (pc.ay*deltatime);
		}
		
	}
	
}
