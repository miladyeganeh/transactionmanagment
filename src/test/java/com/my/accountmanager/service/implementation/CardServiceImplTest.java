package com.my.accountmanager.service.implementation;

import com.my.accountmanager.domain.entity.CardEntity;
import com.my.accountmanager.repository.CardRepository;
import com.my.accountmanager.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(SpringRunner.class)
class CardServiceImplTest {

    @MockBean
    private CardRepository cardRepository;
    private CardEntity mockedCardEntity;
    private final CardService cardService;
    private final static String MOCKED_CARD_PAN = "1234.1234.1234.1234";

    @Autowired
    public CardServiceImplTest(CardService cardService) {
        this.cardService = cardService;
    }

    @BeforeEach
    void setUp() {
        mockedCardEntity = createMockedCardEntity();
    }

    @Test
    void getByCardPAN_If_Found() {
        Mockito.when(this.cardRepository.findByCardPAN(any())).thenReturn(Optional.of(mockedCardEntity));
        Optional<CardEntity> obtainedEntity = this.cardService.findByCardPAN(MOCKED_CARD_PAN);
        assertThat(obtainedEntity.isEmpty(), is(false));
        assertThat(obtainedEntity.get().getCardPAN(), is(mockedCardEntity.getCardPAN()));
    }

    @Test
    void getByCardPAN_If_Not_Found() {
        Mockito.when(this.cardRepository.findByCardPAN(any())).thenReturn(Optional.empty());
        Optional<CardEntity> obtainedEntity = this.cardService.findByCardPAN(MOCKED_CARD_PAN);
        assertThat(obtainedEntity.isEmpty(), is(true));
    }


    private CardEntity createMockedCardEntity() {
        CardEntity cardEntity = new CardEntity();
        cardEntity.setId(1L);
        cardEntity.setCardPAN(MOCKED_CARD_PAN);
        return cardEntity;
    }
}