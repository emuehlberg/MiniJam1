package com.gamejam.engine;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gamejam.engine.enums.Facing;

public class DisplaySystem extends EntitySystem
{
	private ImmutableArray<Entity> entities;
	private ImmutableArray<Entity> text;
	
	private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);
	private ComponentMapper<DisplayComponent> dm = ComponentMapper.getFor(DisplayComponent.class);
	private ComponentMapper<TextComponent> tm = ComponentMapper.getFor(TextComponent.class);
	
	private GEngine eng;
	
	public DisplaySystem(GEngine eng)
	{
		this.eng = eng;
	}
	
	public void addedToEngine(Engine engine)
	{
		entities = engine.getEntitiesFor(Family.all(PositionComponent.class, DisplayComponent.class).get());
		text = engine.getEntitiesFor(Family.all(TextComponent.class).get());
	}
	
	public void update(float deltaTime)
	{
		Gdx.gl.glClearColor(1, 1, 1, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		eng.batch.setProjectionMatrix(eng.camera.combined);
		
		//Draw Images
		eng.batch.begin();
		for(Entity e:entities)
		{
			PositionComponent pc = pm.get(e);
			DisplayComponent dc = dm.get(e);
			eng.batch.draw(dc.getTexture(), pc.x, pc.y);
		}
		eng.batch.end();
		
		//Draw Text
		for(Entity e:text)
		{
			TextComponent tc = tm.get(e);
			PositionComponent pc = pm.get(e);
			eng.batch.begin();
			eng.sw.font.setColor(tc.c);
			eng.sw.font.draw(eng.batch, tc.text, pc.x, pc.y, tc.width, tc.align, true);
			eng.batch.end();
		}
	}
	
}
