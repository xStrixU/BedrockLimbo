package me.xstrixu.bedrocklimbo.configuration

class LimboConfiguration {

    lateinit var address: String
    var port: Int = 0
    var maxPlayers: Int = 0
    lateinit var motd: String
    lateinit var joinMessage: List<String>
    lateinit var serverStopped: String
}