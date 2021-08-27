package me.xstrixu.bedrocklimbo.command.commands

import me.xstrixu.bedrocklimbo.command.Command
import me.xstrixu.bedrocklimbo.player.PlayerManager
import me.xstrixu.bedrocklimbo.util.Logger

class ListCommand : Command("list", "Pokazuje liste graczy na limbo") {

    override fun onCommand(args: Array<String>) {
        val players = HashSet<String>().also { PlayerManager.players.forEach { player -> it.add(player.name) } }

        Logger.info("&6Lista graczy (&c${players.size}&6): &c${players.joinToString("&6, &c")}")
    }
}