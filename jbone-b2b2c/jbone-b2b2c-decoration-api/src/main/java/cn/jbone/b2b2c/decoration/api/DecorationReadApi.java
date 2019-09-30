package cn.jbone.b2b2c.decoration.api;

import cn.jbone.b2b2c.decoration.api.dto.response.ShopDecorationInfoRespDTO;
import cn.jbone.common.rpc.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



public interface DecorationReadApi {

    @HystrixCommand(commandKey = "getShopDecorationInfo")
    @RequestMapping(value = "/getShopDecorationInfo")
    Result<ShopDecorationInfoRespDTO> getShopDecorationInfo(@RequestParam("shopId") long shopId);
}
