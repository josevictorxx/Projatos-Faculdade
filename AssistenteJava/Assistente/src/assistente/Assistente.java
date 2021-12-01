package assistente;

import jandl.wizard.WizardBase;
import jandl.wizard.WizardFactory;
import javax.swing.SwingUtilities;
import jandl.wizard.WizardText;
import jandl.wizard.Data;
import java.text.DecimalFormat;

public class Assistente {

   public static void main(String[] args) {
        
        //Criando as janelas
        
        //Janela 1 - Apresentaçao
        WizardBase janela1 = WizardFactory.createBase("Assistente","alunos.png");
        
        //Janela 2 - Cadastrando aluno
            //Criando campos de interação
        String[] tags = {"Nome","Curso","Email","Enem"};
        String[] label = {"Digite seu nome: ","Digite qual curso está se matriculando: ","Digite seu email:","Digite a nota que tirou no ENEM: "};
        String[] tips = {"Seu nome", "Seu curso","Seu email","Nota enem"};
        WizardBase janela2 = WizardFactory.createField("Criando matricula Aluno 1-", tags, label, tips);
        janela2.setImage(".\resources\tux-luke-skywalker\"");
        
        //Janela 3 - Cadastrando aluno
            //Criando campos de interação
        String[] tags1 = {"Nome","Curso","Email","Enem"};
        String[] label1 = {"Digite seu nome: ","Digite qual curso está se matriculando: ","Digite seu email:","Digite a nota que tirou no ENEM: "};
        String[] tips1 = {"Seu nome", "Seu curso","Seu email","Nota enem"};
        WizardBase janela3 = WizardFactory.createField("Criando matricula Aluno 2-", tags1, label1, tips1);
        
        //Janela 4 - Mostrando informação dos alunos
        WizardBase janela4 = WizardFactory.createText("Informções dos alunos","imagem", true);
        
        //Janela 5 - Analisando se cada aluno foi aprovado para fazer matricula
        WizardBase janela5 = WizardFactory.createText("Analisando notas","imagem", true);
        
        //Janela 6 - Final ?
        WizardBase janela6 = WizardFactory.createText("FIM", "imagem", true);
        
        
        //Encadeando janelas
        janela1.nextWizard(janela2).nextWizard(janela3).nextWizard(janela4).nextWizard(janela5).nextWizard(janela6);
        
        //Ativando aplicação
        SwingUtilities.invokeLater(()-> janela1.setVisible(true));
        
        //Pos processamento
        janela2.addPostProcessor((wiz)->janela2PostProcessor(wiz));
        janela3.addPostProcessor((wiz)->janela3PostProcessor(wiz));
        
        //Pre processamento
        janela4.addPreProcessor((wiz)->janela4PreProcessor(wiz));
        janela5.addPreProcessor((wiz)->janela5PreProcessor(wiz));
        janela6.addPreProcessor((wiz)->janela6PreProcessor(wiz));
            
        
    }

    private static void janela2PostProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        String nome = data.getAsString("Wizard2.fieldPane0.Nome");
        String curso = data.getAsString("Wizar2.fieldPane0.Curso");
        String email = data.getAsString("Wizard2.fieldPane0.Email");
        Double nota = data.getAsDouble("Wizard2.fieldPane0.Enem");
        
        //Inserindo no BD
        Conection con = new Conection();
        String sql = "insert into alunos values(default, '" + nome + "', '" + curso + "', '" + email + "', '" + nota + "')";
        int res = con.executaSQL(sql);
        if(res > 0){
            System.out.println("Cadastrado com sucesso!!!");
        }else{
            System.out.println("Erro durante o cadastro!!!");
        }
       
    }
    
    private static void janela3PostProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        String nome = data.getAsString("Wizard3.fieldPane0.Nome");
        String curso = data.getAsString("Wizar3.fieldPane0.Curso");
        String email = data.getAsString("Wizard3.fieldPane0.Email");
        Double nota = data.getAsDouble("Wizard3.fieldPane0.Enem");
        
        //Inserindo no BD
        Conection con = new Conection();
        String sql = "insert into alunos values(default, '" + nome + "', '" + curso + "', '" + email + "', '" + nota + "')";
        int res = con.executaSQL(sql);
        if(res > 0){
            System.out.println("Cadastrado com sucesso!!!");
        }else{
            System.out.println("Erro durante o cadastro!!!");
        }
       
    }

    private static void janela4PreProcessor(WizardBase wizard) {
        
        Data data = Data.instance();
        //Aluno 1
        String nome1 = data.getAsString("Wizard2.fieldPane0.Nome");
        String curso1 = data.getAsString("Wizar2.fieldPane0.Curso");
        String email1 = data.getAsString("Wizard2.fieldPane0.Email");
        Double nota1 = data.getAsDouble("Wizard2.fieldPane0.Enem");
        //Aluno 2
        String nome2 = data.getAsString("Wizard3.fieldPane0.Nome");
        String curso2 = data.getAsString("Wizar3.fieldPane0.Curso");
        String email2 = data.getAsString("Wizard3.fieldPane0.Email");
        Double nota2 = data.getAsDouble("Wizard3.fieldPane0.Enem");
        
        WizardText wizardtext = (WizardText)wizard;
        wizardtext.append("\tInformções dos Alnuos");
        wizardtext.append("\n=============================================");
        wizardtext.append("\nAluno 1");
        wizardtext.append("\nNome: "+data.get("Wizard2.fieldPane0.Nome"));
        wizardtext.append("\nCurso: "+data.get("Wizard2.fieldPane0.Curso"));
        wizardtext.append("\nEmail: "+data.get("Wizard2.fieldPane0.Email"));
        wizardtext.append("\nNota: "+data.get("Wizard2.fieldPane0.Enem"));
        wizardtext.append("\n=============================================");
        wizardtext.append("\nAluno 2");
        wizardtext.append("\nNome: "+data.get("Wizard3.fieldPane0.Nome"));
        wizardtext.append("\nCurso: "+data.get("Wizard3.fieldPane0.Curso"));
        wizardtext.append("\nEmail: "+data.get("Wizard3.fieldPane0.Email"));
        wizardtext.append("\nNota: "+data.get("Wizard3.fieldPane0.Enem"));
    }

    private static void janela5PreProcessor(WizardBase wiz) {
        
        Data data = Data.instance();
        
        WizardText wizardtext = (WizardText)wiz;
        wizardtext.append("\n=============================================");
        wizardtext.append("\t\tUNIP");
        wizardtext.append("\n \tNota Enem >= 750 pontos");
        wizardtext.append("\n=============================================");
        
        //Verificando se aluno1 foi aprovado!
        wizardtext.append("\nALUNO 1: "+data.get("Wizard2.fieldPane0.Nome"));
        wizardtext.append("\nNota enem foi: "+data.get("Wizard2.fieldPane0.Enem"));
        Double notaAluno1 = data.getAsDouble("Wizard2.fieldPane0.Enem");
        if(notaAluno1 >= 750)
            wizardtext.append("\nVOCÊ FOI APROVADO");
        else
            wizardtext.append("\nVOCÊ NÃO FOI APROVADO! \nTENTE DE NOVO ANO QUE VEM");
        
        wizardtext.append("\n=============================================");
        
        //Verificando se aluno2 foi aprovado!
        wizardtext.append("\nALUNO 1: "+data.get("Wizard3.fieldPane0.Nome"));
        wizardtext.append("\nNota enem foi: "+data.get("Wizard3.fieldPane0.Enem"));
        Double notaAluno2 = data.getAsDouble("Wizard3.fieldPane0.Enem");
        if(notaAluno2 >= 750)
            wizardtext.append("\nVOCÊ FOI APROVADO");
        else
            wizardtext.append("\nVOCÊ NÃO FOI APROVADO! \nTENTE DE NOVO ANO QUE VEM");
    }

    private static void janela6PreProcessor(WizardBase wiz) {
        Data data = Data.instance();
        WizardText wizardtext = (WizardText)wiz;
        
        wizardtext.append("\n=============================================");
        wizardtext.append("\n\tALUNOS APROVADOS!");
        wizardtext.append("\n=============================================");
        Double notaAluno1 = data.getAsDouble("Wizard2.fieldPane0.Enem");
        Double notaAluno2 = data.getAsDouble("Wizard3.fieldPane0.Enem");
        
        if(notaAluno1 >= 750)
            wizardtext.append("\n"+data.getAsString("Wizard2.fieldPane0.Nome"));
        
        if(notaAluno2 >= 750)
            wizardtext.append("\n"+data.getAsString("Wizard3.fieldPane0.Nome"));
        
        wizardtext.append("\n=============================================");
        wizardtext.append("\nPARABENS A TODOS OS ALUNOS APROVADOS! \nVOCES FORAM OS MELHORES ESSE ANO! \nSEJAM MUITO BEM VINDOS");
    }
    
}
