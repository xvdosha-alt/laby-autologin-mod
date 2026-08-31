EN | [RU](docs/README_RU.md)

# Auto Login

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)


LabyMod 4 addon for Minecraft **1.20.1** that auto-sends auth passwords on protected servers and exposes a small localhost API for external tools (for example Python scripts).

## Features

- Detects common `/login` and `/register` prompts in chat
- Stores nick/password pairs per server
- Sends auth commands automatically with cooldown
- Local JSON-line TCP API on `127.0.0.1`
- Persists config and accounts in the Minecraft config directory

## Requirements

- JDK 21
- LabyMod 4 for Minecraft 1.20.1

## Build

```bash
./gradlew createReleaseJar
```

Windows:

```bat
build.bat
```

Release JAR:

```
build/libs/autologin-release.jar
```

## Project layout

```
api/           - version-independent bridge interface
core/          - addon logic, password store, TCP server
game-runner/   - Minecraft 1.20.1 bridge implementation
```

## Local API

Default bind: `127.0.0.1:47923` (falls back to the next free port if busy).

Config file:

```
.minecraft/config/autologin/autologin.json
```

Accounts file:

```
.minecraft/config/autologin/accounts.json
```

Each request is one JSON object per line. Supported commands:

### `nick`

Returns the current in-world nickname.

```json
{"cmd":"nick"}
```

### `status`

Returns addon status and number of stored accounts.

```json
{"cmd":"status"}
```

### `set_passwords`

Merges nick/password entries into the local store.

```json
{
  "cmd": "set_passwords",
  "accounts": [
    { "nick": "Player", "password": "secret" }
  ]
}
```

## Install

1. Build `build/libs/autologin-release.jar`
2. Place the addon into your LabyMod addons folder
3. Start Minecraft 1.20.1 with LabyMod
4. Configure accounts through the API or `accounts.json`

## Notes

- The addon only reacts on allowed server addresses configured in code
- Passwords are stored locally on disk
- Do not expose the localhost API outside your machine
