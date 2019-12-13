package com.iohgame.framework.utility;

import com.iohgame.framework.utility.parameters.property.Action;
import com.iohgame.framework.utility.parameters.property.Factory;
import com.iohgame.framework.utility.parameters.property.OptionElement;

public class Launcher extends MainClass
{
    private Factory m_factory;
    private OptionElement[] m_options;

    public Launcher(Factory factory, OptionElement... options)
    {
        m_factory = factory;
        m_options = options;
    }

    public void execute()
    {
        for (OptionElement page : m_options)
        {
            LOG.info("Action execute for " + page);

            Action act = m_factory.getAction(page);
            if (!act.doMainValidate())
            {
                LOG.error("Validation has error happened");
                continue;
            }

            if (!act.doMainExecute())
            {
                LOG.error("Execution has error happened");
            }
        }
    }
}
