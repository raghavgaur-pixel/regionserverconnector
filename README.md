# Region Server Connector
**RegionServerConnector** is a lightweight Minecraft server-side plugin designed for BungeeCord or Velocity networks. It allows you to automatically connect players to different backend servers based on their location in the world.

Perfect for large servers using a region-based architecture (e.g., dividing the map into zones or areas with dedicated servers), this plugin enables seamless server transitions as players cross region boundaries.

---

## ✅ Features

- Automatically transfers players to a designated server when they enter a specific region
- Integrates with WorldGuard (or region-defining alternatives)
- Configurable via simple YAML files
- Supports BungeeCord and Velocity proxies
- Lightweight and efficient

---

## 📦 Installation

1. **Drop the plugin JAR** into your server's `/plugins` directory (typically on the **Spigot/Paper** servers).
2. **Make sure you are using a proxy** like **BungeeCord** or **Velocity**.
3. **(Optional)** Install **WorldGuard** if you want to define regions using it.

---

## ⚙️ Configuration

The configuration file is located at:  
`/plugins/RegionServerConnector/config.yml`
- All you have to do is mentions the server you wish to connect to and the coordinates of the region which will take the players to that server.