package com.wangfuzeng.common.util;

import cn.hutool.json.JSONUtil;
import com.wangfuzeng.common.model.Result;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**

 * @datetime 2023/4/22 19:28
 * @description 类对象
 */
public class ResponseJsonUtil {
    /**
     * Http响应方法
     *
     * @param response
     * @param code
     * @param msg
     */
    public static void responseJson(HttpServletResponse response, int code, String msg) {
        Result<Void> result = Result.fail(code, msg);
        String json = JSONUtil.toJsonStr(result);

        PrintWriter writer = null;
        //设置编码个数
        response.setCharacterEncoding("UTF-8");
        //设置响应类型
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Http响应方法
     *
     * @param response
     * @param status
     */
    public static void responseJson(HttpServletResponse response, HttpStatus status) {
        responseJson(response, status.value(), status.getReasonPhrase());
    }
}
