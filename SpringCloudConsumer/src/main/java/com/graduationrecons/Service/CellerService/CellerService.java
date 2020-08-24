package com.graduationrecons.Service.CellerService;

import com.graduationrecons.POJO.CellerInOut;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER")
public interface CellerService {

    @RequestMapping(value = "/getCellerdata")
    public List<CellerInOut> SearchCeller(@RequestBody CellerInOut celler);

    @RequestMapping(value = "/AddCellerdata")
    public int AddCeller(@RequestBody CellerInOut celler);

    @RequestMapping(value = "/EditCellerdata")
    public int EditCeller(@RequestBody List<CellerInOut> CellerList);

    @RequestMapping(value = "/DeleteCellerdata")
    public int DeleteCeller(@RequestBody CellerInOut outceller);
}
