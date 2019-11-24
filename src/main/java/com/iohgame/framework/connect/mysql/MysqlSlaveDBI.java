package com.iohgame.framework.connect.mysql;

import java.sql.ResultSet;

import com.iohgame.framework.connect.mysql.parameters.MysqlAccount;
import com.iohgame.framework.connect.mysql.property.MysqlConnect;

public class MysqlSlaveDBI<T extends MysqlAccount> extends MysqlConnect<T>
{
    public MysqlSlaveDBI(T source)
    {
        super(source);
    }

    public ResultSet query(String sql)
    {
        return slaveQuery(sql);
    }
}
