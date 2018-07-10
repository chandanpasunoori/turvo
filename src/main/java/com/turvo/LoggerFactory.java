package com.turvo;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.List;

public class LoggerFactory {
  private final Config config = ConfigFactory.load();

  public static List<Provider> getProviders() {
    return providers;
  }

  private static final List<Provider> providers = new ArrayList<>();


  private static LoggerFactory ourInstance = new LoggerFactory();

  public static LoggerFactory getInstance() {
    return ourInstance;
  }

  public LoggerFactory() {
    config.getStringList("providers").forEach(p -> {
      Config pl = ConfigFactory.load(p);
      String sink_type = pl.getString("sink_type");
      String ts_format = pl.getString("ts_format");
      String log_level = pl.getString("log_level");
      switch (sink_type) {
        case "FILE":
          String file_location = pl.getString("file_location");
          providers.add(new Provider(ts_format));
          break;
        case "DB":
          String dbhost = pl.getString("dbhost");
          int dbport = pl.getInt("dbport");
          providers.add(new Provider(ts_format));
          break;
      }
    });
  }

  public static Logger getLogger(String namespace) {
    return new Logger(namespace);
  }
}
