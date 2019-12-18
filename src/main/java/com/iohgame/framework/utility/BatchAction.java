package com.iohgame.framework.utility;

import com.iohgame.framework.connect.base.ConnectBase;

public abstract class BatchAction<T extends ConnectBase> extends ServiceAction
{
    private T m_connect;

    public BatchAction(T connect, Request request)
    {
        super(request);
        m_connect = connect;
    }

    protected T connect()
    {
        return m_connect;
    }
}
