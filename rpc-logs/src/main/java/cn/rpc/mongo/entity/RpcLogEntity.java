package cn.rpc.mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Vincent.wang
 *
 */
@Document(collection = "logs")
public class RpcLogEntity implements BaseEntity<String> {

    private static final long serialVersionUID = 1538192110129669137L;

    @Id
    @Field("_id")
    private String id;

    // 日志对应的业务系统标识
    @Field("project")
    private String project;

    // 日志产生的时间
    @Field("time_stamp")
    private Long timeStamp;

    // 日志级别
    @Field("level")
    private String level;

    // 日志信息当的线程名
    @Field("thread_name")
    private String threadName;

    // 日志当中类名称
    @Field("logger_name")
    private String loggerName;

    // 日志的描述
    @Field("formatted_message")
    private String formattedMessage;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLoggerName() {
        return loggerName;
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getFormattedMessage() {
        return formattedMessage;
    }

    public void setFormattedMessage(String formattedMessage) {
        this.formattedMessage = formattedMessage;
    }

}
