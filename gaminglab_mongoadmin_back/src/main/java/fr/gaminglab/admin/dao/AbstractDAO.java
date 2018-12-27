package fr.gaminglab.admin.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.Block;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import fr.gaminglab.admin.entities.Top;


public  class AbstractDAO {
    
	protected MongoCollection<Document> coll;
    
    public AbstractDAO() {
    }

    protected void setDbCollection(MongoCollection<Document> coll) {
        this.coll = coll;
    }
    public  MongoCursor<Document> getAll(){
    	MongoCursor<Document> find = coll.find().iterator();
        return find;
    }
    public void insert(Document dbObject){
    	coll.insertOne(dbObject);
    }
    public void remove(Document dbObject){
    	coll.deleteOne(dbObject);
    }
    public void update(Document old,Document dbObject){
    	coll.updateOne(old, dbObject);
    }
    public Document getByID(int id){
    	Document returnObject=null;
    	Document query=new Document();
        query.put("id", new Integer(id));
        returnObject = coll.find(query).first();
        
        return returnObject;
    }
    
    public List<Top> getTop5(String id) {
    	List<Document> operations = new ArrayList<Document>();

		final List<Top> results = new ArrayList<Top>();

		Document group = Document.parse("{$group : { '_id' : '$"+id+"', 'nombre' : {'$sum': 1} } }");
		operations.add(group);
		
		Document sort = Document.parse("{$sort : {'nombre': -1} }");
		operations.add(sort);
		
		Document limit = Document.parse("{$limit : 5}");
		operations.add(limit);

		AggregateIterable<Document> iterable = coll.aggregate(operations);
		
		iterable.forEach(new Block<Document>() {
			Top top=null;
		    @Override
		    public void apply(final Document document) {
		    	top = new Top();
		    	
		    	top.setId((Integer)document.get("_id"));
		    	top.setNombre((Integer)document.get("nombre"));
				
		    	results.add(top);
		    }
		});
		
		return results;
    }
}

