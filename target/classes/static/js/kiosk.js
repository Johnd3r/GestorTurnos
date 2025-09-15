document.addEventListener('DOMContentLoaded', function() {
    const btnAsesoria = document.getElementById('btnAsesoria');
    const btnCaja = document.getElementById('btnCaja');
    const ticketDisplay = document.getElementById('ticketDisplay');
    const ticketNumber = document.getElementById('ticketNumber');

    btnAsesoria.addEventListener('click', function() {
        generateTicket('A');
    });

    btnCaja.addEventListener('click', function() {
        generateTicket('C');
    });

    function generateTicket(queueType) {
        fetch(`/api/tickets/${queueType}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(ticket => {
            ticketNumber.textContent = ticket.id;
            ticketDisplay.style.display = 'block';
            
            setTimeout(() => {
                ticketDisplay.style.display = 'none';
            }, 5000);
        })
        .catch(error => {
            console.error('Error generating ticket:', error);
            alert('Error al generar turno');
        });
    }
});