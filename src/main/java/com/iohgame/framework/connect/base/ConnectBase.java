package com.iohgame.framework.connect.base;

import com.iohgame.framework.utility.MainClass;
import com.iohgame.framework.utility.parameters.property.Account;

/**
 * 连接基础类
 * @param Account接口实现类
 */
public abstract class ConnectBase<T extends Account> extends MainClass
{
    private String m_userName;
    private String m_password;

    public ConnectBase(T account)
    {
        m_userName = account.userName();
        m_password = account.password();
    }

    protected String userName()
    {
        return m_userName;
    }

    protected String password()
    {
        return m_password;
    }
}
