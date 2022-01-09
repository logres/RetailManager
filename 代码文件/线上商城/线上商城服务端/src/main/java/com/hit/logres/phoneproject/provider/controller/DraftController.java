package com.hit.logres.phoneproject.provider.controller;


import com.hit.logres.phoneproject.api.entity.Draft;
import com.hit.logres.phoneproject.api.entity.DraftLine;
import com.hit.logres.phoneproject.provider.mapper.DraftLineMapper;
import com.hit.logres.phoneproject.provider.mapper.DraftMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "Draft")
public class DraftController {
    @Autowired
    DraftMapper draftMapper;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public List<Draft> getAllDraftLine() {
        try {
            return draftMapper.getAllDraft();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    public int insertDraft(@RequestBody Draft draft) {
        try {
            draftMapper.insertDraft(draft);
            return draft.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public Boolean deleteDraft(@RequestBody Integer id) {
        try {
            draftMapper.deleteDraft(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/findById",method = RequestMethod.POST)
    public Draft findDraftById(@RequestBody Integer id) {
        try {
            return draftMapper.findDraftById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public Boolean updateDraft(@RequestBody Draft draft) {
        try {
            draftMapper.updateDraft(draft);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
