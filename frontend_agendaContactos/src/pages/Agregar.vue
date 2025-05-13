<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
import iconoNav from '../assets/libreta-de-contactos.png';
import sweet from 'sweetalert2';

const router = useRouter();

const nuevoContacto = ref({
    nombre: '',
    direccion: '',
    telefono: '',
    email: '',
    oficio: '',
    imagen: ''
});

const guardarContacto = async () => {
        await axios.post('http://localhost:8080/contactos/agregarContacto', nuevoContacto.value);
        sweet.fire({
            icon: 'success',
            title: 'Contacto guardado',
            text: 'El contacto ha sido guardado exitosamente.',
            confirmButtonText: 'Aceptar'
        });
        router.push('/contactos');
  
};
</script>

<template>
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
    <div class="container my-5">
        <h2 class="text-center fw-bold mb-4">Agregar Contacto</h2>
        <form @submit.prevent="guardarContacto" class="mx-auto" style="max-width: 600px;">
            <div class="mb-3">
                <label class="form-label">Nombre</label>
                <input v-model="nuevoContacto.nombre" type="text" class="form-control" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Dirección</label>
                <input v-model="nuevoContacto.direccion" type="text" class="form-control" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Teléfono</label>
                <input v-model="nuevoContacto.telefono" type="text" class="form-control" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Correo Electrónico</label>
                <input v-model="nuevoContacto.email" type="email" class="form-control" required />
            </div>
            <div class="mb-3">
                <label class="form-label">Oficio</label>
                <input v-model="nuevoContacto.oficio" type="text" class="form-control" required />
            </div>
            <div class="mb-3">
                <label class="form-label">URL de la Imagen</label>
                <input v-model="nuevoContacto.imagen" type="text" class="form-control" />
            </div>
            <button type="submit" class="btn btn-success w-100">Guardar Contacto</button>
        </form>
    </div>
     <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">&copy; 2025 Agenda Contactos - Todos los derechos reservados</p>
    </footer>
</template>
