package ServerLet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Api.Autenticar;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;




/**
 *
 * @author ocris
 */
@WebServlet(name = "Login", urlPatterns = {"/loginUser"})
public class LoginUsuarioServerLet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginUsuarioServerLet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginUsuarioServerLet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/");
        dispatcher.forward(request, response);
        //processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          Gson g = new Gson();
          String urlPost = "http://localhost:8080/WebService/webresources/usuario/autenticar";
          Autenticar autenticar = new Autenticar();
          autenticar.setLogin(request.getParameter("usuario"));
          autenticar.setSenha(request.getParameter("senha"));
          String jsonBody = g.toJson(autenticar);
          //HttpPost method = new HttpPost(new URI("https://host/service"));
          
          //HttpPost post = net HttpPost(urlPost);
                  
        //TODO 
        // Fazer aqui a requsição para api do webservice
       // E dependendo da reposta redicionar ou não para a pagina princial
  
//Rotina abaixo é a original que já faz esse processamento, porem ela se comunicava diretamente com o dao    
//        String usuario = request.getParameter("usuario");
//        String senha = request.getParameter("senha");
//        Usuario daoUsuario = new Usuario();;
//
//        daoUsuario = daoUsuario.getUsuario(usuario, senha);
//
//        String destPage = request.getContextPath() + "/login/";
//        if (daoUsuario != null) {
//            destPage = request.getContextPath() + "/principal/";
//            HttpSession session = request.getSession();
//            session.setAttribute("usuario", daoUsuario);
//        } else {
//            String message = "Invalid email/password";
//            request.setAttribute("message", message);
//        }

         String destPage = request.getContextPath() + "/principal/";
         response.sendRedirect(destPage);
        //RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        //dispatcher.forward(request, response);
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
