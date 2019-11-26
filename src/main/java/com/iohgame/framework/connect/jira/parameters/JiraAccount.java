package com.iohgame.framework.connect.jira.parameters;

import com.iohgame.framework.utility.parameters.property.Account;

public interface JiraAccount extends Account
{
    public String requestUrl();

    public String proxyHost();

    public String proxyPort();

    public Integer authMax();
}
