package me.xstrixu.bedrocklimbo

import com.nukkitx.nbt.NbtMap
import com.nukkitx.nbt.NbtUtils
import com.nukkitx.protocol.bedrock.BedrockServer
import com.nukkitx.protocol.bedrock.v448.Bedrock_v448
import me.xstrixu.bedrocklimbo.configuration.LimboConfiguration
import me.xstrixu.bedrocklimbo.console.TerminalConsole
import me.xstrixu.bedrocklimbo.extension.fixColors
import me.xstrixu.bedrocklimbo.player.PlayerManager
import me.xstrixu.bedrocklimbo.util.Logger
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File
import java.net.InetSocketAddress
import java.nio.file.Files

class BedrockLimbo {

    companion object {
        val CODEC = Bedrock_v448.V448_CODEC
        val BIOME_DEFINITIONS: NbtMap = javaClass.classLoader.getResourceAsStream("biome_definitions.dat").let {
            NbtUtils.createNetworkReader(it).readTag() as NbtMap
        }

        lateinit var instance: BedrockLimbo
            private set
    }

    private val console: TerminalConsole
    private val server: BedrockServer

    lateinit var configuration: LimboConfiguration
    var isShutdown = false

    init {
        instance = this

        loadConfig()

        console = TerminalConsole(this)
        server = BedrockServer(InetSocketAddress(configuration.address, configuration.port))
        server.handler = LimboServerHandler(this)

        start()
    }

    private fun loadConfig() {
        val file = File("config.yml")

        javaClass.classLoader.getResourceAsStream("config.yml")?.let {
            if(!file.exists()) {
                Files.copy(it, file.toPath())
            }
        }

        configuration = Yaml(Constructor(LimboConfiguration::class.java)).load(file.inputStream())
    }

    private fun start() {
        console.consoleThread.start()
        server.bind().join()
        Runtime.getRuntime().addShutdownHook(Thread(this::shutdown));

        Logger.info("Limbo nasluchuje na: &c${server.bindAddress.hostName}&8:&c${server.bindAddress.port}")
    }

    fun shutdown() {
        if(isShutdown) return

        PlayerManager.players.forEach{ it.disconnect(configuration.serverStopped.fixColors()) }

        isShutdown = true
    }
}