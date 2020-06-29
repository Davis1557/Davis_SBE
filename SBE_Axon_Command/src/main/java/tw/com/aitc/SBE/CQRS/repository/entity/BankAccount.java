package tw.com.aitc.SBE.CQRS.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data               // Lombok
@NoArgsConstructor  // Lombok
@AllArgsConstructor // Lombok
@Entity             // JPA
//@Document           // MongoDB
public class BankAccount {
	@Id
	private UUID id;
	private String owner;
	private long balance;
	private LocalDateTime createDT;
	private LocalDateTime modifyDT;
}