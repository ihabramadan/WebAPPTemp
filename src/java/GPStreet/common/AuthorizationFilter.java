/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GPStreet.common;

import GPStreet.DB.Mapping.Entity.GpstGroups;
import GPStreet.DB.Mapping.Entity.GpstPages;
import GPStreet.DB.Mapping.Entity.GpstUsers;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
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

public class AuthorizationFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
         HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        res.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        
        if (!req.getRequestURI().endsWith("login.xhtml")) {
            GpstUsers user = (GpstUsers) ((HttpServletRequest) request).getSession().getAttribute("CURRENT_USER");
            if (user == null) {
                ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/common/login.xhtml");
            }else
            {
               Set<String> pages = (Set<String>) ((HttpServletRequest) request).getSession().getAttribute("USER_PAGES");
               Path pagePath = Paths.get(req.getRequestURI());
               if(!pages.contains(pagePath.getFileName().toString())){
                   ((HttpServletResponse) response).sendRedirect(req.getContextPath() + "/common/error.xhtml");
               }
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
