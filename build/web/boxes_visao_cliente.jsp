<!DOCTYPE html>
<html lang="en">

<head>
    <!-- meta -->
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>L'elegance Estilos</title>
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
    <%@include file="menu.jsp"%>
        <!-- start section header -->
        <div id="estilo" class="home">
            <div class="container">
                <div class="header-content">
                    <h12>Estilos</h12>
                    <h13>Um estilo para sempre estar elegante.</h13>
                    <h14>
                        <a></a>
                    </h14>
                </div>
            </div>
        </div>
        <!-- End section header -->
        <!--==========================
      About Us Section
    ============================-->
        <div class="container">
            <header class="section-header">
                <h1>

                </h1>
                <p></p>
                <p></p>
            </header>
            <div class="row about-cols">
                <c:forEach var="e" items="${Estilos}">
                    <div class="col-md-4 wow fadeInUp">
                        <div class="about-col">
                            <c:if test="${e.status== 'Ativo'}">
                                <div class="img">
                                    <img src="${e.imagem}" alt="" class="img-fluid">
                                </div>
                                <br>
                                <h2 class="title"><a href="#">${e.nome}</a></h2>
                                <p>
                                    <input type="hidden" value="${e.id}"> ${estilo.descricao}
                                </p>
                                <center>
                                    <p><a href="buscaEstilo?id=${e.id}">Ver</a> </p>
                                </center>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <!-- #about -->

        <!-- start section footer -->

        <!-- End section footer -->

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