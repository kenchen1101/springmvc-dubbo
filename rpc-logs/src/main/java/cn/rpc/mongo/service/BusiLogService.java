package cn.rpc.mongo.service;

import java.util.List;

import cn.rpc.mongo.common.utils.Page;
import cn.rpc.mongo.dto.BusiLogDto;
import cn.rpc.mongo.dto.DownloadBusiLogDto;
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
     * 根据条件分页查询日志
     * 
     * @param page
     *            分页对象
     * @param dto
     *            日志dto对象
     * @return
     */
    public Page<BusiLog> findBusiLogByPage(Page<BusiLog> page, BusiLogDto dto);

    /**
     * 查询所有的日志
     * 
     * @return
     */
    public List<BusiLog> findBusiLogAll();

    /***
     * 根据条件查询日志，并生成byte[]
     * 
     * @param busiLogDto
     * @return
     */
    public DownloadBusiLogDto getBytes(BusiLogDto busiLogDto);

}
