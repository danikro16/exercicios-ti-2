package exercicioDois;

public class Usuario {
    private int id;
    private String nomeUsuario;
    private String senha;
    private String sexo;

    public Usuario() {}

    public Usuario(int id, String nomeUsuario, String senha, String sexo) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.sexo = sexo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNomeUsuario() { return nomeUsuario; }
    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", senha='" + "********" + '\'' +
                ", sexo='" + sexo + '\'' +
                '}';
    }
}