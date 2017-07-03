package com.gamejam.engine;

import com.badlogic.ashley.core.Component;

public class PhysicsComponent implements Component
{
	public float ax;
	public float ay;

	public PhysicsComponent(float ax, float ay)
	{
		this.ax = ax;
		this.ay = ay;
	}

}
