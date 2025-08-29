package exercicioDois;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        Scanner leitor = new Scanner(System.in);
        
        if (!dao.conectar()) {
            System.out.println("Falha grave na conexão com o banco de dados. Programa encerrado.");
            leitor.close();
            return;
        }

        int escolha = -1;

        while (escolha != 0) {
            System.out.println("\n====== MENU DE OPÇÕES ======");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("0 - Sair do Programa");
            System.out.print("Sua escolha: ");
            
            try {
                escolha = leitor.nextInt();
                leitor.nextLine(); 

                switch (escolha) {
                    case 1:
                        List<Usuario> lista = dao.obterTodos();
                        System.out.println("\n--- LISTA DE USUÁRIOS ---");
                        for (Usuario u : lista) {
                            System.out.println(u);
                        }
                        break;
                    case 2:
                        System.out.println("\n--- INSERIR NOVO USUÁRIO ---");
                        System.out.print("ID: ");
                        int idIns = leitor.nextInt();
                        leitor.nextLine();
                        System.out.print("Nome de usuário: ");
                        String loginIns = leitor.nextLine();
                        System.out.print("Senha: ");
                        String senhaIns = leitor.nextLine();
                        System.out.print("Sexo (M/F): ");
                        String sexoIns = leitor.nextLine();
                        Usuario novo = new Usuario(idIns, loginIns, senhaIns, sexoIns);
                        if(dao.inserirUsuario(novo)) {
                           System.out.println("Usuário inserido com sucesso.");
                        }
                        break;
                    case 3:
                        System.out.println("\n--- ATUALIZAR UM USUÁRIO ---");
                        System.out.print("ID do usuário para atualizar: ");
                        int idAlt = leitor.nextInt();
                        leitor.nextLine();
                        System.out.print("Novo nome de usuário: ");
                        String loginAlt = leitor.nextLine();
                        System.out.print("Nova senha: ");
                        String senhaAlt = leitor.nextLine();
                        System.out.print("Novo sexo (M/F): ");
                        String sexoAlt = leitor.nextLine();
                        Usuario atualizado = new Usuario(idAlt, loginAlt, senhaAlt, sexoAlt);
                        if(dao.atualizarUsuario(atualizado)) {
                            System.out.println("Usuário atualizado com sucesso.");
                        }
                        break;
                    case 4:
                        System.out.println("\n--- EXCLUIR UM USUÁRIO ---");
                        System.out.print("ID do usuário para excluir: ");
                        int idExc = leitor.nextInt();
                        if(dao.excluirUsuario(idExc)) {
                            System.out.println("Usuário excluído com sucesso.");
                        }
                        break;
                    case 0:
                        System.out.println("\nDesconectando e saindo...");
                        break;
                    default:
                        System.out.println("Opção não reconhecida. Tente novamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: por favor, digite um número válido para a opção.");
                leitor.nextLine(); 
            }
        }

        dao.desconectar();
        leitor.close();
    }
}