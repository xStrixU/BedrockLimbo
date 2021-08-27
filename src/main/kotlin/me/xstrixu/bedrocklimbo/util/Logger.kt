package me.xstrixu.bedrocklimbo.util

import me.xstrixu.bedrocklimbo.extension.fixColors
import org.apache.logging.log4j.LogManager

object Logger {

    private val log = LogManager.getLogger(Logger.javaClass)

    fun info(message: String) = log.info(message.fixColors())
}