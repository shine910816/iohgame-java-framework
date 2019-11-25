package com.iohgame.framework.utility;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelBook extends MainClass
{
    private Workbook m_book;
    private Font m_font;
    private CellStyle m_style;

    public ExcelBook()
    {
        LOG.info("Create Excle book resource");
        m_book = new XSSFWorkbook();

        LOG.info("Create default font with ＭＳ Ｐゴシック #10");
        m_font = m_book.createFont();
        m_font.setFontName("ＭＳ Ｐゴシック");
        m_font.setFontHeightInPoints((short) 10);

        LOG.info("Create default cell style with vertical top and horizontal left");
        m_style = m_book.createCellStyle();
        m_style.setVerticalAlignment(VerticalAlignment.TOP);
        m_style.setAlignment(HorizontalAlignment.LEFT);
        m_style.setFont(m_font);
    }

    public Workbook getBook()
    {
        return m_book;
    }

    public Font getFont()
    {
        return m_font;
    }

    public CellStyle getStyle()
    {
        return m_style;
    }

    public void saveExcelFile(String path)
    {
        try
        {
            File file = new File(path);
            if (file.exists())
            {
                file.delete();
            }
            FileOutputStream fs = new FileOutputStream(file);
            m_book.write(fs);
            fs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
