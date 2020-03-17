$(() => {

    if( localStorage.getItem('user') == null ){
        
        location.replace('/');
    }

    //globals
    var htmlRiquadroNota = '<form class="needs-validation" novalidate>    <div class="mb-3">      <label for="email">Email <span class="text-muted"></span></label>   <input type="email" class="form-control" id="email" placeholder="mario.rossi@gmail.com">       <div class="invalid-feedback">            Please enter a valid email address for shipping updates.        </div>    </div>   <div class="form-group">        <label for="inputState">Stazione:</label>        <select id="inputState" class="form-control">        <option selected>Choose...</option>        <option>...</option>        </select>    </div>  <form class="was-validated">        <div class="mb-3">        <label for="validationTextarea">Note:</label>        <textarea class="form-control" style="height:6em" id="validationTextarea" placeholder="Required example textarea" required></textarea>        <div class="invalid-feedback">            Please enter a message in the textarea.        </div>        </div>    </form>    <hr class="mb-4">    <button class="btn btn-primary btn-lg btn-block" type="submit">Invia nota</button></form>'; 

    var linea = JSON.parse(localStorage.getItem('linea'));
    var utente = JSON.parse(localStorage.getItem('user'));

    var STOP_STRING_VALUE = 'true'; 

    //setup
    $('#titolo').text( 'Linea: ' + linea.codiceLinea);
    $('#nomeUtente').text( utente.nome);
    
    $('#riquadro-sx').html( htmlRiquadroNota);

    $('#nomeLineaRiquadroDestro').text( 'Linea: ' + linea.codiceLinea);
    aggiornaLinea(); 

    //aggiornamento stazioni
    window.setInterval( aggiornaLinea, 10000); 

    $('#btnAvvia').click(() => {

        STOP_STRING_VALUE = 'false'; 

        $.ajax({

            url: '/linea',
            method: 'post',
            data: {

                tipo: 'avviamento',
                codiceLinea: linea.codiceLinea,
                stop: STOP_STRING_VALUE
            } 
        })
    });
    $('#btnStop').click(() => {

        STOP_STRING_VALUE = 'true'; 

        $.ajax({

            url: '/linea',
            method: 'post',
            data: {

                tipo: 'stop', 
                codiceLinea: linea.codiceLinea,
                stop: STOP_STRING_VALUE
            } 
        })
    });
    $('#btnPausa').click(() => {

        STOP_STRING_VALUE = 'true'; 

        $.ajax({

            url: '/linea',
            method: 'post',
            data: {

                tipo: 'pausa',
                codiceLinea: linea.codiceLinea,
                stop: STOP_STRING_VALUE
            } 
        })
    });

    $('#writeNota').click( () => {

        $('#riquadro-sx').html( htmlRiquadroNota); 
        $('#readAnomalia').removeClass('active'); 
        $('#writeNota').addClass('active'); 

    });

    $('#readAnomalia').click( () => {

        $('#riquadro-sx').html('<p> test</p>'); 
        $('#writeNota').removeClass('active'); 
        $('#readAnomalia').addClass('active'); 

    }); 

    $('#btnHOME').click( () => {

        location.replace('/'); 
    }); 

    /*
    *   funzioni
    */
    
    function aggiornaLinea() {

        $.ajax({

            url: '/linea',
            method: 'post',
            data: {

                tipo: 'aggiornamento', 
                codiceLinea: linea.codiceLinea,
                stop: STOP_STRING_VALUE
            } 
        })
        .done( (inputArray) => {

            var result = ""; 

            $.each( inputArray, function( index, stato){

                    result += '<li class="list-group-item d-flex justify-content-between lh-condensed"> <div> <h6 class="my-0">'+ stato.stazione.nome + '</h6>' + '<small class="text-muted">' + stato.statoSegnale  + '</small> </div> </li>'; 
            }); 
            
            //manipolazione DOM
            $('#stazioniRiquadroDestro').html( result); 
        })
        .fail((a, b) => console.log('fail!!', a, b));
    }
}); 