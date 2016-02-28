package com.rpc.logback;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.status.ErrorStatus;

/**
 * logback 日志工具类，输出到mongodb
 */
public class MongoDbAppender<E extends ILoggingEvent> extends AppenderBase<E> {

    // System name
    private String system;

    // db name
    private String database;

    // mongo collection
    private String collection;

    // mongo host
    private String host;

    // mongo port
    private String port;

    private Mongo mongo;

    private MongoCollection<BasicDBObject> dbCol;

    @Override
    protected void append(E event) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("system_name", system);
        doc.put("create_time", new DateTime(event.getTimeStamp()).toDate());
        doc.put("level", logLevel(event.getLevel()));
        doc.put("thread_name", event.getThreadName());
        doc.put("log_name", event.getLoggerName());
        doc.put("message", event.getFormattedMessage());
        dbCol.insertOne(doc);
    }

    private String logLevel(Level level) {
        switch (level.toInt()) {
        case Level.DEBUG_INT:
            return "debug";
        case Level.INFO_INT:
            return "info";
        case Level.WARN_INT:
            return "warn";
        case Level.ERROR_INT:
            return "error";
        case Level.TRACE_INT:
            return "trace";
        default:
            return "X";
        }
    }

    @Override
    public void start() {

        boolean noErrors = true;
        if (StringUtils.isBlank(host) || StringUtils.isBlank(port)) {
            System.err.println("No MongoServerAddress values provided");
            addStatus(new ErrorStatus("No MongoServerAddress values provided", this));
            noErrors = false;
        }
        if (database == null) {
            addStatus(new ErrorStatus("No database name provided", this));
            System.out.println("No database name provided");
            noErrors = false;
        }
        if (collection == null) {
            System.out.println("No collection name provided");
            addStatus(new ErrorStatus("No collection name provided", this));
        }
        if (noErrors) {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>();
            try {
                addresses.add(new ServerAddress(host, Integer.parseInt(port)));
            } catch (Exception e) {
                noErrors = false;
                System.out.println("Error connecting to server.");
                addStatus(new ErrorStatus("Error connecting to server." + "address=" + host + ", port=" + port, this, e));
            }
            if (noErrors) {
                System.out.println("host=" + host + " 、port=" + port + " 、database=" + database + " 、collection=" + collection);
                dbCol = new MongoClient(host, Integer.parseInt(port)).getDatabase(database).getCollection(collection, BasicDBObject.class);
                super.start();
            }
        }
    }

    @Override
    public void stop() {
        if (mongo != null) {
            mongo.close();
        }
        super.stop();
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setMongo(Mongo mongo) {
        this.mongo = mongo;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
