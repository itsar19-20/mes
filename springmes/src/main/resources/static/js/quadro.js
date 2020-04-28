/**
 * 	QUADRO DI CONTROLLO
 */

$(() => {
	

	if( localStorage.getItem('user') == null || $('#codice').text() == null ){
        
        location.replace('/');
    }

    /*
     * GLOBALS
     */
    var htmlRiquadroNota = '<form class="needs-validation" novalidate>    <div class="mb-3">      <label for="email">Email <span class="text-muted"></span></label>   <input type="email" class="form-control" id="email" placeholder="mario.rossi@gmail.com">       <div class="invalid-feedback">            Please enter a valid email address for shipping updates.        </div>    </div>   <div class="form-group">        <label for="inputState">Stazione:</label>        <select id="inputState" class="form-control">        <option selected>Choose...</option>        <option>...</option>        </select>    </div>  <form class="was-validated">        <div class="mb-3">        <label for="validationTextarea">Note:</label>        <textarea class="form-control" style="height:6em" id="validationTextarea" placeholder="Required example textarea" required></textarea>        <div class="invalid-feedback">            Please enter a message in the textarea.        </div>        </div>    </form>    <hr class="mb-4">    <button class="btn btn-info btn-lg btn-block" type="submit">Invia nota</button></form>'; 
    var htmlRiquadroAnomalie = '<ul id="listaRiquadroAnomalie" class="list-group mb-3"> <li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 1</h6><small class="text-muted">timestamp</small> </div> </li><li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 2</h6><small class="text-muted">timestamp</small> </div> </li><li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 3</h6><small class="text-muted">timestamp</small> </div> </li><li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 4</h6><small class="text-muted">timestamp</small> </div> </li><li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 5</h6><small class="text-muted">timestamp</small> </div> </li><li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">Anomalia 6</h6><small class="text-muted">timestamp</small> </div> </li></ul>';

    var utente = JSON.parse(localStorage.getItem('user'));
    
    /*
     * SETUP
     */
    $('#nomeUtente').text(utente.nome);
    $('#riquadro-sx').html( htmlRiquadroNota);

    /*
     * UPDATE
     */
    window.setInterval( aggiorna, 5000); 

    
    /*
     * BUTTONS
     */
    $('#btnAvvia').click(() => {

        $.ajax({

            url: '/linea?stato=avviata&codice=' + $('#codice').text(),
            method: 'post'
        })
    });
    $('#btnStop').click(() => {

        $.ajax({

            url: '/linea?stato=ferma&codice=' + $('#codice').text(),
            method: 'post'         
        })
    });
    $('#btnPausa').click(() => {

        $.ajax({

            url: '/linea?stato=pausa&codice=' + $('#codice').text(),
            method: 'post' 
        })
    });

    $('#tabNota').click( () => {

        $('#riquadro-sx').html( htmlRiquadroNota); 
        $('#tabAnomalia').removeClass('active'); 
        $('#tabNota').addClass('active'); 

    });

    $('#tabAnomalia').click( () => {

        $('#riquadro-sx').html(	htmlRiquadroAnomalie); 
        $('#tabNota').removeClass('active'); 
        $('#tabAnomalia').addClass('active'); 

    }); 

    $('#btnHOME').click( () => {

    	localStorage.clear(); 
        location.replace('/'); 
    }); 

    
    /*
    *   FUNZIONI
    */
    
    function aggiorna() {

        $.ajax({

            url: '/linea?codice='+ $('#codice').text(),
            method: 'get'
        })
        .done( (stazioni) => { 

            if( stazioni) {
            	
            	for( var i=0; i < stazioni.length; i++){
            		
            		console.log('debug:'+stazioni[i].segnale);
            		$('#item'  + i ).attr('style', 'background-color:'+ bgcolor(stazioni[i].segnale) + ';');
            		$('#value' + i ).text(stazioni[i].segnale);
            	}
            } 
        })
        .fail((a, b) => console.log('fail!!', a, b));
    }
    
    function bgcolor( statoLinea ){

        var color = ""; 

        if( statoLinea == 'oggetto'){

            color = 'lightcyan'; 

        }else if( statoLinea == 'libera'){

            color = 'white'; 

        }else if( statoLinea == 'anomalia'){

            color = 'lightpink'; 

        }else if( statoLinea == 'azione'){

            color = 'cyan';             
        }

        return color;
    }
}); 