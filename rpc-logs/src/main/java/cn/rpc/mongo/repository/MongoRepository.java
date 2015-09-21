package cn.rpc.mongo.repository;

import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;

import cn.rpc.mongo.common.utils.Pagination;

public interface MongoRepository<T> {

    public void insert(Object entity);

    public T findOne(String id, Class<T> entity);

    public List<T> findByRegex(String regex, Class<T> entity);

    public void removeOne(String id, Class<T> entity);

    public List<T> findAll(Class<T> entity);

    public Pagination<T> findPagination(Class<T> entity);

    public void updateEntity(Object entity);

    public T findEntityByCriteria(Criteria criteria, Class<T> entity);

}
