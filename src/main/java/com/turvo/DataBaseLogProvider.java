package com.turvo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DataBaseLogProvider extends Provider {
  private final String dbhost;
  private final int dbport;

  public DataBaseLogProvider(String ts_format, String log_level, String dbhost, int dbport) {
    super(ts_format, log_level);
    this.dbhost = dbhost;
    this.dbport = dbport;
  }

  @Override
  public String getTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getTs_format()); // TODO: make it field
    return LocalDateTime.now().format(formatter);
  }

  private ConcurrentLinkedQueue<LogMessage> queue = new ConcurrentLinkedQueue<>();

  @Override
  public Queue<LogMessage> getQueue() {
    return queue;
  }

  @Override
  public boolean register() {
    return false;
  }
}
