package by.it.academy.justshooter.servlet.admin.shop;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.CategoryDto;
import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.dto.ShopDto;
import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.services.AdminServiceImpl;
import by.it.academy.justshooter.services.ShopServiceImpl;
import by.it.academy.justshooter.services.api.AdminService;
import by.it.academy.justshooter.services.api.ShopService;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.*;

@WebServlet(name = "ShopsServlet", value = "/admin/Shops")
public class ShopsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = ParamUtils.getStringParam(request, ACTION);
        if (null == action) {
            action = "";
        }
        ShopService shopService = new ShopServiceImpl();
        AdminService adminService = new AdminServiceImpl();
        Integer shopId = ParamUtils.getIntegerParam(request, SHOP_ID);
        switch (action) {
            case (NEW):
                getDataForShop(request, shopService, adminService);
                request.getRequestDispatcher("/admin/shop/shop.jsp").forward(request, response);
                break;
            case (EDIT):
                ShopDto shop = null;
                try {
                    shop = shopService.getShopById(shopId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                getDataForShop(request, shopService, adminService);
                request.getSession().setAttribute(EDIT_SHOP_ID, shopId);
                request.setAttribute("shop", shop);
                request.getRequestDispatcher("/admin/shop/shop.jsp").forward(request, response);
                break;
            case (DELETE):
                try {
                    adminService.deleteShopById(shopId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                response.sendRedirect("Shops");
                break;
            default:
                List<ShopDto> allShops = shopService.getAllShops();
                request.setAttribute("allShops", allShops);
                request.getRequestDispatcher(request.getContextPath() + "/admin/shop/shopsList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService adminService = new AdminServiceImpl();
        Integer shopId = ParamUtils.getIntegerParam(request, SHOP_ID);
        String shopName = ParamUtils.getStringParam(request, SHOP_NAME);
        Integer shopLocationId = ParamUtils.getIntegerParam(request, SHOP_LOCATION);
        Integer shopOwnerId = ParamUtils.getIntegerParam(request, SHOP_OWNER);
        Integer shopCategoryId = ParamUtils.getIntegerParam(request, SHOP_CATEGORY);
        if (ParamUtils.isNull(shopName) ||
                ParamUtils.isNull(shopLocationId) ||
                ParamUtils.isNull(shopOwnerId) ||
                ParamUtils.isNull(shopCategoryId)) {
            request.setAttribute(ERROR, "Attribute can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        String action = ParamUtils.getStringParam(request, ACTION);
        switch (action) {
            case (NEW):
                try {
                    adminService.createNewShop(shopName, shopOwnerId, shopLocationId, shopCategoryId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;
            case (EDIT):
                if (ParamUtils.isNull(shopId)) {
                    request.setAttribute(ERROR, "Shop ID can not be empty");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                if (!ParamUtils.isNull(shopId) &&
                        !shopId.equals(request.getSession().getAttribute(EDIT_SHOP_ID))) {
                    request.setAttribute(ERROR, "Shop ID was changed!!!");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                try {
                    adminService.updateShopData(shopId, shopName, shopLocationId, shopOwnerId, shopCategoryId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;
        }
        response.sendRedirect("/admin/Shops");

    }

    private void getDataForShop(HttpServletRequest request, ShopService shopService, AdminService adminService) {
        List<LocationDto> freeLocations = adminService.getFreeLocations();
        List<ShopOwnerDto> shopOwnerList = adminService.getAllShopOwners();
        List<CategoryDto> categoryList = shopService.getAllCategories();
        request.setAttribute("categories", categoryList);
        request.setAttribute("shopOwners", shopOwnerList);
        request.setAttribute("freeLocations", freeLocations);
    }
}
