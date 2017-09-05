<?xml version="1.0" encoding="ISO-8859-1"?>
<configuration>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <!-- encoder ?????PatternLayoutEncoder -->
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n %X{Appname} %X{Mode} %X{Referer} %X{UserAgent} %X{RequestId}</pattern>
        </encoder>
    </appender>
    <appender name="FILE" class="ch.qos.logback.core.FileAppender">
        <file>testFile.log</file>
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n %X{Appname} %X{Mode} %X{Referer} %X{UserAgent} %X{RequestId}</pattern>
        </encoder>
    </appender>
    <appender name="http" class="com.shcem.logback.HttpAppender">
        <logUrl>http://192.168.60.121:5454</logUrl>
        <logBuffer>5</logBuffer>
        <logSys>WEBFT</logSys>
        <logEnv>DEV</logEnv>
    </appender>

    <root level="INFO">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE" />
    </root>
    <logger name="controller" level="INFO">
        <appender-ref ref="http"/>
    </logger>
    <logger name="service" level="INFO">
        <appender-ref ref="http"/>
    </logger>
</configuration>
