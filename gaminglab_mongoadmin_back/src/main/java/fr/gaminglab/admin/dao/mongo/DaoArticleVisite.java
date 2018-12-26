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
import fr.gaminglab.admin.entities.Top;

public class DaoArticleVisite extends AbstractDAO {
	public DaoArticleVisite() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("articlesVisite");
		setDbCollection(coll);
	}
	
	public List<TotalDTO> getTotalArticlesVisite() {
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
	
	public List<Top> getTop5ArticlesVisite() {
		// 	Clé : id de l'Article
		//	Valeur : nombre de fois l'article a été acheté
		
		List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$idArticle', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombre': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top topArt=null;
		    @Override
		    public void apply(final Document document) {
		    	topArt = new Top();
		    	
		    	topArt.setId((Integer)document.get("_id"));
		    	topArt.setNombre((Integer)document.get("nombre"));
				
		    	results.add(topArt);
		    }
		});
		
		return results;
	}
	
	public ArticleVisite create (ArticleVisite articleVisite) {
		// Construction de l'objet
		Document doc = new Document("idArticle", articleVisite.getIdArticle())
				.append("idUtilisateur", articleVisite.getIdUtilisateur())
				.append("dateAchat", articleVisite.getDateVisite());
		coll.insertOne(doc);
		return articleVisite;
	}
}
