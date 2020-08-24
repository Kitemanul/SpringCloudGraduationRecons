package com.graduationrecons.Service.TemperatureService;

import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.POJO.WorkShop;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-Tem")
public interface TemperatureService {

    @RequestMapping(value = "/SearchTem")
    public List<WorkShop> SearchTempearture(@RequestParam(value = "rate") int rate, @RequestBody CellerInOut in);

    @RequestMapping(value = "/SearchErrorTem")
    public List<WorkShop> SearchErrorTempearture(@RequestBody WorkShop workShop);
}
