package com.sinndevelopment.askesbot.data;

import com.sinndevelopment.askesbot.Main;

import java.io.*;

public class TokenLogger
{
    private String name;

    public TokenLogger(String name)
    {
        this.name = name;
    }

    public void logLatest(String token)
    {
        Main.getLogger().info(name + ":" +token);
    }

    public void updateCurrent(String token)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(new File(name+".txt"), false)))
        {
            bw.write(token);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String getLatest()
    {
        try(BufferedReader re = new BufferedReader(new FileReader(new File(name+".txt"))))
        {
            String line;
            while ((line = re.readLine()) != null)
            {
                if(!line.equals(""))
                    return line;
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
