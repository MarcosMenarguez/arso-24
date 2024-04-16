
using Repositorio;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using MongoDB.Bson;
using Bookle.Modelo;

namespace Bookle.Repositorio
{


    public class RepositorioActividadesMongoDB : Repositorio<Actividad, string>
    {
        private readonly IMongoCollection<Actividad> actividades;

        public RepositorioActividadesMongoDB()
        {
            var client = new MongoClient("uri");
            var database = client.GetDatabase("arso");

            actividades = database.GetCollection<Actividad>("actividades.net");
        }

        public string Add(Actividad entity)
        {
            actividades.InsertOne(entity);

            return entity.Id;
        }

        public void Update(Actividad entity)
        {
            actividades.ReplaceOne(actividad => actividad.Id == entity.Id, entity);
        }

        public void Delete(Actividad entity)
        {
            actividades.DeleteOne(actividad => actividad.Id == entity.Id);
        }

        public List<Actividad> GetAll()
        {
            return actividades.Find(_ => true).ToList();
        }

        public Actividad GetById(string id)
        {
            return actividades
                .Find(actividad => actividad.Id == id)
                .FirstOrDefault();
        }

        public List<string> GetIds()
        {
            var todas =  actividades.Find(_ => true).ToList();

            return todas.Select(p => p.Id).ToList();

        }
    }
}