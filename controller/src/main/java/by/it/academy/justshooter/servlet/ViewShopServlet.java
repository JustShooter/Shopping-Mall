package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ItemService;
import by.it.academy.justshooter.services.ShopService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ViewShopServlet", value = "/viewShopItems")
public class ViewShopServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShopService shopService = new ShopService();
        ItemService itemService = new ItemService();
        Integer shopId = Integer.valueOf(request.getParameter("shopId"));
        try {
            String shopName = shopService.getShopById(shopId).getShopName();
            request.setAttribute("shopName", shopName);
        } catch (NoDataFoundById e) {
            request.setAttribute("error", "Shop " + shopId + " not found!");
            //TODO Сделать модалку на Index.jsp для вывода ошибки!!!
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        List<ItemForShopDto> itemsWithPriceForShop = itemService.getAllItemsWithPriceForShop(shopId);
        request.setAttribute("items", itemsWithPriceForShop);
        request.getRequestDispatcher("allItemsOfShop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
