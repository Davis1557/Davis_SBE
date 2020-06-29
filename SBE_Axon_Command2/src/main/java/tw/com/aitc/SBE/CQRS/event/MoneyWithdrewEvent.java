package tw.com.aitc.SBE.CQRS.event;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value  // Lombok
public class MoneyWithdrewEvent {
	UUID id;
	long amount;
	LocalDateTime modifyDT;
}