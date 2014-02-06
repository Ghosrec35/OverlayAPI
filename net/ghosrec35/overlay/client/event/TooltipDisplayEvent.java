package net.ghosrec35.overlay.client.event;

import java.util.HashMap;

import net.ghosrec35.overlay.TextureOverlayEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;

public class TooltipDisplayEvent
{
	private HashMap<Item, TextureOverlayEntry> map;
	
	public TooltipDisplayEvent(HashMap<Item, TextureOverlayEntry> evtMap)
	{
		map = evtMap;
	}
	
	@ForgeSubscribe
	public void onTooltipEvent(ItemTooltipEvent event)
	{
		TextureOverlayEntry entry = map.get(event.itemStack.getItem());
		if((entry.details & TextureOverlayEntry.USE_TOOLTIP) == 1)
		{
			if(!event.itemStack.hasTagCompound())
			{
				event.itemStack.setTagCompound(new NBTTagCompound());
				for(int i = 0; i < entry.getNumLayers(); i++)
					event.itemStack.stackTagCompound.setString(entry.getInfoByLayer(i).getNBTKey(), "Unset");
			}
			
			if((entry.details & TextureOverlayEntry.REQUIRE_TOOLTIP_KEY_PRESS) == 1)
			{	
				if(GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak))
				{
					for(int i = 0; i < entry.getNumLayers(); i++)
					{
						event.toolTip.add("Layer " + i + " Color (" + entry.getInfoByLayer(i).getNBTKey() + "): " + event.itemStack.stackTagCompound.getString(entry.getInfoByLayer(i).getNBTKey()));
					}
				}
			}
			else
			{
				event.toolTip.add("<Hold shift to view data>");
			}
		}
	}
}
