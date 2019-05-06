package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.solent.group.project.web.WebObjectFactory;
import org.solent.group.project.model.ServiceFactory;
import org.solent.group.project.model.ServiceFacade;
import org.solent.group.project.model.Entity;

public final class ListEntities_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    if ("deleteEntity".equals(action)) {
        try {
            Integer entityId = Integer.parseInt(entityIdReq);
            serviceFacade.deleteEntity(entityId);
        } catch (Exception e) {
            errorMessage = "problem deleting Entity " + e.getMessage();
        }
    } else if ("modifyEntity".equals(action)) {
        try {
            Integer entityId = Integer.parseInt(entityIdReq);
            Entity entityTemplate = new Entity();
            entityTemplate.setId(entityId);
            entityTemplate.setField_A(entityField_AReq);
            entityTemplate.setField_B(entityField_BReq);
            entityTemplate.setField_C(entityField_CReq);
            Entity entity = serviceFacade.updateEntity(entityTemplate);
            if (entity == null) {
                errorMessage = "problem modifying Entity. could not find entityId " + entityId;
            }
        } catch (Exception e) {
            errorMessage = "problem modifying Entity " + e.getMessage();
        }
    } else if ("createEntity".equals(action)) {
        try {
            Entity entityTemplate = new Entity();
            entityTemplate.setField_A(entityField_AReq);
            entityTemplate.setField_B(entityField_BReq);
            entityTemplate.setField_C(entityField_CReq);
            Entity entity = serviceFacade.createEntity(entityTemplate);
            if (entity == null) {
                errorMessage = "problem creating Entity. Service returned null ";
            }
        } catch (Exception e) {
            errorMessage = "problem creating  Entity " + e.getMessage();
        }
    } 

    List<Entity> entityList = serviceFacade.retrieveAllEntities();


      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/style.css\">\r\n");
      out.write("        <title>Entity List</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <!-- print error message if there is one -->\r\n");
      out.write("        <div style=\"color:red;\">");
      out.print(errorMessage);
      out.write("</div>\r\n");
      out.write("        <h1>Entity List</h1>\r\n");
      out.write("        <table>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <th>id</th>\r\n");
      out.write("                <th>field_A</th>\r\n");
      out.write("                <th>field_B</th>\r\n");
      out.write("                <th>field_C</th>\r\n");
      out.write("                <th></th>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
  for (Entity entity : entityList) {
            
      out.write("\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td>");
      out.print(entity.getId());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(entity.getField_A());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(entity.getField_B());
      out.write("</td>\r\n");
      out.write("                <td>");
      out.print(entity.getField_C());
      out.write("</td>\r\n");
      out.write("                <td>\r\n");
      out.write("                    <form action=\"AddOrModifyEntity.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"modifyEntity\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(entity.getId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Modify Entity\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    <form action=\"ListEntities.jsp\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"action\" value=\"deleteEntity\">\r\n");
      out.write("                        <input type=\"hidden\" name=\"entityId\" value=\"");
      out.print(entity.getId());
      out.write("\">\r\n");
      out.write("                        <input type=\"submit\" value=\"Delete Entity\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            ");
 }
      out.write("\r\n");
      out.write("\r\n");
      out.write("        </table> \r\n");
      out.write("        <BR>\r\n");
      out.write("        <form action=\"AddOrModifyEntity.jsp\">\r\n");
      out.write("            <input type=\"hidden\" name=\"action\" value=\"createEntity\">\r\n");
      out.write("            <input type=\"submit\" value=\"Create Entity\">\r\n");
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
