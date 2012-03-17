package ar.jug.application;

import static ar.jug.domain.QEvent.event;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.jug.domain.Event;

import com.mysema.query.jpa.impl.JPAQuery;

@Controller
@Transactional
public class EventManager {
  private EntityManager em;

  @Inject
  public EventManager(final EntityManager em) {
    this.em = em;
  }

  protected EventManager() {
  }

  @RequestMapping(value="/events", method = GET)
  @ResponseBody
  public List<Event> list() {
    return new JPAQuery(em).from(event).list(event);
  }

  @RequestMapping(value="/events",method = POST)
  @ResponseBody
  public Event newEvent(final String name) {
    return em.merge(new Event(name));
  }
}
