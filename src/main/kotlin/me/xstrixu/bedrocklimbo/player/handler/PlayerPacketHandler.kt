package me.xstrixu.bedrocklimbo.player.handler

import com.nukkitx.protocol.bedrock.BedrockServerSession
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler
import com.nukkitx.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket
import me.xstrixu.bedrocklimbo.BedrockLimbo
import me.xstrixu.bedrocklimbo.extension.fixColors
import me.xstrixu.bedrocklimbo.player.PlayerManager

class PlayerPacketHandler(private val limbo: BedrockLimbo, private val session: BedrockServerSession) : BedrockPacketHandler {

    override fun handle(packet: SetLocalPlayerAsInitializedPacket): Boolean {
        PlayerManager[session]?.let {
            limbo.configuration.joinMessage.forEach { message -> it.sendMessage(message.replace("{PLAYER_COUNT}", PlayerManager.players.size.toString()).fixColors()) }
        }

        return false
    }
}