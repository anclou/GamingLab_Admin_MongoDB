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
import fr.gaminglab.admin.entities.JeuJouer;
import fr.gaminglab.admin.entities.Top;

public class DaoJeuJouer extends AbstractDAO {
	
	public DaoJeuJouer() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("jeuxJouer");
		setDbCollection(coll);
	}
	
	public List<TotalDTO> getTotalJeuxJouer() {
		List<Document> operations = new ArrayList<Document>();

		final List<TotalDTO> results = new ArrayList<TotalDTO>();

		Document group = Document.parse("{$group : { '_id' : '$moisJouer', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			TotalDTO totalJeuJouer=null;
		    @Override
		    public void apply(final Document document) {
		    	totalJeuJouer = new TotalDTO();
		    	totalJeuJouer.setMois((Integer)document.get("_id"));
		    	totalJeuJouer.setNombre((Integer)document.get("nombre"));
				results.add(totalJeuJouer);
		    }
		});
		
		return results;
	}
	
	public List<Top> getTop5JeuxJouer() {
		List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$idJeu', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombre': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top topJeu=null;
		    @Override
		    public void apply(final Document document) {
		    	topJeu = new Top();
		    	topJeu.setId((Integer)document.get("_id"));
		    	topJeu.setNombre((Integer)document.get("nombre"));
				results.add(topJeu);
		    }
		});
		
		return results;
	}
	
	public List<Top> getTop5JoueursJouer() {
		List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$idUtilisateur', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombre': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top topJoueur=null;
		    @Override
		    public void apply(final Document document) {
		    	topJoueur = new Top();
		    	topJoueur.setId((Integer)document.get("_id"));
		    	topJoueur.setNombre((Integer)document.get("nombre"));
				results.add(topJoueur);
		    }
		});
		
		return results;
	}
	
	public JeuJouer create(JeuJouer jeuJouer) {
		Document doc = new Document("idJeu", jeuJouer.getIdJeu())
				.append("idUtilisateur", jeuJouer.getIdUtilisateur())
				.append("moisJouer", jeuJouer.getMoisJouer());
		coll.insertOne(doc);
		return jeuJouer;
	}

}
