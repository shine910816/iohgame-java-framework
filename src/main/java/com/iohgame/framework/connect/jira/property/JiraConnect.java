package com.iohgame.framework.connect.jira.property;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.iohgame.framework.connect.base.ConnectBase;
import com.iohgame.framework.connect.jira.parameters.JiraAccount;
import com.iohgame.framework.utility.Utility;

public class JiraConnect<T extends JiraAccount> extends ConnectBase<T>
{
    private SearchRestClient m_searchRestClient;
    private final Integer m_maxResult;

    public JiraConnect(T account)
    {
        super(account);
        m_maxResult = account.authMax();
        if (!Utility.isEmpty(account.proxyHost()))
        {
            String proxyPort = Utility.isEmpty(account.proxyport()) ? "80" : account.proxyport();

            LOG.info("Set proxy to " + account.proxyHost() + ":" + proxyPort);

            System.setProperty("http.proxyHost", account.proxyHost());
            System.setProperty("http.proxyPort", proxyPort);
            System.setProperty("https.proxyHost", account.proxyHost());
            System.setProperty("https.proxyPort", proxyPort);
        }
        try
        {
            JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            URI uri = new URI(account.requestUrl());
            m_searchRestClient = factory.createWithBasicHttpAuthentication(uri, userName(), password()).getSearchClient();
        }
        catch (URISyntaxException e)
        {
            LOG.error(e.getMessage());
        }
    }

    public List<Issue> searchIssue(String jql)
    {
        List<Issue> result = new ArrayList<>();
        if (m_searchRestClient == null)
        {
            return result;
        }
        int index = 0;
        while (true)
        {
            final Set<String> fields = new HashSet<>();
            SearchResult searchResult = m_searchRestClient.searchJql(jql, m_maxResult, m_maxResult * index, fields).claim();
            Iterable<Issue> issues = searchResult.getIssues();
            int issueCount = 0;
            for (final Issue issue : issues)
            {
                result.add(issue);
                issueCount++;
            }
            if (issueCount < m_maxResult)
            {
                break;
            }
            else
            {
                index++;
                continue;
            }
        }
        return result;
    }
}
