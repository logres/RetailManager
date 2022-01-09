package com.hit.logres.provider.mapper;

import com.hit.logres.api.entity.Good;

import java.util.List;

public interface GoodMapper {
    void insertGood(Good good) throws Exception;

    void deleteGood(Integer id) throws Exception;

    Good findGoodById(Integer id) throws Exception;

    List<Good> getAllGood() throws Exception;

    void updateGood(Good good) throws Exception;

    List<Good> findGoodByName(String name) throws Exception;//
}
