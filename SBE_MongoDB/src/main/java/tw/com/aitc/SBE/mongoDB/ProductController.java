package tw.com.aitc.SBE.mongoDB;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	public ProductRepository repository;

	@GetMapping("/listAll")
	public List<Product> listAll() {
		return repository.listAll();
	}

	@PostMapping("/insertAll")
	public Collection<Product> insertAll(@RequestBody List<Product> products) {
		return repository.insertAll(products);
	}
}
