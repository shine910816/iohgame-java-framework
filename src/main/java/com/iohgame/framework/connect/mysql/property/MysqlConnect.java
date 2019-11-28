package com.iohgame.framework.connect.mysql.property;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.iohgame.framework.connect.base.ConnectBase;
import com.iohgame.framework.connect.mysql.parameters.MysqlAccount;
import com.iohgame.framework.utility.parameters.property.Connectable;
import com.iohgame.framework.utility.parameters.property.OptionElement;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MysqlConnect<T extends MysqlAccount> extends ConnectBase implements Connectable<T>
{
    private final String DATABASE_DRIVER = "com.mysql.jdbc.Driver";
    private Connection m_connect;
    private Statement m_statement;
    private ResultSet m_result;

    public MysqlConnect(T account)
    {
        String addr = "jdbc:mysql://" + account.requestUrl() + ":" + account.port() + "/" + account.databaseName();
        if (m_connect == null)
        {
            try
            {
                Class.forName(DATABASE_DRIVER);
                m_connect = (Connection) DriverManager.getConnection(addr, account.userName(), account.password());
            }
            catch (Exception e)
            {
                LOG.error(e.getMessage());
            }
        }
    }

    protected ResultSet slaveQuery(String sql)
    {
        LOG.info("SQL query: " + sql);

        try
        {
            m_statement = (Statement) m_connect.createStatement();
            m_result = m_statement.executeQuery(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return m_result;
    }

    protected Integer masterQuery(String sql)
    {
        Integer result = 0;
        try
        {
            m_statement = (Statement) m_connect.createStatement();
            result = m_statement.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void free()
    {
        try
        {
            if (m_result != null)
            {
                m_result.close();
                m_result = null;
            }
            if (m_statement != null)
            {
                m_statement.close();
                m_statement = null;
            }
            if (m_connect != null)
            {
                m_connect.close();
                m_connect = null;
            }
        }
        catch (Exception e)
        {
            LOG.error(e.getMessage());
        }
    }

    public enum DatabaseType implements OptionElement
    {
        MASTER,

        SLAVE;

        @Override
        public String val()
        {
            return name();
        }
    }

    public enum OptionType implements OptionElement
    {
        INSERT,

        UPDATE,

        DELETE,

        SELECT;

        @Override
        public String val()
        {
            return name();
        }

        public boolean check(String sql)
        {
            if (name().toLowerCase().equals(sql.substring(0, 6).toLowerCase()))
            {
                return true;
            }
            return false;
        }
    }
}
