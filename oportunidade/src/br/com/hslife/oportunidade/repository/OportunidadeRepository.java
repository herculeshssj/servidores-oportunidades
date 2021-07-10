package br.com.hslife.oportunidade.repository;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

import org.openxava.jpa.XPersistence;
import org.springframework.stereotype.Repository;

import br.com.hslife.oportunidade.model.Oportunidade;

@Repository
public class OportunidadeRepository {
    
    public void salvarOportunidades(List<Oportunidade> oportunidades) throws Exception {
        oportunidades.forEach(oportunidade -> {

            try {

                // Caso não seja lançado uma exceção, significa que a oportunidade já foi cadastrada
                XPersistence.getManager()
                    .createQuery("SELECT o FROM Oportunidade o WHERE o.hash = :hash", Oportunidade.class)
                    .setParameter("hash", oportunidade.getHash())
                    .getSingleResult();

            } catch (NoResultException nr) {

                // Exceção lançada, efetua o cadastro da oportunidade
                XPersistence.getManager().persist(oportunidade);

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
