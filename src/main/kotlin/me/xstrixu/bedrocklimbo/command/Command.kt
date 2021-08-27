package me.xstrixu.bedrocklimbo.command

abstract class Command(val name: String, val description: String) {

    abstract fun onCommand(args: Array<String>)
}