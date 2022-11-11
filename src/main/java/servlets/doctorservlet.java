package servlets;

import com.DaoImpl;
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
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/doctorservlet")
public class doctorservlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter printWriter = resp.getWriter();
        ServletContext servletContext = getServletContext();
        servletContext.setAttribute("dslots", "doctorAvailiabilitySlots");
        String dname = req.getParameter("doctorName").trim().toLowerCase();
        String dspecialisation = req.getParameter("doctorSpecialisation").trim().toLowerCase();
        int dslots = Integer.parseInt(req.getParameter("doctorAvailiabilitySlots"));
        int dfees = Integer.parseInt(req.getParameter("doctorFees"));
        Date slotDate = Date.valueOf(req.getParameter("slotDate"));
        Time startTime = Time.valueOf(req.getParameter("slotStartTime"));
        Time endTime = Time.valueOf(req.getParameter("slotEndTime"));

        LocalTime localTime = LocalTime.now();
        if (localTime.isBefore(startTime.toLocalTime()) && localTime.isAfter(endTime.toLocalTime()) ) {

            EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ayushman");
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            Query query = entityManager.createQuery("select vetdoctorpojo from VetDoctorPojo vetdoctorpojo");
            List<VetDoctorPojo> list = query.getResultList();
            List<VetDoctorPojo> list1 = list.stream().filter(s -> s.getDoctorSpecialisation().equals(dspecialisation)).
                    collect(Collectors.toList());

            List<VetDoctorPojo> list2 = list.stream().filter(s -> s.getDoctorName().equals(dname)).collect(Collectors.toList());
            if (list1.size() == 0 && list2.size() >= 0) {
                VetDoctorPojo doctorPojo = new VetDoctorPojo();
                doctorPojo.setDoctorName(dname);
                doctorPojo.setDoctorFees(dfees);
                doctorPojo.setDoctorSpecialisation(dspecialisation);
                doctorPojo.setDoctorAppointmentSlots(dslots);
                DaoImpl dao = new DaoImpl();
                dao.doctorinsertion(doctorPojo);

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.html");
                requestDispatcher.include(req, resp);
                printWriter.println("doctor added");
            } else {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor.html");
                requestDispatcher.include(req, resp);
                printWriter.println("doctor with same specialisation is present");
            }
        }
else {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("doctor.html");
            requestDispatcher.include(req, resp);
            printWriter.println("doctor is not availiable at this time");
        }
    }
}