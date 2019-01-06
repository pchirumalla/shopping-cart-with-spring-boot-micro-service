package org.apache.logging.log4j.core;


import java.net.URI;
import java.util.Collections;
import java.util.Map;

import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LoggerContext {

    public static LoggerContext getContext() {
        return new LoggerContext();
    }

    public static LoggerContext getContext(final boolean currentContext) {
        return getContext();
    }

    public static LoggerContext getContext(final ClassLoader loader, final boolean currentContext, final URI configLocation) {
        return getContext();
    }

    public Configuration getConfiguration() {
        return new Configuration() {

            public Map<String, LoggerConfig> getLoggers() {
                return Collections.emptyMap();
            }
        };
    }
}