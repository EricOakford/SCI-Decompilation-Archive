;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh) (include "803.shm") (include "14.shm")
(use Main)
(use Game)
(use System)

(public
	rm14 0
)

(local
	saveBits
	[local1 99]
	saveBits2
	[local101 99]
)
(instance rm14 of Room
	(properties
		picture 111
		style FADEOUT
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: waitAWhile)
		(keyDownHandler add: waitAWhile)
		(theGame setCursor: INVIS_CURSOR TRUE)
		(curRoom setScript: waitAWhile)
	)
)

(instance waitAWhile of Script
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(if (== prevRoomNum 1)
					(Message MsgGet 803 N_START NULL C_END_DEMO 1 @str)
				else
					(Message MsgGet 14 N_ROOM NULL C_START_DEMO 1 @str)
				)
				(= saveBits
					(Display @str
						p_at 76 71
						p_color 0
						p_width 225
						p_font USERFONT
						p_save
					)
				)
				(= saveBits2
					(Display @str
						p_at 75 70
						p_color 9
						p_width 225
						p_font USERFONT
						p_save
					)
				)
				(= ticks 450)
			)
			(1
				(if (== prevRoomNum 1)
					(curRoom newRoom: 803)
				else
					(curRoom newRoom: 1)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(== (event type?) keyDown)
				(== (event type?) mouseDown)
			)
			(event claimed: TRUE)
			(if (== prevRoomNum 1)
				(curRoom newRoom: 803)
			else
				(curRoom newRoom: 1)
			)
		else
			(super handleEvent: event)
		)
	)
)
