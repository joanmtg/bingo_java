package com.progracol.bingo.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name="bingo_param_board")
public class BingoParamBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer board_id;
    private String board_numbers;
    private String status;

    @Column(name="CREATED_BY", nullable = true)
    private Integer created_by;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LAST_UPDATED_AT", nullable = true)
    private Date last_updated_at;
    @Column(name="LAST_UPDATED_BY", nullable = true)
    private Integer last_updated_by;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="DELETED_AT", nullable = true)
    private Date deleted_at;
    @Column(name="DELETED_BY", nullable = true)
    private Integer deleted_by;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_AT", nullable = true)
    private Date created_at;

    public BingoParamBoard() {

    }

    public Integer getBoard_id() {
        return board_id;
    }

    public void setBoard_id(Integer board_id) {
        this.board_id = board_id;
    }

    public String getBoard_numbers() {
        return board_numbers;
    }

    public void setBoard_numbers(String board_numbers) {
        this.board_numbers = board_numbers;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Integer created_by) {
        this.created_by = created_by;
    }

    public Date getLast_updated_at() {
        return last_updated_at;
    }

    public void setLast_updated_at(Date last_updated_at) {
        this.last_updated_at = last_updated_at;
    }

    public Integer getLast_updated_by() {
        return last_updated_by;
    }

    public void setLast_updated_by(Integer last_updated_by) {
        this.last_updated_by = last_updated_by;
    }

    public Date getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(Date deleted_at) {
        this.deleted_at = deleted_at;
    }

    public Integer getDeleted_by() {
        return deleted_by;
    }

    public void setDeleted_by(Integer deleted_by) {
        this.deleted_by = deleted_by;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
