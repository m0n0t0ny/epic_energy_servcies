package com.epicenergyservices.u5w4.entities;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface ClientRepository extends JpaRepository <Client, UUID> {
    @Query("SELECT c FROM Client c WHERE c.companyName = :companyName")
    List<Client> findClientsByName(@Param("companyName")String companyName);
    list<Client> findByAnnualRevenue(int annualRevenue);
    Optional<Client> findByVatNumber(String vatNumber);
    List<Client> findByType(Client.Type type);
    @Query ("SELECT c FROM Client c Where c.purchaseDate > :purchaseDate")
    List<Client> findClientWithPurchaseAfterDate(@Param("purchaseDate")Date purchaseDate);
    List<Client> findByOderByAnnualRevenueDesc();
    List<Client> findByOrderByCompanyName();
    List<Client> findAllByAnnualRevenueDesc();
    List<Client> findByInsertionDateBetween(Date startDate, Date endDate);
    List<Client> findByCompanyContaining(String partOfName);

    List<Invoice> findInvoicesByClientAndStatus(Client client, String status);
    List<Invoice> findInvoicesByDate(Date date);
    List<Invoice> findInvoicesByDate(Date date);
    List<Invoice> findInvoicesByAmountBetween(int minAmount, int maxAmount);
}