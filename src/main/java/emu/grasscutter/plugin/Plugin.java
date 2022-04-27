package emu.grasscutter.plugin;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.plugin.api.ServerHook;
import emu.grasscutter.server.game.GameServer;

import java.io.InputStream;
import java.net.URLClassLoader;

/**
 * The base class for all plugins to extend.
 */
public abstract class Plugin {
    private final ServerHook server = ServerHook.getInstance();
    
    private PluginIdentifier identifier;
    private URLClassLoader classLoader;

    /**
     * This method is reflected into.
     * 
     * Set plugin variables.
     * @param identifier The plugin's identifier.
     */
    private void initializePlugin(PluginIdentifier identifier, URLClassLoader classLoader) {
        if(this.identifier == null)
            this.identifier = identifier;
        if(this.classLoader == null)
            this.classLoader = classLoader;
        else Grasscutter.getLogger().warn(this.identifier.name + " had a reinitialization attempt.");
    }

    /**
     * The plugin's identifier instance.
     * @return An instance of {@link PluginIdentifier}.
     */
    public final PluginIdentifier getIdentifier(){
        return this.identifier;
    }

    /**
     * Get the plugin's name.
     */
    public final String getName() {
        return this.identifier.name;
    }

    /**
     * Get the plugin's description.
     */
    public final String getDescription() {
        return this.identifier.description;
    }

    /**
     * Get the plugin's version.
     */
    public final String getVersion() {
        return this.identifier.version;
    }

    /**
     * Returns the server that initialized the plugin.
     * @return A server instance.
     */
    public final GameServer getServer() {
        return this.server.getGameServer();
    }

    /**
     * Returns an input stream for a resource in the JAR file.
     * @param resourceName The name of the resource.
     * @return An input stream.
     */
    public final InputStream getResource(String resourceName) {
        return this.classLoader.getResourceAsStream(resourceName);
    }

    /**
     * Returns the server hook.
     * @return A server hook singleton.
     */
    public final ServerHook getHandle() {
        return this.server;
    }
    
    /* Called when the plugin is first loaded. */
    public void onLoad() { }
    /* Called after (most of) the server enables. */
    public void onEnable() { }
    /* Called before the server disables. */
    public void onDisable() { }
}