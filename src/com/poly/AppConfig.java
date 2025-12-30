package com.poly;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Initialize system properties before Jersey starts. Disables JAXB bytecode
 * optimization that fails on recent JDKs and old Jersey/JAXB combo.
 */
public class AppConfig implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Avoid NPE in com.sun.xml.bind.v2.runtime.reflect.opt.Injector on JDK 17+
        System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // no-op
    }
}
