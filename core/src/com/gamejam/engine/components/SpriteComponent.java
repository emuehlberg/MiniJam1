package com.gamejam.engine.components;

import com.badlogic.ashley.core.Component;

public class SpriteComponent implements Component
{
	public float rotation;
	public float oX,oY;
	public float w,h;
	public float scaleX = 1, scaleY = 1;
	
	public SpriteComponent()
	{
		rotation = 0;
		oX = oY = 0;
	}
	
	public SpriteComponent(float r)
	{
		rotation = r;
	}

}
