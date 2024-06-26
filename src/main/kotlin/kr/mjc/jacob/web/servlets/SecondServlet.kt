package kr.mjc.jacob.web.servlets

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.mjc.jacob.web.webContext
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.thymeleaf.TemplateEngine

@WebServlet("/servlets/second")
class SecondServlet : HttpServlet() {

  @Autowired lateinit var templateEngine: TemplateEngine

  private val log = LoggerFactory.getLogger(this.javaClass)

  override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
    log.debug("request scope : {}", req.getAttribute("title"))
    log.debug("session scope : {}", req.session.getAttribute("username"))
    log.debug("application scope : {}",
              servletContext.getAttribute("categories"))

    resp.contentType = "text/html"
    templateEngine.process("examples/scope", webContext(req, resp), resp.writer)
  }
}
