package by.it.academy.justshooter.filter;

import by.it.academy.justshooter.services.ShopService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AddShopListToSessionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        ShopService shopService = new ShopService();
        HttpSession session = ((HttpServletRequest) request).getSession();
        if (session.getAttribute("shopList") == null) {
            session.setAttribute("shopList", shopService.getAllShops());
        }
        chain.doFilter(request, response);
    }
}
