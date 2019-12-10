package com.iohgame.framework.utility;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonAnalyse extends MainClass
{
    private BufferedReader m_jsonReader;

    public JsonAnalyse(String url)
    {
        try
        {
            URLConnection uc = new URL(url).openConnection();
            m_jsonReader = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }
    }

    public Reader getJsonFileReader()
    {
        return m_jsonReader;
    }

    public JsonObject getJsonObject()
    {
        JsonParser parse = new JsonParser();
        return (JsonObject) parse.parse(m_jsonReader);
    }

    public static JsonAnalyse getInstance(String url)
    {
        return new JsonAnalyse(url);
    }
}
