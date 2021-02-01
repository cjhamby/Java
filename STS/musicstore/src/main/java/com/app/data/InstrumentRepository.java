package com.app.data;

import org.springframework.data.repository.CrudRepository;

import com.app.dao.Instrument;

public interface InstrumentRepository extends CrudRepository<Instrument, Integer> {

}
