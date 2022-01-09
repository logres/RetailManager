package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.DraftLine;
import com.hit.logres.provider.mapper.DraftLineMapper;
import com.hit.logres.api.service.DraftLineService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("DraftLineService")
@DubboService
public class DraftLineServiceImp implements DraftLineService {

    @Autowired
    DraftLineMapper draftlineMapper;

    @Override
    public List<DraftLine> getAllDraftLine() {
        try {
            return draftlineMapper.getAllDraftLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertDraftLine(DraftLine user) {
        try {
            draftlineMapper.insertDraftLine(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteDraftLine(Integer id) {
        try {
            draftlineMapper.deleteDraftLine(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public DraftLine findDraftLineById(Integer id) {
        try {
            return draftlineMapper.findDraftLineById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateDraftLine(DraftLine customer) {
        try {
            draftlineMapper.updateDraftLine(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<DraftLine> findDraftLineByDraftId(Integer id) {
        try {
            return draftlineMapper.findDraftLineByDraftId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}