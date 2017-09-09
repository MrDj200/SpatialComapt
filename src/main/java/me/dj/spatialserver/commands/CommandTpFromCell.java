package me.dj.spatialserver.commands;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

/**
 *
 * @author Dj
 */
public class CommandTpFromCell extends CommandBase
{
    private final String commandName = "tpFromCell";
    
    public CommandTpFromCell()
    {
        aliases = Lists.newArrayList(commandName, "tfc");
    }
    private final List<String> aliases;
    
    @Override
    public int getRequiredPermissionLevel()
    {
	return 0;
    }

    @Override
    public String getName() 
    {
        return commandName;
    }    
    
    @Override
    public String getUsage(ICommandSender sender) 
    {
        return "/" + commandName + "  |  (Only usable in AE2 cell dim)";
    }

    @Override
    public List<String> getAliases() 
    {
        return aliases;
    }

    @Override
    public void execute( MinecraftServer server, ICommandSender sender,  String[] args) throws CommandException
    {
        if (sender instanceof EntityPlayer)
        {
            if( sender.getEntityWorld().getHeight() < 100)
            {
                server.sendMessage(new TextComponentString("Not Implemented yet! Sry :/"));
            }
        }
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender)
    {
        return true;
    }
    
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos)
    {
        List<String> suggestions = new ArrayList<>();

        if (args.length == 1)
        {
            suggestions.add("dunno");
        }

        return getListOfStringsMatchingLastWord(args, suggestions);
    }
}
