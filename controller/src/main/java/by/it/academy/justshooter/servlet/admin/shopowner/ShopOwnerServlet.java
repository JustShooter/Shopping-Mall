package by.it.academy.justshooter.servlet.admin.shopowner;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.AddressDto;
import by.it.academy.justshooter.dto.ShopOwnerDto;
import by.it.academy.justshooter.entity.enums.StreetType;
import by.it.academy.justshooter.services.AdminServiceImpl;
import by.it.academy.justshooter.services.api.AdminService;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.*;

@WebServlet(name = "ShopOwnerServlet", value = "/admin/ShopOwners")
public class ShopOwnerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = ParamUtils.getStringParam(request, ACTION);
        if (null == action) {
            action = "";
        }
        AdminService adminService = new AdminServiceImpl();
        Integer shopOwnerId = ParamUtils.getIntegerParam(request, SHOP_OWNER_ID);
        switch (action) {
            case (NEW):
                addAllAddressesAndStreetTypesToAttribute(request, adminService);
                request.getRequestDispatcher("/admin/shopOwner/shopOwner.jsp").forward(request, response);
                break;
            case (EDIT):
                addAllAddressesAndStreetTypesToAttribute(request, adminService);
                ShopOwnerDto shopOwner = null;
                try {
                    shopOwner = adminService.getShopOwnerById(shopOwnerId);
                } catch (NoDataFoundById e) {
                    throwError(request, response, e);
                }
                request.setAttribute(SHOP_OWNER, shopOwner);
                request.getRequestDispatcher("/admin/shopOwner/shopOwner.jsp").forward(request, response);
                break;
            case (DELETE):
                try {
                    adminService.deleteShopOwnerById(shopOwnerId);
                } catch (NoDataFoundById e) {
                    throwError(request, response, e);
                }
                response.sendRedirect("/admin/ShopOwners");
                break;
            default:
                List<ShopOwnerDto> allShopOwners = adminService.getAllShopOwners();
                request.setAttribute("allShopOwners", allShopOwners);
                request.getRequestDispatcher(request.getContextPath() + "/admin/shopOwner/shopsOwnerList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService adminService = new AdminServiceImpl();
        String companyName = ParamUtils.getStringParam(request, "companyName");
        Integer shopOwnerId = ParamUtils.getIntegerParam(request, SHOP_OWNER_ID);
        Integer companyAddressId = ParamUtils.getIntegerParam(request, "companyAddress");
        if (ParamUtils.isNull(companyAddressId)) {
            String city = ParamUtils.getStringParam(request, "city");
            String streetType = ParamUtils.getStringParam(request, "streetType");
            String street = ParamUtils.getStringParam(request, "street");
            String buildingNumber = ParamUtils.getStringParam(request, "buildingNumber");
            String officeNumber = ParamUtils.getStringParam(request, "officeNumber");
            companyAddressId = adminService.createNewAddress(city, streetType, street, buildingNumber, officeNumber);
        }
        String action = ParamUtils.getStringParam(request, ACTION);
        switch (action) {
            case (NEW):
                try {
                    adminService.createNewShopOwner(companyName, companyAddressId);
                } catch (NoDataFoundById e) {
                    throwError(request, response, e);
                }
                break;
            case (EDIT):
                try {
                    adminService.updateShopOwnerData(shopOwnerId, companyName, companyAddressId);
                } catch (NoDataFoundById e) {
                    throwError(request, response, e);
                }
                break;
            case (DELETE):
                try {
                    adminService.deleteShopOwnerById(shopOwnerId);
                } catch (NoDataFoundById e) {
                    throwError(request, response, e);
                }
        }
        response.sendRedirect("/admin/ShopOwners");
    }

    private void addAllAddressesAndStreetTypesToAttribute(HttpServletRequest request, AdminService adminService) {
        List<AddressDto> allAddresses = adminService.getAllAddresses();
        StreetType[] streetTypes = StreetType.values();
        request.setAttribute(ALL_ADDRESSES, allAddresses);
        request.setAttribute(STREET_TYPES, streetTypes);
    }

    private void throwError(HttpServletRequest request, HttpServletResponse response, NoDataFoundById e) throws ServletException, IOException {
        request.setAttribute(ERROR, e.getMessage());
        request.getRequestDispatcher("/").forward(request, response);
    }

}
