package eu.suro.lmenu.config;

import eu.suro.lmenu.LaunchMenu;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

public class Config  {

    private FileConfiguration config;

    public Config(){
        LaunchMenu.getInstance().saveDefaultConfig(); //create default config.yml
        config = LaunchMenu.getInstance().getConfig();
    }

    public void CreateOrNot(String key, String message){
        if(!config.contains(key)){
            config.set(key, message);
        }
    }

    public String getString(String key){
       return config.getString(key);
    }

    public int getInt(String key){
        return config.getInt(key);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
