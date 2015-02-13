package ro.scene.hq.holidays;

import java.io.Serializable;
import java.time.LocalDate;

public class HolidayRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private Identity employee;
    private Identity manager;
    private LocalDate fromDate;
    private int days;
    private State state = State.NEW;

    public static HolidayRequest fromId(String id) {
        return ServiceLocator.getHolidayRequestRepository().getById(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void submit() {
        if (State.NEW != state)
            throw new IllegalStateException("Can submit only new requests.");

        Message message = MessageTemplate.createSubmitMessage(employee, manager, fromDate, toDate());
        message.send(ServiceLocator.getNotificationChannel());

        changeStateTo(State.REQUEST_SENT);
    }

    public void accept() {
        if (State.REQUEST_SENT != state)
            throw new IllegalStateException("Can accept only requests that have been sent and not given a resolution for.");

        Message message = MessageTemplate.createAcceptMessage(manager, employee, fromDate, toDate());
        message.addRecipient(SystemConfiguration.HR_DEPARTMENT);
        message.send(ServiceLocator.getNotificationChannel());

        changeStateTo(State.APPROVED);
    }

    public void reject() {
        if (State.REQUEST_SENT != state)
            throw new IllegalStateException("Can reject only requests that have been sent and not given a resolution for.");

        Message message = MessageTemplate.createRejectMessage(manager, employee, fromDate, toDate());
        message.send(ServiceLocator.getNotificationChannel());

        changeStateTo(State.REJECTED);
    }

    public HolidayRequest fromEmployee(Identity employee) {
        this.employee = employee;
        return this;
    }

    public HolidayRequest toManager(Identity manager) {
        this.manager = manager;
        return this;
    }

    public HolidayRequest startingOn(LocalDate localDate) {
        this.fromDate = localDate;
        return this;
    }

    public HolidayRequest lastingForDays(int days) {
        this.days = days;
        return this;
    }

    private LocalDate toDate() {
        return LocalDate.from(fromDate).plusDays(days);
    }

    private void changeStateTo(State state) {
        this.state = state;
        ServiceLocator.getHolidayRequestRepository().save(this);
    }

    @Override
    public String toString() {
        return "HolidayRequest{" +
                "id='" + id + '\'' +
                ", employee=" + employee +
                ", manager=" + manager +
                ", fromDate=" + fromDate +
                ", days=" + days +
                ", state=" + state +
                '}';
    }

    public enum State {
        NEW,
        REQUEST_SENT,
        APPROVED,
        REJECTED
    }
}