package br.com.cod3r.facade.callCenter.isolated.facade;

import br.com.cod3r.facade.callCenter.isolated.model.Card;
import br.com.cod3r.facade.callCenter.isolated.model.Register;
import br.com.cod3r.facade.callCenter.isolated.services.*;

import java.util.List;

public class CallCenterNewCardFacade {

    CardService cardService;
    RegisterService registerService;
    ReportService reportService;
    PaymentService paymentService;
    SecurityService securityService;

    public CallCenterNewCardFacade(){
        cardService = new CardService();
        registerService = new RegisterService();
        reportService = new ReportService(registerService);
        paymentService = new PaymentService(registerService);
        securityService = new SecurityService(cardService,registerService);
    }

    public void cancelLastRegister(Long user){
        Card card = cardService.getCardByUser(user);
        List<Register> registers = registerService.getRegistersByCard(card);
        registerService.removeByIndex(card,registers.size()-1);
        List<Register> pedingRegisters = securityService.blockCard(card);
        Card newCard = cardService.createNewCard(123456L,33445566L);
        registerService.addCardRegisters(newCard,pedingRegisters);
        reportService.getSumary(newCard);
    }

    public void orderNewCard(Long user){
        Card card = cardService.getCardByUser(user);
        List<Register> pedingRegisters = securityService.blockCard(card);
        Card newCard = cardService.createNewCard(123456L,33445566L);
        registerService.addCardRegisters(newCard,pedingRegisters);
        reportService.getSumary(newCard);
    }

}
