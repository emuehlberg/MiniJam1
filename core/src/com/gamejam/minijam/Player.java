package com.gamejam.minijam;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.gamejam.engine.GEngine;
import com.gamejam.engine.components.DisplayComponent;
import com.gamejam.engine.components.PositionComponent;
import com.gamejam.engine.components.StateComponent;
import com.gamejam.engine.components.TimedComponent;
import com.gamejam.engine.components.VelocityComponent;

public class Player implements InputProcessor
{
	Entity self;
	GEngine eng;
	Vector3 mouse;
	float mx, my;
	
	public Player(Entity ent, GEngine eng)
	{
		self = ent;
		this.eng = eng;
		mx = my = 0;
		mouse = new Vector3();
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(button == Input.Buttons.LEFT)
		{
			VelocityComponent vc = self.getComponent(VelocityComponent.class);
			StateComponent sc = self.getComponent(StateComponent.class);
			PositionComponent pc = self.getComponent(PositionComponent.class);

			Vector3 mouse = eng.getMousePosition();
			
			mx = (int)mouse.x;
			my = (int)mouse.y;
			
			float angle = -eng.getTheta(eng.getEntityVector(self),mouse)+180;
			
			DisplayComponent dc = self.getComponent(DisplayComponent.class);
			dc.rotation = angle;
			
			eng.generateBullet(self, mouse, 5);
			
			return true;
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
