package com.hit.logres.phoneproject.provider.mapper;

import com.hit.logres.phoneproject.api.entity.DraftLine;

import java.util.List;

public interface DraftLineMapper {
    void insertDraftLine(DraftLine draftLine) throws Exception;

    void deleteDraftLine(Integer id) throws Exception;

    DraftLine findDraftLineById(Integer id) throws Exception;

    List<DraftLine> getAllDraftLine() throws Exception;

    void updateDraftLine(DraftLine draftline) throws Exception;

    List<DraftLine> findDraftLineByDraftId(Integer id) throws Exception;
}
