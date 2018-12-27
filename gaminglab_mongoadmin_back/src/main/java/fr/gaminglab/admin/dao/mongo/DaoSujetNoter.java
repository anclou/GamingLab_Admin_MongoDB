package fr.gaminglab.admin.dao.mongo;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.gaminglab.admin.dao.AbstractDAO;
import fr.gaminglab.admin.entities.SujetNoter;

public class DaoSujetNoter extends AbstractDAO {

	public DaoSujetNoter() {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		MongoDatabase db = mongoClient.getDatabase("gaminglab-admin");
		MongoCollection<Document> coll = db.getCollection("sujetsNoter");
		setDbCollection(coll);
	}
	
	public SujetNoter create(SujetNoter sujetNoter) {
		Document doc = new Document("idSujet", sujetNoter.getIdSujet())
				.append("idUtilisateur", sujetNoter.getIdUtilisateur())
				.append("moisNote", sujetNoter.getMoisNote());
		coll.insertOne(doc);
		return sujetNoter;
	}

	
}
