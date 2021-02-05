package com.aurora.jpa.service;

import com.aurora.jpa.api.SysLogService;
import com.aurora.jpa.model.SysLog;
import com.aurora.jpa.repository.SysLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author :PHQ
 * @date：2021/1/29
 **/
@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    SysLogRepository sysLogRepository;

    @Override
    public Page<SysLog> findRecordList(SysLog syslog) {
        try {
            //排序规则和分页
            List<String> sortProperties = new ArrayList<String>();
            sortProperties.add("logCreateTime");

            //Sort sort = new Sort(Sort.Direction.DESC,sortProperties);
            Sort sort = Sort.by(Sort.Direction.ASC, "logCreateTime");
//            Sort sort = new Sort(
//                    new Sort.Order(
//                            Sort.Direction.DESC, "logCreateTime"));
            //创建一个分页对象
            PageRequest pageRequest =  PageRequest.of(syslog.getStart(), syslog.getLimit(), sort);
            //创建一个复杂查询对象
            Specification specification = new Specification() {
                @Override
                public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                    //增加筛选条件
                    Predicate predicate = cb.conjunction();

                    predicate.getExpressions().add(cb.equal(root.get("logUser"), syslog.getLogUser()));
                    //起始日期

                    return predicate;
                }
            };
            //执行查询
            Page page = sysLogRepository.findAll(specification, pageRequest);
            return page;
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }

}