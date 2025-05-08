package com.equipoXD.agenda_contactos.controller;

import java.util.List;

import org.hibernate.query.NativeQuery.ReturnProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.equipoXD.agenda_contactos.model.agendaContactosModel;
import com.equipoXD.agenda_contactos.repository.agendaContactosRepository;

@RestController
@RequestMapping("/contactos")
public class agendaContactosController {
    @Autowired
   private agendaContactosRepository agendaContactosRepository;
   //metodo para traer contactos
   @GetMapping("/traerContactos")
   public List <agendaContactosModel> traerContactos() {
       return agendaContactosRepository.findAll();
    }
    //metodo para agragar contactos a la base de datos
   @PostMapping("/agregarContacto")
   public agendaContactosModel agregarContacto(@RequestBody agendaContactosModel contacto) {
       return agendaContactosRepository.save(contacto);
   }
   @PutMapping("/editarContacto/{id}")
    public ResponseEntity<agendaContactosModel> editarContacto (@PathVariable Long id, @RequestBody agendaContactosModel contacto) {
       return agendaContactosRepository.findById(id).map(contactoExistente -> {
           contactoExistente.setNombre(contacto.getNombre());
           contactoExistente.setTelefono(contacto.getTelefono());
           contactoExistente.setEmail(contacto.getEmail());
           contactoExistente.setDireccion(contacto.getDireccion());
           contactoExistente.setOficio(contacto.getOficio());
           contactoExistente.setImagen(contacto.getImagen());
           agendaContactosModel actualizado = agendaContactosRepository.save(contactoExistente);
           return ResponseEntity.ok(actualizado);
       }).orElse(ResponseEntity.notFound().build());
    }
}
