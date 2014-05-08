package com.dianping.sansi.moviecrawler.master.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by sansi on 2014/5/8.
 */
public class PageServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        String page=req.getParameter("page");
        String redirect=getRedirect(page);
        RequestDispatcher rd=req.getRequestDispatcher(redirect);
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp){
        doGet(req,resp);
    }

    private String getRedirect(String page){
        return "page/"+page+".jsp";
    }
}
