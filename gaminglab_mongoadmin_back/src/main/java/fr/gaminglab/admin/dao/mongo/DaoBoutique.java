package fr.gaminglab.admin.dao.mongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.gaminglab.admin.dao.AbstractDAO;

public class DaoBoutique extends AbstractDAO {
	
	public DaoBoutique() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("articlesAchat");
		setDbCollection(coll);
	}
	
	public Map<Integer, Integer> getTop5ArticlesAchat() {
		// 	Clé : id de l'Article
		//	Valeur : nombre de fois l'article a été acheté
		
//		List<Document> operations = new ArrayList<Document>();
//
//		final List<CategoryMontant> results = new ArrayList<CategoryMontant>();
//
//		Document groupFields = new Document("_id", "$category");
//		groupFields.put("montant", new Document("$sum", "$price"));
//		Document group = new Document("$group", groupFields);
//		operations.add(group);
//
//		AggregateIterable<Document> iterable = coll.aggregate(operations);
//		
//		iterable.forEach(new Block<Document>() {
//			CategoryMontant cat=null;
//		    @Override
//		    public void apply(final Document document) {
//		    	cat=new CategoryMontant();
//				cat.setCategory(document.get("_id").toString());
//				cat.setMontant(document.get("montant").toString());
//				results.add(cat);
//		    }
//		});
		
		//return results;
		return null;
	}
}
