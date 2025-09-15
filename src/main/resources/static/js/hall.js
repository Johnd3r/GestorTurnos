document.addEventListener('DOMContentLoaded', function() {
    const currentA = document.getElementById('currentA');
    const currentC = document.getElementById('currentC');
    
    let stompClient = null;

    function connect() {
        const socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            
            stompClient.subscribe('/topic/queue/A', function(message) {
                const ticket = JSON.parse(message.body);
                updateDisplay('A', ticket.id);
            });
            
            stompClient.subscribe('/topic/queue/C', function(message) {
                const ticket = JSON.parse(message.body);
                updateDisplay('C', ticket.id);
            });
        }, function(error) {
            console.error('WebSocket connection error:', error);
            setTimeout(connect, 5000);
        });
    }

    function updateDisplay(queue, ticketId) {
        const element = queue === 'A' ? currentA : currentC;
        element.textContent = ticketId;
        
        element.style.backgroundColor = '#FFD700';
        element.style.color = '#000';
        
        setTimeout(() => {
            element.style.backgroundColor = '#3a3a3a';
            element.style.color = '#fff';
        }, 3000);
    }

    function loadInitialState() {
        fetch('/api/state')
        .then(response => response.json())
        .then(state => {
            if (state.currentTickets.A) {
                currentA.textContent = state.currentTickets.A.id;
            }
            if (state.currentTickets.C) {
                currentC.textContent = state.currentTickets.C.id;
            }
        })
        .catch(error => {
            console.error('Error loading initial state:', error);
        });
    }

    loadInitialState();
    connect();
});