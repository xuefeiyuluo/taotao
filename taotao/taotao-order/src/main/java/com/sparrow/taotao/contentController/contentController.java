package com.sparrow.taotao.contentController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sparrow.taotao.ContentCategory;
import com.sparrow.taotao.service.ContentCategoryService;
import com.sparrow.taotao.utils.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xuefeiyuluo on 2019/1/30.
 */


@Controller
@RequestMapping("/content")
public class contentController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @ResponseBody
    @RequestMapping("/updateContentCategory")
    // http://localhost:8080/content/updateContentCategory?categoryId=102&name=测试测试测试
    public JSON updateContentCategory(HttpServletRequest req) {
        ContentCategory category = new ContentCategory();
        long categoryId = Long.valueOf(req.getParameter("categoryId"));
        if (req.getParameter("parentId") != null) {
            long parentId = Long.valueOf(req.getParameter("parentId"));
            category.setParentId(parentId);
        }

        if (req.getParameter("status") != null){
            Integer status = Integer.valueOf(req.getParameter("status"));
            category.setStatus(status);
        }

        if (req.getParameter("sortOrder") != null){
            Integer sortOrder = Integer.valueOf(req.getParameter("sortOrder"));
            category.setSortOrder(sortOrder);
        }

        if (req.getParameter("isParent") != null){
            category.setIsParent(req.getParameter("isParent").equals("1") ? true : false);
        }

        String name = req.getParameter("name");
        category.setId(categoryId);
        category.setName(name);
        category.setUpdated(new Date());

        Integer result = contentCategoryService.updateContentCategory(category);
        JSONObject json = new JSONObject();
        if (result == 1) {
            return JsonFormat.jsonSuccessWithMsgResult("更新成功",json);
        } else {
            return JsonFormat.jsonFailWithMsgResult("更新失败",json);
        }
    }

    @ResponseBody
    @RequestMapping("/addContentCategory")
    // http://localhost:8080/content/addContentCategory?parentId=86&status=1&sortOrder=1&isParent=1&name=测试
    public JSON addContentCategory(HttpServletRequest req) {
        long parentId = req.getParameter("parentId") == null ? 0 : Long.valueOf(req.getParameter("parentId"));
        Integer status = req.getParameter("status") == null ? 1 : Integer.valueOf(req.getParameter("status"));
        Integer sortOrder = req.getParameter("sortOrder") == null ? 0 : Integer.valueOf(req.getParameter("sortOrder"));
        Boolean isParent = req.getParameter("isParent") == null ? true : req.getParameter("isParent").equals("1")  ? true : false;
        String name = req.getParameter("name");

        ContentCategory category = new ContentCategory();
        category.setParentId(parentId);
        category.setName(name);
        category.setStatus(status);
        category.setSortOrder(sortOrder);
        category.setIsParent(isParent);
        category.setCreated(new Date());
        category.setUpdated(new Date());

        Integer result = contentCategoryService.addContentCategory(category);
        JSONObject json = new JSONObject();
        if (result == 1) {
            return JsonFormat.jsonSuccessWithMsgResult("添加成功",json);
        } else {
            return JsonFormat.jsonFailWithMsgResult("添加失败",json);
        }
    }



    @ResponseBody
    @RequestMapping("/contentCategoryList")
    // http://localhost:8080/content/contentCategoryList?parentId=1
    public JSON contentCategoryList(HttpServletRequest req) {
        long parentId = req.getParameter("parentId") == null ? 0 : Long.valueOf(req.getParameter("parentId"));

        ContentCategory category = new ContentCategory();
        category.setParentId(parentId);
        List<ContentCategory> categoryList = contentCategoryService.queryContentCategory(category);

        List<JSONObject> jsonList = new ArrayList<>();
        for (ContentCategory temp: categoryList) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("categoryId",temp.getId());
            jsonObject.put("parentId",temp.getParentId());
            jsonObject.put("name",temp.getName());
            jsonObject.put("status",temp.getStatus());
            jsonObject.put("sortOrder",temp.getSortOrder());
            jsonObject.put("isParent",temp.getIsParent());
            jsonObject.put("created",temp.getCreated());
            jsonObject.put("updated",temp.getUpdated());
            jsonList.add(jsonObject);
        }

        if (categoryList.size() > 0) {
            return JsonFormat.jsonSuccessResult(jsonList);
        } else {
            return JsonFormat.jsonFailWithMsgResult("无数据",jsonList);
        }
    }
}