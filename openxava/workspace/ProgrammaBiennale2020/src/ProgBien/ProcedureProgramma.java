package ProgBien;

import java.math.*;
import java.util.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;

import org.hibernate.annotations.*;
import org.hibernate.envers.*;
import org.openxava.annotations.*;
import org.openxava.util.*;

import Calculators.*;
import Filters.*;
import ProgBien.Enumerators.*;
import actions.*;
import util.*;

@Entity
@Views({
@View(
	members="stato; descrizione; ultimopianoapprovato; TipoOperazione [variante; descrizioneVariante; cancellazione; notenonriproposta]; servizi; anno0; data; cup; Ricompreso[ricompreso; cuiRicompreso; cuiRicompresoLavori]; LottoFunzionale[lotto, nolotto];"
			+ "ambitogeografico; cupmaster; CPV [settore; divisione; codiceLivello1; cpv]; Aggregabile[aggregabile, nonaggregabile; noteaggregabile]; dl662014; pdc;"
			+ "Verdi [verdi, noVerdi; riferimentoNormativoVerdi; oggettoAcquistiVerdi, cpvAcquistiVerdi; importoNettoAcquistiVerdi, aliquotaIvaAcquistiVerdi]; MaterialiRiciclati[oggettoAcquistiMaterialiRiciclati, cpvAcquistiMaterialiRiciclati; importoNettoAcquistiMaterialiRiciclati, aliquotaIvaAcquistiMaterialiRiciclati]; Priorita[priorita; prioritamotivation;]; dipendenti; quantita; umisura; durata;"
			+ "nuovoAffidamentoDiContrattoInEssere[ affidamentoContrattoInEssere, nonAffidamentoContrattoInEssere; ]; affidamentoAdUnicoOperatoreExArt[ affidamentoExArt63, nonAffidamentoExArt63; ]; Delega[delega, nondelega; ausa]; QuadroEconomico[quadroeconomico; valoreStimatoAppalto, importoBaseAsta, sommeADisposizione, totaleImposte; costiComplessivi, costiA1, costiA2, costiAs; totaleIvaQuadroEconomico, totaleQuadroEconomico; Incentivi [fondoenable, fondoArt113Totale, programenable, gdl113Program, quotacollaudoenable, gdl113Collaudo; quotainnovazioneenable, quotaInnovazioneTotale, affidaenable, gdl113Affida; quotagdlenable, gdl113Totale, execenable, gdl113Exec;]];"
			+ "progettiict; Coperture[coperture; coperturericomprese; costipregressi, totaleCoperture, costoTotale;]; note;" // Shows only number and name in the same line
),
@View(name="Simple", // This view is used only when �Simple� is specified
members="anno0, descrizione" // Shows only number and name in the same line
),
@View(name="Ticket", members="anno0, cui, descrizione")
,
@View(name="Accorpamento", members="anno0, cui, descrizione, costicomplessivi")
,
@View(name="Custom", members="anno0, cui, descrizione; programenable, gdl113Program; affidaenable, gdl113Affida; execenable, gdl113Exec; quotagdlenable, gdl113Totale;")
,
@View(name="Archive", members="stato; descrizione; ultimopianoapprovato; TipoOperazione [variante; descrizioneVariante; cancellazione; notenonriproposta]; servizi; anno0; data; cup; Ricompreso[ricompreso; cuiRicompreso; cuiRicompresoLavori]; LottoFunzionale[lotto, nolotto];"
		+ "ambitogeografico; cupmaster; CPV [settore; divisione; codiceLivello1; cpv]; Aggregabile[aggregabile, nonaggregabile; noteaggregabile]; dl662014; pdc;"
		+ "Verdi [verdi, noVerdi; riferimentoNormativoVerdi; oggettoAcquistiVerdi, cpvAcquistiVerdi; importoNettoAcquistiVerdi, aliquotaIvaAcquistiVerdi]; MaterialiRiciclati[oggettoAcquistiMaterialiRiciclati, cpvAcquistiMaterialiRiciclati; importoNettoAcquistiMaterialiRiciclati, aliquotaIvaAcquistiMaterialiRiciclati]; Priorita[priorita; prioritamotivation;]; dipendenti; quantita; umisura; durata;"
		+ "nuovoAffidamentoDiContrattoInEssere[ affidamentoContrattoInEssere, nonAffidamentoContrattoInEssere; ]; affidamentoAdUnicoOperatoreExArt[ affidamentoExArt63, nonAffidamentoExArt63; ]; Delega[delega, nondelega; ausa]; QuadroEconomico[quadroeconomico; valoreStimatoAppalto, importoBaseAsta, sommeADisposizione, totaleImposte; costiComplessivi, costiA1, costiA2, costiAs; totaleIvaQuadroEconomico, totaleQuadroEconomico; Incentivi [fondoenable, fondoArt113Totale, programenable, gdl113Program, quotacollaudoenable, gdl113Collaudo; quotainnovazioneenable, quotaInnovazioneTotale, affidaenable, gdl113Affida; quotagdlenable, gdl113Totale, execenable, gdl113Exec;]];"
		+ "progettiict; Coperture[coperture; coperturericomprese; costipregressi, totaleCoperture, costoTotale;]; note;")
})
@Tabs({
	@Tab(
			//name="Current",
			filter=CurrentUserServizioStrutturaRupFilter.class,
			editors ="List",
			//properties="servizio, struttura, descrizione, codicesicurezza, datainizio, datafine, nstruttura, nservizio, inquadrato",
			baseCondition="${deleted} = false and ${archived} = false and (? = 1 or ${servizi.servizio} in (?)) and (? = 1 or ${servizi.struttura} in (?)) and ${dipendenti.cf} like ?",
			properties="stato.descrizione, servizi.servizio, servizi.struttura, dipendenti.cf, dipendenti.cognome," + 
					"anno0, descrizione, valorestimatoappalto, importobaseasta, totalequadroeconomico, totaleivaquadroeconomico, costipregressi, totalecoperture, costototale"
		),
	@Tab(name="Deleted", baseCondition = "${deleted} = true") // A named tab
	,
	@Tab(name="Archived", baseCondition = "${archived} = true and ${deleted} = false") // A named tab
})
@Audited
public class ProcedureProgramma extends Deletable {
	
	@ReadOnly
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
	@DefaultValueCalculator(value=DefaultStatoProgettoCalculator.class)
    @DescriptionsList(
    		descriptionProperties="key, descrizione"
    		) // Thus the reference is displayed using a combo
    private StatoProgetto stato;
	
	/*
	@Id
    @ReadOnly
    @DefaultValueCalculator(value=UUIDCalculator.class)
    private String oid;
    */
	
	@Id
    @Hidden // The property is not shown to the user. It's an internal identifier
    @GeneratedValue(generator="system-uuid") // Universally Unique Identifier (1)
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(length=32)
    private String oid;
	
	@Required
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne
	@ReferenceView("Simple")
	@JoinColumns({
     @JoinColumn(
	           name = "Id_servizio",
	           referencedColumnName = "oid"),
	 @JoinColumn(
	           name = "Servizio",
	           referencedColumnName = "servizio"),
	 @JoinColumn(
	           name = "Struttura",
	         referencedColumnName = "struttura")
	    })
    private Servizi servizi; // A regular Java reference
	
	@Required
	@ManyToOne
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ReferenceView("Simple")
	@JoinColumns({
	 @JoinColumn(
	           name = "CF",
	           referencedColumnName = "cf"),
	 @JoinColumn(
	           name = "Nome",
	         referencedColumnName = "nome"),
	 @JoinColumn(
	           name = "Cognome",
	         referencedColumnName = "cognome")
	    })
    private Dipendenti dipendenti; // A regular Java reference
	
	@Required
    @Column(name = "Descrizione", length = 256, nullable = false)
    private String descrizione;

    @Required
    @ReadOnly
    @Column(name = "Anno0", updatable=false, nullable = false)
    @DefaultValueCalculator(value=CurrentYearCalculatorProgramma.class)
    private Integer anno0;
    
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ReadOnly
	@ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
	@SearchListCondition("${stato.key} not like 'K' and ${stato.key} not like 'A' and ${stato.key} not like 'M'")
	@ReferenceView("Simple")
    private ProcedureDefinitive ultimopianoapprovato;
	
    /*
    @ReadOnly
    @DefaultValueCalculator(value=CuiTmpCalculator.class)
    private String cui;
	*/
    
    @Hidden
    private String cui;

    @OnChange(UpdateCopertureProgramma.class)
	@Required
	@Column(name = "Data", nullable = true)
    private Date data;

    @Column(name = "CUP", length = 15, nullable = true)
    private String cup;
    
    @Required
    @Column(name = "Ricompreso", length = 1, nullable = true)
    @org.hibernate.annotations.Type(type="org.openxava.types.EnumLetterType",
    	    parameters={
        		@org.hibernate.annotations.Parameter(name="letters", value="NCWILS")
        		,@org.hibernate.annotations.Parameter(name="enumType", value="ProgBien.Enumerators$Ricompreso")
    	    }
    	)
    private Ricompreso ricompreso;   
    
    @ManyToOne( // The reference is persisted as a database relationship
            fetch=FetchType.LAZY, // The reference is loaded on demand
            optional=true) // The reference can have no value
    @DescriptionsList(
    		descriptionProperties="cui, descrizione",
    		depends="this.ricompreso",
    		condition="? = 'S'") // Thus the reference is displayed using a combo
    private ProcedureDefinitive cuiRicompreso; // A regular Java reference
	
	@Column(name = "CuiRicompresoLavori", length = 25, nullable = true)
    private String cuiRicompresoLavori;	
	
	public void saveTotali() throws Exception {
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(data);
		
		BigDecimal tot = getGdl113Totale();
	    BigDecimal affida = tot.multiply(soglia.getPercentAffidamento()).setScale(2, RoundingMode.UP);
	    BigDecimal exec = tot.multiply(soglia.getPercentEsecuzione()).setScale(2, RoundingMode.UP);
	    BigDecimal program = tot.multiply(soglia.getPercentProgrammazione()).setScale(2, RoundingMode.UP);
	    BigDecimal innovazione = getQuotaInnovazioneTotale().setScale(2, RoundingMode.UP);
	    BigDecimal fondo113 = getFondoArt113Totale().setScale(2, RoundingMode.UP);
	    
	    setCostitotali(getCostiTot());
		setGdl113totale(tot);
		setGdl113program(program);
		setGdl113affida(affida);
		setGdl113exec(exec);
		setQuotaInnovazione(innovazione);
		setFondoart113(fondo113);
		
		setCostia1(getCostiA1());
		setCostia2(getCostiA2());
		setCostias(getCostiAs());
		setValorestimatoappalto(getValoreStimatoAppalto());
		setImportobaseasta(getImportoBaseAsta());
		setSommeadisposizione(getSommeADisposizione());
		setTotaleimposte(getTotaleImposte());
		setCosticomplessivi(getCostiComplessivi());
		setTotaleivaquadroeconomico(getTotaleIvaQuadroEconomico());
		setTotalequadroeconomico(getTotaleQuadroEconomico());
		
		if (coperture != null && coperture.size() > 0)
		{
			setTotalecoperture(getTotaleCoperture());
			setCostototale(getCostoTotale());
		}
		
	}
	
	@PrePersist // Just before inserting first time
	private void syncTotali() throws Exception {
		
		if (!util.ProgBienUtils.ControllaFase(Calendar.getInstance().get(Calendar.YEAR), new Date(), "C") && !util.ProgBienUtils.ControllaFaseStraordinaria(Calendar.getInstance().get(Calendar.YEAR), new Date(),Users.getCurrent(), "C"))
		{
			throw new javax.validation.ValidationException(
		            XavaResources.getString(
		                "not_allowed_in_this_fase"
		            )
		        );
		}
		
		/*
		if (modifica && variante == null && !Users.getCurrent().equals("admin")) {
			throw new javax.validation.ValidationException(
		            XavaResources.getString(
		                "specify_edit_motivation"
		            )
		        );
		}
		*/
		
		if (anno0 == 0 || anno0 > (Calendar.getInstance().get(Calendar.YEAR) + 10) || anno0 < (Calendar.getInstance().get(Calendar.YEAR)) - 10) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "year_must_be_valid"
	            )
	        );
	    }
		
		if ((cuiRicompresoLavori.isEmpty() && ricompreso.toString() == "SiLavori") || (ricompreso.toString() == "Si" && cuiRicompresoLavori.isEmpty() && cuiRicompreso == null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "cui_must_be_indicated"
	            )
	        );
	    }
		
		if (ricompreso.toString() == "No" && (coperturericomprese != null && coperturericomprese.size() > 0)) {
			// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "coperture_ricomprese_invalid"
	            )
	        );
		}
		
		/*
		if (ricompreso.toString() != "No" && coperturericomprese.size() == 0) {
			// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_coperture_ricomprese"
	            )
	        );
		}
		*/
		
		if ((delega && nondelega) || (!delega && !nondelega)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_delega"
	            )
	        );
	    }
		
		if (ausa == null && delega) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_ausa_delegated"
	            )
	        );
	    }
		
		if (ausa != null && nondelega) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_ausa_delegated"
	            )
	        );
	    }
		
		if ((affidamentoContrattoInEssere && nonAffidamentoContrattoInEssere) || (!affidamentoContrattoInEssere && !nonAffidamentoContrattoInEssere)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_affidamento_in_essere"
	            )
	        );
	    }
		
		if ((affidamentoExArt63 && nonAffidamentoExArt63) || (!affidamentoExArt63 && !nonAffidamentoExArt63)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_affidamento_ex_art"
	            )
	        );
	    }
		
		if ((lotto && nolotto) || (!lotto && !nolotto)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_lotto"
	            )
	        );
	    }
		
		if ((aggregabile && nonaggregabile) || (!aggregabile && !nonaggregabile)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_aggregabile"
	            )
	        );
	    }
		
		if (!noteaggregabile.isEmpty() && aggregabile) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_aggregabile_motivation"
	            )
	        );
	    }
	    
	    if (noteaggregabile.isEmpty() && nonaggregabile) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "insert_aggregabile_motivation"
	            )
	        );
	    }
	    
	    if ((verdi && noVerdi) || (!verdi && !noVerdi)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_verdi"
	            )
	        );
	    }
	    
	    if (verdi && (riferimentoNormativoVerdi.isEmpty() || oggettoAcquistiVerdi.isEmpty() || importoNettoAcquistiVerdi == null || aliquotaIvaAcquistiVerdi == null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_verdi_detail"
	            )
	        );
	    }
	    
	    if (noVerdi && (!riferimentoNormativoVerdi.isEmpty() || !oggettoAcquistiVerdi.isEmpty() || importoNettoAcquistiVerdi != null || aliquotaIvaAcquistiVerdi != null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "remove_verdi_detail"
	            )
	        );
	    }
	    
	    if ((notenonriproposta == null || notenonriproposta.isEmpty()) && cancellazione) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "insert_nonriproposta_motivation"
	            )
	        );
	    }
	    
	    if ((notenonriproposta != null && !notenonriproposta.isEmpty()) && !cancellazione) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_nonriproposta_motivation"
	            )
	        );
	    }
	    
	    if (priorita.toString().equals("max")  && (prioritamotivation == null || prioritamotivation.toString().isEmpty())) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "priorita_motivation_must_be_indicated"
	            )
	        );
	    }
	    
	    if (!priorita.toString().equals("max")  && prioritamotivation != null) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "priorita_motivation_must_not_be_indicated"
	            )
	        );
	    }
	    
	    // quando ripropongo un intervento, cio� lo carico e non � in stato di nuovo, devo poterlo persistere anche senza variante o cancellazione
	    if (!Users.getCurrent().equals("admin") && stato.getKey().equals("N") && ((variante != null && cancellazione) || (variante == null && !cancellazione))) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option"
	            )
	        );
	    }
	    
	    /*
	    if (nuovo && (variante.equals(Enumerators.VarianteValori.ModificaD) || variante.equals(Enumerators.VarianteValori.ModificaE))) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_new_motivation"
	            )
	        );
	    }
	    */
	    
	    if (variante != null && (variante.equals(Enumerators.VarianteValori.ModificaB) || variante.equals(Enumerators.VarianteValori.ModificaC) || variante.equals(Enumerators.VarianteValori.Modifica)) && ultimopianoapprovato != null) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "if_new_ultimopianoapprovato_must_be_empty"
	            )
	        );
	    }
	    
	    /*
	    if (modifica && (variante.equals(Enumerators.VarianteValori.ModificaB) || variante.equals(Enumerators.VarianteValori.ModificaC) || variante.equals(Enumerators.VarianteValori.Modifica))) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_edit_motivation"
	            )
	        );
	    }
	    */
	    
	    /*
	    if (modifica && linkProtocollo.isEmpty()) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_edit_file"
	            )
	        );
	    }
	    */
		
		saveTotali();	
		
		if (this.getStato() != null && !this.getStato().getKey().equals("M")  && !this.getStato().getKey().equals("Z"))
		{
			StatoProgetto s = new StatoProgetto();
	        s.setKey("N");
	        setStato(s);
		}
	}
	
	@PreUpdate // Just before updating the database
	private void validate()  throws Exception {
		if (!util.ProgBienUtils.ControllaFase(Calendar.getInstance().get(Calendar.YEAR), new Date(), "C") && !util.ProgBienUtils.ControllaFaseStraordinaria(Calendar.getInstance().get(Calendar.YEAR), new Date(),Users.getCurrent(), "C"))
		{
			throw new javax.validation.ValidationException(
		            XavaResources.getString(
		                "not_allowed_in_this_fase"
		            )
		        );
		}
		
		if (!util.ProgBienUtils.ControllaServizioStruttura(this.getServizi().getOid()))
		{
			throw new javax.validation.ValidationException(
		            XavaResources.getString(
		                "invalid_servizio"
		            )
		        );
		}
		
		/*
		if (modifica && variante == null && !Users.getCurrent().equals("admin")) {
			throw new javax.validation.ValidationException(
		            XavaResources.getString(
		                "specify_edit_motivation"
		            )
		        );
		}
		*/
		
		if (anno0 == 0 || anno0 > (Calendar.getInstance().get(Calendar.YEAR) + 10) || anno0 < (Calendar.getInstance().get(Calendar.YEAR)) - 10) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "year_must_be_valid"
	            )
	        );
	    }
		
		if ((cuiRicompresoLavori.isEmpty() && ricompreso.toString() == "SiLavori") || (ricompreso.toString() == "Si" && cuiRicompresoLavori.isEmpty() && cuiRicompreso == null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "cui_must_be_indicated"
	            )
	        );
	    }
	    
		if (ricompreso.toString() == "No" && (coperturericomprese != null && coperturericomprese.size() > 0)) {
			// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "coperture_ricomprese_invalid"
	            )
	        );
		}
		
		if (ricompreso.toString() != "No" && (coperturericomprese == null && coperturericomprese.size() == 0)) {
			// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_coperture_ricomprese"
	            )
	        );
		}
	    
		if ((delega && nondelega) || (!delega && !nondelega)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_delega"
	            )
	        );
	    }
		
		if (ausa == null && delega) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_ausa_delegated"
	            )
	        );
	    }
		
		if (ausa != null && nondelega) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_ausa_delegated"
	            )
	        );
	    }
		
		if ((affidamentoContrattoInEssere && nonAffidamentoContrattoInEssere) || (!affidamentoContrattoInEssere && !nonAffidamentoContrattoInEssere)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_affidamento_in_essere"
	            )
	        );
	    }
		
		if ((affidamentoExArt63 && nonAffidamentoExArt63) || (!affidamentoExArt63 && !nonAffidamentoExArt63)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_affidamento_ex_art"
	            )
	        );
	    }
		
		if ((lotto && nolotto) || (!lotto && !nolotto)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_lotto"
	            )
	        );
	    }
		
		if ((aggregabile && nonaggregabile) || (!aggregabile && !nonaggregabile)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_aggregabile"
	            )
	        );
	    }
		
		if (!noteaggregabile.isEmpty() && aggregabile) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_aggregabile_motivation"
	            )
	        );
	    }
	    
	    if (noteaggregabile.isEmpty() && nonaggregabile) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "insert_aggregabile_motivation"
	            )
	        );
	    }
	    
	    if ((verdi && noVerdi) || (!verdi && !noVerdi)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option_verdi"
	            )
	        );
	    }
	    
	    if (verdi && (riferimentoNormativoVerdi.isEmpty() || oggettoAcquistiVerdi.isEmpty() || importoNettoAcquistiVerdi == null || aliquotaIvaAcquistiVerdi == null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_verdi_detail"
	            )
	        );
	    }
	    
	    if (noVerdi && (!riferimentoNormativoVerdi.isEmpty() || !oggettoAcquistiVerdi.isEmpty() || importoNettoAcquistiVerdi != null || aliquotaIvaAcquistiVerdi != null)) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "remove_verdi_detail"
	            )
	        );
	    }
	    
	    if ((notenonriproposta == null || notenonriproposta.isEmpty()) && cancellazione) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "insert_nonriproposta_motivation"
	            )	            
	        );
	    }
	    
	    if ((notenonriproposta != null && !notenonriproposta.isEmpty()) && !cancellazione) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_nonriproposta_motivation"
	            )
	        );
	    }
	    
	    if (priorita.toString().equals("max")  && (prioritamotivation == null || prioritamotivation.toString().isEmpty())) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "priorita_motivation_must_be_indicated"
	            )
	        );
	    }	
	    
	    if (!priorita.toString().equals("max")  && (prioritamotivation != null && !prioritamotivation.toString().isEmpty())) { // The validation logic
	        // The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "priorita_motivation_must_not_be_indicated"
	            )
	        );
	    }
	    
	    if (!Users.getCurrent().equals("admin") && ((variante != null && cancellazione) || (variante == null && !cancellazione))) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_only_one_option"
	            )
	        );
	    }
	    
	    /*
	    if (cancellazione && variante != null) {// The validation exception from Bean Validation
	    	throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "delete_motivation"
	            )
	        );
	    }
	    */
	    
	    /*
	    if (nuovo && (variante == null || variante.equals(Enumerators.VarianteValori.ModificaD) || variante.equals(Enumerators.VarianteValori.ModificaE))) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_new_motivation"
	            )
	        );
	    }
	    */
	    
	    if (variante != null && (variante.equals(Enumerators.VarianteValori.ModificaB) || variante.equals(Enumerators.VarianteValori.ModificaC) || variante.equals(Enumerators.VarianteValori.Modifica)) && ultimopianoapprovato != null) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "if_new_ultimopianoapprovato_must_be_empty"
	            )
	        );
	    }
	    
	    if (!cancellazione && variante == null) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "choose_edit_motivation"
	            )
	        );
	    }
	    
	    /*
	    if (modifica && linkProtocollo.isEmpty()) {
	    	// The validation exception from Bean Validation
	        throw new javax.validation.ValidationException(
	            XavaResources.getString(
	                "add_edit_file"
	            )
	        );
	    }
	    */
	    
	    saveTotali();
	}

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=false) // The reference can have no value
    @DescriptionsList(
    		descriptionProperties="codice, descrizione"
    		) // Thus the reference is displayed using a combo
    private Nuts ambitogeografico;

    //@Required
    //@Column(name = "Settore", length = 1, nullable = false)
    //private String settore;
    //public enum Settore { F, S }
    
    @Required
    @Column(name = "Settore", length = 1, nullable = false)
    @org.hibernate.annotations.Type(type="org.openxava.types.EnumLetterType",
    	    parameters={
        		@org.hibernate.annotations.Parameter(name="letters", value="XFS")
        		,@org.hibernate.annotations.Parameter(name="enumType", value="ProgBien.Enumerators$Settore")
    	    }
    	)
    private Settore settore;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=false) // The reference can have no value
    @DescriptionsList(
		descriptionProperties="divisione, descrizione", 
		depends="settore",
		condition="e.fos = ? and e.codice like '%000000%'"
		) // Thus the reference is displayed using a combo
    private Cpv divisione; // A regular Java reference   

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
            fetch=FetchType.LAZY, // The reference is loaded on demand
            optional=false) // The reference can have no value
        @DescriptionsList(
        		descriptionProperties="codicelivello1, descrizione", 
    			depends="this.divisione",
        		condition="e.divisione = (select c.divisione from Cpv c where c.codice = ?) and e.codice like '%00000%' and e.codice not like '%000000%'"
        		) // Thus the reference is displayed using a combo
        private Cpv codiceLivello1; // A regular Java reference    

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=false) // The reference can have no value
    @DescriptionsList(
    		descriptionProperties="codice, descrizione", 
			depends="this.codiceLivello1",
    		condition="e.codice like (select c.divisione || c.codicelivello1 from Cpv c where c.codice = ?) || '%'"
    		) // Thus the reference is displayed using a combo
    private Cpv cpv; // A regular Java reference

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
    @DefaultValueCalculator(value=DefaultCodiciIctCalculator.class)
    @DescriptionsList(
    		descriptionProperties="key, descrizione",
    		condition="e.gruppicodici = 'B'") // Thus the reference is displayed using a combo
    private CodiciIct dl662014; // A regular Java reference

	//@Column(name = "PdC", length = 20, nullable = true)
    //private String pdc;
	
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
	@ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
	@ReferenceView("Simple")
    private Pdc pdc; // A regular Java reference

    @Required
    @Column(name = "Priorita", length = 1, nullable = false)
    private Priorita priorita;
    
    @Column(name = "PrioritaMotivation", length = 1, nullable = true)
    private PrioritaMotivation prioritamotivation;   	     

    @Required
    @Column(name = "Durata", nullable = false)
    private int durata;
    
    /*
    @Required
    @Column(name = "Taffidamento", length = 1, nullable = false)
    @org.hibernate.annotations.Type(type="org.openxava.types.EnumLetterType",
    	    parameters={
        		@org.hibernate.annotations.Parameter(name="letters", value="NRD")
        		,@org.hibernate.annotations.Parameter(name="enumType", value="ProgBien.Enumerators$Taffidamento")
    	    }
    	)
    private Taffidamento taffidamento;
    */  
    
    @Column(name = "AffidamentoContrattoInEssere")
    private boolean affidamentoContrattoInEssere;
    
    @Column(name = "NonAffidamentoContrattoInEssere")
    private boolean nonAffidamentoContrattoInEssere;
    
    @Column(name = "AffidamentoExArt63")
    private boolean affidamentoExArt63;
    
    @Column(name = "NonAffidamentoExArt63")
    private boolean nonAffidamentoExArt63;

    /*
    @ElementCollection
    @ListProperties("descrizionevoce, tipologia, capitolo, pdc, importonetto, base, aliquotaiva, importoIvaCalculated, totaleCalculated")
	private Collection<QuadroEconomicoEmbeddable> quadroeconomico;
    */  
    
    @OneToMany (mappedBy="procedura", cascade=CascadeType.REMOVE)
    @NewAction("QuadroEconomicoProgramma.add") // @AddAction instead
    @SaveAction("QuadroEconomicoProgramma.save") // @AddAction instead
    @EditAction("QuadroEconomicoProgramma.edit") // @AddAction instead
    @RemoveAction("QuadroEconomicoProgramma.remove")
    @RemoveSelectedAction("")
    @OrderBy("tipologia, sottotipologia")
    private Collection<QuadroEconomicoProgramma> quadroeconomico;     

    @Stereotype("MONEY")
    @Column(name = "ValoreStimatoAppalto", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal valorestimatoappalto;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getValoreStimatoAppalto() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("B"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}    	
    	}
        return sum;
    }   
    
	@Stereotype("MONEY")
    @Column(name = "ImportoBaseAsta", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal importobaseasta;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getImportoBaseAsta() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}    	
    	}
        return sum;
    }
	
	@Stereotype("MONEY")
    @Column(name = "SommeADisposizione", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal sommeadisposizione;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getSommeADisposizione() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("C"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}    	
    	}
        return sum;
    } 
	
	@Stereotype("MONEY")
    @Column(name = "TotaleImposte", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal totaleimposte;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getTotaleImposte() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    	
	    		sum = sum.add(q.getImportoiva());
			}    	
    	}
        return sum;
    }
	
	@Stereotype("MONEY")
    @Column(name = "CostiComplessivi", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal costicomplessivi;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico, costipregressi")
    public BigDecimal getCostiComplessivi() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}    
    	}
    	if (costipregressi != null)
    	{
    		return sum.add(costipregressi);
    	}
    	else
    		return sum;
    }
	
	@Stereotype("MONEY")
	@Column(name = "CostiA1", length = 16, precision = 12, nullable = true)
	@Hidden
	private BigDecimal costia1;
	
	/*
	@Stereotype("MONEY")
    @Depends("quadroeconomico, fondoenable, quotainnovazioneenable, quotagdlenable, programenable, affidaenable, execenable")
    public BigDecimal getCostiA1() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			sum = sum.add(q.getImportonetto().multiply(q.getPercentualeA1().divide(new BigDecimal(100))));
	    		}    		
			}
    	}
    	if (fondoenable) {
    		sum = sum.add(getQuotaInnovazioneTotale()).add(getGdl113Affida()).add(getGdl113Program());
    	}
    	if (quotainnovazioneenable && !fondoenable)
    	{
    		sum = sum.add(getQuotaInnovazioneTotale());
    	}
    	if (quotagdlenable && !fondoenable)
    	{
    		sum = sum.add(getGdl113Affida()).add(getGdl113Program());
    	}
    	if (programenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Program());
    	}
    	if (affidaenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Affida());
    	}
		return sum;
    }
    */
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getCostiA1() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			sum = sum.add(q.getImportonetto().multiply(q.getPercentualeA1().divide(new BigDecimal(100))));
	    		}    		
			}
    	}
		return sum;
    }

    @Stereotype("MONEY")
    @Column(name = "CostiA2", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal costia2;
    
    @Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getCostiA2() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			sum = sum.add(q.getImportonetto().multiply(q.getPercentualeA2().divide(new BigDecimal(100))));
	    		}    		
			}
    	}
		return sum;
    }

    @Stereotype("MONEY")
    @Column(name = "CostiAs", length = 16, precision = 12, nullable = true)
    @Hidden
    private BigDecimal costias;
    
    /*
    @Stereotype("MONEY")
    @Depends("quadroeconomico, fondoenable, quotagdlenable, execenable")
    public BigDecimal getCostiAs() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	BigDecimal percentage = BigDecimal.ZERO;
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			percentage = q.getPercentualeA1().add(q.getPercentualeA2());
	    			if (percentage.compareTo(new BigDecimal(100)) == -1)
	    				sum = sum.add(q.getImportonetto().multiply((new BigDecimal(100).subtract(percentage)).divide(new BigDecimal(100))));
	    		}    		
			}
    	}
    	if (fondoenable) {
    		sum = sum.add(getGdl113Exec());
    	}
    	if (quotagdlenable && !fondoenable)
    	{
    		sum = sum.add(getGdl113Exec());
    	}
    	if (execenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Exec());
    	}
		return sum;
    }
    */
    
    @Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getCostiAs() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	BigDecimal percentage = BigDecimal.ZERO;
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A") || q.getTipologia().getKey().toString().equals("C") || q.getTipologia().getKey().toString().equals("D"))
	    		{
	    			percentage = q.getPercentualeA1().add(q.getPercentualeA2());
	    			if (percentage.compareTo(new BigDecimal(100)) == -1)
	    				sum = sum.add(q.getImportonetto().multiply((new BigDecimal(100).subtract(percentage)).divide(new BigDecimal(100))));
	    		}    		
			}
    	}
		return sum;
    }
    
    @Stereotype("MONEY")
    @Column(name = "TotaleIvaQuadroEconomico", precision = 16, scale=2, nullable = true)
    @Hidden
    private BigDecimal totaleivaquadroeconomico;
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getTotaleIvaQuadroEconomico() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getImportoiva() != null)
				{
					sum = sum.add(q.getImportoiva());
				}
			}    	
    	}
        return sum;
    }
	
	@Stereotype("MONEY")
    @Column(name = "TotaleQuadroEconomico", precision = 16, scale=2, nullable = true)
    @Hidden
    private BigDecimal totalequadroeconomico;
	
	/*
	@Stereotype("MONEY")
    @Depends("quadroeconomico, fondoenable, quotainnovazioneenable, quotagdlenable, programenable, affidaenable, execenable")
    public BigDecimal getTotaleQuadroEconomico() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getImportoiva() != null)
				{			
	    			sum = sum.add(q.getImportoiva()).add(q.getImportonetto());
				}
	    	}
    	}
    	if (fondoenable) {
    		sum = sum.add(getFondoArt113Totale());
    	}
    	if (quotainnovazioneenable && !fondoenable)
    	{
    		sum = sum.add(getQuotaInnovazioneTotale());
    	}
    	if (quotagdlenable && !fondoenable)
    	{
    		sum = sum.add(getGdl113Totale());
    	}
    	if (programenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Program());
    	}
    	if (affidaenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Affida());
    	}
    	if (execenable && !fondoenable && !quotagdlenable)
    	{
    		sum = sum.add(getGdl113Exec());
    	}
        return sum;
    }
    */
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico")
    public BigDecimal getTotaleQuadroEconomico() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   	
    	if (quadroeconomico != null) {
	    	for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getImportoiva() != null)
				{			
	    			sum = sum.add(q.getImportoiva()).add(q.getImportonetto());
				}
	    	}
    	}
        return sum;
    }

	@Stereotype("MONEY")
    @Column(name = "CostiPregressi", length = 16, precision = 12, nullable = true)
    private BigDecimal costipregressi;
    
    @Hidden
    @Stereotype("MONEY")
    @Column(name = "CostiTotali", length = 16, precision = 12, nullable = true)
    private BigDecimal costitotali;

	@Stereotype("MONEY")
    @Depends("costipregressi, costia1, costia2, costias")
    public BigDecimal getCostiTot() {
        return (costipregressi==null?BigDecimal.ZERO:costipregressi).add(costia1==null?BigDecimal.ZERO:costia1).add(costia2==null?BigDecimal.ZERO:costia2).add(costias==null?BigDecimal.ZERO:costias);
    }
	
	@Required
    @Column(name = "Delega")
    private boolean delega;
    
    @Required
    @Column(name = "NonDelega")
    private boolean nondelega;
    
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
    @DescriptionsList(descriptionProperties="codicefiscale, denominazione") // Thus the reference is displayed using a combo
    private Ausa ausa; // A regular Java reference
    
    @Column(name = "Variante", nullable = true)
    private VarianteValori variante;  
    
    @Stereotype("TEXT_AREA")
	@Depends("variante")
    public String getDescrizioneVariante() {
    	String descrizione = "";
    	if (variante != null)
    	{
	    	if (variante.equals(VarianteValori.ModificaB))
	    		descrizione = "Aggiunta di uno o pi� acquisti in conseguenza di atti amministrativi adottati a livello statale o regionale";
	    	else if (variante.equals(VarianteValori.ModificaC))
	    		descrizione = "Aggiunta di uno o pi� acquisti per la sopravvenuta disponibilit� di finanziamenti all�interno del bilancio non prevedibili al momento della prima approvazione del programma, ivi comprese le ulteriori risorse disponibili anche a seguito di ribassi d�asta o di economie";
	    	else if (variante.equals(VarianteValori.ModificaD))
	    		descrizione = "Anticipazione alla prima annualit� dell�acquisizione di una fornitura o di un servizio ricompreso nel programma biennale degli acquisti";
	    	else if (variante.equals(VarianteValori.ModificaE))
	    		descrizione = "Modifica del quadro economico degli acquisti gi� contemplati nell�elenco annuale, per la quale si rendano necessarie ulteriori risorse";
	    	else if (variante.equals(VarianteValori.Modifica))
	    		descrizione = "Un servizio o una fornitura non inseriti nell�elenco annuale possono essere realizzati quando siano resi necessari da eventi imprevedibili o calamitosi o da sopravvenute disposizioni di legge o regolamentari. Un servizio o una fornitura non inseriti nella prima annualit� del programma possono essere altres� realizzati sulla base di un autonomo piano finanziario che non utilizzi risorse gi� previste tra i mezzi finanziari dell�amministrazione al momento della formazione dell�elenco, avviando le procedure di aggiornamento della programmazione";
    	}
        return descrizione;
    }
    
    @Column(name = "Avviata", nullable = false)
    private boolean avviata;
    
    @Required
    @Column(name = "Aggregabile")
    @DefaultValueCalculator(value=DefaultAggregabileCalculator.class)
    private boolean aggregabile;
    
    @Required
    @Column(name = "NonAggregabile")
    @DefaultValueCalculator(value=DefaultNonAggregabileCalculator.class)
    private boolean nonaggregabile;
    
    @Stereotype("TEXT_AREA") // This is for a big text, a text area or equivalent will be used
    @Column(name = "NoteAggregabile", length = 500, nullable = true)
    private String noteaggregabile;
	
	@Stereotype("TEXT_AREA") // This is for a big text, a text area or equivalent will be used
    @Column(name = "NoteNonRiproposta", length = 500, nullable = true)
    private String notenonriproposta;
    
	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.EAGER, // The reference is loaded on demand
        optional=true) // The reference can have no value
    @JoinColumns({                                                                                 // 2
      @JoinColumn(name="progettiict_id_servizio",referencedColumnName="id_servizio"),
      @JoinColumn(name="progettiict_sigla",referencedColumnName="sigla")
    })
    @DescriptionsList(descriptionProperties="sigla") // Thus the reference is displayed using a combo
    private ProgettiIct progettiict; // A regular Java reference

	@Column(name = "Quantita", nullable = true)
    private Integer quantita;

	@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    @ManyToOne( // The reference is persisted as a database relationship
        fetch=FetchType.LAZY, // The reference is loaded on demand
        optional=true) // The reference can have no value
    @DescriptionsList(
    		descriptionProperties="descrizione"
    		) // Thus the reference is displayed using a combo
    private UnitaMisura umisura;

	@Required
    @Column(name = "Lotto")
    private boolean lotto;
    
    @Required
    @Column(name = "NonLotto")
    private boolean nolotto;

    @Column(name = "CupMaster", length = 15, nullable = true)
    private String cupmaster;

	@Column(name = "Verdi", nullable = true)
    private boolean verdi;
	
	@Column(name = "noVerdi", nullable = true)
    private boolean noVerdi;
	
	@Column(name = "RiferimentoNormativoVerdi", nullable = true)
    private String riferimentoNormativoVerdi;
	
	@Column(name = "OggettoAcquistiVerdi", nullable = true)
    private String oggettoAcquistiVerdi;
	
	@Column(name = "CpvAcquistiVerdi", nullable = true)
    private String cpvAcquistiVerdi;
	
	@Stereotype("MONEY")
    @Column(name = "ImportoNettoAcquistiVerdi", length = 16, precision = 12)
    private BigDecimal importoNettoAcquistiVerdi;
	
	@Column(name = "AliquotaIvaAcquistiVerdi", length = 5, precision = 2, nullable = true)
    private BigDecimal aliquotaIvaAcquistiVerdi;
	
	@Column(name = "OggettoAcquistiMaterialiRiciclati", nullable = true)
    private String oggettoAcquistiMaterialiRiciclati;
	
	@Column(name = "CpvAcquistiMaterialiRiciclati", nullable = true)
    private String cpvAcquistiMaterialiRiciclati;
	
	@Stereotype("MONEY")
    @Column(name = "ImportoNettoAcquistiMaterialiRiciclati", length = 16, precision = 12)
    private BigDecimal importoNettoAcquistiMaterialiRiciclati;
	
	@Column(name = "AliquotaIvaAcquistiMaterialiRiciclati", length = 5, precision = 2, nullable = true)
    private BigDecimal aliquotaIvaAcquistiMaterialiRiciclati;
	
	@Column(name = "ProgramEnable", nullable = true)
    private boolean programenable;	
	
	@Column(name = "AffidaEnable", nullable = true)
    private boolean affidaenable;
	
	@Column(name = "ExecEnable", nullable = true)
    private boolean execenable;
	
	@Column(name = "FondoEnable", nullable = true)
    private boolean fondoenable;	
	
	@Column(name = "QuotaInnovazioneEnable", nullable = true)
    private boolean quotainnovazioneenable;
	
	@Column(name = "QuotaGdlEnable", nullable = true)
    private boolean quotagdlenable;
	
	@Column(name = "QuotaCollaudoEnable", nullable = true)
    private boolean quotacollaudoenable;
	
	@Stereotype("MONEY")
    @Column(name = "GdL113Program", length = 16, precision = 12, nullable = true)
	@Hidden
    private BigDecimal gdl113program;

	@Stereotype("MONEY")
    @Column(name = "GdL113Affida", length = 16, precision = 12, nullable = true)
	@Hidden
    private BigDecimal gdl113affida;

	@Stereotype("MONEY")
    @Column(name = "GdL113Exec", length = 16, precision = 12, nullable = true)
	@Hidden
    private BigDecimal gdl113exec;
	
	@Stereotype("MONEY")
    @Column(name = "GdL113Collaudo", precision = 16, scale=2, nullable = true)
	@Hidden
    private BigDecimal gdl113collaudo;
    
	@Stereotype("MONEY")
    @Column(name="GdL113Totale", length=16, nullable = true) 
    @Hidden
    private BigDecimal gdl113totale; //Amount of pieces (calculated on the amount of pieces of the selected package)	
	
	@Stereotype("MONEY")
    @Column(name="QuotaInnovazione", length=16, nullable = true) 
    @Hidden
    private BigDecimal quotaInnovazione; //Amount of pieces (calculated on the amount of pieces of the selected package)
	
	@Stereotype("MONEY")
    @Column(name="FondoArt113", length=16, nullable = true) 
    @Hidden
    private BigDecimal fondoart113; //Amount of pieces (calculated on the amount of pieces of the selected package)
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico, this.data")
    public BigDecimal getGdl113Program() {
		
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(data);
		
		if (quadroeconomico != null)
		{
			return getGdl113Totale().multiply(soglia.getPercentProgrammazione());
		}
		else return BigDecimal.valueOf(0);
	}
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico, this.data")
    public BigDecimal getGdl113Affida() {
		
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(data);
		
		if (quadroeconomico != null)
		{
			return getGdl113Totale().multiply(soglia.getPercentAffidamento());
		}
		else return BigDecimal.valueOf(0);
	}
	
	@Stereotype("MONEY")
    @Depends("quadroeconomico, this.data")
    public BigDecimal getGdl113Exec() {
		
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(data);
		
		if (quadroeconomico != null)
		{
			return getGdl113Totale().multiply(soglia.getPercentEsecuzione());
		}
		else return BigDecimal.valueOf(0);
	}
	
	@Action(value="ProcedureProgramma.addIncentivi" , alwaysEnabled=true)
	@Stereotype("MONEY")
    @Depends("quadroeconomico, this.data")
    public BigDecimal getGdl113Collaudo() {
		
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(data);
		
		if (quadroeconomico != null)
		{
			return getGdl113Exec().multiply(soglia.getPercentCollaudo());
		}
		else return BigDecimal.valueOf(0);
	}
	
	@Stereotype("MONEY")
	@Depends("this.quadroeconomico, this.data")
    public BigDecimal getQuotaInnovazioneTotale() {
		BigDecimal sum = BigDecimal.ZERO;   
		if (quadroeconomico != null && !quadroeconomico.isEmpty())
		{
			for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}    
		}
		
		Date dataSoglia = new Date();
		
		if (data != null)
			dataSoglia = data;
    	
		//BigDecimal valoreSoglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
		
		List<SogliaScaglioni> scaglioni = util.ProgBienUtils.getSogliaScaglioni(soglia.getOid());
		
    	//BigDecimal bg1, bg2, bg3, bg4;
        
    	/*
    	bg1 = new BigDecimal("1000000");
    	bg2 = new BigDecimal("500000");
    	if (valoreSoglia == null)
    		bg3 = new BigDecimal("0");
    	else
    		bg3 = valoreSoglia;
    	bg4 = new BigDecimal("100000"); 	
    	*/
    	
    	BigDecimal tot = BigDecimal.valueOf(0);
		
		if (sum != null) {
			
			tot = ProgBienUtils.getGdlTotScaglioniEccedenza(scaglioni, sum);
			/*
			for (SogliaScaglioni s: scaglioni) {
	    		if (sum.compareTo(s.getValoreDa()) == -1 && sum.compareTo(s.getValoreA()) == 1) {
	    			tot = sum.multiply(s.getPercentScaglione());
	    	}
	    	*/
			
			BigDecimal gdlTot = getGdl113Totale();
			
			BigDecimal gdl1 = gdlTot.multiply(soglia.getPercentEsecuzione());
			BigDecimal gdl2 = gdlTot.multiply(soglia.getPercentAffidamento());
			BigDecimal gdl3 = gdlTot.multiply(soglia.getPercentProgrammazione());
			
			BigDecimal sumGdl = gdl1.add(gdl2).add(gdl3);
			
			return tot.add(sumGdl.negate());
		}
		else 
			return BigDecimal.valueOf(0);
	}
	
	@Stereotype("MONEY")
    @Depends("this.quadroeconomico, this.data")
    public BigDecimal getGdl113Totale() {
		
		BigDecimal sum = BigDecimal.ZERO;   
		if (quadroeconomico != null && !quadroeconomico.isEmpty())
		{
			for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}  
		}
    	
		Date dataSoglia = new Date();
		
		if (data != null)
			dataSoglia = data;
    	
		//BigDecimal valoreSoglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
    	
    	//BigDecimal bg1, bg2, bg3, bg4;
        
    	/*
    	bg1 = new BigDecimal("1000000");
    	bg2 = new BigDecimal("500000");
    	if (valoreSoglia == null)
    		bg3 = new BigDecimal("0");
    	else
    		bg3 = valoreSoglia;
    	bg4 = new BigDecimal("100000"); 	
    	*/
    	
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
    	
    	List<SogliaScaglioni> scaglioni = util.ProgBienUtils.getSogliaScaglioni(soglia.getOid());
    	
    	BigDecimal tot = BigDecimal.valueOf(0);
    	
    	if (sum != null) {
    		tot = ProgBienUtils.getGdlTotScaglioniEccedenza(scaglioni, sum);
			/*
			for (SogliaScaglioni s: scaglioni) {
	    		if (sum.compareTo(s.getValoreDa()) == -1 && sum.compareTo(s.getValoreA()) == 1) {
	    			tot = sum.multiply(s.getPercentScaglione());
	    	}
	    	*/
    	}
    	
    	return tot.multiply(soglia.getPercentIncentivi());
    	
    	/*
    	else if (sum.compareTo(soglia.getValoreMax()) == 1)
    		return sum.multiply(soglia.getPercentScaglione1().multiply(soglia.getPercentIncentivi()));
    	else if (sum.compareTo(soglia.getValoreMed()) == 1)
    		return sum.multiply(soglia.getPercentScaglione2().multiply(soglia.getPercentIncentivi()));
    	else if (sum.compareTo(soglia.getValore()) == 1)
    		return sum.multiply(soglia.getPercentScaglione3().multiply(soglia.getPercentIncentivi()));
    	else if (sum.compareTo(soglia.getValoreMin()) == 1)
    		return sum.multiply(soglia.getPercentScaglione4().multiply(soglia.getPercentIncentivi()));
    	else 
    		return sum.multiply(soglia.getPercentScaglione5().multiply(soglia.getPercentIncentivi()));
    	*/
    }
	
	@Stereotype("MONEY")
	@Depends("this.quadroeconomico, this.data")
    public BigDecimal getFondoArt113Totale() {

		BigDecimal sum = BigDecimal.ZERO;   
		if (quadroeconomico != null && !quadroeconomico.isEmpty())
		{
			for (QuadroEconomicoProgramma q: quadroeconomico) {
	    		if (q.getTipologia().getKey().toString().equals("A"))
	    		{
	    			sum = sum.add(q.getImportonetto());
	    		}    		
			}  
		}
    	
		Date dataSoglia = new Date();
		
		if (data != null)
			dataSoglia = data;
    	
		//BigDecimal valoreSoglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
    	
    	//BigDecimal bg1, bg2, bg3, bg4;
        
    	/*
    	bg1 = new BigDecimal("1000000");
    	bg2 = new BigDecimal("500000");
    	if (valoreSoglia == null)
    		bg3 = new BigDecimal("0");
    	else
    		bg3 = valoreSoglia;
    	bg4 = new BigDecimal("100000"); 	
    	*/ 
    	
		SogliaImporti soglia = util.ProgBienUtils.getSogliaImporti(dataSoglia);
		 
    	List<SogliaScaglioni> scaglioni = util.ProgBienUtils.getSogliaScaglioni(soglia.getOid());
    	
    	BigDecimal tot = BigDecimal.valueOf(0);
    	
    	if (sum != null) {
    		tot = ProgBienUtils.getGdlTotScaglioniEccedenza(scaglioni, sum);
			/*
			for (SogliaScaglioni s: scaglioni) {
	    		if (sum.compareTo(s.getValoreDa()) == -1 && sum.compareTo(s.getValoreA()) == 1) {
	    			tot = sum.multiply(s.getPercentScaglione());
	    	}
	    	*/
    	}
    	
    	return tot;
    	
    	/*
    	 * if (sum == null)
    		return BigDecimal.valueOf(0);
    	
    	else if (sum.compareTo(soglia.getValoreMax()) == 1)
    		return sum.multiply(soglia.getPercentScaglione1());
    	else if (sum.compareTo(soglia.getValoreMed()) == 1)
    		return sum.multiply(soglia.getPercentScaglione2());
    	else if (sum.compareTo(soglia.getValore()) == 1)
    		return sum.multiply(soglia.getPercentScaglione3());
    	else if (sum.compareTo(soglia.getValoreMin()) == 1)
    		return sum.multiply(soglia.getPercentScaglione4());
    	else 
    		return sum.multiply(soglia.getPercentScaglione5());
		*/
    }
	
	/*
	@ElementCollection
	private Collection<CopertureEmbeddable> coperture;
	*/
	
	@OneToMany (mappedBy="procedura", cascade=CascadeType.REMOVE)
    @AddAction("ProcedureProgramma.addCoperture") // @AddAction instead
    private Collection<CopertureProgramma> coperture;
	
	@OneToMany (mappedBy="procedura", cascade=CascadeType.REMOVE)
    @AddAction("ProcedureProgramma.addCopertureRicomprese") // @AddAction instead
    private Collection<CopertureRicompreseProgramma> coperturericomprese;
	
	@Stereotype("MONEY")
    @Column(name="TotaleCoperture", precision = 16, scale=2, nullable = true) 
    @Hidden
    private BigDecimal totalecoperture; //Amount of pieces (calculated on the amount of pieces of the selected package)
	
	
	@Stereotype("MONEY")
	@Depends("this.coperture, this.coperturericomprese, this.ricompreso")
    public BigDecimal getTotaleCoperture() {
    			
    	BigDecimal sum = BigDecimal.ZERO;   
    	/*
    	if (ricompreso.toString() == "No")
    	{
	    	for (CopertureProgramma c: coperture) {
	    		if (c.getTotale() != null)
				{
					sum = sum.add(c.getTotale());
				}
			}
    	}
    	else
    	{
	    	for (CopertureRicompreseProgramma c: coperturericomprese) {
	    		if (c.getTotale() != null)
				{
					sum = sum.add(c.getTotale());
				}
			}
    	} 
    	*/
    	for (CopertureProgramma c: coperture) {
    		if (c.getTotale() != null)
			{
				sum = sum.add(c.getTotale());
			}
		}
    	
        return sum;
    }
	
	@Stereotype("MONEY")
    @Column(name="CostoTotale", precision = 16, scale=2, nullable = true) 
    @Hidden
    private BigDecimal costototale; //Amount of pieces (calculated on the amount of pieces of the selected package)
	
	@Action(value="ProcedureProgramma.aggiornaCoperture" , alwaysEnabled=true)
	@Stereotype("MONEY")
    @Depends("costripregressi")
	public BigDecimal getCostoTotale() {	
		BigDecimal sum = getTotaleCoperture();
    	if (costipregressi != null)
    	{
    		sum = sum.add(costipregressi);
    	}
        return sum;
    }
	
	@Stereotype("TEXT_AREA") // This is for a big text, a text area or equivalent will be used
    @Column(name = "Note", length = 500, nullable = true)
    private String note;
	
	/*
    @Column(name = "NuovoAcquisto", nullable = true)
    private boolean nuovo;
	
    @Column(name = "ModificaAcquisto", nullable = true)
    private boolean modifica;
    */
	
    @Column(name = "CancellazioneAcquisto", nullable = true)
    private boolean cancellazione;    
    
    /*
    @Stereotype("WEBURL") // The user can view and change a photo
	private String linkProtocollo;	    
    */
    
    @Hidden
    @Column(columnDefinition="boolean default false", nullable = true)
    private boolean archived;

	/*
    public String getLinkProtocollo() {
		return linkProtocollo;
	}

	public void setLinkProtocollo(String linkProtocollo) {
		this.linkProtocollo = linkProtocollo;
	}
	*/

    /*
	public boolean isNuovo() {
		return nuovo;
	}
	
	public void setNuovo(boolean nuovo) {
		this.nuovo = nuovo;
	}

	public boolean isModifica() {
		return modifica;
	}

	public void setModifica(boolean modifica) {
		this.modifica = modifica;
	}
	*/

	public boolean isCancellazione() {
		return cancellazione;
	}

	public void setCancellazione(boolean cancellazione) {
		this.cancellazione = cancellazione;
	}

	public void setAnno0(Integer aValue) {
        anno0 = aValue;
    }

    public Integer getAnno0() {
        return anno0;
    }  
    
    public ProcedureDefinitive getUltimopianoapprovato() {
		return ultimopianoapprovato;
	}

	public void setUltimopianoapprovato(ProcedureDefinitive ultimopianoapprovato) {
		this.ultimopianoapprovato = ultimopianoapprovato;
	}

	public Servizi getServizi() {
		return servizi;
	}

	public void setServizi(Servizi servizi) {
		this.servizi = servizi;
	}

    public void setData(Date aValue) {
        data = aValue;
    }

    public Date getData() {
        return data;
    }

    public void setCup(String aValue) {
        cup = aValue;
    }

    public String getCup() {
        return cup;
    }       
	
    public String getCui() {
		return cui;
	}

	public void setCui(String cui) {
		this.cui = cui;
	}	

	public Ricompreso getRicompreso() {
		return ricompreso;
	}

	public void setRicompreso(Ricompreso ricompreso) {
		this.ricompreso = ricompreso;
	}
	
	public ProcedureDefinitive getCuiRicompreso() {
		return cuiRicompreso;
	}

	public void setCuiRicompreso(ProcedureDefinitive cuiRicompreso) {
		this.cuiRicompreso = cuiRicompreso;
	}

	public String getCuiRicompresoLavori() {
		return cuiRicompresoLavori;
	}

	public void setCuiRicompresoLavori(String cuiRicompresoLavori) {
		this.cuiRicompresoLavori = cuiRicompresoLavori;
	}

	public void setAmbitogeografico(Nuts aValue) {
        ambitogeografico = aValue;
    }

    public Nuts getAmbitogeografico() {
        return ambitogeografico;
    }

    public void setSettore(Settore aValue) {
        settore = aValue;
    }

    public Settore getSettore() {
        return settore;
    }
    
	public Cpv getDivisione() {
		return divisione;
	}

	public void setDivisione(Cpv divisione) {
		this.divisione = divisione;
	}
	
	public Cpv getCodiceLivello1() {
		return codiceLivello1;
	}

	public void setCodiceLivello1(Cpv codiceLivello1) {
		this.codiceLivello1 = codiceLivello1;
	}
    
    public Cpv getCpv() {
		return cpv;
	}

	public void setCpv(Cpv cpv) {
		this.cpv = cpv;
	}
	
	public CodiciIct getDl662014() {
		return dl662014;
	}

	public void setDl662014(CodiciIct dl662014) {
		this.dl662014 = dl662014;
	}

    public void setPdc(Pdc aValue) {
        pdc = aValue;
    }

    public Pdc getPdc() {
        return pdc;
    }

    public void setDescrizione(String aValue) {
        descrizione = aValue;
    }

    public String getDescrizione() {
        return descrizione;
    }    
    
    public Priorita getPriorita() {
		return priorita;
	}

	public void setPriorita(Priorita priorita) {
		this.priorita = priorita;
	}

	public PrioritaMotivation getPrioritamotivation() {
		return prioritamotivation;
	}

	public void setPrioritamotivation(PrioritaMotivation prioritamotivation) {
		this.prioritamotivation = prioritamotivation;
	}

    public Dipendenti getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(Dipendenti dipendenti) {
		this.dipendenti = dipendenti;
	}

    public void setDurata(int aValue) {
        durata = aValue;
    }

    public int getDurata() {
        return durata;
    }

    /*
    public void setTaffidamento(Taffidamento aValue) {
        taffidamento = aValue;
    }

    public Taffidamento getTaffidamento() {
        return taffidamento;
    } 
    */   
    
    public Collection<QuadroEconomicoProgramma> getQuadroeconomico() {
		return quadroeconomico;
	}

	public void setQuadroeconomico(Collection<QuadroEconomicoProgramma> quadroeconomico) {
		this.quadroeconomico = quadroeconomico;
	}
    
	/*
    public Collection<QuadroEconomicoEmbeddable> getQuadroeconomico() {
		return quadroeconomico;
	}

	public void setQuadroeconomico(Collection<QuadroEconomicoEmbeddable> quadroeconomico) {
		this.quadroeconomico = quadroeconomico;
	}*/
	
    public BigDecimal getImportobaseasta() {
		return importobaseasta;
	}

	public void setImportobaseasta(BigDecimal importobaseasta) {
		this.importobaseasta = importobaseasta;
	}

    public BigDecimal getCostipregressi() {
		return costipregressi;
	}

	public void setCostipregressi(BigDecimal costipregressi) {
		this.costipregressi = costipregressi;
	}

	public void setCostia1(java.math.BigDecimal aValue) {
        costia1 = aValue;
    }

    public java.math.BigDecimal getCostia1() {
        return costia1;
    }

    public void setCostia2(java.math.BigDecimal aValue) {
        costia2 = aValue;
    }

    public java.math.BigDecimal getCostia2() {
        return costia2;
    }

    public void setCostias(java.math.BigDecimal aValue) {
        costias = aValue;
    }

    public java.math.BigDecimal getCostias() {
        return costias;
    }
    
    public BigDecimal getCostitotali() {
		return costitotali;
	}

	public void setCostitotali(BigDecimal costitotali) {
		this.costitotali = costitotali;
	}

    public Ausa getAusa() {
		return ausa;
	}

	public void setAusa(Ausa ausa) {
		this.ausa = ausa;
	}	

    public VarianteValori getVariante() {
		return variante;
	}

	public void setVariante(VarianteValori variante) {
		this.variante = variante;
	}

	public void setAvviata(boolean aValue) {
        avviata = aValue;
    }

    public boolean getAvviata() {
        return avviata;
    }
    
    public boolean isAggregabile() {
		return aggregabile;
	}

	public void setAggregabile(boolean aggregabile) {
		this.aggregabile = aggregabile;
	}
	
	public String getNoteaggregabile() {
		return noteaggregabile;
	}

	public void setNoteaggregabile(String noteaggregabile) {
		this.noteaggregabile = noteaggregabile;
	}	

	public String getNotenonriproposta() {
		return notenonriproposta;
	}

	public void setNotenonriproposta(String notenonriproposta) {
		this.notenonriproposta = notenonriproposta;
	}

	public ProgettiIct getProgettiict() {
		return progettiict;
	}

	public void setProgettiict(ProgettiIct progettiict) {
		this.progettiict = progettiict;
	}

    public void setQuantita(Integer aValue) {
        quantita = aValue;
    }

    public Integer getQuantita() {
        return quantita;
    }
    
    public UnitaMisura getUmisura() {
		return umisura;
	}

	public void setUmisura(UnitaMisura umisura) {
		this.umisura = umisura;
	}

	public void setLotto(boolean aValue) {
        lotto = aValue;
    }

    public boolean getLotto() {
        return lotto;
    }

    public String getCupmaster() {
		return cupmaster;
	}

	public void setCupmaster(String cupmaster) {
		this.cupmaster = cupmaster;
	}

    public void setVerdi(boolean aValue) {
        verdi = aValue;
    }

    public boolean getVerdi() {
        return verdi;
    }
    
	public boolean isNoVerdi() {
		return noVerdi;
	}

	public void setNoVerdi(boolean noVerdi) {
		this.noVerdi = noVerdi;
	}

	public BigDecimal getValorestimatoappalto() {
		return valorestimatoappalto;
	}

	public void setValorestimatoappalto(BigDecimal valorestimatoappalto) {
		this.valorestimatoappalto = valorestimatoappalto;
	}

	public BigDecimal getSommeadisposizione() {
		return sommeadisposizione;
	}

	public void setSommeadisposizione(BigDecimal sommeadisposizione) {
		this.sommeadisposizione = sommeadisposizione;
	}

	public BigDecimal getTotaleimposte() {
		return totaleimposte;
	}

	public void setTotaleimposte(BigDecimal totaleimposte) {
		this.totaleimposte = totaleimposte;
	}

	public BigDecimal getCosticomplessivi() {
		return costicomplessivi;
	}

	public void setCosticomplessivi(BigDecimal costicomplessivi) {
		this.costicomplessivi = costicomplessivi;
	}

	public String getRiferimentoNormativoVerdi() {
		return riferimentoNormativoVerdi;
	}

	public void setRiferimentoNormativoVerdi(String riferimentoNormativoVerdi) {
		this.riferimentoNormativoVerdi = riferimentoNormativoVerdi;
	}

	public String getOggettoAcquistiVerdi() {
		return oggettoAcquistiVerdi;
	}

	public void setOggettoAcquistiVerdi(String oggettoAcquistiVerdi) {
		this.oggettoAcquistiVerdi = oggettoAcquistiVerdi;
	}

	public String getCpvAcquistiVerdi() {
		return cpvAcquistiVerdi;
	}

	public void setCpvAcquistiVerdi(String cpvAcquistiVerdi) {
		this.cpvAcquistiVerdi = cpvAcquistiVerdi;
	}

	public BigDecimal getImportoNettoAcquistiVerdi() {
		return importoNettoAcquistiVerdi;
	}

	public void setImportoNettoAcquistiVerdi(BigDecimal importoNettoAcquistiVerdi) {
		this.importoNettoAcquistiVerdi = importoNettoAcquistiVerdi;
	}

	public BigDecimal getAliquotaIvaAcquistiVerdi() {
		return aliquotaIvaAcquistiVerdi;
	}

	public void setAliquotaIvaAcquistiVerdi(BigDecimal aliquotaIvaAcquistiVerdi) {
		this.aliquotaIvaAcquistiVerdi = aliquotaIvaAcquistiVerdi;
	}

	public String getOggettoAcquistiMaterialiRiciclati() {
		return oggettoAcquistiMaterialiRiciclati;
	}

	public void setOggettoAcquistiMaterialiRiciclati(String oggettoAcquistiMaterialiRiciclati) {
		this.oggettoAcquistiMaterialiRiciclati = oggettoAcquistiMaterialiRiciclati;
	}
	
	public String getCpvAcquistiMaterialiRiciclati() {
		return cpvAcquistiMaterialiRiciclati;
	}

	public void setCpvAcquistiMaterialiRiciclati(String cpvAcquistiMaterialiRiciclati) {
		this.cpvAcquistiMaterialiRiciclati = cpvAcquistiMaterialiRiciclati;
	}

	public BigDecimal getImportoNettoAcquistiMaterialiRiciclati() {
		return importoNettoAcquistiMaterialiRiciclati;
	}

	public void setImportoNettoAcquistiMaterialiRiciclati(BigDecimal importoNettoAcquistiMaterialiRiciclati) {
		this.importoNettoAcquistiMaterialiRiciclati = importoNettoAcquistiMaterialiRiciclati;
	}

	public BigDecimal getAliquotaIvaAcquistiMaterialiRiciclati() {
		return aliquotaIvaAcquistiMaterialiRiciclati;
	}

	public void setAliquotaIvaAcquistiMaterialiRiciclati(BigDecimal aliquotaIvaAcquistiMaterialiRiciclati) {
		this.aliquotaIvaAcquistiMaterialiRiciclati = aliquotaIvaAcquistiMaterialiRiciclati;
	}

	public boolean isAffidaenable() {
		return affidaenable;
	}

	public void setAffidaenable(boolean affidaenable) {
		this.affidaenable = affidaenable;
	}

	public boolean isExecenable() {
		return execenable;
	}

	public void setExecenable(boolean execenable) {
		this.execenable = execenable;
	}

	public boolean isProgramenable() {
		return programenable;
	}

	public void setProgramenable(boolean programenable) {
		this.programenable = programenable;
	}		
	
    public BigDecimal getGdl113program() {
		return gdl113program;
	}

	public void setGdl113program(BigDecimal gdl113program) {
		this.gdl113program = gdl113program;
	}

	public BigDecimal getGdl113affida() {
		return gdl113affida;
	}

	public void setGdl113affida(BigDecimal gdl113affida) {
		this.gdl113affida = gdl113affida;
	}

	public BigDecimal getGdl113exec() {
		return gdl113exec;
	}

	public void setGdl113exec(BigDecimal gdl113exec) {
		this.gdl113exec = gdl113exec;
	}

	public BigDecimal getGdl113totale() {
		return gdl113totale;
	}

	public void setGdl113totale(BigDecimal gdl113totale) {
		this.gdl113totale = gdl113totale;
	}	

	/*
	public Collection<CopertureEmbeddable> getCoperture() {
		return coperture;
	}

	public void setCoperture(Collection<CopertureEmbeddable> coperture) {
		this.coperture = coperture;
	}
	*/

	public BigDecimal getQuotaInnovazione() {
		return quotaInnovazione;
	}

	public void setQuotaInnovazione(BigDecimal quotaInnovazione) {
		this.quotaInnovazione = quotaInnovazione;
	}	

	public BigDecimal getFondoart113() {
		return fondoart113;
	}

	public void setFondoart113(BigDecimal fondoart113) {
		this.fondoart113 = fondoart113;
	}

	public Collection<CopertureProgramma> getCoperture() {
		return coperture;
	}

	public void setCoperture(Collection<CopertureProgramma> coperture) {
		this.coperture = coperture;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public StatoProgetto getStato() {
		return stato;
	}

	public void setStato(StatoProgetto stato) {
		this.stato = stato;
	}

	public boolean isFondoenable() {
		return fondoenable;
	}

	public void setFondoenable(boolean fondoenable) {
		this.fondoenable = fondoenable;
	}

	public boolean isQuotainnovazioneenable() {
		return quotainnovazioneenable;
	}

	public void setQuotainnovazioneenable(boolean quotainnovazioneenable) {
		this.quotainnovazioneenable = quotainnovazioneenable;
	}

	public boolean isQuotagdlenable() {
		return quotagdlenable;
	}

	public void setQuotagdlenable(boolean quotagdlenable) {
		this.quotagdlenable = quotagdlenable;
	}

	public Collection<CopertureRicompreseProgramma> getCoperturericomprese() {
		return coperturericomprese;
	}

	public void setCoperturericomprese(Collection<CopertureRicompreseProgramma> coperturericomprese) {
		this.coperturericomprese = coperturericomprese;
	}	
	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public BigDecimal getTotaleivaquadroeconomico() {
		return totaleivaquadroeconomico;
	}

	public void setTotaleivaquadroeconomico(BigDecimal totaleivaquadroeconomico) {
		this.totaleivaquadroeconomico = totaleivaquadroeconomico;
	}

	public BigDecimal getTotalequadroeconomico() {
		return totalequadroeconomico;
	}

	public void setTotalequadroeconomico(BigDecimal totalequadroeconomico) {
		this.totalequadroeconomico = totalequadroeconomico;
	}	
	
	public BigDecimal getTotalecoperture() {
		return totalecoperture;
	}

	public void setTotalecoperture(BigDecimal totalecoperture) {
		this.totalecoperture = totalecoperture;
	}

	public BigDecimal getCostototale() {
		return costototale;
	}

	public void setCostototale(BigDecimal costototale) {
		this.costototale = costototale;
	}

	public boolean isDelega() {
		return delega;
	}

	public void setDelega(boolean delega) {
		this.delega = delega;
	}

	public boolean isNondelega() {
		return nondelega;
	}

	public void setNondelega(boolean nondelega) {
		this.nondelega = nondelega;
	}

	public boolean isNonaggregabile() {
		return nonaggregabile;
	}

	public void setNonaggregabile(boolean nonaggregabile) {
		this.nonaggregabile = nonaggregabile;
	}

	public boolean isNolotto() {
		return nolotto;
	}

	public void setNolotto(boolean nolotto) {
		this.nolotto = nolotto;
	}

	public boolean isAffidamentoContrattoInEssere() {
		return affidamentoContrattoInEssere;
	}

	public void setAffidamentoContrattoInEssere(boolean affidamentoContrattoInEssere) {
		this.affidamentoContrattoInEssere = affidamentoContrattoInEssere;
	}

	public boolean isNonAffidamentoContrattoInEssere() {
		return nonAffidamentoContrattoInEssere;
	}

	public void setNonAffidamentoContrattoInEssere(boolean nonAffidamentoContrattoInEssere) {
		this.nonAffidamentoContrattoInEssere = nonAffidamentoContrattoInEssere;
	}

	public boolean isAffidamentoExArt63() {
		return affidamentoExArt63;
	}

	public void setAffidamentoExArt63(boolean affidamentoExArt63) {
		this.affidamentoExArt63 = affidamentoExArt63;
	}

	public boolean isNonAffidamentoExArt63() {
		return nonAffidamentoExArt63;
	}

	public void setNonAffidamentoExArt63(boolean nonAffidamentoExArt63) {
		this.nonAffidamentoExArt63 = nonAffidamentoExArt63;
	}

	public boolean isQuotacollaudoenable() {
		return quotacollaudoenable;
	}

	public void setQuotacollaudoenable(boolean quotacollaudoenable) {
		this.quotacollaudoenable = quotacollaudoenable;
	}

	public BigDecimal getGdl113collaudo() {
		return gdl113collaudo;
	}

	public void setGdl113collaudo(BigDecimal gdl113collaudo) {
		this.gdl113collaudo = gdl113collaudo;
	}

}
