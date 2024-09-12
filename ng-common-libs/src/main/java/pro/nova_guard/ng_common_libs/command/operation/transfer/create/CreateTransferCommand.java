package pro.nova_guard.ng_common_libs.command.operation.transfer.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransferCommand {
    @TargetAggregateIdentifier
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
}
