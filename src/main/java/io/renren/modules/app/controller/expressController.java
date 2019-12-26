package io.renren.modules.app.controller;


import io.renren.modules.app.express.KdApiOrderDistinguish;
import io.renren.modules.app.express.KdniaoTrackQueryAPI;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/express")
@Api("快递功能接口")
public class expressController {

    @GetMapping("/test")
    @ApiOperation("内外网链接测试")
    public int test(){
        System.out.println("内外网链接测试");
        return 0;
    }


    // @Login
    @GetMapping("/OrderD")
    @ApiOperation("获取快递单公司信息")
    public String OrderDistinct(@RequestParam(value="OrderId") String OrderId){ //"3967950525457"
        //              public String TrackQuery(@RequestAttribute("OrderId") String OrderId){
        KdApiOrderDistinguish api = new KdApiOrderDistinguish();
        String resultcode = null;
        String result=null;
        {
            try {
                resultcode = api.getOrderTracesByJson(OrderId);
                System.out.print(resultcode);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        JSONObject object = new JSONObject(resultcode);
       // System.out.println("object:"+object);
        JSONArray resultship = object.getJSONArray("Shippers");
      //  System.out.println("Shippers:"+resultship);
        String ShipperCode =  resultship.get(0).toString();
       // System.out.println("ShipperCode:"+ShipperCode);
        JSONObject object1 = new JSONObject(ShipperCode);
       // System.out.println("object1:"+object1);
        String code=object1.getString("ShipperCode");
       // System.out.println("code:"+code);
        KdniaoTrackQueryAPI apitq = new KdniaoTrackQueryAPI();
        try {
            result = apitq.getOrderTracesByJson(code, OrderId);
            System.out.print(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject object2 = new JSONObject(result);
        String newresult= "["+resultcode+","+result+"]";
        return newresult;

    }

    @GetMapping("/trackq")
    @ApiOperation("获取快递物流信息")
    //    public String TrackQuery(@RequestAttribute("expCode") String expCode,@RequestAttribute("expNo") String expNo){ //" ("ANE", "210001633605");
    public String TrackQuery(@RequestParam("expCode") String expCode, @RequestParam("expNo") String expNo){ //"
        String result=null;
        KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
        try {
            result = api.getOrderTracesByJson(expCode, expNo);
            System.out.print(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

}
