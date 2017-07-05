package com.gamejam.engine.components;

import com.badlogic.ashley.core.Component;
import com.gamejam.engine.Quad;

public class SpatialComponent implements Component
{
	public Quad quad;
	public float theta;
	public float scaleX,scaleY;
	
	public SpatialComponent(float x, float y)
	{
		quad = new Quad();
		this.quad.x = x;
		this.quad.y = y;
		theta = scaleX = scaleY = 0;
	}
	
	public SpatialComponent(float x, float y, float w, float h)
	{
		quad = new Quad(x,y,w,h);
		theta = scaleX = scaleY = 0;
	}

}
