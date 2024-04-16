
using System;
using System.Collections.Generic;
using Bookle.Modelo;
using Repositorio;

namespace Bookle.Servicio
{

    public class ActividadResumen
    {

        public String Id { get; set; }
        public String Titulo { get; set; }
        public String Profesor { get; set; }

    }
    public interface IServicioBookle
    {

        string Create(Actividad actividad);

        void Update(Actividad actividad);

        Actividad Get(string id);

        void Remove(string id);

        bool Reservar(string idActividad, DateTime fecha, int indice, string alumno, string email);

        List<ActividadResumen> GetResumenes();
    }

    public class ServicioBookle : IServicioBookle
    {

        private Repositorio<Actividad, String> repositorio;

        public ServicioBookle(Repositorio<Actividad, String> repositorio)
        {

            this.repositorio = repositorio;
        }


        public String Create(Actividad actividad)
        {

            return repositorio.Add(actividad);

        }

        public void Update(Actividad actividad)
        {

            repositorio.Update(actividad);
        }

        public Actividad Get(String id)
        {

            return repositorio.GetById(id);
        }

        public void Remove(String id)
        {

            Actividad actividad = repositorio.GetById(id);

            repositorio.Delete(actividad);
        }

        public bool Reservar(String id, DateTime fecha, int indice, String alumno, String email)
        {
            if (indice < 1)
                throw new ArgumentException("Los índices comienzan en 1");

            if (alumno == null || alumno == "")
                throw new ArgumentException("El nombre del alumno no debe ser vacío");

            // email es opcional

            Actividad actividad = repositorio.GetById(id);

            DiaActividad diaActividad = actividad.Agenda.Find(dia => dia.Fecha.Date == fecha.Date);

            if (diaActividad == null)
                throw new ArgumentException("La fecha no esta en la agenda: " + fecha);

            if (indice > diaActividad.Turnos.Count)
                throw new ArgumentException("No existe el turno " + indice + " para la fecha " + fecha);

            Turno turno = diaActividad.Turnos[indice - 1];

            if (turno.Reserva != null)
                return false;

            turno.Reserva = new Reserva()
            {
                Alumno = alumno,
                Email = email
            };

            repositorio.Update(actividad);

            return true;
        }

        public List<ActividadResumen> GetResumenes()
        {

            var resultado = new List<ActividadResumen>();

            foreach (String id in repositorio.GetIds())
            {

                    var actividad = Get(id);
                    var resumen = new ActividadResumen
                    {
                        Id = actividad.Id,
                        Titulo = actividad.Titulo,
                        Profesor = actividad.Profesor
                    };
                    resultado.Add(resumen);
            }

            return resultado;
        }

    }


}