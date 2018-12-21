package fr.gaminglab.admin.dao.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.gaminglab.admin.dao.AbstractDAO;
import fr.gaminglab.admin.entities.JeuJouer;

public class DaoJeux extends AbstractDAO  {

	public DaoJeux() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("jeuxJouer");
		setDbCollection(coll);
	}
	
	
	
	public JeuJouer create(JeuJouer jeuJouer) {
		// Construction de l'objet
		Document doc = new Document("idJeu", jeuJouer.getIdJeu())
				.append("idUtilisateur", jeuJouer.getIdUtilisateur())
				.append("moisJouer", jeuJouer.getMoisJouer());
		coll.insertOne(doc);
		return jeuJouer;
	}
}
