package com.turvo;

public class Logger extends LoggerFactory {

  private final String nameSpace;

  public Logger(String nameSpace) {
    this.nameSpace = nameSpace;
  }

  private void fireEvent(int logLevel, String msg, Throwable t) {
    LoggerFactory.getProviders().forEach(p -> {
      String enrichedMessage = p.getTimestamp().concat(" : ").concat("[" + nameSpace + "] ").concat(msg); // TODO: FORMAT HANDLER
      p.getQueue().add(new LogMessage(logLevel, enrichedMessage, t));
    });
  }

  private void fireEvent(int logLevel, String msg) {
    fireEvent(logLevel, msg, null);
  }

  public void trace(String msg) {
    fireEvent(LoggerLevel.TRACE.ordinal(), msg);
  }

  public void debug(String msg) {
    fireEvent(LoggerLevel.DEBUG.ordinal(), msg);
  }

  public void info(String msg) {
    fireEvent(LoggerLevel.INFO.ordinal(), msg);
  }

  public void warn(String msg) {
    fireEvent(LoggerLevel.WARN.ordinal(), msg);
  }

  public void error(String msg, Throwable t) {
    fireEvent(LoggerLevel.ERROR.ordinal(), msg, t);
  }

  public void fatal(String msg, Throwable t) {
    fireEvent(LoggerLevel.FATAL.ordinal(), msg, t);
  }
}
