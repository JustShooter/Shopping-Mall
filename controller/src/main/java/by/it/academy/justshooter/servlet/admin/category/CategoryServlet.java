package by.it.academy.justshooter.servlet.admin.category;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.CategoryDto;
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

@WebServlet(name = "CategoryServlet", value = "/admin/Categories")
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ParamUtils.getStringParam(request, ACTION);
        if (null == action) {
            action = "";
        }
        AdminServiceImpl adminService = new AdminServiceImpl();
        Integer categoryId = ParamUtils.getIntegerParam(request, CATEGORY_ID);
        switch (action) {
            case (NEW):
                request.getRequestDispatcher("/admin/category/category.jsp").forward(request, response);
                break;
            case (EDIT):
                CategoryDto category = null;
                try {
                    category = adminService.getCategoryById(categoryId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                request.setAttribute("category", category);
                request.getRequestDispatcher("/admin/category/category.jsp").forward(request, response);
                break;
            case (DELETE):
                try {
                    adminService.deleteCategoryById(categoryId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                response.sendRedirect("/admin/Categories");
                break;
            default:
                List<CategoryDto> allCategories = adminService.getAllCategories();
                request.setAttribute("allCategories", allCategories);
                request.getRequestDispatcher("/admin/category/categoryList.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AdminServiceImpl adminService = new AdminServiceImpl();
        Integer categoryId = ParamUtils.getIntegerParam(request, "categoryId");
        String categoryName = ParamUtils.getStringParam(request, "categoryName");
        String description = ParamUtils.getStringParam(request, "description");
        if (ParamUtils.isNull(categoryName) ||
                ParamUtils.isNull(description)) {
            request.setAttribute(ERROR, "Attribute can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        String action = ParamUtils.getStringParam(request, ACTION);
        switch (action) {
            case (NEW):
                adminService.createNewCategory(categoryName, description);
                break;
            case (EDIT):
                if (ParamUtils.isNull(categoryId)) {
                    request.setAttribute(ERROR, "Category ID can not be empty");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                try {
                    adminService.updateCategoryData(categoryId, categoryName, description);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;
        }
        response.sendRedirect("/admin/Categories");
    }
}
