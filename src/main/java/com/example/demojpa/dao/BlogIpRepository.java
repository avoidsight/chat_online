package com.example.demojpa.dao;

import com.example.demojpa.model.BlogIp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author: ZhaPeng
 * @Date: 2019-07-17 15:45
 * @Description:
 */
public interface BlogIpRepository extends JpaRepository<BlogIp, Integer> {
    /**
     * 一般化
     * @param specification
     * @param pageable
     * @return
     */
    Page<BlogIp> findAll(Specification<BlogIp> specification, Pageable pageable);

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    @Query(value = "select * from blog_ip o where o.id= :id ",nativeQuery=true)
    BlogIp findById(@Param("id") int id);

    @Query(value = "select max(rank) from blog_ip",nativeQuery = true)
    Integer findMaxRank();

}