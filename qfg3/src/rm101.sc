;;; Sierra Script 1.0 - (do not remove this comment)
(script# WHERE_TO) ;101
(include game.sh) (include "101.shm")
(use Main)
(use Print)
(use Game)

(public
	rm101 0
)

(procedure (WhereTo &tmp n [str 5])
	(= str 0)
	(= n
		(Print
			addText: N_WHERE_TO NULL C_TITLE 1 0 12
			addEdit: @str 5 120 12
			addButton: 200 N_WHERE_TO NULL NULL 1 0 26
			addButton: 320 N_WHERE_TO NULL NULL 2 120 26
			addButton: 410 N_WHERE_TO NULL NULL 3 0 40
			addButton: 490 N_WHERE_TO NULL NULL 4 120 40
			addButton: 720 N_WHERE_TO NULL NULL 5 0 54
			addButton: 999 N_WHERE_TO NULL NULL 6 120 54
			init:
		)
	)
	(if str
		(curRoom newRoom: (ReadNumber @str))
	else
		(switch n
			(200 (curRoom newRoom: 200))
			(320 (curRoom newRoom: 320))
			(410 (curRoom newRoom: 410))
			(490 (curRoom newRoom: 490))
			(720 (curRoom newRoom: 720))
			(999 (curRoom newRoom: 130))
			(else  (curRoom newRoom: 210))
		)
	)
)

(instance rm101 of Room
	
	(method (init)
		(DrawPic 0)
		(Animate (cast elements?) FALSE)
		(super init:)
		(WhereTo)
	)
)
