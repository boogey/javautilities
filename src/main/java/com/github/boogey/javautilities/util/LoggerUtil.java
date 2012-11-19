/**
 * Copyright 2012 Karsten Schulz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.boogey.javautilities.util;

import java.io.File;
import java.net.URL;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.lf5.LogLevel;

/**
 * Utility-class to logging something messages and exceptions to a specify appender. This logging-class is use the
 * famous log4j-framework. The dependency is log4j 1.x.
 * 
 * @author Karsten Schulz <a href="mailto:lennylinux.ks@googlmail.com">(lennylinux.ks@googlmail.com)</a>
 */
public final class LoggerUtil
{

    /**
     * Returns a specify logger for the called classes. To get the name of the called class this method create a new
     * {@link Throwable} to get the stacktrace.
     * 
     * @return the logger as reference of a {@link Logger}-object
     */
    public static Logger getLogger()
    {
        final Throwable t = new Throwable();
        final StackTraceElement methodCaller = t.getStackTrace()[2];
        return getLogger( methodCaller.getClassName() );
    }

    /**
     * Returns a logger with the name
     * 
     * @param name hierachie name
     * @return the logger as reference of a {@link Logger}-object
     */
    public static Logger getLogger( final String name )
    {
        final Logger logger = Logger.getLogger( name );
        return logger;
    }

    /**
     * Load log4j configuration from a specify file. If the String parameter is <code>null</code> or not exist, there
     * will be load the default configuration.
     * 
     * @param configLocation the location of the config file
     * @see BasicConfigurator#configure()
     */
    public static void loadConfig( final String configLocation )
    {
        File location = new File( configLocation );
        if ( null != configLocation && location.exists() )
        {
            loadFromResource( configLocation );
        }
        else
        {
            BasicConfigurator.configure();
        }
    }

    /**
     * Load a log4j configuration from a specify location with the {@link ClassLoader} and the resource management.
     * 
     * @param configLocation a resource that will be load and configuration the log4j framework.
     */
    public static void loadFromResource( final String configLocation )
    {
        URL configURL = ClassLoader.getSystemResource( configLocation );
        System.setProperty( "log4j.configuration", configURL.getProtocol() + ":" + configURL.getFile() );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#INFO}
     * 
     * @param message the specify message to log
     */
    public static void createInfoLog( final Object message )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.info( message );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#INFO} and a {@link Throwable} object
     * 
     * @param message the specify message to log
     * @param t a {@link Throwable} object
     */
    public static void createInfoLog( final Object message, final Throwable t )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.info( message, t );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#WARN}
     * 
     * @param message the specify message to log
     */
    public static void createWarningLog( final Object message )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.warn( message );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#WARN} and a {@link Throwable} object
     * 
     * @param message the specify message to log
     * @param t a {@link Throwable} object
     */
    public static void createWarningLog( final Object message, final Throwable t )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.warn( message, t );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#DEBUG}
     * 
     * @param message the specify message to log
     */
    public static void createDebugLog( final Object message )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.debug( message );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#DEBUG} and a {@link Throwable} object
     * 
     * @param message the specify message to log
     * @param t a {@link Throwable} object
     */
    public static void createDebugLog( final Object message, final Throwable t )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.debug( message, t );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#ERROR}
     * 
     * @param message the specify message to log
     */
    public static void createErrorLog( final Object message )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.error( message );
    }

    /**
     * Add a message to the {@link Logger} with {@link LogLevel#ERROR} and a {@link Throwable} object
     * 
     * @param message the specify message to log
     * @param t a {@link Throwable} object
     */
    public static void createErrorLog( final Object message, final Throwable t )
    {
        Logger defaultLogger = getLogger();
        defaultLogger.error( message, t );
    }
}
