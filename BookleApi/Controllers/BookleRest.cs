using Bookle.Modelo;
using Bookle.Servicio;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;

namespace BookleApi.Controllers
{
    [Route("api/actividades")]
    [ApiController]
    public class ActividadesController : ControllerBase
    {
        private readonly IServicioBookle _servicio;

        public ActividadesController(IServicioBookle servicio)
        {
            _servicio = servicio;
        }

        [HttpGet]
        public ActionResult<List<ActividadResumen>> Get() =>
            _servicio.GetResumenes();

        [HttpGet("{id}", Name = "GetActividad")]
        public ActionResult<Actividad> Get(string id)
        {
            var entidad = _servicio.Get(id);

            if (entidad == null)
            {
                return NotFound();
            }

            return entidad;
        }

        [HttpPost]
        public ActionResult<Actividad> Create(Actividad actividad)
        {
            _servicio.Create(actividad);

            return CreatedAtRoute("GetActividad", new { id = actividad.Id }, actividad);
        }

        [HttpPut("{id}")]
        public IActionResult Update(string id, Actividad actividad)
        {
            var entidad = _servicio.Get(id);

            if (entidad == null)
            {
                return NotFound();
            }

            _servicio.Update(actividad);

            return NoContent();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(string id)
        {
            var actividad = _servicio.Get(id);

            if (actividad == null)
            {
                return NotFound();
            }

            _servicio.Remove(id);

            return NoContent();
        }

        [HttpPost("{id}/agenda/{fecha}/turno/{indice}/reserva")]
        public IActionResult Reservar(string id, DateTime fecha, int indice, [FromForm] string alumno, [FromForm] string email)
        {
            var actividad = _servicio.Get(id);

            if (actividad == null)
            {
                return NotFound();
            }
            
            _servicio.Reservar(id, fecha, indice, alumno, email);

            return NoContent();
        }
    }
}
