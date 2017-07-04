package com.gamejam.engine.components;

import java.util.HashMap;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gamejam.engine.Animation;

public class AnimationComponent implements Component
{
	public Animation animation;
	public AnimationComponent(String filename)
	{
		animation = new Animation(filename);
	}
	
}
