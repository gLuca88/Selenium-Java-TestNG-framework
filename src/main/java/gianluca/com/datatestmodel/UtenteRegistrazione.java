package gianluca.com.datatestmodel;

public class UtenteRegistrazione {

	// Prima pagina (Signup)
	private String nome; // Name
	private String email; // Email

	// Account Information
	private String sesso;
	private String password;
	private String giornoNascita;
	private String meseNascita;
	private String annoNascita;
	private boolean newsletter;
	private boolean offerteSpeciali;

	// Address Information
	private String cognome;
	private String azienda;
	private String indirizzo1;
	private String indirizzo2;
	private String paese;
	private String stato;
	private String citta;
	private String cap;
	private String telefono;
	private String messaggioConfermaCreazioneAccount;
	private String messaggioConfermaCancellazioneAccount;

	public String getMessaggioConfermaCancellazioneAccount() {
		return messaggioConfermaCancellazioneAccount;
	}

	public void setMessaggioConfermaCancellazioneAccount(String messaggioConfermaCancellazioneAccount) {
		this.messaggioConfermaCancellazioneAccount = messaggioConfermaCancellazioneAccount;
	}

	public UtenteRegistrazione() {
		super();
	}

	// ---- GETTER & SETTER ----

	public String getMessaggioConfermaCreazioneAccount() {
		return messaggioConfermaCreazioneAccount;
	}

	public void setMessaggioConfermaCreazioneAccount(String messaggioConfermaCreazioneAccount) {
		this.messaggioConfermaCreazioneAccount = messaggioConfermaCreazioneAccount;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGiornoNascita() {
		return giornoNascita;
	}

	public void setGiornoNascita(String giornoNascita) {
		this.giornoNascita = giornoNascita;
	}

	public String getMeseNascita() {
		return meseNascita;
	}

	public void setMeseNascita(String meseNascita) {
		this.meseNascita = meseNascita;
	}

	public String getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(String annoNascita) {
		this.annoNascita = annoNascita;
	}

	public boolean isNewsletter() {
		return newsletter;
	}

	public void setNewsletter(boolean newsletter) {
		this.newsletter = newsletter;
	}

	public boolean isOfferteSpeciali() {
		return offerteSpeciali;
	}

	public void setOfferteSpeciali(boolean offerteSpeciali) {
		this.offerteSpeciali = offerteSpeciali;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getAzienda() {
		return azienda;
	}

	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	public String getIndirizzo1() {
		return indirizzo1;
	}

	public void setIndirizzo1(String indirizzo1) {
		this.indirizzo1 = indirizzo1;
	}

	public String getIndirizzo2() {
		return indirizzo2;
	}

	public void setIndirizzo2(String indirizzo2) {
		this.indirizzo2 = indirizzo2;
	}

	public String getPaese() {
		return paese;
	}

	public void setPaese(String paese) {
		this.paese = paese;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}
