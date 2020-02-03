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

              // una funzione che ha parametro UTENTE 
        .done((utente) => {
            if (utente) {
                localStorage.setItem('user', JSON.stringify(utente));
                location.href = './home.html';
            } else {
                $('#msgFail').show();
            }
        })
    });
});