package com.gamejam.engine;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Align;

public class ScreenWriter
{
	public FreeTypeFontGenerator ttf;
	public FreeTypeFontParameter fp;
	public BitmapFont font;
	public Color c;
	public SpriteBatch batch;
	public int alignment;
	
	public LinkedList<TextBox> texts;
	
	public ScreenWriter(String filename, SpriteBatch batch)
	{
		texts = new LinkedList<TextBox>();
		ttf = new FreeTypeFontGenerator(Gdx.files.internal(filename));
		fp = new FreeTypeFontParameter();
		this.batch = batch;
		alignment = Align.topLeft;
	}
	
	public ScreenWriter(SpriteBatch batch)
	{
		texts = new LinkedList<TextBox>();
		this.batch = batch;
		alignment = Align.topLeft;
	}
	
	public void setTTF(String filename)
	{
		ttf = new FreeTypeFontGenerator(Gdx.files.internal(filename));
		fp = new FreeTypeFontParameter();
	}
	
	
	public void setFontSize(int size)
	{
		fp.size = size;
		fp.characters="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.!?'>[]1234567890:,/";
		font = ttf.generateFont(fp);
	}
	
	public void setColor(Color c)
	{
		this.c = c;
	}
	
	public void writeScreen(TextBox b)
	{
	}
	
	public void update()
	{

		
	}
	
	public void dispose()
	{
		font.dispose();
		ttf.dispose();
	}

}
