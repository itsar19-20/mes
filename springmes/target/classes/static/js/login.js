/**
 * 	LOGIN
 */

$(() => {

	localStorage.removeItem('user');

    $('#btnLogin').click(() => {
        $.ajax({
            url: '/login',
            method: 'post',
            data: { 
                nome: $('#inputUsername').val(), 
                password: $('#inputPassword').val()
            }
        })
        .done((utente) => {
            
            console.log(utente);
            
            if (utente) {
                
                localStorage.setItem('user', JSON.stringify(utente));
                
                if( localStorage.getItem('codice') != null ){
                	
                	location.replace('/quadro?codice='+localStorage.getItem('codice')); 
                
                }else{
                	
                	$('#msgFailCode').show();
                	window.setTimeout( function () { location.href = '/';}, 8000); 
                }
                
            } else {
            	
                $('#msgFailAuth').show();
            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    });
});