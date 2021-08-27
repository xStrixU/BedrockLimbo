package me.xstrixu.bedrocklimbo.command.commands

import me.xstrixu.bedrocklimbo.BedrockLimbo
import me.xstrixu.bedrocklimbo.command.Command
import me.xstrixu.bedrocklimbo.util.Logger

class StopCommand : Command("stop", "Zatrzymuje limbo") {

    override fun onCommand(args: Array<String>) {
        Logger.info("&6Zatrzymywanie limbo...")
        BedrockLimbo.instance.shutdown()
    }
}