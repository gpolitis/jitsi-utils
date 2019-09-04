/*
 * Copyright @ 2019 - Present 8x8, Inc
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
package org.jitsi.utils;

import java.io.*;
import java.nio.charset.*;

/**
 * Implements utility functions to facilitate work with <tt>String</tt>s.
 *
 * @author Grigorii Balutsel
 * @author Emil Ivov
 *
 * @deprecated Use StringUtils from apache commons.
 */
@Deprecated
public final class StringUtils
{
    /**
     * Prevents the initialization of <tt>StringUtils</tt> instances because the
     * <tt>StringUtils</tt> class implements utility function only.
     */
    private StringUtils()
    {
    }

    /**
     * Checks whether a string is {@code null} or blank (empty or whitespace).
     *
     * @param s the string to analyze.
     * @return {@code true} if the string is {@code null} or blank.
     */
    public static boolean isNullOrEmpty(String s)
    {
        return isNullOrEmpty(s, true);
    }

    /**
     * Indicates whether string is <tt>null</tt> or empty.
     *
     * @param s the string to analyze.
     * @param trim indicates whether to trim the string.
     * @return <tt>true</tt> if string is <tt>null</tt> or empty.
     */
    public static boolean isNullOrEmpty(String s, boolean trim)
    {
        if (s == null)
            return true;
        if (trim)
            s = s.trim();
        return s.length() == 0;
    }

    /**
     * Creates <tt>InputStream</tt> from the string in UTF8 encoding.
     *
     * @param string the string to convert.
     * @return the <tt>InputStream</tt>.
     * @throws UnsupportedEncodingException if UTF8 is unsupported.
     */
    public static InputStream fromString(String string)
            throws UnsupportedEncodingException
    {
        return fromString(string, "UTF-8");
    }

    /**
     * Creates <tt>InputStream</tt> from the string in the specified encoding.
     *
     * @param string   the string to convert.
     * @param encoding the encoding
     * @return the <tt>InputStream</tt>.
     * @throws UnsupportedEncodingException if the encoding is unsupported.
     */
    public static InputStream fromString(String string, String encoding)
            throws UnsupportedEncodingException
    {
        return new ByteArrayInputStream(string.getBytes(encoding));
    }

    /**
     * Returns the UTF8 bytes for <tt>string</tt> and handles the unlikely case
     * where UTF-8 is not supported.
     *
     * @param string the <tt>String</tt> whose bytes we'd like to obtain.
     * @return <tt>string</tt>'s bytes.
     */
    public static byte[] getUTF8Bytes(String string)
    {
        try
        {
            return string.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException exc)
        {
            // shouldn't happen. UTF-8 is always supported, anyways ... if
            // this happens, we'll cheat
            return string.getBytes();
        }
    }

    /**
     * Converts <tt>string</tt> into an UTF8 <tt>String</tt> and handles the
     * unlikely case where UTF-8 is not supported.
     *
     * @param bytes the <tt>byte</tt> array that we'd like to convert into a
     * <tt>String</tt>.
     * @return the UTF-8 <tt>String</tt>.
     */
    public static String getUTF8String(byte[] bytes)
    {
        try
        {
            return new String(bytes, "UTF-8");
        }
        catch(UnsupportedEncodingException exc)
        {
            // shouldn't happen. UTF-8 is always supported, anyways ... if
            // this happens, we'll cheat
            return new String(bytes);
        }
    }

    /**
     * Indicates whether the given string contains any letters.
     *
     * @param string the string to check for letters
     * @return <tt>true</tt> if the given string contains letters;
     * <tt>false</tt>, otherwise
     */
    public static boolean containsLetters(String string)
    {
        for (int i = 0; i < string.length(); i++)
        {
            if (Character.isLetter(string.charAt(i)))
                return true;
        }
        return false;
    }

    /**
     * Initializes a new <tt>String</tt> instance by decoding a specified array
     * of bytes (mostly used by JNI).
     *
     * @param bytes the bytes to be decoded into characters/a new
     * <tt>String</tt> instance
     * @return a new <tt>String</tt> instance whose characters were decoded from
     * the specified <tt>bytes</tt>
     */
    public static String newString(byte[] bytes)
    {
        if ((bytes == null) || (bytes.length == 0))
            return null;
        else
        {
            Charset defaultCharset = Charset.defaultCharset();
            String charsetName
                = (defaultCharset == null) ? "UTF-8" : defaultCharset.name();

            try
            {
                return new String(bytes, charsetName);
            }
            catch (UnsupportedEncodingException ueex)
            {
                return new String(bytes);
            }
        }
    }
}
