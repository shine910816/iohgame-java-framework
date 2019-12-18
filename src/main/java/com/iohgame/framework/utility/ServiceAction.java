package com.iohgame.framework.utility;

import com.iohgame.framework.utility.parameters.property.Action;

public abstract class ServiceAction extends MainClass implements Action
{
    private Request m_request;

    public ServiceAction(Request request)
    {
        m_request = request;
    }

    protected Request request()
    {
        return m_request;
    }
}
