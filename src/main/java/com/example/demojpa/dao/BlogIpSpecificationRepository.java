package com.example.demojpa.dao;

import com.example.demojpa.model.BlogIp;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * @author: ZhaPeng
 * @Date: 2019-07-18 13:54
 * @Description:
 */
public interface BlogIpSpecificationRepository extends JpaSpecificationExecutor<BlogIp>, Repository<BlogIp, Integer> {
}
