package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class StateSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<StateComponent> sm = ComponentMapper.getFor(StateComponent.class);
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	
	public StateSystem(){}

	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(StateComponent.class).get());
	}
	
	public void update(float deltatime)
	{
		for(Entity e:entities)
		{
			StateComponent sc = sm.get(e);
			VelocityComponent vc = vm.get(e);
		}
	}
	
}
