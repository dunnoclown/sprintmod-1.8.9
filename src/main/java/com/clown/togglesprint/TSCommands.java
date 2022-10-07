package com.clown.togglesprint;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class TSCommands extends CommandBase {

    private List<String> aliases;

    public TSCommands() {
        aliases = new ArrayList<String>();
        aliases.add("ts");
    }

    @Override
    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public String getCommandName() {
        return "sprint";
    }

    @Override
    public String getCommandUsage(ICommandSender arg0) {
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 1)
            return getListOfStringsMatchingLastWord(args, "enable", "disable");
        return null;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

        if(args.length == 0) {
            ToggleSprint.enabled = !ToggleSprint.enabled;
        }

        if(args.length > 0) {
            if(args[0].equals("enable"))
                ToggleSprint.enabled = true;
            else if(args[0].equals("disable")) {
                ToggleSprint.enabled = false;
                ToggleSprint.toggled = false;
            }else if(args[0].equals("toggle"))
                ToggleSprint.enabled = !ToggleSprint.enabled;
            else {
                sender.addChatMessage(new ChatComponentText("sprint " + EnumChatFormatting.RED + "error" + EnumChatFormatting.WHITE + " usage: /sprint toggle, enable or disable"));
                return;
            }
        }

        if(ToggleSprint.enabled)
            sender.addChatMessage(new ChatComponentText("sprint " + EnumChatFormatting.DARK_GREEN + "enabled"));
        else
            sender.addChatMessage(new ChatComponentText("sprint " + EnumChatFormatting.DARK_RED + "disabled"));
    }
}
