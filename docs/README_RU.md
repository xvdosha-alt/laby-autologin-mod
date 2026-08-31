[EN](../README.md) | RU

# Auto Login

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white)


Аддон LabyMod 4 для Minecraft **1.20.1**: автоматически отправляет пароли авторизации на защищённых серверах и даёт небольшой localhost API для внешних инструментов (например Python-скриптов).

## Возможности

- Распознаёт типичные запросы `/login` и `/register` в чате
- Хранит пары ник/пароль для каждого сервера
- Автоматически отправляет команды авторизации с cooldown
- Локальный JSON-line TCP API на `127.0.0.1`
- Сохраняет конфиг и аккаунты в каталоге конфигов Minecraft

## Требования

- JDK 21
- LabyMod 4 для Minecraft 1.20.1

## Сборка

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

## Структура проекта

```
api/           - version-independent bridge interface
core/          - addon logic, password store, TCP server
game-runner/   - Minecraft 1.20.1 bridge implementation
```

## Локальный API

По умолчанию: `127.0.0.1:47923` (если порт занят - следующий свободный).

Файл конфига:

```
.minecraft/config/autologin/autologin.json
```

Файл аккаунтов:

```
.minecraft/config/autologin/accounts.json
```

Каждый запрос - один JSON-объект на строку. Поддерживаемые команды:

### `nick`

Возвращает текущий ник в мире.

```json
{"cmd":"nick"}
```

### `status`

Возвращает статус аддона и число сохранённых аккаунтов.

```json
{"cmd":"status"}
```

### `set_passwords`

Добавляет/обновляет пары ник/пароль в локальном хранилище.

```json
{
  "cmd": "set_passwords",
  "accounts": [
    { "nick": "Player", "password": "secret" }
  ]
}
```

## Установка

1. Собери `build/libs/autologin-release.jar`
2. Положи аддон в папку addons LabyMod
3. Запусти Minecraft 1.20.1 с LabyMod
4. Настрой аккаунты через API или `accounts.json`

## Заметки

- Аддон реагирует только на разрешённые адреса серверов из кода
- Пароли хранятся локально на диске
- Не открывай localhost API за пределы своей машины
