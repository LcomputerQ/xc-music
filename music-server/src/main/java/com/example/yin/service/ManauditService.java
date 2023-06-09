package com.example.yin.service;
import com.example.yin.domain.Manaudit;
import java.util.List;

public interface ManauditService {
    //    发送审核
    int SendAudit( Integer myid, Integer pyid);

    //    同意审核者
    int ConsentAudit( Integer pyid,Integer myid);
    //    查看审核
    List<Manaudit> AuditAll( Integer myid);
    //通过审核 或者 没有通过
    int getApproved(Integer myid,Integer pyid,Integer type);

}
