package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.services.ItemServiceImpl;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.services.api.ShopService;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.ERROR;

@WebServlet(name = "ViewShopItemsServlet", value = "/viewShopItems")
public class ViewShopItemsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShopService shopService = new ShopServiceImpl();
//        ItemServiceImpl itemService = new ItemServiceImpl();
        Integer shopId = ParamUtils.getIntegerParam(request, "shopId");
        request.setAttribute("shopId", shopId);
        if (ParamUtils.isNull(shopId)) {
            request.setAttribute(ERROR, "ID can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        try {
            String shopName = shopService.getShopById(shopId).getShopName();
            request.setAttribute("shopName", shopName);
        } catch (NoDataFoundById e) {
            request.setAttribute("error", "Shop " + shopId + " not found!");
            request.getRequestDispatcher("/").forward(request, response);
        }
//        List<ItemForShopDto> itemsWithPriceForShop = itemService.getAllItemsWithPriceForShop(shopId);
//        request.setAttribute("items", itemsWithPriceForShop);
        request.getRequestDispatcher("allItemsOfShop.jsp").forward(request, response);
    }


}
