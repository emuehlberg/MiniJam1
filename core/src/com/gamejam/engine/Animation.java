package com.gamejam.engine;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation
{
	
	public HashMap<String,TextureRegion> animmap;
	
	public Animation(String filename)
	{
		animmap = new HashMap<String,TextureRegion>();
		loadMap(filename);
	}
	
	public void loadMap(String filename)
	{
		
	}

}
