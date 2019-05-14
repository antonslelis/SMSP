package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.Activity;
import org.solent.group.project.model.Pupil;
import org.solent.group.project.model.Board;
import java.util.List;
import org.solent.group.project.model.ActivityList;

public final class addOrModifyActivity_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html");
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

	String action = (String) request.getParameter("action");
	String account = (String) session.getAttribute("acct_type");

	if ("modifyActivity".equals(action)){
		int activity_id = Integer.parseInt(request.getParameter("id"));


		Pupil pupil_acc = (Pupil) session.getAttribute("pupil_acc");
		Activity activity = pupil_acc.getPersonalActivities().getActivitybyId(activity_id);





	}
	
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("\t");

		if ("createActivity".equals(action)){
	
      out.write("\r\n");
      out.write("\t<title>Add Activity</title>\r\n");
      out.write("\t");

		}
		else {		
	
      out.write("\t\r\n");
      out.write("\t<title>Modify Activity</title>\r\n");
      out.write("\t");

		}
	
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<h1>Please enter the activity details.</h1>\r\n");
      out.write("\t<h2><a href=\"./events.jsp\">Back</a></h2>\r\n");
      out.write("\t<form id=\"createActivity_form\" action=\"./activities.jsp\" method=\"post\">\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"task_label\">Task:</label>\r\n");
      out.write("\t\t<input type=\"text\" name=\"task\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"comment_label\">Pupil Comment:</label>\r\n");
      out.write("\t\t<textarea name=\"comment\"></textarea><br/>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t");
 if (account.equals("BOARD")){
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"free_label\">Free?:</label>\r\n");
      out.write("\t\t<input type=\"checkbox\" name=\"free\" value=\"true\"><br/>\r\n");
      out.write("\t\t");
 }
		
      out.write("\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"createActivity\"/>\r\n");
      out.write("\t\t<p><input type=\"submit\" id=\"createActivity_btn\" value=\"Create activity\"></p>\r\n");
      out.write("\t\t\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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
