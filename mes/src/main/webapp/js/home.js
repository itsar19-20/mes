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
        .done( (snapshot) => {

            if( snapshot){
                        
                localStorage.setItem( 'snapshot', JSON.stringify(snapshot));
                console.log(JSON.stringify(snapshot));
            
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
        .done( (snapshot) => {

                if( snapshot){
                            
                    localStorage.setItem( 'snapshot', JSON.stringify(snapshot));
                    console.log(JSON.stringify(snapshot));
                
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
        .done( (snapshot) => {

                if( snapshot){
                        
                    localStorage.setItem( 'snapshot', JSON.stringify(snapshot));
                    console.log(JSON.stringify(snapshot));
                
                    location.href = '/login';
                }    
           })
        .fail((a, b) => console.log('fail!!', a, b));
    });
});