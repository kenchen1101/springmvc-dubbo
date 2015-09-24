package com.rpc.logback;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.status.ErrorStatus;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.ServerAddress;
import com.rpc.util.DateUtil;

public class MongoDbAppender<E extends ILoggingEvent> extends AppenderBase<E> {

    // 项目名
    private String system;

    // 数据库
    private String database;

    // mongo collection
    private String collection;

    private List<MongoServerAddress> servers = new ArrayList<MongoServerAddress>();

    private Mongo mongo;

    private DBCollection dbCol;

    @Override
    protected void append(E event) {
        BasicDBObject doc = new BasicDBObject();
        doc.put("system_name", system);
        doc.put("time_stamp", DateUtil.transferLongToString(event.getTimeStamp()));
        doc.put("level", logLevel(event.getLevel()));
        doc.put("thread_name", event.getThreadName());
        doc.put("log_name", event.getLoggerName());
        doc.put("message", event.getFormattedMessage());
        dbCol.insert(doc);
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
        // Make sure we have all required configuration info
        // has been provided.
        if (servers.isEmpty()) {
            addStatus(new ErrorStatus("No MongoServerAddress values provided", this));
            noErrors = false;
        }
        if (database == null) {
            addStatus(new ErrorStatus("No database name provided", this));
            noErrors = false;
        }
        if (collection == null) {
            addStatus(new ErrorStatus("No collection name provided", this));
        }

        // Make sure all configuration values have been
        // provided for the MongoDB servers we're going
        // to try to connect to.
        if (noErrors) {
            for (MongoServerAddress server : servers) {
                if (!server.isValid()) {
                    addStatus(new ErrorStatus("MongoServerAddress was not valid. " + "address=" + server.getAddress() + ", port=" + server.getPort(), this));
                    noErrors = false;
                }
            }
        }

        // Convert the configured server addresses into
        // values that can be used to create the connection
        // to the MongoDB servers.
        if (noErrors) {
            List<ServerAddress> addresses = new ArrayList<ServerAddress>(servers.size());
            for (MongoServerAddress server : servers) {
                try {
                    addresses.add(new ServerAddress(server.getAddress(), server.getPort()));
                } catch (UnknownHostException e) {
                    noErrors = false;
                    addStatus(new ErrorStatus("Error connecting to server. " + "address=" + server.getAddress() + ", port=" + server.getPort(), this, e));
                }
            }

            // OK so far so create the connection.
            if (noErrors) {
                mongo = new Mongo(addresses);
                dbCol = mongo.getDB(database).getCollection(collection);
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

    public void addMongoServerAddress(MongoServerAddress address) {
        servers.add(address);
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }
}
