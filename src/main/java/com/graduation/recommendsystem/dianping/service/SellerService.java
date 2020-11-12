package com.graduation.recommendsystem.dianping.service;


import com.graduation.recommendsystem.dianping.common.BusinessException;
import com.graduation.recommendsystem.dianping.model.SellerModel;

import java.util.List;

public interface SellerService {
    SellerModel create(SellerModel sellerModel);
    SellerModel get(Integer id);
    List<SellerModel> selectAll();
    SellerModel changeStatus(Integer id, Integer disableFlag) throws BusinessException;
}
