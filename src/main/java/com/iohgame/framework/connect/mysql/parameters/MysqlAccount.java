package com.iohgame.framework.connect.mysql.parameters;

import com.iohgame.framework.utility.parameters.property.Account;

public interface MysqlAccount extends Account
{
    public String requestUrl();

    public String port();

    public String databaseName();
}
