package net.ghosrec35.overlay.client.render;

import java.util.HashMap;
import java.util.Map;

import net.ghosrec35.overlay.TextureOverlayEntry;
import net.ghosrec35.overlay.TextureOverlayEntry.OverlayInfo;
import net.ghosrec35.overlay.lib.Utils;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraftforge.client.IItemRenderer;

public class ItemIconRenderer implements IItemRenderer
{
	private HashMap<Item, TextureOverlayEntry> itemsSet;

	public ItemIconRenderer(Map<Item, TextureOverlayEntry> overlayEntries)
	{
		itemsSet = (HashMap<Item, TextureOverlayEntry>) overlayEntries;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return type == ItemRenderType.ENTITY && (helper == ItemRendererHelper.ENTITY_BOBBING || helper == ItemRendererHelper.ENTITY_ROTATION);
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		if (itemsSet.containsKey(item))
		{
			TextureOverlayEntry entry = itemsSet.get(item.getItem());
			Icon itemIcon = item.getIconIndex();
			
			if(!item.hasTagCompound())
			{
				item.setTagCompound(new NBTTagCompound());
			}
			
			switch (type)
			{
				case EQUIPPED:
				{
					RenderItemHelper.drawIconIn3D(item, itemIcon, false, 1, 1, 1);

					for (int i = 0; i < entry.getNumLayers(); i++)
					{
						OverlayInfo info = entry.getInfoByLayer(i);
						Icon iconToRender = entry.getIconFromLayer(i);
						int[] iconColor = Utils.getRGBFromHexStr(item.stackTagCompound.getString(info.getNBTKey()));

						if (iconColor != null)
						{
							RenderItemHelper.drawIconIn3D(item, iconToRender, false, iconColor[0], iconColor[1], iconColor[2]);
						}
					}
					break;
				}
				case EQUIPPED_FIRST_PERSON:
				{
					RenderItemHelper.drawIconIn3D(item, itemIcon, false, 1, 1, 1);

					for (int i = 0; i < entry.getNumLayers(); i++)
					{
						OverlayInfo info = entry.getInfoByLayer(i);
						Icon iconToRender = entry.getIconFromLayer(i);
						int[] iconColor = null;
						
						if(item.stackTagCompound.hasKey(info.getNBTKey()))
						{
							if(!item.stackTagCompound.getString(info.getNBTKey()).equalsIgnoreCase("Unset"))
							{
								iconColor = Utils.getRGBFromHexStr(item.stackTagCompound.getString(info.getNBTKey()));
							}
						}
						else
						{
							item.stackTagCompound.setString(info.getNBTKey(), "Unset");
						}
						
						if (iconColor != null)
						{
							RenderItemHelper.drawIconIn3D(item, iconToRender, false, iconColor[0], iconColor[1], iconColor[2]);
						}
					}
					break;
				}
				case ENTITY:
				{
					RenderItemHelper.drawIconIn3D(item, itemIcon, true, 1, 1, 1);

					for (int i = 0; i < entry.getNumLayers(); i++)
					{
						OverlayInfo info = entry.getInfoByLayer(i);
						Icon iconToRender = entry.getIconFromLayer(i);
						int[] iconColor = Utils.getRGBFromHexStr(item.stackTagCompound.getString(info.getNBTKey()));

						if (iconColor != null)
						{
							RenderItemHelper.drawIconIn3D(item, iconToRender, true, iconColor[0], iconColor[1], iconColor[2]);
						}
					}
					break;
				}
				default:
					break;
			}
		}
	}
}
