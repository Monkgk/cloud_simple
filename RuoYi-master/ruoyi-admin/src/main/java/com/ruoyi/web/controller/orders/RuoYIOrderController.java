package com.ruoyi.web.controller.orders;

import com.common.Entity.Order;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.service.IOrderService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/orders/order")
public class RuoYIOrderController extends BaseController {
    private String prefix = "orders/order";

    @Autowired
    private IOrderService iOrderService;

    @RequiresPermissions("orders:order:view")
    @GetMapping()
    public String order() {
        return prefix + "/order";
    }

    @RequiresPermissions("orders:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Order order) {
        startPage();
        List<Order> list = iOrderService.selectOrderList(order);
        return getDataTable(list);
    }

    @RequiresPermissions("orders:order:refund")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @PostMapping("/refund/{order_id}")
    @ResponseBody
    public AjaxResult edit(@PathVariable("order_id") String order_id) throws IOException {
        //token令牌
        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiNSIsImNlc2hpIl0sImV4cCI6MTY1MTc1NTM3OSwiaWF0IjoxNjUxNzUxNzc5fQ.eDaAmoA41N2_-h1emOYNNa_SmllY7PcOetXF8BKQ0AM";
        //退款链接
        final String refund_url = "http://gqpweb.natapp1.cc/order/toRefund";
        //创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        //根据url创建HttpPost实例
        HttpPost post = new HttpPost(refund_url);
        //构建post参数
        List<NameValuePair> params  = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("orderId",order_id));
        params.add(new BasicNameValuePair("reason","后台退款"));
        params.add(new BasicNameValuePair("token",token));
        //编码格式转换
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        //传入请求体
        post.setEntity(entity);
        post.addHeader("token",token);
        //发送请求，获得响应
        HttpResponse response = client.execute(post);

        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() == 200) {
            // 解析数据
            HttpEntity resEntity = response.getEntity();
            String data = EntityUtils.toString(resEntity);
            System.out.println(data);
        }

        return toAjax(1);
    }

    @RequiresPermissions("orders:order:cancel")
    @Log(title = "订单管理", businessType = BusinessType.DELETE)
    @PostMapping("/cancel/{order_id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("order_id") String order_id) throws IOException {
        //token令牌
         final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOlsiNSIsImNlc2hpIl0sImV4cCI6MTY1MTc1NTM3OSwiaWF0IjoxNjUxNzUxNzc5fQ.eDaAmoA41N2_-h1emOYNNa_SmllY7PcOetXF8BKQ0AM";
        //退款链接
        final String refund_url = "http://gqpweb.natapp1.cc/order/toRefund";
        //创建HttpClient实例
        HttpClient client = HttpClientBuilder.create().build();
        //根据url创建HttpPost实例
        HttpPost post = new HttpPost(refund_url);
        //构建post参数
        List<NameValuePair> params  = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("data",order_id));
        params.add(new BasicNameValuePair("token",token));
        //编码格式转换
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params);
        //传入请求体
        post.setEntity(entity);
        post.addHeader("token",token);
        //发送请求，获得响应
        HttpResponse response = client.execute(post);

        // 判断是否正常返回
        if (response.getStatusLine().getStatusCode() == 200) {
            // 解析数据
            HttpEntity resEntity = response.getEntity();
            String data = EntityUtils.toString(resEntity);
            System.out.println(data);
        }
        return toAjax(1);
    }
}
