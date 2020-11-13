<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="theme-color" content="#009cac">
        <title>L'elegance Revisao</title>
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
                <h2 class="tituloPagina">Revise seu pedido e assine ja! </h2>
                <p class="subtituloPagina"></p>
            </section>

            <article class="widhtPadrao">
                <section class="row trocarOrderm">
                    <section class="col-xl-2 col-lg-2 col-md-12 col-sm-12-col-xs-12">
                    </section>
                    <section class="col-xl-8 col-lg-8 col-md-12 col-sm-12-col-xs-12">
                        <!-- Informacoes de pagamento -->
                        <section class="blocosCheckout row pagamento">
                            <section class="col-xl-6 col-lg-6 col-md-12 col-sm-12-col-xs-12 cardPagamentoChecked p0R">

                            </section>

                            <section class="col-xl-12">
                                <p class="subtituloPagina">Endereço </p>
                                <div class="row p0">
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Cidade</label>
                                        <input type="text" name="cidade" placeholder="${endereco.cidade}" value="${endereco.cidade}" readonly=true>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Endereço </label>
                                        <input type="text" name="endereco" value="${endereco.endereco}" readonly=true>
                                    </div>

                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Número</label>
                                        <input type="text" name="numero" value="${endereco.numero}" readonly=true>
                                    </div>
                                </div>
                            </section>
                            <section class="col-xl-6 col-lg-6 col-md-12 col-sm-12-col-xs-12 cardPagamentoChecked p0R">
                                <p class="subtituloPagina"> Cartão </p>
                                <div class="row p0">
                                    <div class="col-xl-5  col-lg-5 col-md-12 col-sm-12 col-12 p0R">
                                        <label>Número cartão </label>
                                        <input type="text" id="numeroCartao" name="numeroCartao" onkeyup="validarCartao()" maxlength="16" value="${cartao.numero}" readonly=true>
                                    </div>
                                </div>
                            </section>
                            <section class="col-xl-12">
                                <p class="subtituloPagina">Estilo</p>
                                <div class="row p0">
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Estilo selecionado</label>
                                        <input type="text" name="cep" placeholder="Estilo selecionado" value="${estilo.nome}" readonly=true>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Assinatura</label>
                                        <input type="text" name="cidade" placeholder="Valor" value="${assi.numeroMeses}" readonly=true>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Valor</label>
                                        <input type="text" name="cidade" placeholder="Valor" value="${estilo.valor}" readonly=true>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Valor do Frete</label>
                                        <input type="text" name="uf" placeholder="Valor do Frete" value="${assi.valorFrete}" readonly=true>
                                    </div>
                                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                        <label>Total</label>
                                        <input type="text" name="uf" placeholder="Total" value="${assi.total}" readonly=true>
                                    </div>
                                </div>
                                <a href="assinar"><button class="botaoComprar center-block" onclick="emailSentMessage()">Assinar</button></a>
                                <script ype="text/javascript" >
                                    function emailSentMessage() {
                                        var msg = "Obrigado pela Assinatura :)"
                                        alert(msg);
                                    }
                                </script> 
                            </section>
                        </section>
                    </section>
                </section>
            </article>
        </main>

</html>