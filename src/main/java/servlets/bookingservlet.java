package servlets;

import com.DaoImpl;
import com.PateintPetPojo;
import com.VetDoctorPojo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/bookingservlet")
public class bookingservlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        ServletContext servletContext = getServletContext();
        int pid= Integer.parseInt(req.getParameter("pateintpetId"));
        int did= Integer.parseInt(req.getParameter("doctorId"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ayushman");
        EntityManager entityManager = entityManagerFactory.createEntityManager();



        Query query = entityManager.createQuery("select vetdoctorpojo from VetDoctorPojo vetdoctorpojo");
        List<VetDoctorPojo> list = query.getResultList();
        //int numberOfSlots = (int) servletContext.getAttribute("dslots");


        if(list.stream().filter(s->s.getDoctorAppointmentSlots()>0).collect(Collectors.toList()).size()!=0 ){

            PateintPetPojo pateintPetPojo = entityManager.find(PateintPetPojo.class,pid);
            VetDoctorPojo vetDoctorPojo = entityManager.find(VetDoctorPojo.class,did);

            List<PateintPetPojo> pateintPetPojos = new ArrayList<>();
            pateintPetPojos.add(pateintPetPojo);
            vetDoctorPojo.setPateintPetPojos(pateintPetPojos);
            vetDoctorPojo.setDoctorAppointmentSlots(vetDoctorPojo.getDoctorAppointmentSlots()-1);

            DaoImpl dao = new DaoImpl();
            dao.booking(vetDoctorPojo);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
            requestDispatcher.include(req,resp);
            printWriter.println("booking done successfully");

        }
       else {

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("booking.html");
            requestDispatcher.include(req,resp);
            printWriter.println("particular doctor is not availiable");
        }

    }
}
