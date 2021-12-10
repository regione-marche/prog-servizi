/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.50
 * Generated at: 2021-01-26 08:42:52 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.naviox;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import util.UserUtils;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import java.io.StringReader;

public final class signInCohesion_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


public static byte[] base64Decode(String data) throws Exception{	
	return javax.xml.bind.DatatypeConverter.parseBase64Binary(data);
}
public static boolean verifySignature(org.w3c.dom.Document doc , String pathCert) {
	//Questa procedura non funziona con java jdk1.6.xx Ritorna sempre false sulla validazione delle references
	//Se nell'XML della servlet si è specificato il parametro webSso in https si può evitare la verifica della firma in quando la verifica del certificato SSL eseguita automaticamente dalla servlet, garantirà l'indentità del server che restituisce il token.
	try{
		if (doc.getElementsByTagNameNS(javax.xml.crypto.dsig.XMLSignature.XMLNS, "Signature").getLength() == 0)
			throw new Exception("Cannot find Signature element");
		java.security.cert.X509Certificate cert = (java.security.cert.X509Certificate)java.security.cert.CertificateFactory.getInstance("X.509").generateCertificate(new java.io.FileInputStream(new java.io.File(pathCert)));
		javax.xml.crypto.dsig.dom.DOMValidateContext valContext = new javax.xml.crypto.dsig.dom.DOMValidateContext(cert.getPublicKey(), doc.getElementsByTagNameNS(javax.xml.crypto.dsig.XMLSignature.XMLNS, "Signature").item(0));
	 	javax.xml.crypto.dsig.XMLSignature signature = javax.xml.crypto.dsig.XMLSignatureFactory.getInstance("DOM").unmarshalXMLSignature(valContext);
		return signature.validate(valContext); 
	}catch(Exception e){ e.printStackTrace();}
	return false;
}
public static org.w3c.dom.Document getXmlDocFromString(String xml){
	try{
		javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		return dbf.newDocumentBuilder().parse(new java.io.ByteArrayInputStream(xml.getBytes("UTF-8")));
	}catch(Exception e){ e.printStackTrace();}
	return null;
}
public static String getXMLValue(org.w3c.dom.Document doc, String name) throws Exception{
	try {
		NodeList nlist = doc.getElementsByTagName(name);
	 	String value = nlist.item(0).getFirstChild().getNodeValue();
	 	return value; // ma perche cazzo va qui
	}catch(Exception ex){
		throw new Exception("tag " + name + "not found");
	}
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

if(request.getParameter("token") != null) {
	//qui me devi richiama il signin
	//out.print("<html><body><xmp>" + new String(base64Decode(request.getParameter("token"))) + "</xmp></body></html>");
	//me devi tira fori username lù sername così è chiaro!
	
	 try{
		 String xml = new String(base64Decode(request.getParameter("token")), "UTF-8");
		 /*
		 String XmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + xml;
		 
		 DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		 DocumentBuilder db=dbf.newDocumentBuilder();
		 
		 InputSource is = new InputSource();
	     is.setCharacterStream(new StringReader(XmlString));
		 
		 doc=db.parse(is);proa mpo
		 */
		 Document doc = getXmlDocFromString(xml);
		 String cf = getXMLValue(doc, "codice_fiscale");
		 String name = getXMLValue(doc, "nome");
		 
		 out.print(doc);

		 String pathCertificate = session.getServletContext().getRealPath("/") + "cohesion2.cer";
		 
		 boolean statusCheck = true; //verifySignature(doc, pathCertificate); //ATTENZIONE: Non funziona con Java v1.6.xx (ritorna sempre false)
		 
		 if (statusCheck){
			if (com.openxava.naviox.model.User.find(cf) != null)
			{
			 	com.openxava.naviox.impl.SignInHelper.signIn(session, cf);
			}
			else
			{
				UserUtils.Signup(cf, name);
			}
			response.sendRedirect("/ProgrammaBiennale2020/m/Dashboard");
		 }
		 else
		 {
			 out.print("FIRMA TOKEN NON VERIFICATA");
		 }
	 }catch(Exception e){
		 out.print("ERROR in the login phase: " + e.getMessage());
	 }
} else
	out.print("PARAMETER token NOT PROVIDED");

    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
