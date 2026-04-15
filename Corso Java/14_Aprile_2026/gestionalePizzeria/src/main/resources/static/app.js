const utenteSelect = document.querySelector('#utenteSelect');
const refreshBtn = document.querySelector('#refreshBtn');
const utenteInfo = document.querySelector('#utenteInfo');
const adminPanel = document.querySelector('#adminPanel');
const userPanel = document.querySelector('#userPanel');
const adminOrders = document.querySelector('#adminOrders');
const menuList = document.querySelector('#menuList');
const cartList = document.querySelector('#cartList');
const userOrders = document.querySelector('#userOrders');
const submitOrderBtn = document.querySelector('#submitOrderBtn');
const createNomeInput = document.querySelector('#createNome');
const createEmailInput = document.querySelector('#createEmail');
const createIsAdminCheckbox = document.querySelector('#createIsAdmin');
const createUserBtn = document.querySelector('#createUserBtn');
const messageBox = document.querySelector('#messageBox');

let users = [];
let selectedUser = null;
let menuData = [];
let cart = {};
const statiOrdine = ['IN_ATTESA', 'CONFERMATO', 'CONSEGNATO'];

async function fetchJson(url, options = {}) {
  const response = await fetch(url, options);
  if (!response.ok) {
    const errorText = await response.text();
    throw new Error(errorText || response.statusText);
  }
  return response.json();
}

function showMessage(text, type = 'success') {
  messageBox.textContent = text;
  messageBox.className = `message-box ${type}`;
  messageBox.classList.remove('hidden');
  setTimeout(() => messageBox.classList.add('hidden'), 5000);
}

function clearMessage() {
  messageBox.textContent = '';
  messageBox.className = 'message-box hidden';
}

function formatDate(value) {
  return new Date(value).toLocaleString('it-IT', { dateStyle: 'short', timeStyle: 'short' });
}

function buildUserInfo() {
  if (!selectedUser) {
    utenteInfo.innerHTML = '<p>Seleziona un utente per iniziare.</p>';
    return;
  }
  utenteInfo.innerHTML = `
    <div class="card">
      <h2>${selectedUser.nome}</h2>
      <p><strong>Email:</strong> ${selectedUser.email}</p>
      <p><strong>Ruolo:</strong> ${selectedUser.isAdmin ? 'Admin' : 'Utente'}</p>
      <p><strong>Punti bonus:</strong> ${selectedUser.puntiBonus || 0}</p>
    </div>
  `;
}

function buildMenu() {
  if (!menuData.length) {
    menuList.innerHTML = '<p>Menu non disponibile.</p>';
    return;
  }
  menuList.innerHTML = menuData.map(item => {
    const quantita = cart[item.id] || 0;
    return `
      <div class="menu-card">
        <h4>${item.pizza.nome}</h4>
        <p>${item.pizza.descrizione || ''}</p>
        <p><strong>Ingredienti:</strong> ${item.pizza.ingredienti || '-'}</p>
        <p><strong>Prezzo:</strong> € ${item.prezzo.toFixed(2)}</p>
        <label>Quantità
          <input type="number" min="0" value="${quantita}" data-menu-id="${item.id}" class="qty-input" />
        </label>
      </div>
    `;
  }).join('');
  menuList.querySelectorAll('.qty-input').forEach(input => {
    input.addEventListener('change', onQuantityChange);
  });
}

function buildCart() {
  const cartItems = menuData.filter(item => cart[item.id] > 0);
  if (!cartItems.length) {
    cartList.innerHTML = '<p>Seleziona almeno una pizza per creare un ordine.</p>';
    return;
  }
  cartList.innerHTML = cartItems.map(item => {
    return `
      <div class="cart-item">
        <div><strong>${item.pizza.nome}</strong> x ${cart[item.id]}</div>
        <div>Totale: € ${(item.prezzo * cart[item.id]).toFixed(2)}</div>
      </div>
    `;
  }).join('');
}

function buildAdminOrders(orders) {
  if (!orders.length) {
    adminOrders.innerHTML = '<p>Non ci sono ordini.</p>';
    return;
  }
  adminOrders.innerHTML = orders.map(order => {
    const userLabel = order.utente ? `${order.utente.nome} (${order.utente.email})` : 'Utente sconosciuto';
    const statoOptions = statiOrdine.map(stato => `
      <option value="${stato}" ${order.stato === stato ? 'selected' : ''}>${stato}</option>
    `).join('');
    return `
      <div class="order-card">
        <h4>Ordine #${order.id}</h4>
        <p><strong>Utente:</strong> ${userLabel}</p>
        <p><strong>Data:</strong> ${order.dataOrdine ? formatDate(order.dataOrdine) : '-'}</p>
        <p><strong>Totale:</strong> € ${order.totale?.toFixed(2) || '0.00'}</p>
        <label>Stato
          <select data-order-id="${order.id}">${statoOptions}</select>
        </label>
        <button type="button" data-update-order-id="${order.id}">Aggiorna stato</button>
      </div>
    `;
  }).join('');
  adminOrders.querySelectorAll('[data-update-order-id]').forEach(button => {
    button.addEventListener('click', onUpdateOrderStatus);
  });
}

function buildUserOrders(orders) {
  if (!orders.length) {
    userOrders.innerHTML = '<p>Non hai ancora ordini.</p>';
    return;
  }
  userOrders.innerHTML = orders.map(order => {
    return `
      <div class="order-card">
        <h4>Ordine #${order.id}</h4>
        <p><strong>Data:</strong> ${order.dataOrdine ? formatDate(order.dataOrdine) : '-'}</p>
        <p><strong>Stato:</strong> ${order.stato}</p>
        <p><strong>Totale:</strong> € ${order.totale?.toFixed(2) || '0.00'}</p>
      </div>
    `;
  }).join('');
}

function onQuantityChange(event) {
  const input = event.currentTarget;
  const menuId = Number(input.dataset.menuId);
  const qty = Math.max(0, Number(input.value));
  cart[menuId] = qty;
  buildCart();
}

async function onUpdateOrderStatus(event) {
  const orderId = event.currentTarget.dataset.updateOrderId;
  const select = adminOrders.querySelector(`select[data-order-id="${orderId}"]`);
  const stato = select.value;
  try {
    await fetchJson(`/ordini/${orderId}/stato`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        'X-User-Id': selectedUser.id,
      },
      body: JSON.stringify({ stato }),
    });
    showMessage('Stato ordine aggiornato con successo.');
    await loadAdminOrders();
  } catch (error) {
    showMessage(`Errore durante l'aggiornamento: ${error.message}`, 'error');
  }
}

async function onSubmitOrder() {
  const items = Object.entries(cart)
    .filter(([, qty]) => qty > 0)
    .map(([menuId, qty]) => ({ pizzaMenu: { id: Number(menuId) }, quantita: qty }));
  if (!items.length) {
    showMessage('Seleziona almeno una pizza per inviare un ordine.', 'error');
    return;
  }
  try {
    await fetchJson('/ordini', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ utenteId: selectedUser.id, items }),
    });
    cart = {};
    buildMenu();
    buildCart();
    showMessage('Ordine inviato correttamente.');
    await loadUserOrders();
  } catch (error) {
    showMessage(`Errore durante l'invio dell'ordine: ${error.message}`, 'error');
  }
}

async function loadUsers(preselectId = null) {
  try {
    users = await fetchJson('/utenti');
    if (!users.length) {
      utenteSelect.innerHTML = '<option value="">Nessun utente trovato</option>';
      selectedUser = null;
      buildUserInfo();
      setupMode();
      return;
    }
    utenteSelect.innerHTML = users.map(user => `
      <option value="${user.id}">${user.nome} (${user.isAdmin ? 'Admin' : 'Utente'})</option>
    `).join('');
    selectedUser = preselectId ? users.find(user => user.id === preselectId) || users[0] : users[0];
    utenteSelect.value = selectedUser.id;
    buildUserInfo();
    setupMode();
  } catch (error) {
    utenteSelect.innerHTML = '<option value="">Errore caricamento utenti</option>';
    showMessage(`Impossibile caricare gli utenti: ${error.message}`, 'error');
  }
}

async function createUser() {
  const nome = createNomeInput.value.trim();
  const email = createEmailInput.value.trim();
  const isAdmin = createIsAdminCheckbox.checked;
  if (!nome || !email) {
    showMessage('Compila nome e email per creare un utente.', 'error');
    return;
  }
  try {
    const createdUser = await fetchJson('/utenti', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ nome, email, isAdmin }),
    });
    createNomeInput.value = '';
    createEmailInput.value = '';
    createIsAdminCheckbox.checked = false;
    showMessage('Utente creato con successo.');
    await loadUsers(createdUser.id);
  } catch (error) {
    showMessage(`Errore nella creazione dell'utente: ${error.message}`, 'error');
  }
}

async function loadMenu() {
  try {
    menuData = await fetchJson('/menu');
    menuData = menuData.filter(item => item.disponibile);
    cart = {};
    buildMenu();
    buildCart();
  } catch (error) {
    menuList.innerHTML = '<p>Impossibile caricare il menu.</p>';
    showMessage(`Errore menu: ${error.message}`, 'error');
  }
}

async function loadAdminOrders() {
  try {
    const orders = await fetchJson('/ordini/all');
    buildAdminOrders(orders);
  } catch (error) {
    adminOrders.innerHTML = '<p>Impossibile caricare gli ordini.</p>';
    showMessage(`Errore ordini admin: ${error.message}`, 'error');
  }
}

async function loadUserOrders() {
  if (!selectedUser) return;
  try {
    const orders = await fetchJson(`/ordini/utente/${selectedUser.id}`);
    buildUserOrders(orders);
  } catch (error) {
    userOrders.innerHTML = '<p>Impossibile caricare gli ordini.</p>';
    showMessage(`Errore ordini utente: ${error.message}`, 'error');
  }
}

function setupMode() {
  clearMessage();
  buildUserInfo();
  const isAdmin = selectedUser?.isAdmin;
  adminPanel.classList.toggle('hidden', !isAdmin);
  userPanel.classList.toggle('hidden', isAdmin);
  if (!selectedUser) {
    adminPanel.classList.add('hidden');
    userPanel.classList.add('hidden');
    return;
  }
  if (isAdmin) {
    loadAdminOrders();
  } else {
    loadMenu();
    loadUserOrders();
  }
}

utenteSelect.addEventListener('change', () => {
  selectedUser = users.find(user => user.id === Number(utenteSelect.value));
  setupMode();
});
refreshBtn.addEventListener('click', () => loadUsers());
submitOrderBtn.addEventListener('click', () => onSubmitOrder());
createUserBtn.addEventListener('click', () => createUser());

loadUsers();
