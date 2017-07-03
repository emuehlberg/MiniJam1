package com.gamejam.engine;

import com.badlogic.ashley.core.Component;
import com.gamejam.engine.enums.Facing;

public class StateComponent implements Component
{
	public State state;
	
	
	public StateComponent()
	{
		state = new State();
	}
	

}
