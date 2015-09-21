package cn.rpc.mongo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.rpc.mongo.entity.RpcLogEntity;
import cn.rpc.mongo.repository.MongoRepository;
import cn.rpc.mongo.service.RpcLogService;

/**
 * @author Vincent.wang
 *
 */
@Service
public class RpcLogServiceImpl implements RpcLogService {

    private static final Logger log = LoggerFactory.getLogger(RpcLogServiceImpl.class);

    @Autowired
    private MongoRepository<RpcLogEntity> baseRepository;

    @Override
    public RpcLogEntity findRpcLogEntityById(String id) {
        log.warn("## find RpcLogEntity By Id , id={}", id);
        return baseRepository.findOne(id, RpcLogEntity.class);
    }

    @Override
    public List<RpcLogEntity> findRpcLogEntityAll() {
        return baseRepository.findAll(RpcLogEntity.class);
    }

}
