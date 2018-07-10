package com.turvo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class FileLogProvider extends Provider {

  private final String file_location;

  public FileLogProvider(String ts_format, String log_level, String file_location) {
    super(ts_format, log_level);
    this.file_location = file_location;
  }

  private BufferedWriter getFileStream() throws Exception {
    System.out.println(file_location);
    File logFile = new File(file_location);
    if (!logFile.exists()) {
      //noinspection ResultOfMethodCallIgnored
      logFile.createNewFile();
    }
    return new BufferedWriter(new FileWriter(logFile));
  }

  @Override
  public String getTimestamp() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getTs_format()); // TODO: make it field
    return LocalDateTime.now().format(formatter);
  }

  private LinkedBlockingQueue<LogMessage> queue = new LinkedBlockingQueue<>();

  @Override
  public Queue<LogMessage> getQueue() {
    return queue;
  }

  @Override
  public boolean register() {
    try {
      BufferedWriter fileStream = getFileStream();
      Executors.newFixedThreadPool(1)
        .submit(() -> {
          try {
            while (true) {
              LogMessage take = queue.take();
              fileStream.write(take.getMsg());
              fileStream.newLine();
              fileStream.flush();
            }
          } catch (InterruptedException | IOException e) {
            e.printStackTrace();
          }
        });
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
