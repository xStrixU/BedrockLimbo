package me.xstrixu.bedrocklimbo.player

import com.nukkitx.protocol.bedrock.BedrockServerSession
import com.nukkitx.protocol.bedrock.packet.DisconnectPacket
import com.nukkitx.protocol.bedrock.packet.TextPacket

data class Player(val session: BedrockServerSession, val loginData: LoginData) {

    val XUID get() = loginData.XUID
    val name get() = loginData.name

    fun sendMessage(message: String) {
        TextPacket().apply {
            type = TextPacket.Type.RAW
            sourceName = ""
            xuid = ""
            this.message = message

            session.sendPacket(this)
        }
    }

    fun disconnect(message: String) {
        DisconnectPacket().apply {
            kickMessage = message

            session.sendPacketImmediately(this)
        }
        session.disconnect(message)
    }
}