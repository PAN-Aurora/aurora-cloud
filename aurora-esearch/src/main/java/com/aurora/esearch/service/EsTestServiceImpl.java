package com.aurora.esearch.service;

import com.aurora.esearch.api.EsTestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  es api 测试实现类
 * @author :PHQ
 * @date：2020/6/5
 **/
@Service
public class EsTestServiceImpl implements EsTestApi {

    @Autowired
    private EsService esService;

    @Override
    public String createIndex() {
        esService.createIndex("test");
        return "success";
    }

//    @Override
//    public ResultModel queryTest(EsTest esTest) {
////        Map<String, Object> map =   EsUtil.searchDataById(
////                "tbl_terminal_vod01"
////                ,"terminal_vod01"
////                ,"s-d_4HMBTZ9D5-w6YyQo","fields" );
//
//        //分页查询
//        QueryBuilder matchQuery = QueryBuilders.matchQuery("vod_name",esTest.getVodName());
//        QueryBuilder matchQuery1 = QueryBuilders.matchPhraseQuery("vod_name",esTest.getVodName());
//        EsPageModel map2 =   EsUtil.searchDataPage(
//                "tbl_terminal_vod01"
//                ,"terminal_vod01"
//                ,1
//                ,10
//                ,matchQuery
//                ,""
//                ,""
//                ,""
//           );
//        return ResultModel.successData(ResultCode.SUCCESS,map2);
//    }
}
