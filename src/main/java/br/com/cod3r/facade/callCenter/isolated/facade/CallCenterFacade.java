package br.com.cod3r.facade.callCenter.isolated.facade;

import br.com.cod3r.facade.callCenter.isolated.model.Card;
import br.com.cod3r.facade.callCenter.isolated.model.Register;
import br.com.cod3r.facade.callCenter.isolated.services.*;

import java.util.List;

public class CallCenterFacade {

    CardService cardService;
    RegisterService registerService;
    ReportService reportService;
    PaymentService paymentService;
    SecurityService securityService;


    public CallCenterFacade(){
        cardService = new CardService();
        registerService = new RegisterService();
        reportService = new ReportService(registerService);
        paymentService = new PaymentService(registerService);
        securityService = new SecurityService(cardService,registerService);
    }

    public Long getCardByUser(Long l){
        Card card = cardService.getCardByUser(l);
        return card.getCardNumber();
    }

    public void getSumary(Long cardNumber){
        reportService.getSumary(new Card(null,cardNumber));
    }

    public void getPaymentInfoByCard(Long cardNumber){
        paymentService.getPaymentInfoByCard(new Card(null,cardNumber));
    }

}
