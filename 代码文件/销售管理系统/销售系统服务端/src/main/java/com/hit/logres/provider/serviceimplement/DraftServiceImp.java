package com.hit.logres.provider.serviceimplement;

import com.hit.logres.api.entity.Draft;
import com.hit.logres.provider.mapper.DraftMapper;
import com.hit.logres.api.service.DraftService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("DraftService")
@DubboService
public class DraftServiceImp implements DraftService {

    @Autowired
    DraftMapper draftMapper;

    @Override
    public List<Draft> getAllDraft() {
        try {
            return draftMapper.getAllDraft();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insertDraft(Draft user) {
        try {
            draftMapper.insertDraft(user);
            System.out.println(user.getId());
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public Boolean deleteDraft(Integer id) {
        try {
            draftMapper.deleteDraft(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Draft findDraftById(Integer id) {
        try {
            return draftMapper.findDraftById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Boolean updateDraft(Draft customer) {
        try {
            draftMapper.updateDraft(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
