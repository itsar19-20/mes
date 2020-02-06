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

!function ($) {
  
        'use strict';
    
        $(function () {
            $('[data-toggle="password"]').each(function () {
                var input = $(this);
                var eye_btn = $(this).parent().find('.input-group-text');
                eye_btn.css('cursor', 'pointer').addClass('input-password-hide');
                eye_btn.on('click', function () {
                    if (eye_btn.hasClass('input-password-hide')) {
                        eye_btn.removeClass('input-password-hide').addClass('input-password-show');
                        eye_btn.find('.fa').removeClass('fa-eye').addClass('fa-eye-slash')
                        input.attr('type', 'text');
                    } else {
                        eye_btn.removeClass('input-password-show').addClass('input-password-hide');
                        eye_btn.find('.fa').removeClass('fa-eye-slash').addClass('fa-eye')
                        input.attr('type', 'password');
                    }
                });
            });
        });
    
    }(window.jQuery);