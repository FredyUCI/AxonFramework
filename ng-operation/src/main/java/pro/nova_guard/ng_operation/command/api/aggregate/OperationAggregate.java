package pro.nova_guard.ng_operation.command.api.aggregate;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;
import pro.nova_guard.ng_common_libs.command.operation.transfer.create.CreateTransferCommand;
import pro.nova_guard.ng_common_libs.event.transfer.create.CreateTransferEvent;

import java.math.BigDecimal;

@Aggregate
@Data
@NoArgsConstructor
@Slf4j
public class OperationAggregate {
    @AggregateIdentifier
    private String transferId;
    private Integer transaction_type;
    private BigDecimal amount;
    private String account_holder;
    private String user_name;
    private String intermediary_bank;
    private String description;
    private String from_account_number;
    private String to_account_number;
    private Integer banking_expenses;
    private Long from_client_id;
    private Long to_client_id;
    private Long from_account_id;
    private Long to_account_id;
    private String currency;
    private String swift_code;
    private String destination_currency;

    @CommandHandler(commandName = "operation")
    public void handle(CreateTransferCommand createTransferCommand) {
        CreateTransferEvent createTransferEvent = new CreateTransferEvent();
        BeanUtils.copyProperties(createTransferCommand, createTransferEvent);
        AggregateLifecycle.apply(createTransferEvent);
    }
    @EventSourcingHandler
    public void on(CreateTransferEvent event) {
        this.transferId = event.getTransferId();
        this.transaction_type = event.getTransaction_type();
        this.amount = event.getAmount();
        this.account_holder = event.getAccount_holder();
        this.user_name = event.getUser_name();
        this.intermediary_bank = event.getIntermediary_bank();
        this.description = event.getDescription();
        this.from_account_number = event.getFrom_account_number();
        this.to_account_number = event.getTo_account_number();
        this.banking_expenses = event.getBanking_expenses();
        this.from_client_id = event.getFrom_client_id();
        this.to_client_id = event.getTo_client_id();
        this.from_account_id = event.getFrom_account_id();
        this.to_account_id = event.getTo_account_id();
        this.currency = event.getCurrency();
        this.swift_code = event.getSwift_code();
        this.destination_currency = event.getDestination_currency();
    }
}
