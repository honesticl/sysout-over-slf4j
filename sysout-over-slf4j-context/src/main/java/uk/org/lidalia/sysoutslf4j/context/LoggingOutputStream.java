package uk.org.lidalia.sysoutslf4j.context;

import static uk.org.lidalia.sysoutslf4j.context.CallOrigin.getCallOrigin;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.org.lidalia.sysoutslf4j.context.exceptionhandlers.ExceptionHandlingStrategy;

class LoggingOutputStream extends ByteArrayOutputStream {

    private static final Logger log = LoggerFactory.getLogger(LoggingOutputStream.class);

    private final LogLevel level;
    private final ExceptionHandlingStrategy exceptionHandlingStrategy;
    private final PrintStream originalPrintStream;
    private final LoggingSystemRegister loggingSystemRegister;

    LoggingOutputStream(final LogLevel level, final ExceptionHandlingStrategy exceptionHandlingStrategy,
            final PrintStream originalPrintStream, final LoggingSystemRegister loggingSystemRegister) {
        super();
        this.level = level;
        this.exceptionHandlingStrategy = exceptionHandlingStrategy;
        this.originalPrintStream = originalPrintStream;
        this.loggingSystemRegister = loggingSystemRegister;
    }

    @Override
    public synchronized void flush() throws IOException {
        final CallOrigin callOrigin = getCallOrigin(loggingSystemRegister);
        if (callOrigin.isInLoggingSystem()) {
            writeToOriginalPrintStream();
        } else {
            String bufferAsString = new String(toByteArray());
            if (bufferAsString.endsWith("\n")) {
                log(callOrigin, bufferAsString);
            } else if (bufferAsString.contains("\n")) {
                List<String> messages = Arrays.asList(bufferAsString.split("\n"));
                List<String> messagesToLog = messages.subList(0, messages.size() - 1);
                for (String messageToLog : messagesToLog) {
                    log(callOrigin, messageToLog);
                }
                String lastMessage = messages.get(messages.size() - 1);
                write(lastMessage.getBytes());
            }
        }
    }

    private void writeToOriginalPrintStream() throws IOException {
        exceptionHandlingStrategy.notifyNotStackTrace();
        warnAboutPerformance();
        writeTo(originalPrintStream);
        originalPrintStream.flush();
        reset();
    }

    private final AtomicBoolean warned = new AtomicBoolean(false);

    private void warnAboutPerformance() {
        if (warned.compareAndSet(false, true)) {
            log.warn(LoggingMessages.PERFORMANCE_WARNING);
        }
    }

    private void log(final CallOrigin callOrigin, String bufferAsString) {
        String valueToLog = StringUtils.stripEnd(bufferAsString, " \r\n");
        if (valueToLog.length() > 0) {
            final Logger log = LoggerFactory.getLogger(callOrigin.getClassName());
            if (callOrigin.isPrintingStackTrace()) {
                exceptionHandlingStrategy.handleExceptionLine(valueToLog, log);
            } else {
                exceptionHandlingStrategy.notifyNotStackTrace();
                level.log(log, valueToLog);
            }
        }
        reset();
    }

    protected void finalize() throws Throwable {
        super.finalize();
        String bufferAsString = StringUtils.stripEnd(new String(toByteArray()), " \r\n");
        if (bufferAsString.length() > 0) {
            level.log(LoggerFactory.getLogger(SysOutOverSLF4J.class), bufferAsString);
        }
        reset();
    }
}