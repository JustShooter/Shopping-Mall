package by.it.academy.justshooter.listener;

import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.services.api.ShopService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

@WebListener
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ShopService shopService = new ShopServiceImpl();
        List<ShopDto> shopList = shopService.getAllShops();
        sce.getServletContext().setAttribute("shopList", shopList);
        ServletContextListener.super.contextInitialized(sce);
    }
}
