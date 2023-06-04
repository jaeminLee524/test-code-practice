package sample.cafekiosk.api.service.mail;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import sample.cafekiosk.client.mail.MailSendClient;
import sample.cafekiosk.domain.mail.MailSendHistory;
import sample.cafekiosk.domain.mail.MailSendHistoryRepository;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    private MailSendClient mailSendClient;
    @Mock
    private MailSendHistoryRepository mailSendHistoryRepository;
    @InjectMocks
    private MailService mailService;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given

        // stubbing
//        when(mailService.sendMail(anyString(), anyString(), anyString(), anyString()))
//            .thenReturn(true);

        /*doReturn(true)
            .when(mailSendClient)
            .sendMail(anyString(), anyString(), anyString(), anyString());*/

        //given
        BDDMockito.given(mailSendClient.sendMail(anyString(), anyString(), anyString(), anyString()))
            .willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));

        assertThat(result).isTrue();
    }

}