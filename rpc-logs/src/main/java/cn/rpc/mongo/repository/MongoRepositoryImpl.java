package cn.rpc.mongo.repository;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import cn.rpc.mongo.common.utils.Pagination;

@Repository
public class MongoRepositoryImpl<T> implements MongoRepository<T> {

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    @Override
    public List<T> findAll(Class<T> entity) {
        return mongoTemplate.findAll(entity);
    }

    @Override
    public Pagination<T> findPagination(Class<T> entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<T> findByRegex(String regex, Class<T> entity) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Criteria criteria = new Criteria("name").regex(pattern.toString());
        return mongoTemplate.find(new Query(criteria), entity);

    }

    @Override
    public T findOne(String id, Class<T> entity) {
        return mongoTemplate.findOne(new Query(Criteria.where("id").is(id)), entity);
    }

    @Override
    public void insert(Object entity) {
        mongoTemplate.insert(entity);
    }

    @Override
    public void removeOne(String id, Class<T> entity) {
        Criteria criteria = Criteria.where("id").in(id);
        if (criteria != null) {
            Query query = new Query(criteria);
            if (query != null && mongoTemplate.findOne(query, entity) != null) {
                mongoTemplate.remove(mongoTemplate.findOne(query, entity));
            }
        }
    }

    @Override
    public void updateEntity(Object entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public T findEntityByCriteria(Criteria criteria, Class<T> entity) {
        Query query = new Query();
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, entity);
    }

}
