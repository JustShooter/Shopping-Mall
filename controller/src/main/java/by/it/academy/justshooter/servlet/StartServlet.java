package by.it.academy.justshooter.servlet;

import by.it.academy.justshooter.services.ShopServiceImpl;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "StartServlet", value = "/StartServlet")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        if (session.getAttribute("shopList") == null) {
            ShopServiceImpl shopService = new ShopServiceImpl();
            session.setAttribute("shopList", shopService.getAllShops());
        }
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}
