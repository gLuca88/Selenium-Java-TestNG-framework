package gianluca.com.datatestmodel;

public class UtenteRegistrato {
	
	private String email;
	private String password;
	private String nome;
	private String titoloAtteso;
	
	public UtenteRegistrato() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTitoloAtteso() {
		return titoloAtteso;
	}

	public void setTitoloAtteso(String titoloAtteso) {
		this.titoloAtteso = titoloAtteso;
	}
	

}
