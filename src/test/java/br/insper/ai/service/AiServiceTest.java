package br.insper.ai.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AiServiceTest {

    @InjectMocks
    private AiService aiService;

    @Mock
    private AiRepository aiRepository;

}	
	