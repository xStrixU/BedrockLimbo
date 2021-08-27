package me.xstrixu.bedrocklimbo.console

import me.xstrixu.bedrocklimbo.BedrockLimbo
import me.xstrixu.bedrocklimbo.command.CommandManager
import net.minecrell.terminalconsole.SimpleTerminalConsole

class TerminalConsole(private val limbo: BedrockLimbo) : SimpleTerminalConsole() {

    val consoleThread = ConsoleThread(this)

    override fun isRunning() = !limbo.isShutdown

    override fun runCommand(command: String) = CommandManager.handleCommand(command)

    override fun shutdown() = limbo.shutdown()
}