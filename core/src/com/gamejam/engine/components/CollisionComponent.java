package com.gamejam.engine.components;

import com.badlogic.ashley.core.Component;
import com.gamejam.engine.enums.CollisionType;

public class CollisionComponent implements Component
{
	public float x, y, w, h;
	public CollisionType type;
	
	public CollisionComponent()
	{
		x = y = w = h = 0;
		type = CollisionType.None;
	}
	
	public CollisionComponent(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		type = CollisionType.None;
	}
	
	public CollisionComponent(float x, float y, float w, float h, CollisionType t)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		type = t;
	}
}
