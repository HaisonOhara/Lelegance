<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <!-- meta -->
            <meta charset="utf-8">
            <meta content="width=device-width, initial-scale=1.0" name="viewport">

            <title>L'elegance Adcionar Estilo</title>
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
            <c:if test="${isComum==true}">
            <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
                <div class="row">
                    <div class="container">

                        <div class="logo">
                            <a href="index.jsp"><img src="img_projeto/logooo.png" alt=""></a>
                        </div>

                        <div class="responsive"><i data-icon="m" class="ion-navicon-round"></i></div>
                        <div class="espaco"></div>
                        <ul class="nav-menu list-unstyled">
                            <li><a ></a></li>
                            <li><a ></a></li>
                            <li><a ></a></li>
                            <li><a href="index.html" class="smoothScroll">Home</a></li>
                            <li><a href="../carregarEstilos" class="smoothScroll">Boxes</a></li>
                            <li><a href="../preAlterarFuncionario" class="smoothScroll"> Dados Pessoais</a></li>
                            <li><a href="../carregarEnvios" class="smoothScroll"> Análise de Envio</a></li> 
                            <li><a href="../sairFuncionario" class="smoothScroll"> Sair</a></li> 
                        </ul>

                    </div>
                </div>
            </nav>
            </c:if>

            <c:if test="${isComum==false}">
                <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
                    <div class="row">
                        <div class="container">

                            <div class="logo">
                                <a href="index.jsp"><img src="img_projeto/logooo.png" alt=""></a>
                            </div>

                            <div class="responsive"><i data-icon="m" class="ion-navicon-round"></i></div>
                            <div class="espaco"></div>
                            <ul class="nav-menu list-unstyled">
                                <li><a href="index.html" class="smoothScroll">Home</a></li>
                                <li><a href="../carregarEstilos" class="smoothScroll">Boxes</a></li>
                                <li><a href="../carregarFuncionarios" class="smoothScroll">Funcionarios</a></li>
                                <li><a href="../preAlterarFuncionario" class="smoothScroll"> Dados Pessoais</a></li>
                                <li><a href="../carregarFornecedores" class="smoothScroll"> Fornecedores</a></li>
                                <li><a href="../sairFuncionario" class="smoothScroll"> Sair</a></li>
                            </ul>

                        </div>
                    </div>
                </nav>
            </c:if>

            <!-- End section navbar -->
            <div class="container">
                <div class="row">
                    <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                        <div class="card card-signin my-5">
                            <div class="card-body">
                                <h5 class="card-title text-center">Adicionar Estilo</h5>
                                <form class="form-signin" method="post" action="../adicionarEstilo" enctype="multipart/form-data">
                                    <div class="form-label-group">
                                        <input type="text" id="inputEstilo" name="nomeEstilo" class="form-control" placeholder="Nome do Estilo" required autofocus>
                                    </div>

                                    <div class="form-label-group">
                                        <input type="numeric" name="valor" id="inputValor" class="form-control" placeholder="Valor da Box" required>
                                    </div>

                                    <div class="form-label-group">
                                        <textarea id="texareaDescricao" rows="4" cols="27" name="descricao" placeholder="Descrição da Box" maxlength="240" required></textarea>
                                    </div>
                                    </br>
                                    <div class="form-label-group">
                                        <textarea id="texareaConteudo" rows="4" cols="27" name="conteudo" placeholder="Conteudo da Box separado por ;" maxlength="240" required></textarea>
                                    </div>
                                    </br>
                                    <div class="form-label-group">
                                        <input type="file" name="file" class="filestyle">
                                    </div>
                                    </br>
                                    <button class="btn btn-lg btn-primary btn-block text-uppercase" name="add" type="submit">Adicionar Estilo</button>
                                    <hr class="my-4">
                                </form>
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