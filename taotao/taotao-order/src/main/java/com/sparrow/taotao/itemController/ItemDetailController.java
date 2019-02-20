package com.sparrow.taotao.itemController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.taotao.ItemDesc;
import com.sparrow.taotao.service.ItemDetailService;
import com.sparrow.taotao.utils.DateUtils;
import com.sparrow.taotao.utils.JsonFormat;
import com.sparrow.taotao.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by xuefeiyuluo on 2019/2/19.
 */


@Controller
@RequestMapping("/itemDetail")
public class ItemDetailController {

    @Autowired
    private ItemDetailService itemDetailService;

    // http://localhost:8080/itemDetail/queryItemDetailById?itemId=679533
    @ResponseBody
    @RequestMapping("/queryItemDetailById")
    public JSON queryItemDetailById(HttpServletRequest req) {
        JSONObject json = new JSONObject();
        String itemId = req.getParameter("itemId");

        if (StringUtils.isEmpty(itemId)) {
            ItemDesc itemDesc = itemDetailService.queryItemDetailInfo(Long.parseLong(itemId));
            if (itemDesc != null) {
                json.put("itemId",itemDesc.getItemId());
                json.put("created", DateUtils.formatDate(itemDesc.getCreated(),null));
                json.put("updated",DateUtils.formatDate(itemDesc.getUpdated(),null));
                json.put("itemDesc",itemDesc.getItemDesc());

                return JsonFormat.jsonSuccessWithMsgResult("查询的成功",json);
            } else {
                return JsonFormat.jsonSuccessWithMsgResult("查询的数据不存在",json);
            }
        } else {
            return JsonFormat.jsonFailWithMsgResult("查询Id错误",json);
        }
    }
}
