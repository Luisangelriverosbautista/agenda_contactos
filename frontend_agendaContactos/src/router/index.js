import { createRouter, createWebHashHistory } from "vue-router";
import inicio from "../pages/inicioAgendaContactos.vue";
import contactos from "../pages/Contactos.vue";
import agregar from "../pages/Agregar.vue";
import editar from "../pages/Editar.vue";
const routes = [
    {
        path: "/",
        name: "Inicio",
        component: inicio,

    },
   {
        path: "/contactos",
        name: "contactos",
        component: contactos,

    },
     {
        path: "/agregar",
        name: "agregar",
        component: agregar,

        
    },
   {
  path: "/editar/:id",
  name: "editar",
  component: editar,
}
   
   
    
    
]

const router = createRouter({ 
    history: createWebHashHistory(import.meta.env.BASE_URL),
    routes,
});
export default router;
