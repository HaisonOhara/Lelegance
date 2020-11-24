<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- meta -->
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>L'elegance Assinar</title>
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
        <form method="post" action="preAssinar">
            <input type="hidden" name="id" value="${estilo.id}">
            <%@include file="menu.jsp"%>
            <!-- start section header -->
            <div id="estilo" class="home">
                <div class="container">
                    <div class="header-content">
                    </div>
                </div>
            </div>
            <!--==========================
      About Us Section
    ============================-->
            <div class="container">

                <header class="section-header">
                    <h1>
                        <center>Box</center>
                    </h1>
                    <p style="font-size:27px">${estilo.descricao}.</p>
                    <p style="font-size:24px">R$ ${estilo.valor}</p>
                </header>

                <div class="row about-cols">

                    <div class="col-md-4 wow fadeInUp">
                        <div class="about-col">
                            <div class="img">
                                <img src="img_projeto/cachecol.png" alt="" class="img-fluid">
                            </div>
                            <br>
                            <h2 class="title"><a href="#">Cachecol</a></h2>
                            <p>A temperatura baixou e você não quer passar perrengue? Aposte em um cachecol ou lenço, que além de ser elegante, oferece um estilo a mais para você. A dica é optar por cores mais sóbrias e neutras (como marrom, preto, cinza e azul marinho).</p>
                        </div>
                    </div>

                    <div class="col-md-4 wow fadeInUp" data-wow-delay="0.1s">
                        <div class="about-col">
                            <div class="img">
                                <img src="img_projeto/pulseira.png" alt="" class="img-fluid" />
                            </div>
                            <br>
                            <h2 class="title"><a href="#">Pulseira</a></h2>
                            <p>Antes, restrita apenas para as mulheres, ou surfistas e hippies, as pulseiras masculinas estão se tornando cada vez mais tendência entre os homens que querem se vestir bem. E para usar uma pulseira, não precisa chamar muita atenção.</p>
                        </div>
                    </div>

                    <div class="col-md-4 wow fadeInUp" data-wow-delay="0.2s">
                        <div class="about-col">
                            <div class="img">
                                <img src="img_projeto/brinco.png" alt="" class="img-fluid">
                            </div>
                            <br>
                            <h2 class="title"><a href="#">Brinco</a></h2>
                            <p>Os brincos masculinos não são uma moda que chegou agora ou jovem. Desde as antigas civilizações, homens usavam o acessório para impor respeito, dar status e ainda incrementar no visual.</p>
                        </div>
                    </div>
                    <c:if test="${usuario!=null}">
                        <header class="section-header">
                            <h1>
                                <center>Tempo de Assinatura</center>
                            </h1>
                            <p>
                                <select id="tempoAssinatura" class="form-control" name="plano">
                                    <option value="0">Tempo de Assinatura</option>
                                    <option value="1">1 mes</option>
                                    <option value="3">3 meses</option>
                                    <option value="6">6 meses</option>                         
                                </select>
                                <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit" value="comprar">Assinar</button>
                            </p>
                        </header>
                    </c:if>
                    <c:if test="${usuario==null}">
                        <h1>
                            <center>Cadastre-se para assinar essa Box e muitas outras !</center>
                        </h1>  
                    </c:if>


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
        </form>
    </body>

</html>