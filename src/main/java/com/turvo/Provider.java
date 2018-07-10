package com.turvo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Provider {
  private final String ts_format;

  public Provider(String ts_format) {
    this.ts_format = ts_format;
  }

  public String getTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ts_format); // TODO: make it field
    return LocalDateTime.now().format(formatter);
  }

  public static Queue<LogMessage> getQueue() {
    return queue;
  }

  private static final Queue<LogMessage> queue = new ConcurrentLinkedQueue<>();

  public Boolean register() {
    return false;
  }

  public void trigger(int logLevel, String msg, Throwable t) {
    String withCause = msg.concat(": CAUSED BY [" + t.getMessage() + "]");
  }

  public void trigger(int logLevel, String msg) {

  }
}
