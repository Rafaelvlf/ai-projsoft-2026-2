package br.insper.ai.controller;

import br.insper.ai.dto.ProdutoDto;
import br.insper.ai.model.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
public class ProdutoControllerTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine")
            .withDatabaseName("produto_test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_shouldCreateProduto() throws Exception {

        ProdutoDto dto = new ProdutoDto();
        dto.setNome("produto1");
        dto.setPreco(new BigDecimal(1000));
        dto.setDescricao("produto1 do teste");
        dto.setQuantidade(11);

        MvcResult result = mockMvc.perform(
                        post("/api/produtos")
                                .contentType("application/json")
                                .content(objectMapper.writeValueAsString(dto))
                )
                .andExpect(status().isOk())
                .andReturn();

        Produto produto = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                Produto.class);
        Assertions.assertNotNull(produto.getId());
    }




}