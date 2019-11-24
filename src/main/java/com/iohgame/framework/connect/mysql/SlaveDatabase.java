package com.iohgame.framework.connect.mysql;

import java.sql.ResultSet;

import com.iohgame.framework.connect.mysql.parameters.MysqlAccount;
import com.iohgame.framework.connect.mysql.property.MysqlConnect;

public class SlaveDatabase<T extends MysqlAccount> extends MysqlConnect<T>
{
    public SlaveDatabase(T source)
    {
        super(source);
    }

    public ResultSet query(String sql)
    {
        return slaveQuery(sql);
    }
}
