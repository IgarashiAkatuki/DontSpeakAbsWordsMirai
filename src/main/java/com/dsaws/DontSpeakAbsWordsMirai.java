package com.dsaws;

import com.dsaws.commands.UserCommand;
import net.mamoe.mirai.console.command.CommandManager;
import net.mamoe.mirai.console.plugin.jvm.JavaPlugin;
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescriptionBuilder;

public final class DontSpeakAbsWordsMirai extends JavaPlugin {
    public static final DontSpeakAbsWordsMirai INSTANCE = new DontSpeakAbsWordsMirai();

    private DontSpeakAbsWordsMirai() {
        super(new JvmPluginDescriptionBuilder("com.dsaws", "0.0.1")
                .name("DontSpeakAbsWordsMirai")
                .info("DontSpeakAbsWords-Mirai-Console-Plugin")
                .author("Midsummra")
                .build());
    }

    @Override
    public void onEnable() {
        getLogger().info("DontSpeakAbsWordsPlugin loaded!");
        CommandManager.INSTANCE.registerCommand(UserCommand.INSTANCE,true);
    }
}