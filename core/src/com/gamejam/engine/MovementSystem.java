package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.gamejam.engine.enums.Facing;

public class MovementSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	
	private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	
	public MovementSystem(){}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(VelocityComponent.class,PositionComponent.class).get());
	}
	
	public void update(float deltaTime)
	{
		for(Entity e:entities)
		{
			VelocityComponent vc = vm.get(e);
			PositionComponent pc = pm.get(e);
			CollisionComponent cc = e.getComponent(CollisionComponent.class);
			StateComponent sc = e.getComponent(StateComponent.class);
			
			if(sc!=null)
			{
				if(sc.state.Moving)			
				{
					if(sc.state.facing==Facing.Left)
					{
						vc.vx-=1f;
						if(vc.vx<-3f)
							vc.vx=-3f;
					}
					if(sc.state.facing==Facing.Right)
					{
						vc.vx+=1f;
						if(vc.vx>3f)
							vc.vx=3f;
					}
				}
				else
					vc.vx=0;
			}
		
			pc.x += vc.vx;
			pc.y += vc.vy;

			
			if(cc!=null)
			{				
				cc.x = pc.x;
				cc.y = pc.y;
			}
		}
	}
}
