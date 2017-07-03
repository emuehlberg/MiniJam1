package com.gamejam.engine;

import com.badlogic.ashley.core.Component;

public class TimedComponent implements Component
{
	public float time;
	
	public TimedComponent()
	{
		time = 0;
	}

	public TimedComponent(float time)
	{
		this.time = time;
	}
	
}
