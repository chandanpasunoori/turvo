package com.turvo;

import java.util.Queue;

public abstract class Provider {
  private final String ts_format;
  private final String log_level;

  public Provider(String ts_format, String log_level) {
    this.ts_format = ts_format;
    this.log_level = log_level;
  }

  public abstract String getTimestamp();

  public abstract Queue<LogMessage> getQueue();

  public abstract boolean register();

  public String getTs_format() {
    return ts_format;
  }

  public String getLog_level() {
    return log_level;
  }
}
