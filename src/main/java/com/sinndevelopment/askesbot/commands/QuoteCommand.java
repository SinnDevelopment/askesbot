package com.sinndevelopment.askesbot.commands;

import com.sinndevelopment.askesbot.data.QuoteData;
import org.pircbotx.hooks.events.PrivateMessageEvent;
import org.pircbotx.hooks.types.GenericMessageEvent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

public class QuoteCommand extends ChatCommand
{
    private HashMap<Integer, QuoteData> cache = new HashMap<>();

    public QuoteCommand()
    {

        super("quote", PermissionLevel.VIEWER);
        if (!(new File("quotes.csv").exists()))
            try
            {
                new File("quotes.csv").createNewFile();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            updateCache();
    }


    @Override
    protected void onPMCommand(PrivateMessageEvent event, String sender, List<String> args)
    {

    }

    @Override
    protected void onCommand(GenericMessageEvent event, String sender, List<String> args)
    {
        if (args.size() > 1)
        {
            try (FileWriter writer = new FileWriter(new File("quotes.csv"), true))
            {
                if (!args.get(0).equalsIgnoreCase("add"))
                {
                    bot.replyMessage(event, sender, "Syntax: _quote add [quote text]");
                    return;
                }
                StringBuilder builder = new StringBuilder();
                args.subList(1, args.size()).forEach(a -> builder.append(a).append(" "));
                String message = builder.toString();
                updateCache();
                int id = cache.size();
                writer.write(id + "~" + message + "\n");
                writer.flush();

                bot.replyMessage(event, sender, "Successfully added quote with ID: " + id);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else if (args.size() == 1)
        {
            int id = Integer.parseInt(args.get(0));
            final boolean[] found = {false};
            try
            {
                List<String> lines = Files.readAllLines(new File("quotes.csv").toPath());
                lines.forEach(line -> {
                    QuoteData data = QuoteData.fromLine(line);
                    cache.put(data.getId(), data);
                    if(data.getId() == id)
                    {
                        bot.replyMessage(event, "Quote #" + id + ": " + cache.get(id).getMessage());
                        found[0] =true;
                    }
                });
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            if(!found[0])
            {
                bot.replyMessage(event, sender, "Could not find quote with ID " + id);
            }
        }
        else
        {
            bot.replyMessage(event, sender, "Syntax: _quote <#|add> [quote text]");
        }
    }

    private void updateCache()
    {
        cache.clear();
        List<String> lines;
        try
        {
            lines = Files.readAllLines(new File("quotes.csv").toPath());
            lines.forEach(line -> {
                QuoteData data = QuoteData.fromLine(line);
                cache.put(data.getId(), data);
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
