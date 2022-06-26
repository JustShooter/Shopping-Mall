package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "AllItemsViewServlets", value = "/viewAllItems")
public class AllItemsViewServlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ShopService shopService = new ShopService();
//        List<ShopDto> shopDtos = shopService.getAllShops();
        Map<ShopDto, List<ItemForShopDto>> shopDtoMap = shopService.getAllItemsOfEachShop();
//        request.setAttribute("shopList", shopDtos);
        request.setAttribute("shopsItemsMap", shopDtoMap);
        request.getRequestDispatcher("allShopsWithItems.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
