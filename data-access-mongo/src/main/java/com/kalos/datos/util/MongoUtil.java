package com.kalos.datos.util;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class MongoUtil {

	public static MongoDatabase getDatabase() {

		MongoClient mongoClient = new MongoClient(new MongoClientURI(
				"mongodb://localhost"));
		MongoDatabase kalosDatabase = mongoClient.getDatabase("kalos");
		return kalosDatabase;

	}
	
	
}
