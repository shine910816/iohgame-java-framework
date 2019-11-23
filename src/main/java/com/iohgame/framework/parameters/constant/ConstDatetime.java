package com.iohgame.framework.parameters.constant;

import com.iohgame.framework.parameters.property.Parameters;

public enum ConstDatetime implements Parameters
{
    DATE_WORD("y年M月d日"),

    DATE("yyyy-MM-dd"),

    DATE_SLASH("yyyy/MM/dd"),

    DATE_NONE("yyyyMMdd"),

    DATE_YEAR_MONTH_WORD("y年M月"),

    DATE_YEAR_MONTH("yyyy-MM"),

    DATE_YEAR_MONTH_SLASH("yyyy/MM"),

    DATE_YEAR_MONTH_NONE("yyyyMM"),

    DATE_MONTH_DAY_WORD("M月d日"),

    DATE_MONTH_DAY("MM-dd"),

    DATE_MONTH_DAY_SLASH("MM/dd"),

    DATE_MONTH_DAY_NONE("MMdd"),

    TIME_WORD("H时m分s秒"),

    TIME("HH:mm:ss"),

    TIME_HOUR_MINUTE_WORD("H时m分"),

    TIME_HOUR_MINUTE("HH:mm"),

    TIME_MINUTE_SECOND_WORD("m分s秒"),

    TIME_MINUTE_SECOND("mm:ss"),

    DATETIME_WORD("y年M月d日 H时m分s秒"),

    DATETIME("yyyy-MM-dd HH:mm:ss"),

    DATETIME_SLASH("yyyy/MM/dd HH:mm:ss"),

    UNKNOWN(Parameters.UNKNOWN);

    private final String m_val;

    ConstDatetime(String val)
    {
        m_val = val;
    }

    @Override
    public String val()
    {
        return m_val;
    }

    @Override
    public Parameters unknown()
    {
        return UNKNOWN;
    }
}
