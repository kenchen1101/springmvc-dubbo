package cn.rpc.mongo.service;

import java.util.List;

import cn.rpc.mongo.entity.RpcLogEntity;

/**
 * @author Vincent.wang
 *
 */
public interface RpcLogService {

    /**
     * 根据ID查询公开问卷
     * 
     * @param id
     *            公开问卷ID
     * @return
     */
    public RpcLogEntity findRpcLogEntityById(String id);

    /**
     * 查询所有的公开问卷
     * 
     * @return
     */
    public List<RpcLogEntity> findRpcLogEntityAll();

}
