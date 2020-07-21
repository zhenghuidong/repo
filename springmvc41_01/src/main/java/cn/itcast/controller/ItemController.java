package cn.itcast.controller;

import cn.itcast.pojo.Item;
import cn.itcast.pojo.QueryVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * 编写处理方法
 * 1.方法上添加注解,作用让该方法作为处理器,接收请求和参数
 * 2.编写方法,方法的返回值为ModelAndView
 * 3.方法中,创建ModelAndView对象 :
 *  作用1: 设置参数,携带到浏览器  addObject(key,value)  ,编写假数据List<Item>
 *  作用2:设置跳转页面  setViewName(跳转路径)
 * 4.返回ModelAndView
 */
@Controller
//@RequestMapping("/item")
public class ItemController {



//    @PostMapping("/itemList")
    @GetMapping("/itemList")
    public ModelAndView itemList(){
        //1.创建ModewAndView
        ModelAndView modelAndView = new ModelAndView();
        //2.制造假数据
        ArrayList<Item> list = new ArrayList<>();

        list.add(new Item(1,"华为Mate20",9999,new Date(),"可以挡子弹"));
        list.add(new Item(2,"奔驰S650",350,new Date(),"可以看"));
        list.add(new Item(3,"挖土机",80,new Date(),"可以开"));
        list.add(new Item(4,"小米扫地机器人",3500,new Date(),"可以坐"));
        //3.将假数据放到modelAndView   addObject(key,value)
        modelAndView.addObject("itemList",list);
        //4.设置跳转路径 itemList.jsp    setViewName(跳转路径)
//        modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
        modelAndView.setViewName("itemList");
        //5.返回ModelAndView
        return modelAndView;


    }

    @RequestMapping("/itemEdit")
    public ModelAndView itemEdit(HttpServletRequest request){

        // 获取id
        String id = request.getParameter("id");
//        System.out.println(id);
        // 模拟数据
        Item item  = new Item(1,"华为Mate20",9999,new Date(),"可以挡子弹","images/car.jpg");

        //构建modelAndView
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("item",item);
        modelAndView.setViewName("itemEdit");
        //5.返回ModelAndView
        return modelAndView;


    }

//    @RequestMapping("/updateItem")
    public ModelAndView updateItem(int id, String name, double price, String detail){
        System.out.println("id:"+id);
        System.out.println("name:"+name);
        System.out.println("price:"+price);
        System.out.println("detail:"+detail);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("itemList");
        return mv;
    }

    /**
     * 如果参数类型是Pojo的,表单的name或者url的参数如果和pojo中的属性名保持一致,即可直接绑定
     * @param item
     * @return
     */
//    @RequestMapping("/updateItem")
//    public ModelAndView updateItem(Item item){
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("itemList");
//        return mv;
//    }

    /**
     * queryItem.action
     * 包装类类型 属性.属性的方式进行绑定
     */
//    @RequestMapping("/queryItem")
//    public ModelAndView queryItem(QueryVo queryVo){
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("itemList");
//        return mv;
//    }

    /**
     * queryItem.action
     * 包装类类型 属性.属性的方式进行绑定

     queryVo的ids有值, 参数列表中的ids也有值
     */
//    @RequestMapping("/queryItem")
//    public ModelAndView queryItem(QueryVo queryVo,int[] ids){
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("itemList");
//        return mv;
//    }

    /**
     * 数组形式接收参数
     * ids
     */
//    @RequestMapping("/queryItem")
//    public ModelAndView queryItem(int[] ids){
//
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("itemList");
//        return mv;
//    }

//    @RequestMapping("/updateItem")
    public void updateItem(Item item, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(item);
        request.setAttribute("item",item);
        request.getRequestDispatcher("WEB-INF/jsp/success.jsp").forward(request, response);


    }

//    @RequestMapping("/itemList")
//    public String itemList(Model model){
//        ArrayList<Item> list = new ArrayList<>();
//
//        list.add(new Item(1,"华为Mate20",9999,new Date(),"可以挡子弹"));
//        list.add(new Item(2,"奔驰S650",350,new Date(),"可以看"));
//        list.add(new Item(3,"挖土机",80,new Date(),"可以开"));
//        list.add(new Item(4,"小米扫地机器人",3500,new Date(),"可以坐"));
//        model.addAttribute("itemList",list);
//        return "itemList";
//    }


//    @RequestMapping("/updateItem")
//    public String updateItem(){
//        return "redirect:itemEdit.action";
//    }

    @RequestMapping("/updateItem.action")
    public String updateItem(MultipartFile pictureFile) throws Exception {
        //原始文件名称
        String pictureFile_name = pictureFile.getOriginalFilename();

        //上传图片
//        File uploadPic = new java.io.File("D:\\project\\springmvc41_01\\src\\main\\webapp\\images\\"+pictureFile_name);
        File uploadPic = new File("D:\\project\\springmvc41_01\\src\\main\\webapp\\images\\" + pictureFile_name);
        //向磁盘写文件
        pictureFile.transferTo(uploadPic);

        // ... 存到数据库中 只需要写上相对路径即可 /images/xxx.jpg 即可
        return "success";
    }

    @RequestMapping("/jsonDemo")
    @ResponseBody
    public Item jsonDemo(){
        Item item = new Item(1,"华为Mate20",9999,new Date(),"可以挡子弹");
        return item;
    }

    @RequestMapping("/jsonDemo2")

    public void jsonDemo2(@RequestBody Item item){
        System.out.println(item);
    }

    @RequestMapping("jsonDemo3.action")
    @ResponseBody
    public Item  itemDemo(@RequestBody Item item){
        //System.out.println(item);

        //item对象我们修改他的内容,然后返回给调用方法 (前端接收到的数据是我们修改后的item对象--->转成json格式)
        item.setId(9);
        item.setName("大炮");
        item.setPrice(100);
        item.setDetail("抗日神剧专用");
        return item;
    }


}
