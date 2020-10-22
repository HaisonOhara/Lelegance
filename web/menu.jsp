<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- start section navbar -->
 <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
    <div class="row">
      <div class="container">

        <div class="logo">
          <a href="index.jsp"><img src="img_projeto/logooo.png" alt=""></a>
        </div>

        <div class="responsive"><i data-icon="m" class="ion-navicon-round"></i></div>
			<div class="espaco"></div>
        <ul class="nav-menu list-unstyled nav1">
          <li><a href="index.jsp" class="smoothScroll tey">Home</a></li>
          <li><a href="todosOsEstilos" class="smoothScroll tey">Boxs</a></li>
          <c:if test="${usuarioAutenticado==null}">
            <li><a href="cadastro.jsp" class="smoothScroll tey">Cadastre-se</a></li>
            <li><a href="login.jsp" class="smoothScroll tey">Login</a></li>         
          </c:if>
          <c:if test="${usuarioAutenticado!=null}">
             <li><a href="usuario.jsp" class="smoothScroll tey">Minha conta</a></li>
            <li><a href="sairUsuario" class="smoothScroll tey">Sair</a></li> 
          </c:if>  
        </ul>

      </div>
    </div>
  </nav>
  <!-- End section navbar -->
  <c:if test="${msg!=null}">
                <script>alert("${msg}")</script>
            </c:if>
  