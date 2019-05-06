package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.solent.group.project.web.WebObjectFactory;
import org.solent.group.project.model.ServiceFactory;
import org.solent.group.project.model.ServiceFacade;
import org.solent.group.project.model.Entity;

public final class AddOrModifyEntity_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");


    ServiceFacade serviceFacade = (ServiceFacade) session.getAttribute("serviceFacade");

    // If the user session has no bankApi, create a new one
    if (serviceFacade == null) {
        ServiceFactory serviceFactory = WebObjectFactory.getServiceFactory();
        serviceFacade = serviceFactory.getServiceFacade();
        session.setAttribute("ServiceFacade", serviceFacade);
    }

    // get request values
    String action = (String) request.getParameter("action");
    String entityIdReq = (String) request.getParameter("entityId");
    String entityField_AReq = (String) request.getParameter("field_A");
    String entityField_BReq = (String) request.getParameter("field_B");
    String entityField_CReq = (String) request.getParameter("field_C");

    String errorMessage = "";

    Entity entity = null;
    Integer entityId = null;

    if ("modifyEntity".equals(action)) {
        try {
            entityId = Integer.parseInt(entityIdReq);
            entity = serviceFacade.retrieveEntity(entityId);
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else if ("createEntity".equals(action)) {
        try {
            entity = new Entity();
        } catch (Exception e) {
            errorMessage = "problem finding entity " + e.getMessage();
        }
    } else {
        errorMessage = "cannot recognise action: " + action;
    }


      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("        <title>Edit Entity</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        ");
 if ("createEntity".equals(action)) {
        
      out.write("\r\n");
      out.write("        <h1>Add New Entity</h1>\r\n");
      out.write("        ");
 } else {
      out.write("\r\n");
      out.write("        <h1>Modify Entity ");
      out.print(entityId);
      out.write("</h1>\r\n");
      out.write("        ");
 }
      out.write("\r\n");
      out.write("        <form action=\"ListEntities.jsp\">\r\n");
      out.write("            <table>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th>Field</th>\r\n");
      out.write("                    <th>Current Value</th>\r\n");
      out.write("                    <th>New Value</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>Entity Id</td>\r\n");
      out.write("                    <td>");
      out.print(entity.getId());
      out.write("</td>\r\n");
      out.write("                    <td></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>field_A</td>\r\n");
      out.write("                    <td>");
      out.print(entity.getField_A());
      out.write("</td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"field_A\" value =\"");
      out.print(entity.getField_A());
      out.write("\"></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>field_B</td>\r\n");
      out.write("                    <td>");
      out.print(entity.getField_B());
      out.write("</td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"field_B\" value =\"");
      out.print(entity.getField_B());
      out.write("\"></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <td>field_C</td>\r\n");
      out.write("                    <td>");
      out.print(entity.getField_C());
      out.write("</td>\r\n");
      out.write("                    <td><input type=\"text\" name=\"field_C\" value =\"");
      out.print(entity.getField_C());
      out.write("\"></td>\r\n");
      out.write("                </tr>\r\n");
      out.write("            </table> \r\n");
      out.write("            <BR>\r\n");
      out.write("            ");
 if ("createEntity".equals(action)) {
            
      out.write("\r\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createEntity\">\r\n");
      out.write("            <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(entityId);
      out.write("\">\r\n");
      out.write("            <input type=\"submit\" value=\"Create New Entity\">\r\n");
      out.write("            ");
 } else if ("modifyEntity".equals(action)) {
            
      out.write("\r\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"modifyEntity\">\r\n");
      out.write("            <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(entityId);
      out.write("\">\r\n");
      out.write("            <input type=\"submit\" value=\"Modify Entity\">\r\n");
      out.write("            ");
 }
      out.write("\r\n");
      out.write("        </form>\r\n");
      out.write("        <form action=\"ListEntities.jsp\">\r\n");
      out.write("            <input type=\"submit\" value=\"Cancel and Return\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
