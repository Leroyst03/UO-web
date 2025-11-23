// Elementos del DOM
const mobileMenuBtn = document.getElementById('mobileMenuBtn');
const closeMenuBtn = document.getElementById('closeMenuBtn');
const mobileMenu = document.getElementById('mobileMenu');
const pageTransition = document.getElementById('pageTransition');

// Variables globales
let currentSection = 'home';

// Inicialización
document.addEventListener("DOMContentLoaded", () => {
    console.log("DOM cargado - Inicializando aplicación");
    initializeApp();
});

function initializeApp() {
    // Configurar event listeners
    setupEventListeners();
    
    // Ocultar transición inicial después de un tiempo
    setTimeout(() => {
        if (pageTransition) {
            pageTransition.classList.remove('active');
        }
    }, 1000);
    
    // Inicializar desde la URL actual
    initializeFromURL();
}

function setupEventListeners() {
    console.log("Configurando event listeners");
    
    // Menú móvil
    if (mobileMenuBtn) {
        mobileMenuBtn.addEventListener('click', () => {
            console.log("Abriendo menú móvil");
            if (mobileMenu) mobileMenu.classList.add('active');
        });
    }
    
    if (closeMenuBtn) {
        closeMenuBtn.addEventListener('click', () => {
            console.log("Cerrando menú móvil");
            if (mobileMenu) mobileMenu.classList.remove('active');
        });
    }
    
    // Cerrar menú al hacer clic en un enlace
    document.querySelectorAll('.mobile-menu a').forEach(link => {
        link.addEventListener('click', () => {
            if (mobileMenu) mobileMenu.classList.remove('active');
        });
    });
}

// Función para cargar publicaciones desde el backend
async function loadPublicaciones() {
    const lista = document.getElementById("lista-publicaciones");
    if (!lista) {
        console.error("No se encontró el elemento lista-publicaciones");
        return;
    }
    
    console.log("Cargando publicaciones desde el backend...");
    
    // Mostrar estado de carga
    lista.innerHTML = `
        <div class="loading-state">
            <i class="fas fa-spinner fa-spin"></i>
            <p>Cargando publicaciones...</p>
        </div>
    `;
    
    try {
        const response = await fetch("/api/publicaciones");
        console.log("Respuesta recibida:", response.status);
        
        if (!response.ok) {
            throw new Error(`Error ${response.status}: ${response.statusText}`);
        }
        
        const publicaciones = await response.json();
        console.log("Publicaciones cargadas:", publicaciones);
        
        // Limpiar lista
        lista.innerHTML = "";
        
        if (!publicaciones || publicaciones.length === 0) {
            lista.innerHTML = `
                <div class="empty-state">
                    <i class="fas fa-book-open"></i>
                    <h3>No hay publicaciones disponibles</h3>
                    <p>Pronto se publicarán nuevos contenidos académicos.</p>
                </div>
            `;
            return;
        }
        
        // Renderizar publicaciones
        publicaciones.forEach(pub => {
            const card = createPublicationCard(pub);
            lista.appendChild(card);
        });
        
    } catch (error) {
        console.error("Error al cargar publicaciones:", error);
        showErrorState(lista, error);
    }
}

// Crear tarjeta de publicación
function createPublicationCard(pub) {
    const card = document.createElement("div");
    card.className = "card";
    
    // Verificar que los campos existan
    const titulo = pub.titulo || 'Sin título';
    const texto = pub.texto || '';
    const autor = pub.autor || 'Autor desconocido';
    const grupo = pub.grupo || 'Grupo no especificado';
    const id = pub.id || '';
    
    // Crear texto abreviado (máximo 120 caracteres)
    const textoAbreviado = texto.length > 120 
        ? texto.substring(0, 120) + '...' 
        : texto;
    
    card.innerHTML = `
        <div class="article-image">
            <img src="https://images.unsplash.com/photo-1481627834876-b7833e8f5570?ixlib=rb-4.0.3&auto=format&fit=crop&w=800&q=80" alt="${titulo}">
        </div>
        <div class="article-content">
            <span class="article-category">${grupo}</span>
            <h3 class="article-title">${titulo}</h3>
            <p class="article-excerpt">${textoAbreviado}</p>
            <div class="article-meta">
                <div class="article-author">
                    <div class="author-avatar">${getInitials(autor)}</div>
                    <span>${autor}</span>
                </div>
                <div class="read-more">Leer más <i class="fas fa-arrow-right"></i></div>
            </div>
        </div>
    `;
    
    // Añadir evento click para ver publicación completa
    if (id) {
        card.addEventListener('click', () => {
            console.log("Navegando a publicación:", id);
            window.location.href = `/publicacion/${id}`;
        });
        
        // Hacer que el card sea clickeable
        card.style.cursor = 'pointer';
    }
    
    return card;
}

// Mostrar estado de error
function showErrorState(lista, error) {
    lista.innerHTML = `
        <div class="error-state">
            <i class="fas fa-exclamation-triangle"></i>
            <h3>Error al cargar publicaciones</h3>
            <p>${error.message}</p>
            <button class="btn btn-primary" onclick="loadPublicaciones()">Reintentar</button>
        </div>
    `;
}

// Función auxiliar para obtener iniciales del autor
function getInitials(nombre) {
    if (!nombre) return '??';
    
    return nombre
        .split(' ')
        .map(part => part.charAt(0))
        .join('')
        .toUpperCase()
        .substring(0, 2);
}

// Navegación entre secciones
function navigateTo(section) {
    console.log("Navegando a:", section);
    
    // Mostrar transición
    if (pageTransition) {
        pageTransition.classList.add('active');
    }
    
    setTimeout(() => {
        // Ocultar todas las secciones
        const sections = ['homeSection', 'publicacionesSection', 'supportSection'];
        sections.forEach(sectionId => {
            const element = document.getElementById(sectionId);
            if (element) element.style.display = 'none';
        });
        
        // Mostrar sección seleccionada
        let targetElement = null;
        if (section === 'home') {
            targetElement = document.getElementById('homeSection');
            currentSection = 'home';
            window.history.pushState({ section: 'home' }, '', '/');
        } else if (section === 'publicaciones') {
            targetElement = document.getElementById('publicacionesSection');
            currentSection = 'publicaciones';
            window.history.pushState({ section: 'publicaciones' }, '', '/publicaciones');
            // Cargar publicaciones cuando se muestra la sección
            setTimeout(() => loadPublicaciones(), 100);
        } else if (section === 'soporte') {
            targetElement = document.getElementById('supportSection');
            currentSection = 'soporte';
            window.history.pushState({ section: 'soporte' }, '', '/soporte');
        }
        
        if (targetElement) {
            targetElement.style.display = section === 'home' ? 'flex' : 'block';
        }
        
        // Ocultar transición
        if (pageTransition) {
            pageTransition.classList.remove('active');
        }
        
        // Cerrar menú móvil si está abierto
        if (mobileMenu) {
            mobileMenu.classList.remove('active');
        }
        
        // Scroll al inicio
        window.scrollTo(0, 0);
        
    }, 500);
}

// Manejar navegación con el botón atrás/adelante del navegador
window.addEventListener('popstate', (event) => {
    console.log("Popstate event:", event);
    const path = window.location.pathname;
    if (path === '/' || path === '') {
        navigateTo('home');
    } else if (path === '/publicaciones') {
        navigateTo('publicaciones');
    } else if (path === '/soporte') {
        navigateTo('soporte');
    }
});

// Inicializar según la URL actual
function initializeFromURL() {
    const path = window.location.pathname;
    console.log("Inicializando desde URL:", path);
    
    if (path === '/publicaciones' || path === '/publicaciones/') {
        navigateTo('publicaciones');
    } else if (path === '/soporte' || path === '/soporte/') {
        navigateTo('soporte');
    } else {
        navigateTo('home');
    }
}

// Manejar errores no capturados
window.addEventListener('error', (event) => {
    console.error('Error global:', event.error);
});

// Exportar funciones para uso global
window.navigateTo = navigateTo;
window.loadPublicaciones = loadPublicaciones;