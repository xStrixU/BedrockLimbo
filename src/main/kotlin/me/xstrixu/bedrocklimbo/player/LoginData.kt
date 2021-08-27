package me.xstrixu.bedrocklimbo.player

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.nimbusds.jose.JWSObject
import com.nukkitx.protocol.bedrock.packet.LoginPacket
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

class LoginData(val XUID: String, val name: String) {

    companion object {
        fun create(packet: LoginPacket): LoginData {
            val jsonObject = JsonParser.parseReader(InputStreamReader(ByteArrayInputStream(packet.chainData.toByteArray()))) as JsonObject
            val chainData = jsonObject.getAsJsonArray("chain")

            val jwt = JWSObject.parse(chainData.get(chainData.size() - 1).asString)
            val payload = JsonParser.parseString(jwt.payload.toString()) as JsonObject
            val extraData = payload.get("extraData").asJsonObject

            return LoginData(extraData.get("XUID").asString, extraData.get("displayName").asString)
        }
    }
}