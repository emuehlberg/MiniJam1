package com.gamejam.engine.assets;

public abstract class Asset
{
	public String assetName;
	int refCount;
	
	public Asset(String name)
	{
		this.assetName = name;
		refCount = 0;
	}
	
	public int getRefCount()
	{
		return refCount;
	}
	
	public abstract Object getAsset();
	
	public void release()
	{
		refCount--;
	}
	
	public abstract void dispose();
	
}
