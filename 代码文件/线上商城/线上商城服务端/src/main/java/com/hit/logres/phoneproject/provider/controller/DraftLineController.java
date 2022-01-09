package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.DraftLine;
import com.hit.logres.phoneproject.provider.mapper.DraftLineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/DraftLine")
public class DraftLineController {
    @Autowired
    DraftLineMapper draftlineMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<DraftLine> getAllDraftLine() {
        try {
            return draftlineMapper.getAllDraftLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertDraftLine(@RequestBody DraftLine user) {
        try {
            draftlineMapper.insertDraftLine(user);
            return user.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteDraftLine(@RequestBody Integer id) {
        try {
            draftlineMapper.deleteDraftLine(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public DraftLine findDraftLineById(@RequestBody Integer id) {
        try {
            return draftlineMapper.findDraftLineById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateDraftLine(@RequestBody DraftLine customer) {
        try {
            draftlineMapper.updateDraftLine(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findByDraftId",method = RequestMethod.POST)
    public List<DraftLine> findDraftLineByDraftId(@RequestBody Integer id) {
        try {
            return draftlineMapper.findDraftLineByDraftId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
