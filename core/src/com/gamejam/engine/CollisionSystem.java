package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

public class CollisionSystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> sentities;

	public static final int TOP = 1;
	public static final int BOTTOM = 2;
	public static final int LEFT = 4;
	public static final int RIGHT = 8;
	
	private ComponentMapper<CollisionComponent> cm = ComponentMapper.getFor(CollisionComponent.class);
	
	public CollisionSystem(){}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(VelocityComponent.class,CollisionComponent.class).get());
		sentities = engine.getEntitiesFor(Family.all(CollisionComponent.class).get());
	}
	
	public void update(float deltatime)
	{
		for(Entity a:entities)
		{
			CollisionComponent ac = a.getComponent(CollisionComponent.class);
			IDComponent ida = a.getComponent(IDComponent.class);
			PositionComponent pc = a.getComponent(PositionComponent.class);
			VelocityComponent vc = a.getComponent(VelocityComponent.class);
			for(Entity b:sentities)
			{
				if(!a.equals(b))
				{					
					IDComponent idb = b.getComponent(IDComponent.class);
					CollisionComponent bc = b.getComponent(CollisionComponent.class);
					if(collide(ac,bc))
					{
						switch(collisionDirection(ac,bc))
						{
						case BOTTOM:
							pc.y = bc.y - ac.h;
							ac.y = pc.y;
							break;
						case TOP:
							pc.y = bc.y + bc.h;
							ac.y = pc.y;				
							vc.vy = 0;
							break;
						case RIGHT:
							pc.x = bc.x + bc.w;
							ac.x = pc.x;
							break;
						case LEFT:
							pc.x = bc.x - ac.w;
							ac.x = pc.x;
							break;
							
						}
						
					}
				}
			}
		}
	}
	
	public boolean collide(CollisionComponent a, CollisionComponent b)
	{
		if(a.x>b.x+b.w || a.x+a.w<b.x)
			return false;
		if(a.y>b.y+b.h || a.y+a.h<b.y)
			return false;
		
		return true;
	}
	
	public int collisionDirection(CollisionComponent a, CollisionComponent b)
	{
		float x = (a.x+(a.w/2)) - (b.x+(b.w/2));
		float y = (a.y+(a.h/2)) - (b.y+(b.h/2));
		
		if(Math.abs(x)>Math.abs(y))
		{
			if(x > 0)
			{
				return RIGHT;
			}
			else
			{
				return LEFT;
			}
		}
		else
		{
			if(y > 0)
			{
				return TOP;
			}
			else
			{
				return BOTTOM;
			}
		}
	}
	
}
