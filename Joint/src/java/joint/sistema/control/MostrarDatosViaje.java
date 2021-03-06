package joint.sistema.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import joint.sistema.gestion.GestionadorCalificacion;
import joint.sistema.gestion.GestionadorViaje;
import joint.sistema.principal.Viaje;

/**
 *
 * @author jdiaz
 */
public class MostrarDatosViaje extends HttpServlet {
private Viaje viaje;
private GestionadorViaje gviaje;

private void iniciarGestionViaje(int idViaje){
    viaje=new Viaje(idViaje);
    gviaje=new GestionadorViaje(viaje);
}

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if(request.getParameter("idCalificacion")!=null){
            int idCalificacion=Integer.parseInt(request.getParameter("idCalificacion"));
            GestionadorCalificacion gc=new GestionadorCalificacion();
            int idViaje=gc.getIDViajeCalificacion(idCalificacion);
            iniciarGestionViaje(idViaje);
            String viaje[]=gviaje.getInfoViajeFinalizado(this.viaje);
            /*Cambiar los id de trabajador por su nombre*/
            request.setAttribute("viaje",viaje);
            request.setAttribute("idCalificacion", idCalificacion);
            request.getRequestDispatcher("sistema/vista/accion/acciones/General/respuestaDatosViaje.jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
