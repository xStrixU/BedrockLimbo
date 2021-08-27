package me.xstrixu.bedrocklimbo.player.handler

import com.nukkitx.protocol.bedrock.BedrockServerSession
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler
import com.nukkitx.protocol.bedrock.packet.LoginPacket
import com.nukkitx.protocol.bedrock.packet.PlayStatusPacket
import com.nukkitx.protocol.bedrock.packet.ResourcePacksInfoPacket
import me.xstrixu.bedrocklimbo.BedrockLimbo
import me.xstrixu.bedrocklimbo.player.LoginData

class LoginPacketHandler(private val limbo: BedrockLimbo, private val session: BedrockServerSession) : BedrockPacketHandler {

    override fun handle(packet: LoginPacket): Boolean {
        session.packetCodec = BedrockLimbo.CODEC

        PlayStatusPacket().apply {
            status = PlayStatusPacket.Status.LOGIN_SUCCESS

            session.sendPacket(this)
        }

        ResourcePacksInfoPacket().apply {
            isForcedToAccept = false
            isScriptingEnabled = false

            session.sendPacket(this)
        }

        session.packetHandler = ResourcePackPacketHandler(limbo, session, LoginData.create(packet))
        return false
    }
}