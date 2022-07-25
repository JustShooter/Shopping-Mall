package by.it.academy.justshooter.rest;

import by.it.academy.justshooter.dto.ItemForShopDto;
import by.it.academy.justshooter.services.ItemServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RestItemsByShopServlet", value = "/rest/shopItems/*")
public class RestItemsByShopServlet extends HttpServlet {

    private static final Gson GSON = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("doGet");
        ItemServiceImpl itemService = new ItemServiceImpl();

        String uri = request.getRequestURI();
        Integer shopId = null;
        try {
            shopId = Integer.parseInt(uri.substring("/rest/shopItems/".length()));
        } catch (NumberFormatException e) {
            response.setStatus(400);
            response.setHeader("Content-Type", "text/html");
            response.getWriter().println(e);
            return;
        }

        List<ItemForShopDto> itemsWithPriceForShop = itemService.getAllItemsWithPriceForShop(shopId);

        String json = GSON.toJson(itemsWithPriceForShop);

        response.setStatus(200);
        response.setHeader("Content-Type", "application/json");
        response.getWriter().println(json);
    }

}
