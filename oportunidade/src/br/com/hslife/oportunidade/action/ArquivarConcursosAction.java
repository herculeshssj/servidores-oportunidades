package br.com.hslife.oportunidade.action;

import java.util.Map;

import org.openxava.actions.IModelAction;
import org.openxava.actions.TabBaseAction;

import br.com.hslife.oportunidade.repository.ConcursoRepository;
import br.com.hslife.oportunidade.repository.OportunidadeRepository;

@SuppressWarnings({"rawtypes","unused"})
public class ArquivarConcursosAction extends TabBaseAction implements IModelAction {

    private String model;
    
    @Override
    public void execute() throws Exception {
        Map[] selectedOnes = getSelectedKeys();
        if (selectedOnes != null) {
            for (int i = 0; i < selectedOnes.length; i++) {
                Map key = selectedOnes[i];
                // Arquiva o concurso
                new ConcursoRepository().arquivarConcurso((String)key.get("id"));
            }
        }

        addMessage("concursos_arquivados_sucesso");
        getTab().deselectAll();
        resetDescriptionsCache();
    }

    @Override
    public void setModel(String modelName) {
        this.model = modelName;        
    }

}
