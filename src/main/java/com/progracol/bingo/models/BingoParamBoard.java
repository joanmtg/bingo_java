package com.progracol.bingo.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name="bingo_param_board")
public class BingoParamBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_id")
    private Integer boardId;
    @Column(name = "board_numbers")
    private String boardNumbers;
    @Column(name = "status")
    private String status;

    @Column(name="created_by", nullable = true)
    private Integer createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_updated_at", nullable = true)
    private Date lastUpdatedAt;
    @Column(name="last_updated_by", nullable = true)
    private Integer lastUpdatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="deleted_at", nullable = true)
    private Date deletedAt;
    @Column(name="deleted_by", nullable = true)
    private Integer deletedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_at", nullable = true)
    private Date createdAt;

    //Constructor
    public BingoParamBoard() {

    }

    // Getters y Setters
    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * Getter personalizado para boardNumbers, JPA no permite mapear arreglos en una columna
     * @return List<Integer> convertido desde el String guardadod boardNumbers
     */

    public List<Integer> getBoardNumbers() {
        List<Integer> lstVals = new ArrayList<Integer>();
        int val = 0;
        String numbersValidString = this.boardNumbers.replaceAll("\\{","")
                                                     .replaceAll("}","")
                                                     .replaceAll("NULL,","")
                                                     .replaceAll("\"", "" ) ;

        for(String field : numbersValidString.split(",") ) {
            try {
                val = Integer.parseInt(field);
            }
            // If the String contains other thing that digits and commas
            catch (NumberFormatException e) {
            }
            if (val != 0){
                lstVals.add(val);
            }
        }

        return lstVals;
    }

    /**
     * Setter personalizado para boardNumbers:
     * @param vals List<Integer> y lo guarda como String
     */

    public void setBoardNumbers(List<Integer> vals) {
        String newVals = "";
        for(int i : vals) {
            newVals.concat(String.valueOf(i));
        }
        this.boardNumbers = newVals;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public Integer getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Integer getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
