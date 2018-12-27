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
import fr.gaminglab.admin.entities.ArticleVisite;
import fr.gaminglab.admin.entities.SujetCommenter;
import fr.gaminglab.admin.entities.Top;

public class DaoSujetCommenter extends AbstractDAO {

	public DaoSujetCommenter() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("sujetsCommenter");
		setDbCollection(coll);
	}
	
	public List<TotalDTO> getTotalSujetCommenter() {
		List<Document> operations = new ArrayList<Document>();

		final List<TotalDTO> results = new ArrayList<TotalDTO>();

		Document group = Document.parse("{$group : { '_id' : '$moisCommentaire', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			TotalDTO totalSujet=null;
		    @Override
		    public void apply(final Document document) {
		    	totalSujet = new TotalDTO();
		    	totalSujet.setMois((Integer)document.get("_id"));
		    	totalSujet.setNombre((Integer)document.get("nombre"));
				results.add(totalSujet);
		    }
		});
		
		return results;
	}
	
//	public List<Top> getTop5SujetsCommenter() {
//		List<Document> operations = new ArrayList<Document>();
//
//		final List<Top> results = new ArrayList<Top>();
//
//		Document group = Document.parse("{$group : { '_id' : '$idSujet', 'nombre' : {'$sum': 1} } }");
//		operations.add(group);
//		
//		Document sort = Document.parse("{$sort : {'nombre': -1} }");
//		operations.add(sort);
//		
//		Document limit = Document.parse("{$limit : 5}");
//		operations.add(limit);
//
//		AggregateIterable<Document> iterable = coll.aggregate(operations);
//		
//		iterable.forEach(new Block<Document>() {
//			Top topSujet=null;
//		    @Override
//		    public void apply(final Document document) {
//		    	topSujet = new Top();
//		    	
//		    	topSujet.setId((Integer)document.get("_id"));
//		    	topSujet.setNombre((Integer)document.get("nombre"));
//				
//		    	results.add(topSujet);
//		    }
//		});
//		
//		return results;
//	}
	
//	public List<Top> getTop5JoueursCommenter() {
//		List<Document> operations = new ArrayList<Document>();
//
//		final List<Top> results = new ArrayList<Top>();
//
//		Document group = Document.parse("{$group : { '_id' : '$idUtilisateur', 'nombre' : {'$sum': 1} } }");
//		operations.add(group);
//		
//		Document sort = Document.parse("{$sort : {'nombre': -1} }");
//		operations.add(sort);
//		
//		Document limit = Document.parse("{$limit : 5}");
//		operations.add(limit);
//
//		AggregateIterable<Document> iterable = coll.aggregate(operations);
//		
//		iterable.forEach(new Block<Document>() {
//			Top topSujet=null;
//		    @Override
//		    public void apply(final Document document) {
//		    	topSujet = new Top();
//		    	
//		    	topSujet.setId((Integer)document.get("_id"));
//		    	topSujet.setNombre((Integer)document.get("nombre"));
//				
//		    	results.add(topSujet);
//		    }
//		});
//		
//		return results;
//	}
	
	public SujetCommenter create (SujetCommenter sujetCommenter) {
		Document doc = new Document("idSujet", sujetCommenter.getIdSujet())
				.append("idUtilisateur", sujetCommenter.getIdUtilisateur())
				.append("moisCommentaire", sujetCommenter.getMoisCommentaire());
		coll.insertOne(doc);
		
		return sujetCommenter;
	}
}
