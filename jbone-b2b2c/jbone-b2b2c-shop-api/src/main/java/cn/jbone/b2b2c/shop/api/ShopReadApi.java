package cn.jbone.b2b2c.shop.api;

import cn.jbone.b2b2c.shop.api.dto.response.ShopDetailsRespDTO;
import cn.jbone.common.rpc.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface ShopReadApi {

    /**
     *
     * @param shopId
     * @return
     */
    @HystrixCommand(commandKey = "getShopDetails")
    @RequestMapping(value = "/getShopDetails")
    Result<ShopDetailsRespDTO> getShopDetails(@RequestParam("shopId") Long shopId);
}
