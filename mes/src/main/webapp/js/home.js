$(() => {
    
    localStorage.clear(); 
    
    $('#lineaUno').click(() => {
        
        if( localStorage.getItem('user') == null ){

            location.href = '/login';

       }else{

           $.ajax({
               url: '/home',
               method: 'post',
               data: {
                   nome: 'Stampaggio componenti plastiche',
                   id: '001' 
               }
           })
           .done( (linea) => {

                if( linea ){
                        
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/linea';
                }    
           })
           .fail((a, b) => console.log('fail!!', a, b));
       }
    });

    $('#lineaDue').click(() => {
        
        if( localStorage.getItem('user') == null ){

            location.href = '/login';

       }else{

            $.ajax({
                url: '/home',
                method: 'post',
                data: {
                    nome: 'Assemblaggio delle parti',
                    id: '002' 
                }
            })
            .done( (linea) => {

                if( linea ){
                    
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/linea';
                }
            })
            .fail((a, b) => console.log('fail!!', a, b));
       }
    });

    $('#lineaTre').click(() => {
        
       if( localStorage.getItem('user') == null ){

            location.href = '/login';

       }else{

            $.ajax({
                url: '/home',
                method: 'post',
                data: {
                    nome: 'Confezionamento',
                    id: '003' 
                }
            })
            .done( (linea) => {

                if( linea ){
                    
                    localStorage.setItem( 'linea', JSON.stringify(linea));
                    location.href = '/linea';
                }
            })
            .fail((a, b) => console.log('fail!!', a, b));
       }
    });
});