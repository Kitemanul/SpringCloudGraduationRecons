package com.graduationrecons.Service.CellerService;

import com.graduationrecons.POJO.CellerInOut;

import java.util.List;

public interface CellerService {

    public List<CellerInOut> SearchCeller(CellerInOut celler);

    public int AddCeller(CellerInOut celler);

    public int EditCeller(List<CellerInOut> CellerList);

    public int DeleteCeller(CellerInOut outceller);
}
