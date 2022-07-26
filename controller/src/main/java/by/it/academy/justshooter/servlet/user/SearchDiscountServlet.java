package by.it.academy.justshooter.servlet.user;

import by.it.academy.justshooter.dto.ShopItemPriceDiscountDto;
import by.it.academy.justshooter.services.DiscountServiceImpl;
import by.it.academy.justshooter.services.api.DiscountService;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.ERROR;

@WebServlet(name = "SearchDiscountServlet", value = "/searchDiscount")
public class SearchDiscountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = ParamUtils.getStringParam(request, "filter");
        Integer discountValue = ParamUtils.getIntegerParam(request, "discountValue");
        if (null == filter && null == discountValue) {
            request.setAttribute(ERROR, "Search attributes can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        DiscountService discountService = new DiscountServiceImpl();
        List<ShopItemPriceDiscountDto> shopItemPriceDiscountList = discountService.searchDiscount(filter, discountValue);
        request.setAttribute("shopItemPriceDiscountList", shopItemPriceDiscountList);
        request.getRequestDispatcher("/user/searchDiscount.jsp").forward(request, response);
    }

}
