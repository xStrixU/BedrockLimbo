package me.xstrixu.bedrocklimbo.command.commands

import me.xstrixu.bedrocklimbo.command.Command
import me.xstrixu.bedrocklimbo.command.CommandManager
import me.xstrixu.bedrocklimbo.util.Logger

class HelpCommand : Command("pomoc", "Wyswietla wszystkie dostepne komendy") {

    override fun onCommand(args: Array<String>) {
        Logger.info("&6Dostepne komendy:")

        CommandManager.commandMap.values.forEach { Logger.info(" &8» &6/${it.name} &c- ${it.description}") }
    }
}