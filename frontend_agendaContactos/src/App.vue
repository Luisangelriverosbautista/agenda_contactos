<script setup>
import {ref,onMounted} from 'vue';
import axios from 'axios';
const contactos= ref([]);

const cargarContactos= async () => {

const response = await axios.get('http://localhost:8080/contactos/traerContactos')
contactos.value = response.data;
console.log(contactos.value);

}
onMounted(cargarContactos);

</script>

<template>
<div class="container">
  <button class="btn btn-primary">boton</button>
</div>
</template>

<style scoped>

</style>