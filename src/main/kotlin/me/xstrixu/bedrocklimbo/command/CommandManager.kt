package me.xstrixu.bedrocklimbo.command

import me.xstrixu.bedrocklimbo.command.commands.HelpCommand
import me.xstrixu.bedrocklimbo.command.commands.ListCommand
import me.xstrixu.bedrocklimbo.command.commands.StopCommand
import me.xstrixu.bedrocklimbo.util.Logger
import kotlin.collections.HashMap

object CommandManager {

    val commandMap = HashMap<String, Command>()

    init {
        listOf(
            HelpCommand(),
            ListCommand(),
            StopCommand()
        ).forEach { commandMap.put(it.name.lowercase(), it) }
    }

    fun handleCommand(fullCommand: String) {
        val splitted = fullCommand.split(" ")

        if(splitted.isEmpty()) {
            return
        }

        commandMap[(splitted[0].lowercase())]?.onCommand(splitted.drop(1).toTypedArray()) ?: run { Logger.info("Ta komenda nie istnieje! Uzyj polecenia &c/pomoc") }
    }
}