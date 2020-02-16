$(() => {
    
    localStorage.clear(); 
    
    $('#lineaUno').click(() => {
        
        $.ajax({
        
            url: '/home',
            method: 'post',
            data: {
        
                nome: 'linea uno',
                id: '001' 
            }
        })
        .done( (linea) => {

                if( linea ){
                        
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/login';
                }    
           })
        .fail((a, b) => console.log('fail!!', a, b));
    });

    $('#lineaDue').click(() => {
    
        $.ajax({
        
            url: '/home',
            method: 'post',
            data: {

                nome: 'linea due',
                id: '002' 
            }
        })
        .done( (linea) => {

                if( linea ){
                        
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/login';
                }    
           })
        .fail((a, b) => console.log('fail!!', a, b));
    });

    $('#lineaTre').click(() => {
        
        $.ajax({
        
            url: '/home',
            method: 'post',
            data: {
        
                nome: 'linea tre',
                id: '003' 
            }
        })
        .done( (linea) => {

                if( linea ){
                        
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/login';
                }    
           })
        .fail((a, b) => console.log('fail!!', a, b));
    });
});