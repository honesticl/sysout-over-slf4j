/*
 * Copyright (c) 2009-2012 Robert Elliot
 * All rights reserved.
 *
 * Permission is hereby granted, free  of charge, to any person obtaining
 * a  copy  of this  software  and  associated  documentation files  (the
 * "Software"), to  deal in  the Software without  restriction, including
 * without limitation  the rights to  use, copy, modify,  merge, publish,
 * distribute,  sublicense, and/or sell  copies of  the Software,  and to
 * permit persons to whom the Software  is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this permission  notice  shall  be
 * included in all copies or substantial portions of the Software.
 *
 * THE  SOFTWARE IS  PROVIDED  "AS  IS", WITHOUT  WARRANTY  OF ANY  KIND,
 * EXPRESS OR  IMPLIED, INCLUDING  BUT NOT LIMITED  TO THE  WARRANTIES OF
 * MERCHANTABILITY,    FITNESS    FOR    A   PARTICULAR    PURPOSE    AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE,  ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package uk.org.lidalia.sysoutslf4j;

import org.junit.Test;

import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4J;
import uk.org.lidalia.sysoutslf4j.context.SysOutOverSLF4JSystemJarNotPresentException;

import static org.junit.Assert.assertEquals;
import static uk.org.lidalia.test.ShouldThrow.shouldThrow;

public class SysoutOverSlf4jWithNoSystemJarOnClasspathTests {

    @Test
    public void sendSystemOutAndErrToSLF4JWithoutSystemJarOnClasspath() {
        SysOutOverSLF4JSystemJarNotPresentException exception = shouldThrow(SysOutOverSLF4JSystemJarNotPresentException.class, new Runnable() {
            @Override
            public void run() {
                SysOutOverSLF4J.sendSystemOutAndErrToSLF4J();
            }
        });

        assertEquals(SysOutOverSLF4JSystemJarNotPresentException.MESSAGE, exception.getMessage());
    }

    @Test
    public void stopSendingSystemOutAndErrToSLF4JWithoutSystemJarOnClasspath() {
        SysOutOverSLF4JSystemJarNotPresentException exception = shouldThrow(SysOutOverSLF4JSystemJarNotPresentException.class, new Runnable() {
            @Override
            public void run() {
                SysOutOverSLF4J.stopSendingSystemOutAndErrToSLF4J();
            }
        });

        assertEquals(SysOutOverSLF4JSystemJarNotPresentException.MESSAGE, exception.getMessage());
    }

    @Test
    public void restoreOriginalSystemOutputsWithoutSystemJarOnClasspath() {
        SysOutOverSLF4JSystemJarNotPresentException exception = shouldThrow(SysOutOverSLF4JSystemJarNotPresentException.class, new Runnable() {
            @Override
            public void run() {
                SysOutOverSLF4J.restoreOriginalSystemOutputs();
            }
        });

        assertEquals(SysOutOverSLF4JSystemJarNotPresentException.MESSAGE, exception.getMessage());
    }

    @Test
    public void systemOutputsAreSLF4JPrintStreamsWithoutSystemJarOnClasspath() {
        SysOutOverSLF4JSystemJarNotPresentException exception = shouldThrow(SysOutOverSLF4JSystemJarNotPresentException.class, new Runnable() {
            @Override
            public void run() {
                SysOutOverSLF4J.systemOutputsAreSLF4JPrintStreams();
            }
        });

        assertEquals(SysOutOverSLF4JSystemJarNotPresentException.MESSAGE, exception.getMessage());
    }
}
