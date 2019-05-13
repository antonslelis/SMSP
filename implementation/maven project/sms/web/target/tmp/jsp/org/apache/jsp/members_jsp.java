package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.Board;
import org.solent.group.project.model.Teacher;
import org.solent.group.project.model.Pupil;
import java.util.List;

public final class members_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private Board board_acc;
	private Teacher teacher_acc;
	private List<Teacher> teacherList;
	private List<Pupil> pupilList; 
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
      out.write('\r');
      out.write('\n');


	String account = (String) session.getAttribute("acct_type");
	
	String action = (String) request.getParameter("action");
	if ("createActivity".equals(action))
	{
		//Image uploadedFile = (Image) request.getParameter("image");
		String event_name = (String) request.getParameter("name");
		String comment = (String) request.getParameter("comment");

	}

	if (account.equals("TEACHER")){
		teacher_acc = (Teacher) session.getAttribute("teacher_acc");
		pupilList = teacher_acc.getPupilList().getPupilList();
	}
	else if (account.equals("BOARD")) {
		board_acc = (Board) session.getAttribute("board_acc");
		teacherList = board_acc.getTeacherList().getTeacherList();
	}

	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <script src=\"./scripts/main.js\"></script>\r\n");
      out.write("  <title>Members</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"main()\">\r\n");
      out.write("\t<div id=\"wrapper\">\r\n");
      out.write("\t\t<div id=\"header\">\r\n");
      out.write("\t\t\t<h1>Members page</h1>\r\n");
      out.write("\t\t\t<p id=\"date\"></p>\r\n");
      out.write("\t\t\t<p id=\"time\"></p>\r\n");
      out.write("\t\t</div> <!-- /header -->\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div id=\"content_members\">\r\n");
      out.write("\t\t\t<div id=\"nav_bar\">\r\n");
      out.write("\t\t\t\t<!-- create list for nav bar -->\r\n");
      out.write("\t\t\t\t<ul>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./home.jsp\">Home</a></li>\r\n");
      out.write("\t\t\t\t\t<li><a href=\"./members.jsp\">Members</a></li>\r\n");
      out.write("\t\t\t\t\t");
 if(account.equals("BOARD")) {
					
      out.write("\r\n");
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

			if (account.equals("TEACHER"))
			{
				for (Pupil pupil : pupilList){
			
      out.write("\r\n");
      out.write("\t\t\t\t<h3 name=\"");
      out.print(pupil);
      out.write('"');
      out.write('>');
      out.print(pupil.getFirst_name() + " " + pupil.getLast_name());
      out.write("</h3><br/>\r\n");
      out.write("\t\t\t\t<a href=\"./addOrModifyActivity.jsp?action=createActivity&id=");
      out.print(pupil);
      out.write("\">Create Activity</a>\r\n");
      out.write("\r\n");
      out.write("\t\t\t");

				}
			}
			else if (account.equals("BOARD")) {

				for (Teacher teacher : teacherList) {
			
      out.write("\r\n");
      out.write("\t\t\t\t<h3 name=\"");
      out.print(teacher);
      out.write('"');
      out.write('>');
      out.print(teacher.getFirst_name() + " " + teacher.getLast_name());
      out.write("</h3><br/>\r\n");
      out.write("\t\t\t\t<a href=\"teachers.jsp?id");
      out.print(teacher);
      out.write("\">Pupil list</a>\r\n");
      out.write("\t\t\t");

				}
			}
			
      out.write("\r\n");
      out.write("\t\t\t\r\n");
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
