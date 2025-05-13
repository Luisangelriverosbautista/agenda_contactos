<script setup>
import { ref, onMounted } from 'vue';
import { computed } from 'vue';
import axios from 'axios';
import imagenPrincipal from '../assets/imgPrincipal.jpg';
import iconoNav from '../assets/libreta-de-contactos.png';
const Contactos = ref([]);

const ContactosRecientes = computed(() => {
  return Contactos.value.slice().sort((a, b) => b.id - a.id).slice(0, 3).reverse();
});
const cargarContactos = async () => {

    const response = await axios.get('http://localhost:8080/contactos/traerContactos')
    Contactos.value = response.data;
    console.log(Contactos.value);

}
onMounted(cargarContactos);

</script>

<template>
    <!-- NAVBAR -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark px-4">
        <div class="container-fluid">
            <router-link class="navbar-brand d-flex align-items-center" to="/">
                <img :src="iconoNav" width="35" height="35" class="me-2" alt="icono_nav">
                Agenda Contactos
            </router-link>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarContent">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <router-link class="nav-link" to="/">Inicio</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/contactos">Contactos</router-link>
                    </li>
                    <li class="nav-item">
                        <router-link class="nav-link" to="/agregar">Agregar Contacto</router-link>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- PORTADA PRINCIPAL -->
    <div class="position-relative mt-3" style="height: 640px; overflow: hidden;">
        <img :src="imagenPrincipal" class="position-absolute top-0 start-0 w-100 h-100 object-fit-cover"
            style="z-index: -1;" alt="Imagen principal">

        <div class="position-absolute top-50 start-50 translate-middle text-white text-center px-3 py-4 bg-dark bg-opacity-75 rounded text-dark"
            style="max-width: 90%; max-height: 90%; overflow-y: auto;">
            <h1 class="fw-bold fs-1">Bienvenido a tu Agenda Personal</h1>
            <p class="fs-4">Administra fácilmente tus contactos.</p>
            <p class="fs-4">Agrega, edita y elimina información de personas importantes.</p>
            <p class="fs-4">Accede a tus contactos desde cualquier lugar.</p>
        </div>
    </div>

    <!-- CONTACTOS DESTACADOS -->
    <div class="container my-5">
        <h2 class="text-center fw-bold mb-4">Contactos Recientes</h2>
        <div class="row g-4">
            <div class="col-md-4" v-for="Contacto in ContactosRecientes" :key="Contacto.id" >
                <div class="card h-100 shadow-sm">
                    <img :src="Contacto.imagen" class="card-img-top" alt="Imagen de contacto"
                        style="height: 200px; object-fit: cover;">
                     
                    <div class="card-body"> 
                        <h5 class="card-title"> {{ Contacto.nombre }} </h5>
                        <p class="card-text">Teléfono:{{Contacto.telefono}}
                            <br>
                            Correo: {{Contacto.email }}</p>
                        <router-link to="/contactos" class="btn btn-outline-primary  w-100"> Ver Detalles</router-link>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">&copy; 2025 Agenda Contactos - Todos los derechos reservados</p>
    </footer>
</template>

<style scoped>
.object-fit-cover {
    object-fit: cover;
}

div::-webkit-scrollbar {
    display: none;
}
</style>