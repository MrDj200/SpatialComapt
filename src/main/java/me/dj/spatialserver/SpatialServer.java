package me.dj.spatialserver;

import java.lang.reflect.Field;
import me.dj.spatialserver.proxy.ServerProxy;
import me.dj.spatialserver.utils.LogHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistryNamespaced;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/**
 *
 * @author Dj
 */
@Mod(modid = SpatialServer.MODID, name = SpatialServer.NAME, version = SpatialServer.VERSION, acceptableRemoteVersions = "*", acceptedMinecraftVersions = "*") 
public class SpatialServer 
{
    public static ServerProxy proxy = new ServerProxy();
    public static final String MODID = "spatialservermod";
    public static final String NAME = "Spatial Server Mod";
    public static final String VERSION = "1.3.1";
    
    @EventHandler
    void preInit( FMLPreInitializationEvent event )
    {
        proxy.preInit(event);
    }
    
    @EventHandler
    void init( FMLInitializationEvent event )
    {
        proxy.init(event);
        registerTiles();
    }
    
    @EventHandler
    void postInit( FMLPostInitializationEvent event )
    {
        proxy.postInit(event);
    }
    
    
    public void registerTiles()
    {
        Field field = ReflectionHelper.findField(TileEntity.class, "REGISTRY", "field_190562_f");
        try
        {
            field.setAccessible(true);
            RegistryNamespaced<ResourceLocation, Class<? extends TileEntity>> reg = (RegistryNamespaced) field.get(null);
            for(ResourceLocation location : reg.getKeys())
            {
                Class<? extends TileEntity> entity = reg.getObject(location);
                if ( entity.getName().contains("funkylocomotion") || (entity.getName().toLowerCase().contains("sealed") && entity.getName().toLowerCase().contains("galacticraft")) )
                    continue;
                FMLInterModComms.sendMessage( "appliedenergistics2", "whitelist-spatial", entity.getName());
                LogHelper.info("Registered TE: " + entity.getName());
            }
        }
        catch ( SecurityException | IllegalArgumentException | IllegalAccessException ex)
        {
            LogHelper.error("An unexpected error occured ", ex);
        }
    }
    
}
