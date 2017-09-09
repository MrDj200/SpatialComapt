package me.dj.spatialserver.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

/**
 *
 * @author Dj
 */
public class EventHandler 
{
    @SubscribeEvent
    public void onPlayerJoin(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            Entity player = event.getEntity();
            String playerName = player.getName();
            player.sendMessage(new TextComponentString("Hello " + playerName + "! SpatialServerMod is loaded!"));
        }
    }
    
    @SubscribeEvent
    public void onPlayerDimChange(PlayerEvent.PlayerChangedDimensionEvent event)
    {
        Entity player = event.player;
        /*WorldServer world = DimensionManager.getWorld(event.toDim);
        BlockPos pos = player.getPosition();
        Chunk curChunk = world.getChunkFromBlockCoords(pos);
        BiomeProvider biomeProvider = world.getBiomeProvider();
        Biome biome = curChunk.getBiome(player.getPosition(), world.getBiomeProvider());
        String biomeName = biome.getBiomeName();*/
        int myDim = event.toDim;
        
        player.sendMessage(new TextComponentString("You entered the Dim #" + myDim));
        if( myDim == 2 )
        {
            player.sendMessage(new TextComponentString("OH NOES! You entered an Instant Dungeon :O"));
        }        
    }
}
