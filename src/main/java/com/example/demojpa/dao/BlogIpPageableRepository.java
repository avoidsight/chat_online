package com.example.demojpa.dao;

import com.example.demojpa.model.BlogIp;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * @author: ZhaPeng
 * @Date: 2019-07-18 09:55
 * @Description:
 */
public interface BlogIpPageableRepository extends PagingAndSortingRepository<BlogIp,Integer> {
    List<BlogIp> findByCity(String city, Pageable pageable);
    List<BlogIp> findByIp(String ip, Sort sort);
}
