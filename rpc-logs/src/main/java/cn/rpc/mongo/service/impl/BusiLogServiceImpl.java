package cn.rpc.mongo.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import cn.rpc.mongo.common.utils.Page;
import cn.rpc.mongo.entity.BusiLog;
import cn.rpc.mongo.repository.MongoRepositoryImpl;
import cn.rpc.mongo.service.BusiLogService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class BusiLogServiceImpl extends MongoRepositoryImpl<BusiLog> implements BusiLogService {

    private static final Logger log = LoggerFactory.getLogger(BusiLogServiceImpl.class);

    @Override
    public Class<BusiLog> getEntityClass() {
        return BusiLog.class;
    }

    @Override
    public BusiLog findBusiLogById(String id) {
        log.warn("## find BusiLog By Id , id={}", id);
        return findOne(id);
    }

    @Override
    public List<BusiLog> findBusiLogAll() {
        return findAll();
    }

    @Override
    public Page<BusiLog> findBusiLogByPage(Page<BusiLog> page, Date beginDate, Date endDate, String systemName, String level, String threadName, String logName, String message) {
        Query query = new Query();

        if (null != beginDate && null != endDate)
            query.addCriteria(Criteria.where("createTime").gte(beginDate).lte(endDate));

        if (StringUtils.isNotBlank(systemName))
            query.addCriteria(Criteria.where("system_name").is(systemName));

        if (StringUtils.isNotBlank(level))
            query.addCriteria(Criteria.where("level").is(level));

        if (StringUtils.isNotBlank(threadName))
            query.addCriteria(Criteria.where("thread_name").regex(".*?" + threadName + ".*"));

        if (StringUtils.isNotBlank(logName))
            query.addCriteria(Criteria.where("log_name").regex(".*?" + logName + ".*"));

        if (StringUtils.isNotBlank(message))
            query.addCriteria(Criteria.where("message").regex(".*?" + message + ".*"));

        return findPagination(page, query);
    }

}
