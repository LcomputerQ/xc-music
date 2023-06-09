package com.example.yin.dao;

import com.example.yin.domain.Manaudit;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManauditMapper {
//    发送审核
    int SendAudit(@Param("myid") Integer myid,@Param("pyid") Integer pyid);

//    同意审核者
    int ConsentAudit(@Param("pyid") Integer pyid,@Param("myid") Integer myid);
//    查看审核
    List<Manaudit> AuditAll(@Param("myid") Integer myid);
    //通过审核 或者 没有通过
    int getApproved(@Param("myid")Integer myid,@Param("pyid")Integer pyid,@Param("type") Integer type);
}
