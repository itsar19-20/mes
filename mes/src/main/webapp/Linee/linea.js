$(() => {

    if( localStorage.getItem('user') == null ){
        
        location.replace('/');
    }

    //setup iniziale
    var htmlRiquadroNota = '<form class="needs-validation" novalidate>    <div class="mb-3">      <label for="email">Email <span class="text-muted"></span></label>   <input type="email" class="form-control" id="email" placeholder="mario.rossi@gmail.com">       <div class="invalid-feedback">            Please enter a valid email address for shipping updates.        </div>    </div>   <div class="form-group">        <label for="inputState">Stazione:</label>        <select id="inputState" class="form-control">        <option selected>Choose...</option>        <option>...</option>        </select>    </div>  <form class="was-validated">        <div class="mb-3">        <label for="validationTextarea">Note:</label>        <textarea class="form-control" id="validationTextarea" placeholder="Required example textarea" required></textarea>        <div class="invalid-feedback">            Please enter a message in the textarea.        </div>        </div>    </form>    <hr class="mb-4">    <button class="btn btn-primary btn-lg btn-block" type="submit">Invia nota</button></form>'; 
    $('#riquadro-sx').html( htmlRiquadroNota);
    
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

}); 