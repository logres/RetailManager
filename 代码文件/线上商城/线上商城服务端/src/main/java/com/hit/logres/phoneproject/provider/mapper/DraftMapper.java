package com.hit.logres.phoneproject.provider.mapper;



import com.hit.logres.phoneproject.api.entity.Draft;

import java.util.List;

public interface DraftMapper {
    void insertDraft(Draft draft) throws Exception;

    void deleteDraft(Integer id) throws Exception;

    Draft findDraftById(Integer id) throws Exception;

    List<Draft> getAllDraft() throws Exception;

    void updateDraft(Draft draft) throws Exception;
}
