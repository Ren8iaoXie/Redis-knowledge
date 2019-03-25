package xrb.service;

import xrb.entry.Province;

import java.util.List;

/**
 * @author xieren8iao
 * @create 2019/3/25 - 9:56
 */
public interface ProvinceService {
    public List<Province> findAll();
    public  String findProvinceJson();
}
