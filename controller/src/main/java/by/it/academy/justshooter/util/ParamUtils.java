package by.it.academy.justshooter.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ParamUtils {
    public static String getParam(HttpServletRequest req, String nameField) {
        return Optional.ofNullable(req.getParameter(nameField))
                .filter(StringUtils::isNotBlank)
                .map(StringUtils::trim)
                .orElse(null);
    }
}
