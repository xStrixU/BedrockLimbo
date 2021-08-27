package me.xstrixu.bedrocklimbo.player

import com.nukkitx.protocol.bedrock.BedrockServerSession

object PlayerManager {

    val players = HashSet<Player>()

    fun removePlayer(session: BedrockServerSession) {
        players.removeIf { it.session == session }
    }

    operator fun get(session: BedrockServerSession) = players.find { it.session == session }
}