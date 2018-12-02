package br.ufba.reuse.bolao.business;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.ufba.reuse.bolao.business.daos.TimeDAO;
import br.ufba.reuse.bolao.business.daos.util.BusinessGeneric;
import br.ufba.reuse.bolao.entities.Time;

@Component
@Transactional
public class TimeBusiness extends BusinessGeneric<TimeDAO, Time> {}
