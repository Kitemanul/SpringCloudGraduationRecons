package com.graduationrecons.Service.TemperatureService;

import com.graduationrecons.Dao.Celler.CellerMapper;
import com.graduationrecons.Dao.Temperature.TemperatureMapper;
import com.graduationrecons.POJO.CellerInOut;
import com.graduationrecons.POJO.WorkShop;
import com.graduationrecons.Util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@Api("温度数据操作类")
@Slf4j
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    TemperatureMapper temperatureMapper;
    @Autowired
    CellerMapper cellerMapper;

    public static float getRate_of_change(List<WorkShop> list, int n, int i, int jar)
    {   float rate=0;
        float Temperature=list.get(i).getTemperature(jar);
        int currow=i;
        if((i-n*2)>0)
        {
            rate=(Temperature-list.get(i-n*2).getTemperature(jar));
            return (float)(Math.round(rate*10000))/10000;
        }
        else
        {
            return 0;
        }

    }

    @Override
    @Cacheable(value="temperateerrordata")
    @RequestMapping("/SearchErrorTem")
    @ApiOperation("搜索错误数据")
    public List<WorkShop> SearchErrorTempearture(@RequestBody WorkShop workShop) {

        HashMap<String,String> map=new HashMap<>();
        map.put("jarid",workShop.getJarid());
        map.put("groupid","'"+String.valueOf(workShop.getGroupid())+"'");
        List<WorkShop> res=temperatureMapper.SearchErrorTemperature(map);

        return res;
    }

    @Override
    @Cacheable(value="temperatedata")
    @RequestMapping("/SearchTem")
    @ApiOperation("搜索温度数据")
    public List<WorkShop> SearchTempearture(@RequestParam(value = "rate") int rate, @RequestBody CellerInOut in) {

        List<CellerInOut> list=cellerMapper.SelectAllCeller(in);
        if(list.size()==0)
        {
            return new ArrayList<>();
        }
        HashMap<String,String> map=new HashMap();
        map.put("groupid","'"+String.valueOf(in.getGroupid())+"'");
        map.put("jarid","罐"+String.valueOf(in.getJarid()));
        map.put("intime", "'"+DateUtils.Date2String(list.get(0).getIntime())+"'");
        map.put("outtime", "'"+ DateUtils.Date2String(list.get(0).getOuttime())+"'");
        List<WorkShop> res=temperatureMapper.SearchTemperature(map);
        for(int i=0;i<res.size();i++)
        {
            res.get(i).setRate(getRate_of_change(res,rate,i,in.getJarid()));
        }
        return res;
    }
}



