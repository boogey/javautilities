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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * This utility class contains methods to handle the default java.io package better before java 7 released.
 * 
 * @author Karsten Schulz <a href="mailto:lennylinux.ks@googlmail.com">(lennylinux.ks@googlmail.com)</a>
 */
public final class StreamUtil
{

    /**
     * A constant for the buffer size. This is set to 8192 bytes.
     */
    public static final int BUFFER_SIZE = 8192;

    /**
     * Close the {@link InputStream} without throwing a {@link IOException}.
     * 
     * @param is the {@link InputStream} that will be close.
     */
    public static void safeClose( final InputStream is )
    {
        try
        {
            if ( is != null )
            {
                is.close();
            }
        }
        catch ( final IOException e )
        {
            // ignore
        }
    }

    /**
     * Close the {@link OutputStream} without throwing a {@link IOException}.
     * 
     * @param os the {@link OutputStream} that will be close.
     */
    public static void safeClose( final OutputStream os )
    {
        try
        {
            if ( os != null )
            {
                os.close();
            }
        }
        catch ( final IOException e )
        {
            // ignore
        }
    }

    /**
     * Close the {@link Reader} without throwing a {@link IOException}.
     * 
     * @param reader the {@link Reader} that will be close.
     */
    public static void safeClose( final Reader reader )
    {
        try
        {
            if ( reader != null )
            {
                reader.close();
            }
        }
        catch ( final IOException e )
        {
            // ignore
        }
    }

    /**
     * Close the {@link Writer} without throwing a {@link IOException}.
     * 
     * @param writer the {@link Writer} that will be close.
     */
    public static void safeClose( final Writer writer )
    {
        try
        {
            if ( writer != null )
            {
                writer.close();
            }
        }
        catch ( final IOException e )
        {
            // ignore
        }
    }

    /**
     * Copy bytewise from {@link InputStream} to the {@link OutputStream}.
     * 
     * @param is the {@link InputStream} that will be read.
     * @param os the {@link OutputStream} taht will be write.
     * @throws IOException default {@link IOException} from {@link InputStream} and {@link OutputStream}.
     */
    public static void copyBytewise( final InputStream is, final OutputStream os )
        throws IOException
    {
        int data = -1;
        while ( ( data = is.read() ) != -1 )
        {
            os.write( data );
        }
        os.flush();
    }

    /***
     * Copy bytewise with a {@link BufferedInputStream} and {@link BufferedOutputStream}. This two streams will be
     * decorate with a buffer.
     * 
     * @param is the {@link InputStream} that will be read.
     * @param os the {@link OutputStream} taht will be write.
     * @throws IOException default {@link IOException} from {@link InputStream} and {@link OutputStream}.
     */
    public static void copyBuffered( final InputStream is, final OutputStream os )
        throws IOException
    {
        InputStream bufferedIn = decorateWithBuffer( is );
        OutputStream bufferedOut = decorateWithBuffer( os );

        copyBytewise( bufferedIn, bufferedOut );
    }

    /**
     * Copy with a own buffering ( byte array ) from a {@link InputStream} to a {@link OutputStream}.
     * 
     * @param is the {@link InputStream} that will be read.
     * @param os the {@link OutputStream} taht will be write.
     * @throws IOException default {@link IOException} from {@link InputStream} and {@link OutputStream}.
     */
    public static void copyOwnBuffering( final InputStream is, final OutputStream os )
        throws IOException
    {
        final byte[] buffer = new byte[StreamUtil.BUFFER_SIZE];
        int length = -1;
        while ( ( length = is.read( buffer, 0, StreamUtil.BUFFER_SIZE ) ) != -1 )
        {
            os.write( buffer, 0, length );
        }
        os.flush();
    }

    /**
     * Copy char wise from {@link Reader} to a {@link Writer}.
     * 
     * @param reader the {@link Reader} that will be read.
     * @param writer the {@link Writer} that will be write.
     * @throws IOException default {@link IOException} from {@link Reader} and {@link Writer}.
     */
    public static void copyCharWise( final Reader reader, final Writer writer )
        throws IOException
    {
        int data = -1;
        while ( ( data = reader.read() ) != -1 )
        {
            writer.write( data );
        }
        writer.flush();
    }

    /**
     * Copy the input from {@link Reader} to the {@link Writer} and decorate this parameters with a
     * {@link BufferedReader} and {@link BufferedWriter}.
     * 
     * @param reader the {@link Reader} that will be read.
     * @param writer the {@link Writer} that will be write.
     * @throws IOException default {@link IOException} from {@link Reader} and {@link Writer}.
     */
    public static void copyBuffered( final Reader reader, final Writer writer )
        throws IOException
    {
        final Reader bufferedIn = decorateWithBuffer( reader );
        final Writer bufferedOut = decorateWithBuffer( writer );

        copyCharWise( bufferedIn, bufferedOut );
    }

    /**
     * Copy with a own buffering from {@link Reader} to a {@link Writer} and use the char array.
     * 
     * @param reader the {@link Reader} that will be read.
     * @param writer the {@link Writer} that will be write.
     * @throws IOException default {@link IOException} from {@link Reader} and {@link Writer}.
     */
    public static void copyOwnBuffering( final Reader reader, final Writer writer )
        throws IOException
    {
        final char[] buffer = new char[StreamUtil.BUFFER_SIZE];
        int length = -1;
        while ( ( length = reader.read( buffer, 0, StreamUtil.BUFFER_SIZE ) ) != -1 )
        {
            writer.write( buffer, 0, length );
        }
        writer.flush();
    }

    /**
     * Decorate the {@link InputStream} with a {@link BufferedInputStream}. If the parameter is null, this method will
     * be throw a {@link NullPointerException}.
     * 
     * @param inStream the {@link InputStream} that will be decorate.
     * @return the decorate {@link InputStream}.
     */
    public static InputStream decorateWithBuffer( final InputStream inStream )
    {
        if ( inStream == null )
        {
            throw new NullPointerException( "parameter 'inStream' must not be null" );
        }

        if ( !( inStream instanceof BufferedInputStream ) )
        {
            return new BufferedInputStream( inStream );
        }

        return inStream;
    }

    /**
     * Decorate a {@link OutputStream} with a {@link BufferedOutputStream}. If the parameter is null, this method will
     * be throw a {@link NullPointerException}
     * 
     * @param outStream the {@link OutputStream} that will be decorate.
     * @return the decorate {@link OutputStream}.
     */
    public static OutputStream decorateWithBuffer( final OutputStream outStream )
    {
        if ( outStream == null )
        {
            throw new NullPointerException( "parameter 'outStream' must not be null!" );
        }

        return outStream instanceof BufferedOutputStream ? outStream
                        : new BufferedOutputStream( outStream, StreamUtil.BUFFER_SIZE );
    }

    /**
     * Decorate a {@link Reader} with a {@link BufferedReader}. If the parameter is null, this method will be throw a
     * {@link NullPointerException}.
     * 
     * @param reader the {@link Reader} that will be decorate.
     * @return the decorate {@link Reader}.
     */
    public static Reader decorateWithBuffer( final Reader reader )
    {
        if ( reader == null )
        {
            throw new IllegalArgumentException( "parameter 'reader' must not be null!" );
        }

        if ( !( reader instanceof BufferedReader ) )
        {
            return new BufferedReader( reader );
        }

        return reader;
    }

    /**
     * Decorate a {@link Writer} with a {@link BufferedWriter}. If the parameter is null, this method will be throw a
     * {@link NullPointerException}.
     * 
     * @param writer the {@link Writer} that will be decorate.
     * @return the decorate {@link Writer}.
     */
    public static Writer decorateWithBuffer( final Writer writer )
    {
        if ( writer == null )
        {
            throw new IllegalArgumentException( "parameter 'writer' must not be null!" );
        }

        return writer instanceof BufferedWriter ? writer : new BufferedWriter( writer );
    }
}
