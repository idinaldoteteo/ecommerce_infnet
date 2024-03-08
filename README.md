# Projeto da aula de Microsserviços


# Proposta
Projeta um desenho arquitetural de microsserviços para um e-commerce, identificando os contextos delimitados (bounded contexts) utilizando modelo C4, e explicando como os microsserviços conversarão entre si.


# 🛠️ Abrir e rodar o projeto

1) Para criar o pedido, rota: http://localhost:8088/api/order/create
2) Para confirmar o pagamento, rota: http://localhost:8088/api/order/confirmation_payment
3) Para confirmar o aceite do pedito pela transportador, rota: http://localhost:8088/api/order/confirmation_delivery


# :hammer: Funcionalidades do projeto
Realizar o acesso do User (usuário) ao um ecommerce, no qual fará o seu cadastro e seguirá para tela de compra de produtos.
Após realizar a compra, o serviço Order vai notificar o serviço externo de pagamento, solicitando a confirmação de pagamento, e posterior a confirmação notificará os serviços de Delivery e o User.


