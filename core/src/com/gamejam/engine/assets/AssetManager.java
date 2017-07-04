package com.gamejam.engine.assets;

import java.util.HashMap;
import java.util.LinkedList;

public class AssetManager
{
	private HashMap<String, Asset> assets;
	private HashMap<String, Class> assetclass;
	
	public AssetManager()
	{
		assets = new HashMap<String, Asset>();
		assetclass = new HashMap<String, Class>();
	}
	
	public void addAsset(Asset a)
	{
		assets.put(a.assetName, a);
		assetclass.put(a.assetName, a.getClass());
	}
	
	public int getRefCount(String assetname)
	{
		return assets.get(assetname).getRefCount();
	}
	
	public Class getClass(String assetname)
	{
		return assetclass.get(assetname);
	}
	
	public Object getAsset(String assetname)
	{
		return assets.get(assetname).getAsset();
	}
	
	public void releaseAsset(String assetname)
	{
		assets.get(assetname).release();
	}
	
	
	public void disposeAsset(String assetname)
	{
		Asset a = assets.get(assetname);
		if(a.getRefCount() == 0)
		{
			assets.remove(assetname);
			a.dispose();
		}
	}
	
	public void disposeAsset(String assetname, boolean override)
	{
		Asset a = assets.get(assetname);
		if(a.getRefCount() == 0 || override)
		{
			assets.remove(assetname);
			a.dispose();
		}
	}
	
	
}
