package servlets;

import com.DaoImpl;
import com.PateintPetPojo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pateintpetservlet")
public class pateintpetservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        String pname=req.getParameter("pateintName").trim().toLowerCase();
        String pdesease=req.getParameter("pateintDesease").trim().toLowerCase();

        PateintPetPojo pateintPetPojo = new PateintPetPojo();
        pateintPetPojo.setPateintName(pname);
        pateintPetPojo.setPateintDesease(pdesease);

        DaoImpl dao = new DaoImpl();
        dao.pateintpetinsertion(pateintPetPojo);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
        requestDispatcher.include(req,resp);
        printWriter.println("pateint added");
    }
}
