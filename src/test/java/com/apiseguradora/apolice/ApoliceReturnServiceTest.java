package com.apiseguradora.apolice;
import com.apiseguradora.cliente.ClienteBuscarPorIDServiceTest;
import com.apiseguradora.model.Apolice;
import com.apiseguradora.service.Apolice.ApoliceReturnService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ApoliceReturnServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(ClienteBuscarPorIDServiceTest.class);

    @Test
    public void testApoliceReturnService() {
        Apolice apolice = new Apolice();
        apolice.setNumeroApolice("12345");
        apolice.setDataInicioVigencia(LocalDate.now().minusDays(5));
        apolice.setDataFimVigencia(LocalDate.now().plusDays(5));
        apolice.setPlacaVeiculo("ABC123");
        apolice.setValorApolice(1000L);

        ApoliceReturnService apoliceReturnService = new ApoliceReturnService(apolice);

        assertEquals("12345", apoliceReturnService.getNumeroApolice());
        assertFalse(apoliceReturnService.getApoliceVencida());
        assertEquals(10, apoliceReturnService.getDiasVencer());
        assertEquals("ABC123", apoliceReturnService.getPlacaVeiculo());
        assertEquals(1000L, apoliceReturnService.getValorApolice());
        assertEquals(apolice.getDataInicioVigencia(), apoliceReturnService.getInicio());
        assertEquals(apolice.getDataFimVigencia(), apoliceReturnService.getFim());

        logger.info("Response: {}", apoliceReturnService);

    }
}