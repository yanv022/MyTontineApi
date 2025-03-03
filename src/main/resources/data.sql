INSERT INTO tontine_group (group_name, total_amount, rotation_order, next_payout)
VALUES ('Groupe Test', 250000, 1, '2023-09-10');

INSERT INTO member (name, has_received, group_id)
VALUES ('Alice', false, 1), ('Bob', false, 1);