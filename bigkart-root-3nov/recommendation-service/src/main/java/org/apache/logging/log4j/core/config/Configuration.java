package org.apache.logging.log4j.core.config;

import java.util.Map;

public interface Configuration {

    public Map<String, LoggerConfig> getLoggers();
}