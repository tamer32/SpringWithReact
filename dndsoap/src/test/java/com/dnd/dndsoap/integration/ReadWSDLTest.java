package com.dnd.dndsoap.integration;

import static org.assertj.core.api.Assertions.assertThat;
import java.net.MalformedURLException;
import org.apache.cxf.ext.logging.LoggingFeature;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.jupiter.api.Test;
import com.dnd.soap.service.MarketService;

public class ReadWSDLTest {

  public void init() throws MalformedURLException {}

  @Test
  public void testReadWsdl() {
    JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
    factoryBean.getFeatures().add(new LoggingFeature());
    factoryBean.setServiceClass(MarketService.class);
    factoryBean.setAddress("http://localhost:8081/services");
    MarketService service = (MarketService) factoryBean.create();

    service.addItem();
    assertThat(service.getItems()).isNotEmpty();
  }
}
