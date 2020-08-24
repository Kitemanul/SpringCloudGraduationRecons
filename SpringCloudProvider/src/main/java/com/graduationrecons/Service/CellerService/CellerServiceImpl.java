package com.graduationrecons.Service.CellerService;

import com.graduationrecons.Dao.Celler.CellerMapper;
import com.graduationrecons.POJO.CellerInOut;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Api("CellerService")
@Slf4j
public class CellerServiceImpl implements CellerService {

    @Autowired
    CellerMapper cellerMapper;


    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

    }

    @Override
    @CacheEvict(value = "Cellerdata",allEntries = true)
    @ApiOperation("删除Celler数据")
    @RequestMapping(value = "/DeleteCellerdata")
    public int DeleteCeller(@RequestBody CellerInOut outceller) {

        log.info(outceller.toString());
        return cellerMapper.DeleteCeller(outceller);
    }

    @Override
    @CacheEvict(value = "Cellerdata",allEntries = true)
    @ApiOperation("编辑Celler数据")
    @RequestMapping(value = "/EditCellerdata")
    public int EditCeller(@RequestBody List<CellerInOut> CellerList) {

        return cellerMapper.EditCeller(CellerList.get(0),CellerList.get(1));
    }

    @Override
    @CacheEvict(value = "Cellerdata",allEntries = true)
    @ApiOperation("添加Celler数据")
    @RequestMapping(value = "/AddCellerdata")
    public int AddCeller(@RequestBody CellerInOut celler) {

        return cellerMapper.AddCeller(celler);
    }

    @Override
    @Cacheable(value = "Cellerdata")
    @RequestMapping(value = "/getCellerdata")
    @ApiOperation("获取Celler数据")
    public List<CellerInOut> SearchCeller(@RequestBody CellerInOut celler) {

        return cellerMapper.SelectAllCeller(celler);
    }
}
