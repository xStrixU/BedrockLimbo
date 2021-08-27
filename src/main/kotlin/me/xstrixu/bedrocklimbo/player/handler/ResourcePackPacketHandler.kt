package me.xstrixu.bedrocklimbo.player.handler

import com.nukkitx.math.vector.Vector2f
import com.nukkitx.math.vector.Vector3f
import com.nukkitx.math.vector.Vector3i
import com.nukkitx.protocol.bedrock.BedrockServerSession
import com.nukkitx.protocol.bedrock.data.*
import com.nukkitx.protocol.bedrock.handler.BedrockPacketHandler
import com.nukkitx.protocol.bedrock.packet.*
import me.xstrixu.bedrocklimbo.BedrockLimbo
import me.xstrixu.bedrocklimbo.player.Player
import me.xstrixu.bedrocklimbo.player.PlayerManager
import me.xstrixu.bedrocklimbo.player.LoginData
import me.xstrixu.bedrocklimbo.util.Logger

class ResourcePackPacketHandler(
    private val limbo: BedrockLimbo,
    private val session: BedrockServerSession,
    private val loginData: LoginData
) : BedrockPacketHandler {

    override fun handle(packet: ResourcePackClientResponsePacket): Boolean {
        when(packet.status) {
            ResourcePackClientResponsePacket.Status.HAVE_ALL_PACKS -> {
                ResourcePackStackPacket().apply {
                    isForcedToAccept = false
                    isExperimentsPreviouslyToggled = false
                    gameVersion = "*"

                    session.sendPacket(this)
                }
            }

            ResourcePackClientResponsePacket.Status.COMPLETED -> {
                StartGamePacket().apply {
                    uniqueEntityId = 1
                    runtimeEntityId = 1
                    playerGameType = GameType.SURVIVAL
                    playerPosition = Vector3f.from(0f, 0f, 0f)
                    rotation = Vector2f.from(1f, 1f)
                    seed = -1
                    dimensionId = 0
                    generatorId = 1
                    levelGameType = GameType.SURVIVAL
                    difficulty = 1
                    defaultSpawn = Vector3i.ZERO
                    isAchievementsDisabled = false
                    currentTick = -1
                    eduEditionOffers = 0
                    isEduFeaturesEnabled = false
                    rainLevel = 0f
                    lightningLevel = 0f
                    isMultiplayerGame = true
                    isBroadcastingToLan = true
                    platformBroadcastMode = GamePublishSetting.PUBLIC
                    xblBroadcastMode = GamePublishSetting.PUBLIC
                    isCommandsEnabled = true
                    isTexturePacksRequired = false
                    isBonusChestEnabled = false
                    isStartingWithMap = false
                    isTrustingPlayers = true
                    defaultPlayerPermission = PlayerPermission.MEMBER
                    serverChunkTickRange = 4
                    isBehaviorPackLocked = false
                    isResourcePackLocked = false
                    isFromLockedWorldTemplate = false
                    isUsingMsaGamertagsOnly = false
                    isFromWorldTemplate = false
                    isWorldTemplateOptionLocked = false
                    authoritativeMovementMode = AuthoritativeMovementMode.CLIENT

                    val settings = SyncedPlayerMovementSettings()

                    settings.movementMode = AuthoritativeMovementMode.CLIENT
                    settings.rewindHistorySize = 0
                    settings.isServerAuthoritativeBlockBreaking = false

                    playerMovementSettings = settings
                    vanillaVersion = "*"
                    levelId = "world"
                    levelName = "world"
                    premiumWorldTemplateId = "00000000-0000-0000-0000-000000000000"
                    currentTick = 0
                    enchantmentSeed = 0
                    multiplayerCorrelationId = ""
                    serverEngine = ""

                    session.sendPacket(this)
                }

                CreativeContentPacket().apply {
                    contents = arrayOfNulls(0)

                    session.sendPacket(this)
                }

                BiomeDefinitionListPacket().apply {
                    definitions = BedrockLimbo.BIOME_DEFINITIONS

                    session.sendPacket(this)
                }

                PlayStatusPacket().apply {
                    status = PlayStatusPacket.Status.PLAYER_SPAWN

                    session.sendPacket(this)
                }

                session.packetHandler = PlayerPacketHandler(limbo, session)

                val player = Player(session, loginData)

                PlayerManager.players.add(player)
                Logger.info("&6Gracz &c${player.name} &8[&c${session.address.hostName}&8:&c${session.address.port}&8] &6wszedl na limbo z XUID: &c${player.XUID}")
            }
        }

        return false
    }
}