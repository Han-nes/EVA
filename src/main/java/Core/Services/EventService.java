package Core.Services;

import Core.Models.exceptions.EventException;
import Core.Interfaces.EventServiceInterface;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import Core.Models.Event;

public class EventService implements EventServiceInterface {

    HashMap<UUID, Event> events = new HashMap<>();

    public Event createEvent(String name, String location, LocalDateTime time, int ticketsAvailable) throws EventException {
        UUID newID = UUID.randomUUID();
        Event newEvent = new Event(newID, name, location, time, ticketsAvailable);
        events.put(newID, newEvent);
        return new Event(newEvent);
    }

    @Override
    public Event getEventById(UUID id) {
        if(events.containsKey(id)) {
            Event event = events.get(id);
            return new Event(event);
        }
        else {
            throw EventException.eventDoesNotExist();
        }
    }

    @Override
    public void updateEvent(Event event) throws EventException {
                validateUpdatedEvent(event);
                events.put(event.getId(), event);
    }

    private void validateUpdatedEvent(Event event){
        Event oldEvent = getEventById(event.getId());
        if(!events.containsKey(event.getId())){
            throw EventException.eventDoesNotExist();
        }
        else if(event.getTicketsAvailable().intValue() < 0){
            throw EventException.negativeTicketsAvailable();
        }
        else if(event.getTicketsAvailable().intValue() < oldEvent.getTicketsAvailable().intValue()){
            throw EventException.shouldNotReduceAvailableTicketsWithUpdate();
        }
        else if(event.getTime().isBefore(LocalDateTime.now())){
            throw EventException.cantSetEventTimeIntoPast();
        }
    }


    @Override
    public void deleteEvent(UUID id) {
        if(events.containsKey(id)) {
            events.remove(id);
        }
        else {
            throw EventException.eventDoesNotExist();
        }
    }

    @Override
    public List<Event> getAllEvents() {
        return new ArrayList<Event>(events.values());
    }

    @Override
    public void deleteAllEvents() {
        events.clear();
    }


}
