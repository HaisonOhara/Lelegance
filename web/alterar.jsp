<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- meta -->
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>L'elegance Alterar</title>
        <meta content="" name="keywords">
        <meta content="" name="description">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,700,700i,900,900i" rel="stylesheet">

        <!-- Bootstrap CSS File -->
        <link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Libraries CSS Files -->
        <link href="lib/ionicons/css/ionicons.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="lib/magnific-popup/magnific-popup.css" rel="stylesheet">
        <link href="lib/hover/hover.min.css" rel="stylesheet">

        <!-- Main Stylesheet File -->
        <link href="css/style.css" rel="stylesheet">

        <!-- Responsive css -->
        <link href="css/responsive.css" rel="stylesheet">

        <!-- Favicon -->
        <link rel="shortcut icon" href="img_projeto/favicon.png">
    </head>

    <body>

        <!-- start section navbar -->
  <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
    <div class="row">
      <div class="container">

        <div class="logo">
          <a href="index.jsp"><img src="img_projeto/logooo.png" alt=""></a>
        </div>

        <div class="responsive"><i data-icon="m" class="ion-navicon-round"></i></div>
			<div class="espaco"></div>
        <ul class="nav-menu list-unstyled">
          <li><a href="index.jsp" class="smoothScroll">Home</a></li>
          <li><a href="todosOsEstilos" class="smoothScroll">Boxes</a></li>
          <li><a href="preAlterar" class="smoothScroll">Dados pessoais</a></li>
          <li><a href="sairUsuario" class="smoothScroll">Sair</a></li>         
        </ul>

      </div>
    </div>
  </nav>
        <!-- End section navbar -->
        <div class="container">
            <div class="row">
                <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                    <div class="card card-signin my-5">
                        <div class="card-body">
                            <h5 class="card-title text-center">Alterar</h5>
                            <form class="form-signin" method="post" action="alterarUsuario">

                                <div class="form-label-group">
                                    <input type="text" id="inputNome" name="nome" value="${usu.nome}" class="form-control" placeholder="Nome" required autofocus>

                                </div>
                                <div class="form-label-group">
                                    <input type="text" id="inputEmail" name="email" value="${usu.email}" class="form-control" placeholder="Email" required autofocus>        
                                </div>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" name="alterar" type="submit">Alterar</button>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" name="excluir" type="submit">Excluir</button>
                            </form>
                            <br>
                            <br>
                            <br>
                            <c:if test="${statusAssinatura==true}">
                                <h4 class="card-title text-center" >Status da Assinatura: Ativa</h4>
                                <a href="inativarAssinatura"> <button class="btn btn-lg btn-primary btn-block text-uppercase" name="excluir" type="submit">Desativar Assinatura</button></a>
                            </c:if>
                            <c:if test="${statusAssinatura==false}">
                                <h4 class="card-title text-center">Status da Assinatura: Inativa</h4>
                            </c:if>
                            <hr class="my-4">
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- JavaScript Libraries -->
        <script src="lib/jquery/jquery.min.js"></script>
        <script src="lib/jquery/jquery-migrate.min.js"></script>
        <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="lib/typed/typed.js"></script>
        <script src="lib/owlcarousel/owl.carousel.min.js"></script>
        <script src="lib/magnific-popup/magnific-popup.min.js"></script>
        <script src="lib/isotope/isotope.pkgd.min.js"></script>

        <!-- Contact Form JavaScript File -->
        <script src="contactform/contactform.js"></script>

        <!-- Template Main Javascript File -->
        <script src="js/main.js"></script>

    </body>

</html>