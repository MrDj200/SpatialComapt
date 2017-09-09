package me.dj.spatialserver.proxy;

import me.dj.spatialserver.events.EventHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 *
 * @author Dj
 */
@Mod.EventBusSubscriber
public class ServerProxy 
{
    public void preInit(FMLPreInitializationEvent e) 
    {
    }
    
    public void init(FMLInitializationEvent e) 
    {
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
    
    public void postInit(FMLPostInitializationEvent e) 
    {
    }
}
