package xrb.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import xrb.entry.Province;
import xrb.service.ProvinceService;
import xrb.service.ProvinceServiveImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xieren8iao
 * @create 2019/3/25 - 10:10
 */
@WebServlet("/findProvinceServlet")
public class FindProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        response.setContentType("application/json;charset=utf-8");

//        ProvinceService service=new ProvinceServiveImpl();
//        List<Province> list = service.findAll();
//        //把list转化为json
//        ObjectMapper mapper=new ObjectMapper();
//        String json = mapper.writeValueAsString(list);



        //！！！注意使用redis缓存，但有增删改的操作时，需要把缓存全部清除重新添加，不然数据无法更新！！！！
        ProvinceService service=new ProvinceServiveImpl();
        String json = service.findProvinceJson();
        System.out.println(json);
        //响应给前段
        response.getWriter().write(json);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
