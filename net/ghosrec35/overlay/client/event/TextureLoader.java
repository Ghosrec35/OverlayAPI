package net.ghosrec35.overlay.client.event;

import java.util.HashMap;
import java.util.Map;

import net.ghosrec35.overlay.OAPILog;
import net.ghosrec35.overlay.TextureOverlayEntry;
import net.ghosrec35.overlay.UnattainedTextureMapException;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.Item;
import net.minecraft.util.Icon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.EventPriority;
import net.minecraftforge.event.ForgeSubscribe;

public class TextureLoader 
{
	public Map<Item, TextureOverlayEntry> entryMap;
	
	public TextureLoader(HashMap<Item, TextureOverlayEntry> map)
	{
		entryMap = map;
	}
	
	private static TextureMap texMap;
	
	@ForgeSubscribe(priority = EventPriority.LOWEST)
	public void onTextureStitch(TextureStitchEvent.Pre event)
	{
		texMap = event.map;
		
		if(texMap.textureType == 1)
		{
			for(Item item : entryMap.keySet())
			{
				TextureOverlayEntry entry = entryMap.get(item);
				for(int i = 0; i < entry.getNumLayers(); i++)
				{
					OAPILog.severe("Reaching this point!");
					entry.addIcon(loadTexture(entry.getInfoByLayer(i).getResLocation()));
				}
			}
		}
	}
	
	private Icon loadTexture(String str)
	{
		return texMap.registerIcon(str);
	}
	
	public static TextureMap getTextureMap()
	{
		if(texMap != null)
			return texMap;
		else 
			throw new UnattainedTextureMapException("Attempting to access unavailable texture map! Either wait until a later time to access it or, if it should be the case, report this errror to the Mod Creator!");
	}
}
