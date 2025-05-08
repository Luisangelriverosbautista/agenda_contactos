package com.equipoXD.agenda_contactos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.equipoXD.agenda_contactos.model.agendaContactosModel;

public interface agendaContactosRepository extends JpaRepository <agendaContactosModel , Long> {
    // No need to define any methods here, JpaRepository provides all the necessary CRUD operations

}
