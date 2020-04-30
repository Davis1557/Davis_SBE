package tw.com.aitc.SBE.mongoDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ProductRepository {

	@Autowired
	public MongoTemplate template;

	public List<Product> listAll() {
		return template.findAll(Product.class);
	}

	public Collection<Product> insertAll(List<Product> products) {
		// insert : 衝突時略過
		// save   : 衝突時更新
		return template.insert(products, Product.class);
	}
}
