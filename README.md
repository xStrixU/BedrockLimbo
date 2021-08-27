# BedrockLimbo
## Co to limbo?
Limbo to prosty serwer, najczęściej używany pod Lobby, który pozwala utrzymać ogromną liczbę graczy przy niskim koszcie procesora.

![image](https://i.imgur.com/vknk0nB.png)

## Uruchamianie
Aby uruchomić Limbo, należy je pobrać z [tego miejsca](https://github.com/xStrixU/BedrockLimbo/releases), a następnie odpalić używając komendy:
```text
java -jar BedrockLimbo.jar
```

## Konfiguracja
Limbo można w prosty sposób konfigurować za pomocą pliku konfiguracyjnego ```config.yml```:
```yaml
address: "0.0.0.0"
port: 19132
maxPlayers: 10
motd: "&6BedrockLimbo &7by &bxStrixU"

joinMessage:
  - "&8[----------( &d&lBedrockLimbo &r&8)----------]"
  - " "
  - " &8» &7Witaj na &cBedrockLimbo&7!"
  - " &8» &7Aktualna liczba graczy: &c{PLAYER_COUNT}"
  - " "
  - " &8» &7Zapraszamy na: &chttps://projectcode.pl"
  - " "
  - "&8[----------( &d&lBedrockLimbo &r&8)----------]"

serverStopped: "&cLimbo zostalo wylaczone!"
```

## Kontakt
W razie jakichkolwiek pytań, błedów, problemów z odpaleniem proszę o kontakt na Discordzie: ```xStrixU#0001```
## Licencja
MIT License, zobacz [LICENSE](LICENSE)
