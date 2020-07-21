package cn.itcast.interceptor;

import cn.itcast.pojo.Item;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("----MyInterceptor1- preHandle ---");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("----MyInterceptor1- postHandle ---" + o );

        List<Item> list = new ArrayList<Item>();
        list.add(new Item(3,"挖土机",80,new Date(),"可以开"));
        list.add(new Item(4,"小米扫地机器人",3500,new Date(),"可以坐"));

        modelAndView.addObject("itemList",list);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("----MyInterceptor1- afterCompletion ---");
    }
}
