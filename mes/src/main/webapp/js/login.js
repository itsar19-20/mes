$(() => {
    
	localStorage.removeItem('user');
	
    // una funzione () senza parametri
    $('#btnLogin').click(() => {
        $.ajax({
            url: '/login',
            method: 'post',
            data: { 
                username: $('#inputUsername').val(), 
                password: $('#inputPassword').val()
            }
        })

        // una funzione che ha parametro UTENTE dal backend
        .done((utente) => {
            
            console.log(utente);
            
            if (utente) {
                
                localStorage.setItem('user', JSON.stringify(utente));
                location.replace('/linea'); 
                
            } else {
                $('#msgFail').show();
            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    });
});