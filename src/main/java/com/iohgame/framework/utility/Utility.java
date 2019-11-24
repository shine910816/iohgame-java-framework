package com.iohgame.framework.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.iohgame.framework.utility.parameters.constant.ConstDatetime;
import com.iohgame.framework.utility.parameters.constant.ConstQuote;
import com.iohgame.framework.utility.parameters.property.Parameters;

public class Utility
{
    public static Integer toInteger(String param)
    {
        try
        {
            Integer result = Integer.valueOf(param);
            return result;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public static Float toFloat(String param)
    {
        try
        {
            Float result = Float.valueOf(param);
            return result;
        }
        catch (Exception e)
        {
            return 0f;
        }
    }

    public static Integer random(int min, int max)
    {
        if (min > max || min < 1)
        {
            return null;
        }
        if (min == max)
        {
            return min;
        }
        Integer random = toInteger(toString(Math.random()).substring(2, 11));
        return random % (max - min + 1) + min;
    }

    public static Integer random(int max)
    {
        return random(1, max);
    }

    public static Integer random()
    {
        return random(1, 100);
    }

    public static Boolean rate(int target)
    {
        if (target < 1 || target > 100)
        {
            return null;
        }
        if (random() <= target)
        {
            return true;
        }
        return false;
    }

    public static Date getCurrentDate()
    {
        return new Date();
    }

    public static String getCurrentDate(ConstDatetime format)
    {
        return getDate(getCurrentDate(), format);
    }

    public static String getDate(Date date, ConstDatetime format)
    {
        if (format != null)
        {
            SimpleDateFormat df = new SimpleDateFormat(format.val());
            return df.format(date);
        }
        return null;
    }

    public static String getDate(Date date, String format)
    {
        try
        {
            SimpleDateFormat df = new SimpleDateFormat(format, Locale.ENGLISH);
            return df.format(date);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static Date getDateByOracleDateString(String param)
    {
        try
        {
            param = param.replace("Z", " UTC");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            return formatter.parse(param);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public static boolean isEmpty(List<?> param)
    {
        return param == null || param.isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> param)
    {
        return param == null || param.isEmpty();
    }

    public static boolean isEmpty(Set<?> param)
    {
        return param == null || param.isEmpty();
    }

    public static <T> boolean isEmpty(T[] param)
    {
        return param == null || param.length == 0;
    }

    public static boolean isEmpty(String param)
    {
        return param == null || param.length() == 0;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T> & Parameters> T getEnum(String keyword, Class<T> clazz)
    {
        EnumSet<T> enumList = EnumSet.allOf(clazz);
        for (T val : enumList)
        {
            if (val.val().equalsIgnoreCase(keyword))
            {
                return val;
            }
        }
        return (T) enumList.iterator().next().unknown();
    }

    public static <T> List<T> toList(T[] args)
    {
        List<T> result = new ArrayList<>();
        for (T itm : args)
        {
            result.add(itm);
        }
        return result;
    }

    public static String implode(List<String> pieces, String glue, ConstQuote quote)
    {
        String result = "";
        for (int i = 0; i < pieces.size(); i++)
        {
            result += quote(pieces.get(i), quote);
            if (i < pieces.size() - 1)
            {
                result += glue;
            }
        }
        return result;
    }

    public static String implode(List<String> pieces, ConstQuote quote)
    {
        return implode(pieces, ", ", quote);
    }

    public static String implode(List<String> pieces)
    {
        return implode(pieces, ", ", ConstQuote.DOUBLE_QUOTE);
    }

    public static String implode(String[] pieces, String glue, ConstQuote quote)
    {
        String result = "";
        for (int i = 0; i < pieces.length; i++)
        {
            result += quote(pieces[i], quote);
            if (i < pieces.length - 1)
            {
                result += glue;
            }
        }
        return result;
    }

    public static String implode(String[] pieces, ConstQuote quote)
    {
        return implode(pieces, ", ", quote);
    }

    public static String implode(String[] pieces)
    {
        return implode(pieces, ", ", ConstQuote.DOUBLE_QUOTE);
    }

    public static String quote(String piece, ConstQuote quote)
    {
        return quote.val() + piece + quote.val();
    }

    public static String quote(String piece)
    {
        return quote(piece, ConstQuote.DOUBLE_QUOTE);
    }

    public static Integer transMinuteToSecond(String param)
    {
        String[] minArr = param.split(":");
        if (minArr.length != 2)
        {
            return 0;
        }
        Integer minSec = Utility.toInteger(minArr[0]) * 60 + Utility.toInteger(minArr[1]);
        return minSec;
    }

    public static Date toDate(String date, ConstDatetime df)
    {
        Date result = null;
        java.text.DateFormat format = new SimpleDateFormat(df.val(), Locale.ENGLISH);
        try
        {
            result = format.parse(date);
        }
        catch (ParseException e)
        {
            return null;
        }
        return result;
    }

    public static Date toDate(String date)
    {
        return toDate(date, ConstDatetime.DATETIME);
    }

    public static <T> String toString(T arg)
    {
        String result = null;
        result = String.valueOf(arg);
        return result;
    }

    public static <T> String[] toStringArray(T[] args)
    {
        String[] result = new String[args.length];
        for (int i = 0; i < args.length; i++)
        {
            result[i] = toString(args[i]);
        }
        return result;
    }

    public static <T> List<String> toStringArray(List<T> args)
    {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < args.size(); i++)
        {
            result.add(i, toString(args.get(i)));
        }
        return result;
    }

    public static <T> T getArrayValue(T[] arr, int index)
    {
        if (arr != null && arr.length > index)
        {
            return arr[index];
        }
        return null;
    }

    public static String addMask(String param, int length, String context)
    {
        if (param.length() >= length)
        {
            return param;
        }
        String result = param;
        for (int i = 0; i < length - param.length(); i++)
        {
            result += context;
        }
        return result;
    }

    public static Date adjustDate(Date date, int days)
    {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    public static File getFileResource(String filePath)
    {
        try
        {
            return new File("src/main/resource/" + filePath);
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static FileReader getFileReaderResource(File filePath)
    {
        try
        {
            return new FileReader(filePath);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static FileReader getFileReaderResource(String filePath)
    {
        return getFileReaderResource(getFileResource(filePath));
    }
}
