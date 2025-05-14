<script setup>

import {ref,onMounted} from 'vue';
import axios from 'axios';

const Contactos = ref([]);

const cargarContactos= async () => {

const response = await axios.get('http://localhost:8080/contactos/traerContactos')
Contactos.value = response.data;
console.log(Contactos.value);

}
onMounted(cargarContactos);


</script>

<template>

<router-view>
</router-view>

</template>

<style scoped>

</style>