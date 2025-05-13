<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import imagenPrincipal from '../assets/imgPrincipal.jpg';
import iconoNav from '../assets/libreta-de-contactos.png';
import sweet from 'sweetalert2';

const Contactos = ref([]);

const eliminarContacto = async (id) => {
    sweet.fire({
        title: '¿Estás seguro?',
        text: "No podrás recuperar este contacto!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar!'
    }).then(async (result) => {
        if (result.isConfirmed) {
            await eliminarContactoId(id);
            sweet.fire(
                'Eliminado!',
                'El contacto ha sido eliminado.',
                'success'
            )
        }
    })
}
const eliminarContactoId = async (id) => {
    try {
        await axios.delete(`http://localhost:8080/contactos/eliminarContacto/${id}`);
    cargarContactos();
    } catch (error) {
        console.error('Error al eliminar el contacto:', error);
    }
    
}

const cargarContactos = async () => {

    const response = await axios.get('http://localhost:8080/contactos/traerContactos')
    Contactos.value = response.data;
    console.log(Contactos.value);

}
const guardarContacto = async () => {
    if (editar.value) {
        await axios.put(`http://localhost:8080/contactos/editarContacto/${nuevoContacto.value.id}`, nuevoContacto.value);
        editar.value = false;
        router.push('/contactos');
    } else {
        await axios.post('http://localhost:8080/contactos/agregarContacto', nuevoContacto.value)
        router.push('/contactos');
        nuevoContacto.value = {
            nombre: '',
            direccion: '',
            telefono: '',
            email: '',
            oficio: '',
            imagen: ''
        };
    }
    const editarContacto = (contacto) => {
        nuevoContacto.value = contacto;
        editar.value = true;

    }


}
onMounted(cargarContactos);

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
        <h2 class="text-center fw-bold mb-4">Todos Los Contactos</h2>
        <div class="row g-4">
            <div class="col-md-4" v-for="Contacto in Contactos" :key="Contacto.id">
                <div class="card h-100 shadow-sm">
                    <img :src="Contacto.imagen" class="card-img-top" alt="Imagen de contacto"
                        style=" height: 300px; object-fit: cover;">

                    <div class="card-body">
                        <h5 class="card-title"> {{ Contacto.nombre }} </h5>
                        <p class="card-text">Direccion:{{ Contacto.direccion }}
                            <br>
                            Telefono: {{ Contacto.telefono }}
                            <br>
                            Correo: {{ Contacto.email }}
                            <br>
                            Oficio: {{ Contacto.oficio }}
                            <br>

                        </p>
                        
                        <div class="d-flex justify-content-center gap-3">
                           
                            <router-link :to="`/editar/${Contacto.id}`" class="btn btn-outline-primary">
                                <i class="bi bi-pencil-fill"></i> Editar
                            </router-link>

                            <!-- Eliminar (usa button) -->
                            <button class="btn btn-outline-danger" @click="eliminarContacto(Contacto.id)">
                                <i class="bi bi-trash-fill"></i> Eliminar
                            </button>
                        </div>


                    </div>
                </div>
            </div>
        </div>
    </div>
 <footer class="bg-dark text-white text-center py-4">
        <p class="mb-0">&copy; 2025 Agenda Contactos - Todos los derechos reservados</p>
    </footer>
</template>