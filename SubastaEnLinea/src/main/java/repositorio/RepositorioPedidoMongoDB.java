package repositorio;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
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
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;

import modelo.Pedido;
import utils.PropertiesReader;

@Singleton(name="RepositorioPedidos")
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Startup
public class RepositorioPedidoMongoDB implements Repositorio<Pedido, String>{
	
	private MongoCollection<Pedido> pedidos;
	
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

			pedidos = database.getCollection("pedidos", Pedido.class).withCodecRegistry(defaultCodecRegistry);

		} catch (Exception e) {

		}
	}

	@Override
	@Lock(LockType.READ)
	public String add(Pedido entity) throws RepositorioException {
		InsertOneResult resultado = pedidos.insertOne(entity);
		if(resultado.getInsertedId() == null) {
			throw new RepositorioException("Error creando un nuevo documento");
		}
		return entity.getId();
	}

	@Override
	@Lock(LockType.WRITE)
	public void update(Pedido entity) throws RepositorioException, EntidadNoEncontrada {
		UpdateResult result = pedidos.replaceOne(new Document("_id", new ObjectId(entity.getId())), entity);
		if(result.getMatchedCount()==0) {
			throw new EntidadNoEncontrada("No se ha encontrado el documento a actualizar");
		}
		if(result.getModifiedCount() == 0) {
			throw new RepositorioException("No se ha actualizado el documento");
		}
		
	}

	@Override
	@Lock(LockType.READ)
	public void delete(String id) throws RepositorioException, EntidadNoEncontrada {
		DeleteResult result = pedidos.deleteOne(new Document("_id", new ObjectId(id)));
		if(result.getDeletedCount() == 0) {
			throw new EntidadNoEncontrada("No se ha encontrado el documento a borrar");
		}
		if(!result.wasAcknowledged()) {
			throw new RepositorioException("No se ha borrado el documento");
		}		
	}

	@Override
	@Lock(LockType.READ)
	public Pedido getById(String id) throws RepositorioException, EntidadNoEncontrada {
		Document query = new Document("_id", new ObjectId(id));
		Pedido entity = pedidos.find(query).first();
		
		if (entity == null) {
			throw new EntidadNoEncontrada("Entidad no encontrada para el ID: " + id);
		}

		return entity;
	}

	@Override
	public List<Pedido> getAll() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getIds() throws RepositorioException {
		// TODO Auto-generated method stub
		return null;
	}

}
