<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <!-- meta -->
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>L'elegance Gerente</title>
  <meta content="" name="keywords">
  <meta content="" name="description">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Poppins:300,300i,400,400i,500,500i,600,600i,700,700i|Playfair+Display:400,400i,700,700i,900,900i" rel="stylesheet">

  <!-- Bootstrap CSS File -->
  <link href="../lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Libraries CSS Files -->
  <link href="../lib/ionicons/css/ionicons.min.css" rel="stylesheet">
  <link href="../lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
  <link href="../lib/magnific-popup/magnific-popup.css" rel="stylesheet">
  <link href="../lib/hover/hover.min.css" rel="stylesheet">

  <!-- Main Stylesheet File -->
  <link href="../css/style.css" rel="stylesheet">

  <!-- Responsive css -->
  <link href="../css/responsive.css" rel="stylesheet">

  <!-- Favicon -->
  <link rel="shortcut icon" href="img_projeto/favicon.png">
</head>

<body>

  <!-- start section navbar -->
  <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-sm">
    <div class="row">
      <div class="container">

        <div class="logo">
          <a href="index.jsp"><img src="img_projeto/logooo.png" alt=""></a>
        </div>
        <div class="responsive"><i data-icon="m" class="ion-navicon-round"></i></div>
        <ul class="nav-menu list-unstyled ">
          <li><a href="index.html" class="smoothScroll">Home</a></li>
          <li><a href="../carregarEstilos" class="smoothScroll">Boxs</a></li>
          <li><a href="../carregarFuncionarios" class="smoothScroll active">Funcionarios</a></li>
          <li class="smoothScroll dropdown">
              <a class="smoothScroll dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Relatorios</a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="../gerarRelatorio" target="_blank">Situação Fornecedor</a>
              </div>
          </li>
          <li><a href="../preAlterarFuncionario" class="smoothScroll"> Dados Pessoais</a></li> 
          <li><a href="../sairFuncionario" class="smoothScroll"> Sair</a></li> 
        </ul>
      </div>
    </div>
  </nav>
  <!-- End section navbar -->
  <!-- table -->
  <div class="container">
	<div class="row">
        <!-- canvas -->
        <div class="chart-size mb-5" ><canvas class="line-chart"></canvas></div>
        <!--end canvas -->
        <div class="col-md-12 mt-5">
        <h3>Lista de Funcionarios</h3>
        <div class="table-responsive">
              <table id="mytable" class="table table-bordred table-striped">
                   
                   <thead>   
                   <th>Nome</th>                    
                     <th>Email</th>
                      <th>Deletar</th>
                      <th>Promover</th>
                   </thead>
    <tbody>
    <c:forEach var="f" items="${funcionarios}">
    <tr>
    <td>${f.nome}</td>    
    <td>${f.email}</td> 
    <td><a href="excluirFuncionarioPorId?id=${f.id}"><input type="submit" name="delete" value="Deletar"></a></td>
    <c:if test="${f.perfil=='COMUM'}">
    <td><a href="promover?id=${f.id}"><input type="submit" name="promover" value="Promover"></a></td>
    </c:if>
    </tr>
    </c:forEach>    
</table>
  <!-- end table -->
  <!-- JavaScript Libraries -->
  <script src="lib/jquery/jquery.min.js"></script>
  <script src="lib/jquery/jquery-migrate.min.js"></script>
  <script src="lib/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="lib/typed/typed.js"></script>
  <script src="lib/owlcarousel/owl.carousel.min.js"></script>
  <script src="lib/magnific-popup/magnific-popup.min.js"></script>
  <script src="lib/isotope/isotope.pkgd.min.js"></script>
  <script src="lib/Chart.js-2.9.4/dist/Chart.min.js"></script>

  <!-- Contact Form JavaScript File -->
  <script src="contactform/contactform.js"></script>

  <!-- Template Main Javascript File -->
  <script src="js/main.js"></script>

  <script>
            var ctx = document.getElementsByClassName("line-chart")
            
            function GerarGrafico(data){
                now = new Date
                var chartGraph = new Chart(ctx,{
               type:'line',
               data:{
                   labels:["jan","fev","mar","abril","mai","jun","jul","ago","set","nov","dez"],
                   datasets:[{
                       label:"Novos assinantes",
                       data:data.positivo,
                       borderWidth: 6,
                       borderColor:'green',
                       backgroundColor:'transparent'
                   },
                   {
                       label: "Cancelamentos",
                       data:data.negativo,
                       borderWidth: 6,
                       borderColor: 'red',
                       backgroundColor:'transparent'
                   }
                ]
               },
               options:{
                   title:{display:true,fontSize:20,text: "Relação de cancelamento e novas assinatura de "+now.getFullYear()}
               }
            });
          }
            
            $.ajax({
                url:'graficoGerar',
                success(data){
                    GerarGrafico(data)
                }
            })
        </script>
</body>

</html>