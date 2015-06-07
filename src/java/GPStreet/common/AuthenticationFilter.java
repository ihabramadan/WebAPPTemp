/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GPStreet.common;

import GPStreet.BB.CommonBB;
import GPStreet.BB.UserBean;
import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author iramadan
 */
@WebFilter("/common/*")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        if (!req.getRequestURI().endsWith("login.xhtml") ) {
            GpstUsers user = (GpstUsers) ((HttpServletRequest) request).getSession().getAttribute("CURRENT_USER");
            if (user == null) {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/common/login.xhtml");
            } 
            
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}
