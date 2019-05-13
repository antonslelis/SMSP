package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.solent.group.project.model.Board;
import org.solent.group.project.model.Parent;
import java.util.List;
import org.solent.group.project.model.Teacher;

public final class createUser_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private List<Parent> parentList;
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
      out.write("\r\n");
      out.write('\r');
      out.write('\n');

	String account = (String) session.getAttribute("acct_type");

	if (!account.equals("ADMIN")) {
		Board board_acc = (Board) session.getAttribute("board_acc");
		//get both lists of school members
		parentList = board_acc.getParentList().getParentList();
		teacherList = board_acc.getTeacherList().getTeacherList();
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("  <link rel=\"stylesheet\" type=\"text/css\" href=\"./css/main.css\">\r\n");
      out.write("  <title>Create user</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<form id=\"createUser_form\" action=\"./home.jsp\" method=\"post\">\r\n");
      out.write("\t\t<label id=\"fname_label\">First Name:</label>\r\n");
      out.write("\t\t<input type=\"text\" name=\"first_name\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"lname_label\">Last Name:</label>\r\n");
      out.write("\t\t<input type=\"text\" name=\"last_name\" value=\"\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"user_label\">Username:</label>\r\n");
      out.write("\t\t<input type=\"text\" name=\"username_c\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"pass_label\">Password:</label>\r\n");
      out.write("\t\t<input type=\"password\" name=\"password_c\" value=\"\"><br/>\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"role_label\">Role:</label>\r\n");
      out.write("\t\t<select name=\"creation_level\">\r\n");
      out.write("\t\t");
 if (account.equals("ADMIN")) {
		
      out.write("\r\n");
      out.write("\t\t\t<option name=\"BOARD\">BOARD</option>\r\n");
      out.write("\r\n");
      out.write("\t\t");
  }
			else if (account.equals("BOARD")) {
		
      out.write("\r\n");
      out.write("\t\t\t<option name=\"TEACHER\">TEACHER</option>\r\n");
      out.write("\t\t\t<option name=\"PARENT\">PARENT</option>\r\n");
      out.write("\t\t\t<option name=\"PUPIL\">PUPIL</option>\r\n");
      out.write("\t\t");

			}
		
      out.write("\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t<br/>\r\n");
      out.write("\t\t<h3>Only change if PUPIL is selected above*</h3>\r\n");
      out.write("\t\t<label id=\"parent_link_label\">*Parent for pupil:</label>\r\n");
      out.write("\t\t");
 if (account.equals("BOARD")){
		
      out.write("\r\n");
      out.write("\t\t<select name=\"parent_link\">\r\n");
      out.write("\t\t\t");

			for (Parent parent : parentList){
			
      out.write("\r\n");
      out.write("\t\t\t\t<option name=\"");
      out.print( parent.getUsername());
      out.write("\">\r\n");
      out.write("\t\t\t\t\t");
      out.print( parent.getUsername());
      out.write("</option>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t");

			}
		
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t\t<label id=\"teacher_link_label\">*Teacher for pupil:</label>\r\n");
      out.write("\t\t");
 if (account.equals("BOARD")){
		
      out.write("\r\n");
      out.write("\t\t<select name=\"teacher_link\">\r\n");
      out.write("\t\t\t");

				for (Teacher teacher : teacherList){
			
      out.write("\r\n");
      out.write("\t\t\t<option name=\"");
      out.print( teacher.getUsername());
      out.write("\">\r\n");
      out.write("\t\t\t\t");
      out.print( teacher.getUsername());
      out.write("</option>\r\n");
      out.write("\t\t\t");

				}
			
      out.write("\r\n");
      out.write("\t\t</select>\r\n");
      out.write("\t\t");

			}
		
      out.write("\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"action\" value=\"createUser\">\r\n");
      out.write("\t\t<p><input type=\"submit\" id=\"createUser_btn\" value=\"Create user\"></p>\r\n");
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
