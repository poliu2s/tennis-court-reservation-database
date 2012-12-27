alter table Account_Holder
	enable Constraint transaction_ref
	enable Constraint court_rental_ref;

alter table Account_Payment
	enable Constraint reservation_id_ref
	enable Constraint court_rental_id_ref
	enable Constraint user_id_ref;

alter table Account_Refund
	enable Constraint cancel_id_ref
	enable Constraint court_rental_id_ref
	enable Constraint user_id_refund_ref;

alter table Based_On
	enable Constraint reservation_id_ref
	enable Constraint cancel_id_ref;

alter table Cancel
	enable Constraint transaction_id_ref
	enable Constraint reservation_id_cancel_ref
	enable Constraint account_holder_id_ref
	enable Constraint court_rental_id_ref;

alter table Course_Reserve
	enable Constraint court_rental_id_reseve_ref
	enable Constraint course_id_ref
	enable Constraint coach_id_ref;

alter table Court_Rental
	enable Constraint coach_id_rental_ref
	enable Constraint account_holder_id_rental_ref
	enable Constraint transaction_id_rental_ref;

alter table Court
	enable Constraint rental_id_ref;

alter table Reservation
	enable Constraint transaction_id_reservation_ref
	enable Constraint account_id_reserv_ref
	enable Constraint court_rental_id_reserv_ref;

alter table  Reserves
	enable Constraint court_id_ref
	enable Constraint court_rental_id_reserves_ref;