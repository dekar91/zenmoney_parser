package models;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;

public class OutputCsvLine {

    @CsvBindByPosition(position = 0)
    @CsvDate( value = "dd/MM/yyyy")
    private LocalDate operationDate;

    @CsvBindByPosition(position = 1)
    private String operationType;

    @CsvBindByPosition(position = 2)
    private String sourceName;

    @CsvBindByPosition(position = 3)
    private String destination;

    @CsvBindByPosition(position = 4)
    private String hashtags;

    @CsvBindByPosition(position = 5)
    private Double cost;

    @CsvBindByPosition(position = 6)
    private String currency;

    @CsvBindByPosition(position = 7)
    private Double destCost;

    @CsvBindByPosition(position = 8)
    private String destCurrency;

    @CsvBindByPosition(position = 9)
    private String repetition;


    @CsvBindByPosition(position = 10)
    private String comment;

    public OutputCsvLine(InputCsvLine inputLine) {
        operationDate = inputLine.getOperationDate();
        operationType = inputLine.getOperationType();
        sourceName = inputLine.getSourceName();
        destination = inputLine.getDestination();
        hashtags  = inputLine.getHashtags();
        cost = inputLine.getCost();
        currency = inputLine.getCurrency();
        destCurrency = inputLine.getDestCurrency();
        destCost = inputLine.getDestCost();
        comment = inputLine.getComment();

        if (operationType.equalsIgnoreCase("Расход")) {
           destCost *= -1;
           cost *= -1;
        }

    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getHashtags() {
        return hashtags;
    }

    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getDestCost() {
        return destCost;
    }

    public void setDestCost(Double destCost) {
        this.destCost = destCost;
    }

    public String getDestCurrency() {
        return destCurrency;
    }

    public void setDestCurrency(String destCurrency) {
        this.destCurrency = destCurrency;
    }

    public String getRepetition() {
        return repetition;
    }

    public void setRepetition(String repetition) {
        this.repetition = repetition;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
