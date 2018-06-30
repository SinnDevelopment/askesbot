package com.sinndevelopment.askesbot.data;

import com.sinndevelopment.askesbot.Main;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class YAMLViewerHandler
{
    private static String configDir = "viewers/";
    private static Yaml yaml = new Yaml();

    public static Viewer getViewer(String name)
    {
        name = name.toLowerCase();
        File confDir = new File(configDir);
        if (!confDir.exists())
        {
            Main.getLogger().info("Data directory does not exist, creating now.");
            confDir.mkdir();
        }

        try (InputStream in = Files.newInputStream(Paths.get(configDir + name + ".yaml")))
        {
            Viewer viewer = yaml.loadAs(in, Viewer.class);
            if (viewer == null)
            {
                viewer = new Viewer(0, name, false);
            }

            return viewer;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Main.getLogger().info("User: " + name);
            Main.getLogger().info("Userdata file for " + name + " was not found. Creating now with base data.");
            return new Viewer(0, name, false);
        }
    }

    public static void saveViewer(Viewer viewer)
    {
        try (FileWriter writer = new FileWriter(new File(configDir + viewer.getUsername() + ".yaml")))
        {
            Map<String, Object> data = new HashMap<>();
            data.put("count", viewer.getAmount());
            data.put("username", viewer.getUsername());
            data.put("subscriber", viewer.isSubscriber());

            yaml.dump(data, writer);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
