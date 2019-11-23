package com.iohgame.framework.parameters.constant;

import com.iohgame.framework.parameters.property.Parameters;

public enum ConstBreak implements Parameters
{
    WINDOWS("\r\n"),

    LINUX("\n"),

    UNKNOWN("");

    private String m_val;

    ConstBreak(String val)
    {
        m_val = val;
    }

    @Override
    public String val()
    {
        return m_val;
    }

    @Override
    public Parameters unknown()
    {
        return UNKNOWN;
    }
}
