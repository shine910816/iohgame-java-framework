package com.iohgame.framework.utility;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Map.Entry;

import com.iohgame.framework.utility.parameters.constant.ConstBreak;

/**
 * Read file and return context
 */
public class ReadFiles extends MainClass
{
    private String m_fileContext = "";

    public ReadFiles getFileContents(String filePath, ConstBreak linebreak)
    {
        m_fileContext = "";
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(Utility.getFileReaderResource("template/" + filePath + ".html"));
            String tempString = null;
            while ((tempString = reader.readLine()) != null)
            {
                m_fileContext += tempString + linebreak.val();
            }
            if (reader != null)
            {
                reader.close();
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }
        return this;
    }

    public ReadFiles getFileContents(String filePath)
    {
        return getFileContents(filePath, ConstBreak.UNKNOWN);
    }

    public String content()
    {
        return m_fileContext;
    }

    public String replace(Map<String, String> params)
    {
        String result = m_fileContext;
        for (Entry<String, String> item : params.entrySet())
        {
            result = result.replace("{$" + item.getKey() + "}", item.getValue());
        }
        return result;
    }

    public static ReadFiles getInstance()
    {
        return new ReadFiles();
    }
}
