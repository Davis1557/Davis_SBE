package tw.com.aitc.SBE.CQRS.service.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDateTime;
import java.util.UUID;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
public class WithdrawMoneyCommand {

	@TargetAggregateIdentifier
	private UUID accountId;

	private long amount;

	private LocalDateTime modifyDT;
}