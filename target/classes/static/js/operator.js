document.addEventListener('DOMContentLoaded', function() {
    const btnNext = document.getElementById('btnNext');
    const currentTicket = document.getElementById('currentTicket');
    const queueSize = document.getElementById('queueSize');
    const role = btnNext.getAttribute('data-role');

    btnNext.addEventListener('click', function() {
        callNext();
    });

    function callNext() {
        btnNext.disabled = true;
        
        fetch(`/api/tickets/${role}/next`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(ticket => {
            if (ticket) {
                currentTicket.textContent = ticket.id;
            } else {
                currentTicket.textContent = '---';
                alert('No hay turnos en cola');
            }
            updateQueueInfo();
        })
        .catch(error => {
            console.error('Error calling next:', error);
            alert('Error al llamar siguiente turno');
        })
        .finally(() => {
            btnNext.disabled = false;
        });
    }

    function updateQueueInfo() {
        fetch('/api/state')
        .then(response => response.json())
        .then(state => {
            queueSize.textContent = state.queueSizes[role] || 0;
        })
        .catch(error => {
            console.error('Error updating queue info:', error);
        });
    }

    // Update queue info on load
    updateQueueInfo();
    
    // Update every 10 seconds
    setInterval(updateQueueInfo, 10000);
});