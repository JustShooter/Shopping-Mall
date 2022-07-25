package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ShopsInCategoryServlet", value = "/viewShopsInCategory")
public class ShopsInCategoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer categoryId = ParamUtils.getIntegerParam(request, "category");
        ShopServiceImpl shopService = new ShopServiceImpl();
        if (null != categoryId) {
            List<ShopDto> shopsInCategory = shopService.getShopsByCategory(categoryId);
            request.setAttribute("shopsInCategory", shopsInCategory);
        }
        List<CategoryDto> allCategories = shopService.getAllCategories();
        request.setAttribute("allCategories", allCategories);

        request.getRequestDispatcher("allShops.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("This is doPost method, called in " + this.getServletName());
    }

}
