# Pagamentos PicPay

Integração simples do Spring com PicPay 
## Principais tecnologias

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Lombok](https://projectlombok.org/)
* [Maven](http://maven.apache.org/)
* [DevTools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)

### Referências

https://studio.picpay.com/docs/e-commerce<br>
https://github.com/luannsr12/picpay-php

## Pre-requisitos

Primeiramente você precisa ter uma conta para empresas no PicPay, para que possamos obter os tokens de uso.

Para obter os tokens ``x-picpay-token`` e ``x-seller-token`` clique [aqui](https://lojista.picpay.com/)
  

## Configuração

Insira seus tokens nas variáveis a seguir

```java
private String x_picpay_token = "YOUR_TOKEN";
	
private String x_seller_token = "YOUR_TOKEN";
```
  
## Endpoint's

``http://localhost:8085/payment`` endpoint para criar um pagamento<br>

```java
PicPay picPay = new PicPay();
Product product = new Product();
Cliente cliente = new Cliente();

//Gerando uma referencia aleatoria
String ref = String.valueOf(new Random().nextInt(99999));

//setando alguns parametros do produto
product.setRef(ref);
product.setNome("Vip Beta");
product.setValor(3.0);

//Informacoes do cliente q ira realizar a compra
cliente.setCpf("16143248552");
cliente.setEmail("cauadev@gmail.com");
cliente.setNome("Cauã");
cliente.setSobrenome("Da Silva Nunes");
cliente.setTelefone("81984019242");

//por fim, realizando a requisicao de pagamento.
Payment res = picPay.requestPayment(product, cliente); 
```

``http://localhost:8085/status`` endpoint para ver o status do pedido<br>

* Necessario passar no parametro da requisição a refefence do pedido exemplo: /status?ref=34564

```java

  if(ref != null) {

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    headers.add("x-picpay-token", "TOKEN");       //Setando token no header da requisicao
    HttpEntity<?> entity = new HttpEntity<>(headers);
    
    //realizando a requisicao
     ResponseEntity<Status> res = restTemplate.exchange("https://appws.picpay.com/ecommerce/public/payments/"+ref+"/status"
        , HttpMethod.GET
        , entity
        , Status.class);

    return ResponseEntity.ok(res.getBody());

  }
  return ResponseEntity.badRequest().body(false);
```
    

