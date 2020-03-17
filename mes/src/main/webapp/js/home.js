$(() => {
    
    localStorage.clear(); 

    window.setInterval( aggiorna, 3000);
    
    $('#linea001').click(() => {
        
        $.ajax({
        
            url: '/home',
            method: 'post',
            data: {
        
                tipo: 'richiestaLinea',
                nome: 'linea uno',
                id: '001' 
            }
        })
        .done( (linea) => {
            
            if( linea ){
                localStorage.setItem('linea', JSON.stringify(linea)); 
                location.href = '/login';
            }
       })
       .fail((a, b) => console.log('fail!!', a, b));
   
    });

    $('#linea002').click(() => {
    
        $.ajax({
        
            url: '/home',
            method: 'post',
            data: {

                tipo: 'richiestaLinea',
                nome: 'linea due',
                id: '002' 
            }
        })
        .done( (linea) => {

            if( linea ){

                localStorage.setItem('linea', JSON.stringify(linea));
                location.href = '/login';   
            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    });

    $('#linea003').click(() => {
        
        $.ajax({    
        
            url: '/home',
            method: 'post',
            data: {
        
                tipo: 'richiestaLinea',
                nome: 'linea tre',
                id: '003' 
            }
        })
        .done( (linea) => {

            if( linea ){

                localStorage.setItem('linea', JSON.stringify(linea));
                location.href = '/login';   
            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    });

    function aggiorna(){

        $.ajax({

            url: '/home',
            method: 'post',
            data: {

                tipo: 'richiestaStatoLinee'
            }
        })
        .done( (string) =>{

            if (string) {

                stato = JSON.parse(string); 

                $('#stato001').text(stato[0].statoLinea);
                $('#stato002').text(stato[1].statoLinea);
                $('#stato003').text(stato[2].statoLinea); 
            }
        })
        .fail((a, b) => console.log('fail!!', a, b));
    }
});