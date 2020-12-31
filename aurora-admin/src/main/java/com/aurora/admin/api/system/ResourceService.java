package com.aurora.admin.api.system;

import com.aurora.admin.model.system.Resource;
import com.aurora.common.model.ResultModel;

/**
 * 资源业务接口
 * @author :PHQ
 * @date：2020/5/15
 **/
public interface ResourceService {

    /**
     * 获取资源集合
     * @param resource
     * @return
     */
    public ResultModel getResourceList(Resource resource);

    /**
     * 获取资源树
     * @param resource
     * @return
     */
    public ResultModel getResourceListTree(Resource resource);
}
