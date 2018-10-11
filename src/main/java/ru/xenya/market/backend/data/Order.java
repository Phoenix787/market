package ru.xenya.market.backend.data;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "OrdersInfo")
@Table(name = "orders")
@Data
public class Order extends AbstractEntity {

  //  @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "{market.due.date.required}")
    private LocalDate date;

    @NotBlank
    private Payment payment;

//    //множество позиций заказа
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
//    @OrderColumn
//    @JoinColumn
//    @NotEmpty
//    @Valid
//    private List<OrderItem> items;
//    //множество позиций платежей
//    //счет
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "fk_invoice_id", referencedColumnName = "invoice_id")
//    private Invoice invoice;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @OrderColumn
//    @JoinColumn
//    private List<HistoryItem> history;

    @NotNull(message = "{market.status.required}")
    private OrderState orderState;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;


    public Order() {
    }

    public Order(User createdBy){
        this.orderState = OrderState.NEW;
        setCustomer(new Customer());
        addHistoryItem(createdBy, "Заказ размещен");
     //   this.items = new ArrayList<>();

    }

    public void addHistoryItem(User createdBy, String comment) {
        HistoryItem item = new HistoryItem(createdBy, comment);
        item.setNewState(orderState);
//        if (history == null) {
//            history = new LinkedList<>();
//        }
//        history.add(item);
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

//    public List<OrderItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<OrderItem> items) {
//        this.items = items;
//    }
//
//    public List<HistoryItem> getHistory() {
//        return history;
//    }
//
//    public void setHistory(List<HistoryItem> history) {
//        this.history = history;
//    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void changeState(User user, OrderState orderState) {
        boolean createHistory = this.orderState != orderState && this.orderState != null && orderState != null;
        this.orderState = orderState;
        if (createHistory) {
            addHistoryItem(user, "Заказ " + orderState.name());
        }
    }
}
