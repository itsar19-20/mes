/**
 *	HOMEPAGE 
 */

$(() => {
	
	/*
	 * SETUP
	 */
    localStorage.clear(); 

    /*
     * UPDATE
     */
    window.setInterval( aggiorna, 5000);
    
    /*
     * BUTTONS
     */
    $('#linea001').click(() => {
        
    	localStorage.setItem('codice', '001');
    	location.href = '/login';
   
    });

    $('#linea002').click(() => {

    	localStorage.setItem('codice', '002');
    	location.href = '/login';
    	
    });

    $('#linea003').click(() => {

    	localStorage.setItem('codice', '003');
    	location.href = '/login';
        
    });

    function aggiorna(){

        $.ajax({

            url: '/statolinee',
            method: 'get'
        })
        .done( (stati) =>{

            if (stati) {

                $('#body001').css('background-color', bgcolor(stati[1].stato)); 
                $('#stato001').text(stati[1].stato);

                $('#body002').css('background-color', bgcolor(stati[2].stato)); 
                $('#stato002').text(stati[2].stato);

                $('#body003').css('background-color', bgcolor(stati[3].stato));                
                $('#stato003').text(stati[3].stato);

            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    }

    function bgcolor( statoLinea ){

        var color = ""; 

        if( statoLinea == 'avviata'){

            color = 'lightgreen'; 

        }else if( statoLinea == 'ferma'){

            color = 'white'; 

        }else if( statoLinea == 'errore'){

            color = 'lightpink'; 

        }else if( statoLinea == 'pausa'){

            color = 'lightyellow';             
        }

        return color;
    }
});
