package fr.gaminglab.admin.dao.mongo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.gaminglab.admin.dao.AbstractDAO;
import fr.gaminglab.admin.dto.TotalDTO;
import fr.gaminglab.admin.entities.ArticleAchat;
import fr.gaminglab.admin.entities.Top;

public class DaoArticleAchat extends AbstractDAO {
	
	public DaoArticleAchat() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("articlesAchat");
		setDbCollection(coll);
	}
	
	public List<TotalDTO> getTotalArticlesAchat() {
		List<Document> operations = new ArrayList<Document>();

		final List<TotalDTO> results = new ArrayList<TotalDTO>();

		Document group = Document.parse("{$group : { '_id' : '$dateAchat', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			TotalDTO totalArtAchat=null;
		    @Override
		    public void apply(final Document document) {
		    	totalArtAchat = new TotalDTO();
		    	totalArtAchat.setMois((Integer)document.get("_id"));
		    	totalArtAchat.setNombre((Integer)document.get("nombre"));
				results.add(totalArtAchat);
		    }
		});
		
		return results;
	}
	
	public List<Top> getTop5ArticlesAchat() {
		// 	Clé : id de l'Article
		//	Valeur : nombre de fois l'article a été acheté
		
		List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$idArticle', 'nombreAchat' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombreAchat': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);
		
		// Autre méthode
//		Document groupFields = new Document("_id", "$idArticle");
//		groupFields.put("nombreAchat", new Document("$sum", 1));	
//		
//		Document group = new Document("$group", groupFields);
//		operations.add(group);
//		
//		Document sort = new Document("$sort", new Document("nombreAchat", -1));
//		operations.add(sort);
//		
//		Document limit = new Document("$limit", 5);
//		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top topArt=null;
		    @Override
		    public void apply(final Document document) {
		    	topArt = new Top();
		    	//System.out.println("_id : "+document.get("_id").getClass());
		    	//Double idArt = (Double)document.get("_id");
		    	topArt.setId((Integer)document.get("_id"));
		    	topArt.setNombre((Integer)document.get("nombreAchat"));
				results.add(topArt);
		    }
		});
		
		return results;
	}
	
	public ArticleAchat create(ArticleAchat articleAchat) {
		// Construction de l'objet
		Document doc = new Document("idArticle", articleAchat.getIdArticle())
				.append("idUtilisateur", articleAchat.getIdUtilisateur())
				.append("dateAchat", articleAchat.getDateAchat());
		coll.insertOne(doc);
		return articleAchat;
	}
}
