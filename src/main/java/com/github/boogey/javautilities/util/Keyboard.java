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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class is a special utility-class. This class represented the keyboard as hardware device. It's possible to read
 * the input values from the commandline to the java project. This utility class converts the input values into the
 * typical primitiv data types. The reading of the input value will be stopped, with a new line ( default: ENTER-key ).
 * 
 * @author Karsten Schulz <a href="mailto:lennylinux.ks@googlmail.com">(lennylinux.ks@googlmail.com)</a>
 */
public final class Keyboard
{

    private static String getInput()
    {
        String input;
        BufferedReader reader;
        reader = new BufferedReader( new InputStreamReader( System.in ) );
        try
        {
            input = reader.readLine();
            return input;
        }
        catch ( IOException ioe )
        {
            LoggerUtil.createWarningLog( "unable to read from input device!", ioe );
        }
        return null;
    }

    /**
     * Return the input value into the int primitive data type. If the parsing wasn't successful, this method will be
     * throw a {@link NumberFormatException}.
     * 
     * @return the input value as int primitive data type.
     */
    public static int getInteger()
        throws NumberFormatException
    {
        String input = getInput();
        int number;
        number = Integer.parseInt( input );
        return number;
    }

    /**
     * Return the input value into the float primitive data type. If this parsing wasn't successful, this method will be
     * throw a {@link NumberFormatException}.
     * 
     * @return the input value as float primitive data type.
     */
    public static float getFloat()
        throws NumberFormatException
    {
        String input = getInput();
        float number;
        number = Float.parseFloat( input );
        return number;
    }

    /**
     * Return the input value into the float primitive data type. If the input value is more than one letter, this
     * method will be throw a {@link IllegalArgumentException}.
     * 
     * @return the input value as a char primitive data type.
     */
    public static char getChar()
    {
        String input = getInput();
        char letter = ' ';
        if ( input.length() >= 1 )
        {
            letter = input.charAt( 0 );
        }
        else
        {
            throw new IllegalArgumentException( "input value is not a character data type!: " + input );
        }

        return letter;
    }

    /**
     * Return the input value into the double primitive data type. If this parsing wasn't successful, this method will
     * be throw a {@link NumberFormatException}.
     * 
     * @return the input value as a double primitive data type
     */
    public static double getDouble()
        throws NumberFormatException
    {
        String input = getInput();
        double number;
        number = Double.parseDouble( input );
        return number;
    }

    /**
     * Return the input value as a {@link String}.
     * 
     * @return the input value as a {@link String}.
     */
    public static String getString()
    {
        String input = getInput();
        return input;
    }
}
