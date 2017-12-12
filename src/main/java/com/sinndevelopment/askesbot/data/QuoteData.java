package com.sinndevelopment.askesbot.data;

public class QuoteData
{
    private int id;
    private String message;

    public QuoteData(int id, String message)
    {
        this.id = id;

        this.message = message;
    }

    private QuoteData()
    {

    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public static QuoteData fromLine(String line)
    {
        String[] split = line.split("~");
        return new QuoteData(Integer.parseInt(split[0]), split[1]);
    }
}
