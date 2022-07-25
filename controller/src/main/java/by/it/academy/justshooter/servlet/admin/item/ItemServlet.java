package by.it.academy.justshooter.servlet.admin.item;

import by.it.academy.justshooter.dao.exception.NoDataFoundById;
import by.it.academy.justshooter.dto.ItemDto;
import by.it.academy.justshooter.services.ItemServiceImpl;
import by.it.academy.justshooter.util.ParamUtils;
import jakarta.servlet.jsp.JspApplicationContext;

import javax.servlet.AsyncContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.it.academy.justshooter.servlet.MockConstants.*;

@WebServlet(name = "ItemServlet", value = "/admin/Items")
public class ItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = ParamUtils.getStringParam(request, ACTION);
        if (null == action) {
            action = "";
        }
        ItemServiceImpl itemService = new ItemServiceImpl();
        Integer itemId = ParamUtils.getIntegerParam(request, ITEM_ID);
        switch (action) {
            case (NEW):
                request.getRequestDispatcher("/admin/item/item.jsp").forward(request, response);
                break;
            case (EDIT):
                ItemDto item = null;
                try {
                    item = itemService.getItemById(itemId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                request.setAttribute("item", item);
                request.getRequestDispatcher("/admin/item/item.jsp").forward(request, response);
                break;
            case (DELETE):
                try {
                    itemService.deleteItemById(itemId);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                response.sendRedirect("/admin/Items");
                break;
            default:
                List<ItemDto> allItems = itemService.getAllItems();
                request.setAttribute("allItems", allItems);
                request.getRequestDispatcher("/admin/item/itemList.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        ItemServiceImpl itemService = new ItemServiceImpl();
        Integer itemId = ParamUtils.getIntegerParam(request, ITEM_ID);
        String itemName = ParamUtils.getStringParam(request, ITEM_NAME);
        String article = ParamUtils.getStringParam(request, ARTICLE);
        String barcode = ParamUtils.getStringParam(request, BARCODE);
        if (ParamUtils.isNull(itemName) ||
                ParamUtils.isNull(article) ||
                ParamUtils.isNull(barcode)) {
            request.setAttribute(ERROR, "Attribute can not be empty");
            request.getRequestDispatcher("/").forward(request, response);
        }
        String action = ParamUtils.getStringParam(request, ACTION);
        switch (action) {
            case (NEW):
                    itemService.createNewItem(itemName, article, barcode);
                break;
            case (EDIT):
                if (ParamUtils.isNull(itemId)) {
                    request.setAttribute(ERROR, "Item ID can not be empty");
                    request.getRequestDispatcher("/").forward(request, response);
                }
                try {
                    itemService.updateItemData(itemId, itemName, article, barcode);
                } catch (NoDataFoundById e) {
                    request.setAttribute(ERROR, e.getMessage());
                    request.getRequestDispatcher("/").forward(request, response);
                }
                break;
        }
        response.sendRedirect("/admin/Items");
    }
}
