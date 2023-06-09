package com.example.yin.service.impl;

import com.example.yin.dao.ManauditMapper;
import com.example.yin.domain.Manaudit;
import com.example.yin.service.ManauditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManauditServiceImpl implements ManauditService {
    @Autowired
    private ManauditMapper manauditMapper;
    @Override
    public int SendAudit(Integer myid, Integer pyid) {
        return manauditMapper.SendAudit(myid, pyid);
    }

    @Override
    public int ConsentAudit(Integer pyid, Integer myid) {
        return manauditMapper.ConsentAudit(pyid, myid);
    }

    @Override
    public List<Manaudit> AuditAll(Integer myid) {
        return manauditMapper.AuditAll(myid);
    }

    @Override
    public int getApproved(Integer myid, Integer pyid, Integer type) {
        return manauditMapper.getApproved(myid, pyid, type);
    }
}
