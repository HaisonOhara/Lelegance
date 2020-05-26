<html>

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="theme-color" content="#009cac">
        <title>L'elegance Endereco</title>
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
                            <section class="col-xl-6 col-lg-6 col-md-12 col-sm-12-col-xs-12 cardPagamentoChecked p0R">

                            </section>

                            <section class="col-xl-12">
                                <p class="subtituloPagina"> Confirmação do Endereço </p>
                                <form method="post" action="CadastrarEndereco">
                                    <div class="row p0">
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>CEP </label>
                                            <input type="text" name="cep" placeholder="00000-000">
                                        </div>
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>Endereço </label>
                                            <input type="text" name="endereco" placeholder="informe o endereço">
                                        </div>

                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>Número</label>
                                            <input type="text" name="numero" placeholder="Informe o Número">
                                        </div>
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>Complemento</label>
                                            <input type="text" name="complemento" placeholder="Informe o Bairro">
                                        </div>
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>Cidade</label>
                                            <input type="text" name="cidade" placeholder="informe a Cidade">
                                        </div>

                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-6">
                                            <label>UF</label>
                                            <input type="text" name="uf" placeholder="Infrome o Estado">
                                        </div>

                                        <button class="botaoComprar center-block" name="Cadastrar" type="submit">Próximo</button>
                                    </div>
                                </form>
                            </section>
                        </section>
                    </section>
                </section>
            </article>
        </main>

</html>





