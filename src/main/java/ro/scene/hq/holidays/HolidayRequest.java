package ro.scene.hq.holidays;

import java.io.PrintStream;
import java.io.Serializable;
import java.time.LocalDate;

public class HolidayRequest implements Serializable {

    private String id;

    private Identity employee;

    private Identity manager;

    private LocalDate fromDate;

    private int days;

    private HolidayRequestState state = HolidayRequestState.NEW;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void submit(DeliveryService deliveryService) {
        if (HolidayRequestState.NEW != state)
            throw new IllegalStateException("Can submit only new requests.");

        Email email = EmailTemplate.createSubmitEmail(employee, manager, fromDate, toDate());
        email.send(deliveryService);

        changeStateTo(HolidayRequestState.REQUEST_SENT);
    }

    public void accept(DeliveryService deliveryService) {
        if (HolidayRequestState.REQUEST_SENT != state)
            throw new IllegalStateException("Can accept only requests that have been sent and not given a resolution for.");

        Email email = EmailTemplate.createAcceptEmail(manager, employee, fromDate, toDate());
        email.ccTo(SystemConfiguration.HR_DEPARTMENT);
        email.send(deliveryService);

        changeStateTo(HolidayRequestState.APPROVED);
    }

    public void reject(DeliveryService deliveryService) {
        if (HolidayRequestState.REQUEST_SENT != state)
            throw new IllegalStateException("Can reject only requests that have been sent and not given a resolution for.");

        Email email = EmailTemplate.createRejectEmail(manager, employee, fromDate, toDate());
        email.send(deliveryService);

        changeStateTo(HolidayRequestState.REJECTED);
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

    private void changeStateTo(HolidayRequestState state) {
        this.state = state;
        ServiceLocator.getHolidayRequestRepository().save(this);
    }

    public void print() {
        PrintStream out = System.out;
        out.println("Request{ ");
        out.println("\tid: " + id);
        out.println("\tstate: " + state.name());
        out.println("\tfrom: " + fromDate);
        out.println("\tto: " + toDate());
        out.println("\temployee: " + employee.email);
        out.println("\tmanager: " + manager.email);
        out.println("}");
    }
}