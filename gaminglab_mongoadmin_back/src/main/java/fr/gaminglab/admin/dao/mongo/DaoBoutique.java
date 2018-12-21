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
import fr.gaminglab.admin.entities.ArticleAchat;
import fr.gaminglab.admin.entities.TopArticleAchat;

public class DaoBoutique extends AbstractDAO {
	
	public DaoBoutique() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("articlesAchat");
		setDbCollection(coll);
	}
	
	public List<TopArticleAchat> getTop5ArticlesAchat() {
		// 	Clé : id de l'Article
		//	Valeur : nombre de fois l'article a été acheté
		
		List<Document> operations = new ArrayList<Document>();

		final List<TopArticleAchat> results = new ArrayList<TopArticleAchat>();
		Map<Integer, Integer> mapIdArticleNombreAchat = new HashMap<Integer, Integer>();

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
			TopArticleAchat topArt=null;
		    @Override
		    public void apply(final Document document) {
		    	topArt = new TopArticleAchat();
		    	
		    	Double idArt = (Double)document.get("_id");
		    	topArt.setIdArticle((Integer)idArt.intValue());
		    	topArt.setNombreAchat((Integer)document.get("nombreAchat"));
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
