package org.ws.rm;

import io.quarkiverse.cxf.annotation.CXFClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.xml.ws.BindingProvider;
import jakarta.xml.ws.Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import javax.xml.namespace.QName;
import java.io.File;

@QuarkusTest
public class WsReliableMessagingTest {

    @CXFClient("incorrect")
    WsrmHelloService incorrect;

    @Test
    public void correct() throws Exception {

        QName serviceName = new QName("https://quarkiverse.github.io/quarkiverse-docs/quarkus-cxf/test/ws-rm",
                "org.ws.rm.WsrmHelloService");
        Service service = Service
                .create(Thread.currentThread().getContextClassLoader().getResource("wsrmCorrect.wsdl").toURI().toURL(),
                        serviceName);
        WsrmHelloService proxy = service.getPort(WsrmHelloService.class, new RMStoreFeature());
        BindingProvider bindingProvider = (BindingProvider) proxy;
        bindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://localhost:8081/cxf/WsrmHelloServicePort");
        final String reply = proxy.sayHello();
        String prefix = "WS-ReliableMessaging Hello World! seqSize: ";
        Assertions.assertThat(reply).startsWith(prefix);
        final String rawSeqSize = reply.substring(prefix.length());
        final int seqSize = Integer.parseInt(rawSeqSize);
        Assertions.assertThat(seqSize).isGreaterThan(0);

    }
    @Test
    public void inCorrect() throws Exception {

        final String reply = incorrect.sayHello();
        String prefix = "WS-ReliableMessaging Hello World! seqSize: ";
        Assertions.assertThat(reply).startsWith(prefix);
        final String rawSeqSize = reply.substring(prefix.length());
        final int seqSize = Integer.parseInt(rawSeqSize);
        Assertions.assertThat(seqSize).isGreaterThan(0);
    }


}
