package br.com.hslife.oportunidade.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.openxava.jpa.XPersistence;
import org.springframework.stereotype.Repository;

import br.com.hslife.oportunidade.model.Concurso;

@Repository
public class ConcursoRepository {
 
    public void salvarConcursos(List<Concurso> concursos) throws Exception {
        concursos.forEach(concurso -> {

            try {

                // Caso não seja lançado uma exceção, significa que o concurso já foi cadastrado
                XPersistence.getManager()
                    .createQuery("SELECT c FROM Concurso c WHERE c.hash = :hash", Concurso.class)
                    .setParameter("hash", concurso.getHash())
                    .getSingleResult();

            } catch (NoResultException nr) {

                // Exceção lançada, efetua o cadastro do concurso
                XPersistence.getManager().persist(concurso);

            } catch (NonUniqueResultException nur) {
                // Não faz nada
                // TODO ver a possibilidade de exclui as entradas repetidas.
            } catch (Exception e) {
                // Lança a exceção para a camada de serviço
                throw e;
            } finally {
                XPersistence.commit();
            }

        });
    }
}
