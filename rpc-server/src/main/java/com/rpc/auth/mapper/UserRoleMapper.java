package com.rpc.auth.mapper;

import org.springframework.stereotype.Repository;

import com.rpc.auth.model.UserRole;
import com.rpc.framework.mybatis.mapper.BaseMapper;

/**
 * 用户角色Mapper
 */
@Repository
public interface UserRoleMapper extends BaseMapper<String, UserRole> {

}
