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

    public BingoParamBoard() {

    }

    public Integer getBoardId() {
        return boardId;
    }

    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /*public String getBoardNumbers() {
        return boardNumbers;
    }

    public void setBoardNumbers(String boardNumbers) {
        this.boardNumbers = boardNumbers;
    }*/

    public List<Integer> getBoardNumbers() {
        List<Integer> lstVals = new ArrayList<Integer>();
        int val = 0;

        for(String field : this.boardNumbers.split(",")) {
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
