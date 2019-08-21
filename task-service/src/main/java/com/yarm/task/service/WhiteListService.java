package com.yarm.task.service;

import java.util.List;

/**
 * Created with IDEA
 * author:Yarm.Yang
 * Date:2019/8/8
 * Time:11:39
 * Des:白名单
 */
public interface WhiteListService {
    /**
     * 接口拦截放行白名单
     * @return
     */
    List<String> loginIpList();
}