package com.iohgame.framework.connect.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

import com.iohgame.framework.connect.mysql.parameters.MysqlAccount;
import com.iohgame.framework.utility.Utility;
import com.iohgame.framework.utility.parameters.constant.ConstDatetime;
import com.iohgame.framework.utility.parameters.constant.ConstQuote;

public class MasterDatabase<T extends MysqlAccount> extends SlaveDatabase<T>
{
    public MasterDatabase(T source)
    {
        super(source);
    }

    public Integer insert(String tableName, Map<String, String> insertData)
    {
        String column = "";
        String value = "";
        Iterator<String> keySet = insertData.keySet().iterator();
        while (keySet.hasNext())
        {
            String key = keySet.next();
            column += Utility.quote(key, ConstQuote.BACK_QUOTE) + ", ";
            value += Utility.quote(insertData.get(key)) + ", ";
        }
        String datatime = Utility.quote(Utility.getCurrentDate(ConstDatetime.DATETIME));
        column += "`insert_date`, `update_date`, `del_flg`";
        value += datatime + ", " + datatime + ", " + Utility.quote("0");
        String sql = String.format("INSERT INTO `%s` (%s) VALUES (%s);", tableName, column, value);
        Integer res = 0;
        if (masterQuery(sql) == 0)
        {
            return res;
        }
        ResultSet lastId = slaveQuery("SELECT LAST_INSERT_ID();");
        try
        {
            lastId.next();
            res = Utility.toInteger(lastId.getString(1));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return res;
    }

    public boolean update(String tableName, Map<String, String> updateData, String where)
    {
        String update = "";
        Iterator<String> keySet = updateData.keySet().iterator();
        while (keySet.hasNext())
        {
            String key = keySet.next();
            update += Utility.quote(key, ConstQuote.BACK_QUOTE) + " = " + Utility.quote(updateData.get(key)) + ", ";
        }
        update += "`update_date` = " + Utility.quote(Utility.getCurrentDate(ConstDatetime.DATETIME));
        String sql = String.format("UPDATE `%s` SET %s WHERE %s;", tableName, update, where);
        if (masterQuery(sql) > 0)
        {
            return true;
        }
        return false;
    }

    public boolean delete(String tableName, String where)
    {
        String sql = String.format("DELETE FROM `%s` WHERE %s;", tableName, where);
        if (masterQuery(sql) > 0)
        {
            return true;
        }
        return false;
    }

    public void begin()
    {
        masterQuery("BEGIN;");
    }

    public void rollback()
    {
        masterQuery("ROLLBACK;");
    }

    public void commit()
    {
        masterQuery("COMMIT;");
    }
}
