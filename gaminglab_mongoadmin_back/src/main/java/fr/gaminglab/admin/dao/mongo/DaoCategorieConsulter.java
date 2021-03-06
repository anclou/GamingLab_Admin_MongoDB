package fr.gaminglab.admin.dao.mongo;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.gaminglab.admin.dao.AbstractDAO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.CategorieConsulter;
import fr.gaminglab.admin.entities.Top;

public class DaoCategorieConsulter extends AbstractDAO {
	
	public DaoCategorieConsulter() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("categoriesConsulter");
		setDbCollection(coll);
	}
	
	public List<TotalDTO> getTotalCategoriesConsulter() {
		List<Document> operations = new ArrayList<Document>();

		final List<TotalDTO> results = new ArrayList<TotalDTO>();

		Document group = Document.parse("{$group : { '_id' : '$moisConsulter', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			TotalDTO totalCategorieConsulter = null;
		    @Override
		    public void apply(final Document document) {
		    	totalCategorieConsulter = new TotalDTO();
		    	totalCategorieConsulter.setMois((Integer)document.get("_id"));
		    	totalCategorieConsulter.setNombre((Integer)document.get("nombre"));
				results.add(totalCategorieConsulter);
		    }
		});
		
		return results;
	}
	
	public List<Top> getTop5CategoriesConsulter() {
		List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$idCategorie', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombre': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top topCat = null;
		    @Override
		    public void apply(final Document document) {
		    	topCat = new Top();
		    	
		    	topCat.setId((Integer)document.get("_id"));
		    	topCat.setNombre((Integer)document.get("nombre"));
				
		    	results.add(topCat);
		    }
		});
		
		return results;
		
	}
	
	public CategorieConsulter create(CategorieConsulter categorieConsulter) {
		Document doc = new Document("idCategorie", categorieConsulter.getIdCategorie())
				.append("idUtilisateur", categorieConsulter.getIdUtilisateur())
				.append("moisConsulter", categorieConsulter.getMoisConsulter());
		coll.insertOne(doc);
		return categorieConsulter;
	}

	
}
