package com.turvo;

public class LogMessage {
  private final int logLevel;
  private final String msg;
  private final Throwable t;

  public LogMessage(int logLevel, String msg, Throwable t) {
    this.logLevel = logLevel;
    this.msg = msg;
    this.t = t;
  }

  public LogMessage(int logLevel, String msg) {
    this.logLevel = logLevel;
    this.msg = msg;
    this.t = null;
  }

  public int getLogLevel() {
    return logLevel;
  }

  public String getMsg() {
    return msg;
  }

  public Throwable getT() {
    return t;
  }
}
