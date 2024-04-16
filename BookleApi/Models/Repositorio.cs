
using System;
using System.Collections.Generic;


namespace Repositorio
{
    public interface Repositorio<T, K>
    {

        K Add(T entity);

        void Update(T entity);

        void Delete(T entity);

        T GetById(K id);

        List<T> GetAll();

        List<K> GetIds();
    }

}