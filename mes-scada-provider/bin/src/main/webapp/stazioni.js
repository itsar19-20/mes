$(function() {
	setInterval(() => {
		$.ajax({
			url: '/stato-stazioni',
			method: 'get'
		})
		.done(function(stazioni) {
			$('#stazioni').empty();
			stazioni.forEach(st => {
				$('#stazioni').append(`<li>${st.linea.nome} + ${st.nome}: ${st.stato}</li>`)
			});
		});		
	}, 1000);
	
});