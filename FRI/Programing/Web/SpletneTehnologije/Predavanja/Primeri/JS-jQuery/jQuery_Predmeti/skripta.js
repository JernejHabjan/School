var predmeti=["Uvod v računalništvo","Programiranje I","Računalniška arhitektura","Matematika","Diskretne strukture","Programiranje II","Podatkovne baze","Računalniške komunikacije","Operacijski sistemi","Osnove verjetnosti in statistike"];

$(document).ready(function(){

	// dodamo vse predmete
	for(i=0; i < predmeti.length; i++){
		$("#predmeti").append("<option>" + predmeti[i] + "</option>");
	}
	
	$("#dodaj").click(function(){
		var izbran = $("#predmeti option:selected").html();
		if (izbran != null)
		{
			$("#izbrani").append("<option>" + izbran + "</option>");
			$("#predmeti option:selected").remove();
		}
	});
	
	$("#odstrani").click(function(){
		var izbran = $("#izbrani option:selected").html();
		if (izbran != null)
		{
			$("#predmeti").append("<option>" + izbran + "</option>");
			$("#izbrani option:selected").remove();
		}
	});
	
});

