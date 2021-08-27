package me.xstrixu.bedrocklimbo

import com.nukkitx.protocol.bedrock.BedrockPong
import com.nukkitx.protocol.bedrock.BedrockServerEventHandler
import com.nukkitx.protocol.bedrock.BedrockServerSession
import me.xstrixu.bedrocklimbo.extension.fixColors
import me.xstrixu.bedrocklimbo.player.PlayerManager
import me.xstrixu.bedrocklimbo.player.handler.LoginPacketHandler
import me.xstrixu.bedrocklimbo.util.Logger
import java.net.InetSocketAddress

class LimboServerHandler(val limbo: BedrockLimbo) : BedrockServerEventHandler {

    private val pong = BedrockPong().apply {
        val configuration = limbo.configuration

        edition = "MCPE"
        motd = configuration.motd.fixColors()
        playerCount = PlayerManager.players.size
        maximumPlayerCount = configuration.maxPlayers
        gameType = "Survival"
        protocolVersion = BedrockLimbo.CODEC.protocolVersion
        isNintendoLimited = false
        ipv4Port = configuration.port
        ipv6Port = configuration.port
    }

    override fun onConnectionRequest(address: InetSocketAddress) = true

    override fun onQuery(address: InetSocketAddress) = pong.apply { playerCount = PlayerManager.players.size }

    override fun onSessionCreation(session: BedrockServerSession) {
        session.packetHandler = LoginPacketHandler(limbo, session)
        session.addDisconnectHandler {
            Logger.info("&6Gracz &c${PlayerManager[session]?.name} &6rozlaczyl sie!")
            PlayerManager.removePlayer(session)
        }
    }
}