package cn.rpc.mongo.service;

import java.util.Date;
import java.util.List;

import cn.rpc.mongo.common.utils.Page;
import cn.rpc.mongo.entity.BusiLog;

/**
 * @author Vincent.wang
 *
 */
public interface BusiLogService {

    /**
     * 根据ID日志
     * 
     * @param id
     *            日志ID
     * @return
     */
    public BusiLog findBusiLogById(String id);

    /**
     * 查询日志
     * 
     * @param page
     *            分页对象
     * @param beginDate
     *            查询开始时间
     * @param endDate
     *            查询结束时间
     * @param systemName
     *            日志所属系统
     * @param level
     *            日志级别
     * @param threadName
     *            线程名
     * @param logName
     *            日志名
     * @param message
     *            日志内容
     * @return
     */
    public Page<BusiLog> findBusiLogByPage(Page<BusiLog> page, Date beginDate, Date endDate, String systemName, String level, String threadName, String logName, String message);

    /**
     * 查询所有的日志
     * 
     * @return
     */
    public List<BusiLog> findBusiLogAll();

}
