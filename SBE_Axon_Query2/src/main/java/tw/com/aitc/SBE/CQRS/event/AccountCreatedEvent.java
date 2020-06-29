package tw.com.aitc.SBE.CQRS.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor // Lombok
@Data               // Lombok
public class AccountCreatedEvent {
	private final UUID id;
	private String owner;
	private long initialBalance;
	private LocalDateTime createDT;
	private LocalDateTime modifyDT;
}