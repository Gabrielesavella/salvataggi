package persone;

public class Cliente {
    private String username,email,Psw,nome,cognome;


    public Cliente(String nome, String cognome, String username, String email, String Psw) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.Psw=Psw;

    }

    public Cliente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }




    public void setUsername(String username) {
        this.username = username;
    }

    public void setPsw(String psw) {
        Psw = psw;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getUsername() {
        return username;
    }

    public String getPsw() {
        return Psw;
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }


}

