package me.xstrixu.bedrocklimbo.console

class ConsoleThread(val console: TerminalConsole) : Thread() {

    override fun run() {
        console.start()
    }
}