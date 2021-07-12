package br.com.hslife.oportunidade.action;

import java.util.Map;

import org.openxava.actions.IModelAction;
import org.openxava.actions.TabBaseAction;

import br.com.hslife.oportunidade.repository.OportunidadeRepository;

@SuppressWarnings({"rawtypes","unused"})
public class ArquivarOportunidadesAction extends TabBaseAction implements IModelAction {

    private String model;
    
    @Override
    public void execute() throws Exception {
        Map[] selectedOnes = getSelectedKeys();
        if (selectedOnes != null) {
            for (int i = 0; i < selectedOnes.length; i++) {
                Map key = selectedOnes[i];
                // Arquiva a oportunidade
                new OportunidadeRepository().arquivarOportunidade((String)key.get("id"));
            }
        }

        addMessage("oportunidades_arquivadas_sucesso");
        getTab().deselectAll();
        resetDescriptionsCache();
    }

    @Override
    public void setModel(String modelName) {
        this.model = modelName;        
    }

}
