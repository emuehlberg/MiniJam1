package com.gamejam.engine;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class JamCam
{
	OrthographicCamera camera;
	Entity follow;
	
	public JamCam(OrthographicCamera cam)
	{
		camera = cam;
		follow = null;
	}
	
	public void update()
	{
		if(follow!=null)
		{
			PositionComponent pc = follow.getComponent(PositionComponent.class);
			camera.position.x = pc.x;
			camera.position.y = pc.y;
			camera.update();
		}
	}

	public void setFollow(Entity e)
	{
		follow = e;
	}
	
}
