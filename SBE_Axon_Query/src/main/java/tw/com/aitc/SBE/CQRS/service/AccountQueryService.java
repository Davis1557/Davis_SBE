package tw.com.aitc.SBE.CQRS.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tw.com.aitc.SBE.CQRS.repository.BankAccountRepository;
import tw.com.aitc.SBE.CQRS.repository.entity.BankAccount;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor // Lombok
@Service
public class AccountQueryService {
	private final BankAccountRepository repository;

	public List<UUID> findByOwner(String owner) {
		return repository
				.findByOwner(owner)
				.stream()
				.map(BankAccount::getId)
				.collect(Collectors.toList());
	}
}