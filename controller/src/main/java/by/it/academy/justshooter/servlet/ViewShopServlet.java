package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.services.ItemServiceImpl;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewShopServlet", value = "/viewShopItems")
public class ViewShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShopServiceImpl shopService = new ShopServiceImpl();
        ItemServiceImpl itemService = new ItemServiceImpl();
        Integer shopId = Integer.valueOf(ParamUtils.getParam(request, "shopId"));
        try {
            String shopName = shopService.getShopById(shopId).getShopName();
            request.setAttribute("shopName", shopName);
        } catch (NoDataFoundById e) {
            request.setAttribute("error", "Shop " + shopId + " not found!");
            //TODO Сделать модалку на Index.jsp для вывода ошибки!!!
            request.getRequestDispatcher("/").forward(request, response);
        }
        List<ItemForShopDto> itemsWithPriceForShop = itemService.getAllItemsWithPriceForShop(shopId);
        request.setAttribute("items", itemsWithPriceForShop);
        request.getRequestDispatcher("allItemsOfShop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
