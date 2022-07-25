package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.services.api.ShopService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("shopList") == null) {
            ShopService shopService = new ShopServiceImpl();
            session.setAttribute("shopList", shopService.getAllShops());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}
