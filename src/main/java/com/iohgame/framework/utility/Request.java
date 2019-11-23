package com.iohgame.framework.utility;

import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Request extends MainClass
{
    private Map<String, String> m_parameter = new TreeMap<>();
    private Map<String, Object> m_attribute = new TreeMap<>();

    public Request(String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if (args[i].substring(0, 1).contentEquals("-"))
            {
                if (i + 1 < args.length)
                {
                    if (!args[i + 1].substring(0, 1).contentEquals("-"))
                    {
                        m_parameter.put(args[i].substring(1), args[i + 1]);
                        i++;
                    }
                    else
                    {
                        m_parameter.put(args[i].substring(1), null);
                    }
                }
                else
                {
                    m_parameter.put(args[i].substring(1), null);
                }
            }
        }
    }

    public Boolean hasParameter(String key)
    {
        return m_parameter.containsKey(key);
    }

    public Map<String, String> getParameters()
    {
        return m_parameter;
    }

    public String getParameter(String key)
    {
        if (hasParameter(key))
        {
            return getParameters().get(key);
        }
        return null;
    }

    public void setAttribute(String key, Object value)
    {
        m_attribute.put(key, value);
    }

    public void setAttributes(Map<String, Object> param)
    {
        for (Entry<String, Object> item : param.entrySet())
        {
            m_attribute.put(item.getKey(), item.getValue());
        }
    }

    public Boolean hasAttribute(String key)
    {
        return m_attribute.containsKey(key);
    }

    public Object getAttribute(String key)
    {
        if (hasAttribute(key))
        {
            return m_attribute.get(key);
        }
        return null;
    }

    public Map<String, Object> getAttributes()
    {
        return m_attribute;
    }
}
