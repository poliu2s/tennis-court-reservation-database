alter table Account_Holder
	disable Constraint transaction_ref
	disable Constraint court_rental_ref;

alter table Account_Payment
	disable Constraint reservation_id_ref
	disable Constraint court_rental_id_ref
	disable Constraint user_id_ref;

alter table Account_Refund
	disable Constraint cancel_id_ref
	disable Constraint court_rental_id_ref
	disable Constraint user_id_refund_ref;

alter table Based_On
	disable Constraint reservation_id_ref
	disable Constraint cancel_id_ref;

alter table Cancel
	disable Constraint transaction_id_ref
	disable Constraint reservation_id_cancel_ref
	disable Constraint account_holder_id_ref
	disable Constraint court_rental_id_ref;

alter table Course_Reserve
	disable Constraint court_rental_id_reseve_ref
	disable Constraint course_id_ref
	disable Constraint coach_id_ref;

alter table Court_Rental
	disable Constraint coach_id_rental_ref
	disable Constraint account_holder_id_rental_ref
	disable Constraint transaction_id_rental_ref;

alter table Court
	disable Constraint rental_id_ref;

alter table Reservation
	disable Constraint transaction_id_reservation_ref
	disable Constraint account_id_reserv_ref
	disable Constraint court_rental_id_reserv_ref;

alter table  Reserves
	disable Constraint court_id_ref
	disable Constraint court_rental_id_reserves_ref;