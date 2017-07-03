package com.gamejam.engine;

import com.badlogic.ashley.core.Component;

public class CollisionComponent implements Component
{
	public float x, y, w, h;
	
	public CollisionComponent()
	{
		x = y = w = h = 0;
	}
	
	public CollisionComponent(float x, float y, float w, float h)
	{
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	
}
