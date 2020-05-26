
<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="theme-color" content="#009cac">
        <title>L'elegance Cartao</title>
        <meta name="robots" content="index,follow">


        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">


        <link rel="stylesheet" href="css/checkout.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/theme-animate.css">
        <script src="js/jquery3.3.1.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/checkout.js"></script>
        <script src="js/plugins_framework_efeitos.js"></script>
        <link rel="shortcut icon" href="img_projeto/favicon.png">

    </head>

    <body>
        <main>

            <section class="widhtPadrao txC">
                <h2 class="tituloPagina">Você esta quase lá!</h2>
                <p class="subtituloPagina">Preencha seus dados para completarmos a assinatura.</h1>
            </section>

            <article class="widhtPadrao">
                <section class="row trocarOrderm">
                    <section class="col-xl-2 col-lg-2 col-md-12 col-sm-12-col-xs-12">
                    </section>
                    <section class="col-xl-8 col-lg-8 col-md-12 col-sm-12-col-xs-12">
                        <!-- Informacoes de pagamento -->
                        <section class="blocosCheckout row pagamento">
                            <h2>Informe os dados de pagamento <span class="obrigatorio">(Todos os dados são obrigatórios)</span></h2>
                            <section id="SectionCartao" class="col-xl-6 col-lg-6 col-md-12 col-sm-12-col-xs-12 row p0XS mg0AXS">
                                <div id="cartao" class="cardPagamentoChecked">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <div class="virarCartao">
                                            <i class="fas fa-exchange-alt"></i>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <div class="front">
                                            <header class="row">
                                                <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9 col-9">
                                                    <!--<img src="img/chip.png" class="chip">-->
                                                </div>
                                                <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col-3">
                                                    <!--<img src="img/mascote_branco.png" class="mascote">-->
                                                    <div id="cvvAmex">
                                                        <p>cvv</p>
                                                        <h5 id="cvvAmexFront">XXX</h5>
                                                    </div>
                                                </div>
                                            </header>
                                            <section class="corpoCartao">
                                                <section class="row">
                                                    <div class="col-xl-9 col-lg-9 col-md-9 col-sm-9 col-9">
                                                        <p class="bandeiraDoCartao_card">Bandeira</p>
                                                        <p class="numeroCartao" id="numeroCartaoTXT">0000 . 0000 . 0000 . 0000</p>
                                                        <h4 id="nomeCartaoTXT">Nome do titular do cartão</h4>
                                                    </div>
                                                    <div class="col-xl-3 col-lg-3 col-md-3 col-sm-3 col-3">
                                                        <p>MM/YY</p>
                                                        <h4><span id="mesCartaoTXT">00/00</span></h4>
                                                    </div>
                                                </section>
                                            </section>
                                        </div>
                                        <div class="back backCartao">
                                            <div class="tarjaCartao"></div>
                                            <p>cvv</p>
                                            <h5 id="cvvBackCard">XXX</h5>
                                        </div>
                                    </div>

                                </div>

                            </section>

                            <section class="col-xl-6 col-lg-6 col-md-12 col-sm-12-col-xs-12 cardPagamentoChecked p0R">
                                <form method="post" action="CadastrarCartao">
                                    <div class="row p0">
                                        <div class="col-xl-12 ">
                                            <label>Nome do titular </label>
                                            <input type="text" placeholder="Nome do titular do cartão" name="nome" id="nomeCartao" onkeyup="validarCartao()">
                                        </div>
                                        <div class="col-xl-5  col-lg-5 col-md-12 col-sm-12 col-12 p0R">
                                            <label>Número cartão </label>
                                            <input type="text" placeholder="Número do cartão" id="numeroCartao" name="numeroCartao" onkeyup="validarCartao()" maxlength="16">
                                        </div>

                                        <div class="col-xl-4  col-lg-4 col-md-6 col-sm-6 col-6 p0R">
                                            <label>Validade</label>
                                            <input type="text" placeholder="MM/YY" id="validadeMes" name="validade" onkeyup="validarCartao()" maxlength="5">
                                        </div>


                                        <div class="col-xl-3 col-lg-3    col-md-6 col-sm-6 col-6">
                                            <label>CVV</label>
                                            <input type="text" placeholder="xxx" id="cvvCartao" name="cnn" onkeyup="validarCartao()" maxlength="3">
                                        </div>
                                        <button class="botaoComprar center-block" name="Cadastrar" type="submit">Proximo</button>
                                    </div>
                                </form>
                            </section>
                        </section>
                    </section>
                </section>
                </section>
            </article>
        </main>

</html>

