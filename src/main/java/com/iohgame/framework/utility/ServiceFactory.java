package com.iohgame.framework.utility;

import com.iohgame.framework.utility.parameters.property.Factory;

public abstract class ServiceFactory extends MainClass implements Factory
{
    private Request m_request;

    public ServiceFactory(Request request)
    {
        m_request = request;
    }

    protected Request request()
    {
        return m_request;
    }
}
