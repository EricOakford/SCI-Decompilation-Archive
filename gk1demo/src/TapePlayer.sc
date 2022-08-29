;;; Sierra Script 1.0 - (do not remove this comment)
(script# TAPE_PLAYER)
(include game.sh)
(use Recorder)
(use Print)
(use System)

(public
	TapePlayer 0
)

(class TapePlayer of Set
	(properties
		who 0
		verb 0
		noun 7
		module 0
		machine 0
		tellers 0
		media 0
		number 0
	)
	
	(method (init &tmp i newPrint temp2)
		((= machine (RecordInfo new:)) init: 2)
		(= tellers (machine readHeader:))
		(= i 0)
		(while (< i 2)
			(if (tellers at: i) (break))
			(++ i)
		)
		(= who
			(if (== i 2)
				-1
			else
				((= newPrint (Print new:)) addText: 0 0 4 1 0 0 920)
				(= temp2 (= i 0))
				(while (< i 2)
					(if (tellers at: i)
						(newPrint
							addButton:
								i
								i
								0
								0
								1
								(* 60 (mod temp2 4))
								(* 12 (+ 1 (/ temp2 4)))
								920
						)
						(++ temp2)
					)
					(++ i)
				)
				(newPrint init:)
			)
		)
		(self add: machine tellers)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 91)
	)
	
	(method (show &tmp temp0 temp1 temp2 temp3 temp4 temp5 newPrint temp7 temp8 temp9)
		(= media (machine readTeller: who))
		(= number (media size?))
		(self delete: machine)
		(machine dispose:)
		(DisposeScript 90)
		(= module 50)
		(self add: media)
		(= temp3
			(Memory MNewPtr (Message MsgSize 920 0 0 10 1))
		)
		(Message MsgGet 920 0 0 10 1 temp3)
		(= temp5
			(+
				(Message MsgSize 920 who 0 0 1)
				(Message MsgSize 920 0 0 11 1)
			)
		)
		(= temp4 (Memory MNewPtr temp5))
		(Message MsgGet 920 who 0 0 1 temp4)
		(Message MsgGet 920 0 0 11 1 (+ temp4 (StrLen temp4)))
		(= verb
			(switch who
				(0 54)
				(1 27)
			))
		(= temp7 5)
		(= temp0 (= temp2 0))
		(= temp1 (media at: temp0))
		(while (!= temp7 6)
			(switch temp7
				(8
					(= temp2 1)
					(if temp0 (-- temp0))
					(= temp1 (media at: temp0))
					(= temp5 (Message MsgSize module noun verb temp1 temp2))
				)
				(5
					(++ temp2)
					(if
						(not
							(= temp5 (Message MsgSize module noun verb temp1 temp2))
						)
						(if (== (++ temp0) number)
							(-- temp0)
							(-- temp2)
						else
							(= temp2 1)
						)
						(= temp1 (media at: temp0))
						(= temp5 (Message MsgSize module noun verb temp1 temp2))
					)
				)
				(7
					(if (== (++ temp0) number)
						(-- temp0)
					else
						(= temp2 1)
					)
					(= temp1 (media at: temp0))
					(= temp5 (Message MsgSize module noun verb temp1 temp2))
				)
			)
			(= temp8 (Memory MNewPtr temp5))
			(= temp9
				(if
					(==
						1
						(Message MsgGet module noun verb temp1 temp2 temp8)
					)
					temp3
				else
					temp4
				)
			)
			(= temp7
				((= newPrint (Print new:))
					x: 6
					y: 130
					width: 250
					addButton: 6 0 0 6 1 40 0 920
					addButton: 8 0 0 8 1 92 0 920
					addButton: 5 0 0 5 1 160 0 920
					addButton: 7 0 0 7 1 212 0 920
					addText: temp9 0 15
					addText: temp8 (* 5 (StrLen temp9)) 15
					init:
				)
			)
			(Memory MDisposePtr temp8)
		)
		(Memory MDisposePtr temp3)
		(Memory MDisposePtr temp4)
		(return 1)
	)
)
