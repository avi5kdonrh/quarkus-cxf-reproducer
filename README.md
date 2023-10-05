# quarkus-cxf
mvn test

CXF WS-RM doesn't allow overwriting the endpoint URL if the scheme (http/https) is different between the URL in wsdl and the new URL.
WSDL URL: https://nowhere:8080/cxf/WsrmHelloServicePort

Override:

BindingProvider bindingProvider = (BindingProvider) proxy;
bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://localhost:8081/cxf/WsrmHelloServicePort");

ERROR:

Caused by: java.net.UnknownHostException: UnknownHostException invoking https://nowhere:8080/cxf/WsrmHelloServicePort: null

