package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.Board;
import org.solent.group.project.model.Teacher;
import java.util.List;

public final class teachers_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


	private List<Teacher> teacherList;

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
      out.write('\r');
      out.write('\n');


	String account = (String) session.getAttribute("acct_type");


	Board board_acc = (Board) session.getAttribute("board_acc");
	if (board_acc != null) {
		teacherList = board_acc.getTeacherList().getTeacherList();
	}

	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <script src=\"./scripts/main.js\"></script>\r\n");
      out.write("  <title>Teachers</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"main()\">\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<h1>Teachers page</h1>\r\n");
      out.write("\t\t\t<p id=\"date\"></p>\r\n");
      out.write("\t\t\t<p id=\"time\"></p>\r\n");
      out.write("\t\t</div> <!-- /header -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"content_members\">\r\n");
      out.write("\t\t\t<div id=\"nav_bar\">\r\n");
      out.write("\t\t\t\t<!-- create list for nav bar -->\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./home.jsp\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t");
 if(account.equals("BOARD")) {
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./teachers.jsp\">Teachers</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./createUser.jsp\">Create User</a></li>\r\n");
      out.write("\t\t\t\t\t");

					}
					
      out.write("\r\n");
      out.write("\t\t\t\t\t<li id=\"acct_type\"> ");
      out.print( session.getAttribute("acct_type") );
      out.write(" </li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./login.jsp?action=logout\">Logout</a></li>\r\n");
      out.write("\t\t\t\t</ul>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t</div> <!-- /nav_bar -->\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

			if (account.equals("BOARD")) {

				for (Teacher teacher : teacherList) {
					session.setAttribute("teacher_acc", teacher);
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t<h3 id=\"");
      out.print(teacher.getUsername());
      out.write('"');
      out.write('>');
      out.print(teacher.getFirst_name() + " " + teacher.getLast_name());
      out.write("</h3>\r\n");
      out.write("\t\t\t\t<p><a href=\"pupils.jsp?id=");
      out.print( teacher.getUsername());
      out.write("\">Pupil list</a>\r\n");
      out.write("\t\t\t");

				}
			}
			
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t</div> <!-- /content_activities -->\r\n");
      out.write("\t</div> <!-- /wrapper -->\r\n");
      out.write("\t\r\n");
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
