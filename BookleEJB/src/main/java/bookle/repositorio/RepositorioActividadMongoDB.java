package bookle.repositorio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;

import bookle.modelo.Actividad;
import repositorio.EntidadNoEncontrada;
import repositorio.Repositorio;
import repositorio.RepositorioException;
import utils.PropertiesReader;

@Singleton(name="RepositorioActividad")
@Startup
@Lock(LockType.READ)
public class RepositorioActividadMongoDB implements Repositorio<Actividad, String>{

	private MongoCollection<Actividad> actividades;
	
	@PostConstruct
	public void init() {
		PropertiesReader properties;
		try {
			properties = new PropertiesReader("mongo.properties");

			String connectionString = properties.getProperty("mongouri");

			MongoClient mongoClient = MongoClients.create(connectionString);

			String mongoDatabase = properties.getProperty("mongodatabase");

			MongoDatabase database = mongoClient.getDatabase(mongoDatabase);

			CodecRegistry defaultCodecRegistry = CodecRegistries.fromRegistries(
					MongoClientSettings.getDefaultCodecRegistry(),
					CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

			actividades = database.getCollection("actividades", Actividad.class).withCodecRegistry(defaultCodecRegistry);

		} catch (Exception e) {

		}
		
	}
	
	@Override
	public String add(Actividad entity) throws RepositorioException {
		
		InsertOneResult resultado = actividades.insertOne(entity);
		if(resultado.getInsertedId() == null) {
		
			throw new RepositorioException("Error creando un nuevo documento");
		}
		return entity.getId();
	}

	@Override
	public void update(Actividad entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Actividad entity) throws RepositorioException, EntidadNoEncontrada {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Actividad getById(String id) throws RepositorioException, EntidadNoEncontrada {
		Document query = new Document("_id", new ObjectId(id));
		Actividad entity = actividades.find(query).first();
		if(entity == null) {
			throw new EntidadNoEncontrada("Entidad no encontrada para el id "+id);
		}
		return entity;
	}

	@Override
	public List<Actividad> getAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

}
