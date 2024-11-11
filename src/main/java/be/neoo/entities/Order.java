package be.neoo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NamedQueries(value = {
        @NamedQuery(name = "order.findAll", query = "select o from Order o "),
//        @NamedQuery(name = "product.findByOrderId", query = "SELECT p FROM Product p " +
//                "JOIN OrderProduct op ON op.product.id = p.id " +
//                "WHERE op.order.id = :orderId"),
        @NamedQuery(name = "product.findByOrderId", query = "SELECT op FROM OrderProduct op " +
                "WHERE op.order.id = :orderId"),
})

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "date_order", nullable = true)
    private Date dateOrder;

    @Column(name = "payed", nullable = false)
    private Boolean payed;

    @Column(name = "payement_date", nullable = true)
    private Date payementDate;

    @Column(name = "deliver", nullable = false)
    private Boolean deliver;

    @Column(name = "deliver_date", nullable = false)
    private Date deliverDate;

    @Column(name = "mode_of_payement", nullable = false)
    private String modeOfPayement;

    @ManyToOne (cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinColumn(name = "customer", referencedColumnName = "id", nullable = true, updatable = false)
    private Customer customer;

    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProduct> orderProducts;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<PayementOrder> payementOrders;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<OrderDocument> orderDocuments;
    @JsonManagedReference
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<PayementReminder> payementReminders;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    public Date getPayementDate() {
        return payementDate;
    }

    public void setPayementDate(Date payementDate) {
        this.payementDate = payementDate;
    }

    public Boolean getDeliver() {
        return deliver;
    }

    public void setDeliver(Boolean deliver) {
        this.deliver = deliver;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getModeOfPayement() {
        return modeOfPayement;
    }

    public void setModeOfPayement(String modeOfPayement) {
        this.modeOfPayement = modeOfPayement;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public List<PayementOrder> getPayementOrders() {
        return payementOrders;
    }

    public void setPayementOrders(List<PayementOrder> payementOrders) {
        this.payementOrders = payementOrders;
    }

    public List<OrderDocument> getOrderDocuments() {
        return orderDocuments;
    }

    public void setOrderDocuments(List<OrderDocument> orderDocuments) {
        this.orderDocuments = orderDocuments;
    }

    public List<PayementReminder> getPayementReminders() {
        return payementReminders;
    }

    public void setPayementReminders(List<PayementReminder> payementReminders) {
        this.payementReminders = payementReminders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(dateOrder, order.dateOrder) && Objects.equals(payed,
                order.payed) && Objects.equals(payementDate, order.payementDate) && Objects.equals(
                deliver, order.deliver) && Objects.equals(deliverDate, order.deliverDate)
                && Objects.equals(modeOfPayement, order.modeOfPayement) && Objects.equals(customer,
                order.customer) && Objects.equals(orderProducts, order.orderProducts)
                && Objects.equals(payementOrders, order.payementOrders) && Objects.equals(
                orderDocuments, order.orderDocuments) && Objects.equals(payementReminders,
                order.payementReminders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateOrder, payed, payementDate, deliver, deliverDate, modeOfPayement, customer,
                orderProducts, payementOrders, orderDocuments, payementReminders);
    }
}
