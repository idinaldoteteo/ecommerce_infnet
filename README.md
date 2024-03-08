# Projeto da aula de Microsserviços


# Proposta
Projeta um desenho arquitetural de microsserviços para um e-commerce, identificando os contextos delimitados (bounded contexts) utilizando modelo C4, e explicando como os microsserviços conversarão entre si.


# 🛠️ Abrir e rodar o projeto

1) Rota para criar o usuário: http://localhost:8082/api/user
2) Rota para criar o pedido: http://localhost:8088/api/order/create
3) Rota para confirmar o pagamento: http://localhost:8088/api/order/confirmation_payment
4) Rota para confirmar o aceite do pedito pela transportadora: http://localhost:8088/api/order/confirmation_delivery

# Portas dos Microsserviços:
* USER: http://localhost:8082/api
* PRODUCT: http://localhost:8083/api
* NOTIFICATION: http://localhost:8086/api
* DELIVERY: http://localhost:8087/api
* ORDER: http://localhost:8088/api

# :hammer: Funcionalidades do projeto
[funcionalidade do front-end] Realizar o acesso do User (usuário) ao um ecommerce, no qual fará o seu cadastro pelo **microsserviço User** e seguirá para tela de compra de produtos utilizando o **microsserviço Product**.
Após realizar a compra, o **microsserviço Order** vai enviar uma mensagem (log) para o serviço externo de pagamento (**Payment Integration**), no qual fará a confirmação de pagamento para o microsserviço Order.
Confirmando o pagamento, o microsserviço Order enviará uma mensagem para o **microsserviço Notification** por mensageria (fila rabbitMQ) e uma mensagem solicitando uma transportadora pelo **microsserviço Delivery** por mensageria (fila rabbitMQ), no qual fará a confirmação de aceite da entrega para o microsserviço Order.
Confirmando o aceite da entrega, o será enviado uma mensagem para o usuário pelo microsserviço Notification.



