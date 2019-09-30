package cn.jbone.b2b2c.item.api;

import cn.jbone.b2b2c.item.api.response.ItemCategoryTreeRespDTO;
import cn.jbone.b2b2c.item.api.response.ShopCagetoryItemsRespDTO;
import cn.jbone.common.rpc.Result;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


public interface ItemCategoryReadApi {


    /**
     *
     * @param shopId
     * @return
     */
    @HystrixCommand(commandKey = "getItemCategoryTree")
    @RequestMapping(value = "/getItemCategoryTree")
    Result<List<ItemCategoryTreeRespDTO>> getItemCategoryTree(@RequestParam("shopId") long shopId);


    /**
     *
     * @param shopId
     * @return
     */
    @HystrixCommand(commandKey = "getShopCategoryItems")
    @RequestMapping(value = "/getShopCategoryItems")
    Result<List<ShopCagetoryItemsRespDTO>> getShopCategoryItems(@RequestParam("shopId") long shopId);
}
