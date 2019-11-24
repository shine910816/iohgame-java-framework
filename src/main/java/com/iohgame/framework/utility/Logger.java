package com.iohgame.framework.utility;

import com.iohgame.framework.utility.parameters.constant.ConstDatetime;

public class Logger
{
    public static Logger instance()
    {
        return new Logger();
    }

    public void info(String context)
    {
        String log = String.format("%s INFO %s%s", Utility.getCurrentDate(ConstDatetime.DATETIME), getStackTrace(), context);
        System.out.println(log);
    }

    public void warn(String context)
    {
        String log = String.format("%s WARN %s%s", Utility.getCurrentDate(ConstDatetime.DATETIME), getStackTrace(), context);
        System.out.println(log);
    }

    public void error(String context)
    {
        String log = String.format("%s ERROR %s%s", Utility.getCurrentDate(ConstDatetime.DATETIME), getStackTrace(), context);
        System.err.println(log);
    }

    public void show(String context)
    {
        System.out.println(context);
    }

    private String getStackTrace()
    {
        StackTraceElement result = null;
        for (StackTraceElement item : Thread.currentThread().getStackTrace())
        {
            String className = item.getClassName();
            if (className.contains("java.lang.Thread") || className.contains("com.iohgame.framework.utility.Logger"))
            {
                continue;
            }
            else
            {
                result = item;
                break;
            }
        }
        if (result != null)
        {
            return String.format("(%s:%d) ", result.getFileName(), result.getLineNumber());
        }
        return "";
    }
}
