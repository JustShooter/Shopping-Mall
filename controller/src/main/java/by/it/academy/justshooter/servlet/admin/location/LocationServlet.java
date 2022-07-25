package by.it.academy.justshooter.servlet.admin.location;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.LocationDto;
import by.it.academy.justshooter.services.AdminServiceImpl;
import by.it.academy.justshooter.util.ParamUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.*;

@WebServlet(name = "LocationServlet", value = "/admin/Locations")
public class LocationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminServiceImpl adminService = new AdminServiceImpl();
        String action = ParamUtils.getStringParam(request, ACTION);
        if (null == action) {
            action = "";
        }
        Integer locationId = ParamUtils.getIntegerParam(request, LOCATION_ID);
        switch (action) {
            case (NEW):
                request.getRequestDispatcher("/admin/location/location.jsp").forward(request, response);
                break;
            case (EDIT):
                LocationDto location = null;
                try {
                    location = adminService.getLocationById(locationId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                request.setAttribute("location", location);
                request.getRequestDispatcher("/admin/location/location.jsp").forward(request, response);
                break;
            case (DELETE):
                try {
                    adminService.deleteLocationById(locationId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                response.sendRedirect("/admin/Locations");
                break;
            default:
                List<LocationDto> allLocations = adminService.getAllLocations();
                request.setAttribute("allLocations", allLocations);
                request.getRequestDispatcher(request.getContextPath() + "/admin/location/locationList.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminServiceImpl adminService = new AdminServiceImpl();
        String shopNumber = ParamUtils.getStringParam(request, SHOP_NUMBER);
        Integer floor = ParamUtils.getIntegerParam(request, FLOOR);
        String description = ParamUtils.getStringParam(request, DESCRIPTION);
        if (ParamUtils.isNull(shopNumber) ||
                ParamUtils.isNull(floor) ||
                ParamUtils.isNull(description)) {
            request.setAttribute(ERROR, "Attribute can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        String action = ParamUtils.getStringParam(request, ACTION);
        switch (action) {
            case (NEW):
                adminService.createNewLocation(shopNumber, floor, description);
                break;
            case (EDIT):
                Integer LocationId = ParamUtils.getIntegerParam(request, LOCATION_ID);
                if (ParamUtils.isNull(LocationId)) {
                    request.setAttribute(ERROR, "Shop ID can not be empty");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                try {
                    adminService.updateLocation(LocationId, shopNumber, floor, description);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;
        }
        response.sendRedirect("/admin/Locations");
    }
}
