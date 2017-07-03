package com.gamejam.engine;

import com.gamejam.engine.enums.Facing;

public class State
{
	public Facing facing;
	public boolean Jumping;
	public boolean Moving;
	public boolean Dead;
	public float jumptime;
	
	public State()
	{
		facing = Facing.Right;
		Jumping = false;
		Moving = false;
		Dead = false;
	}

}
