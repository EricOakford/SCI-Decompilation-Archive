;;; Sierra Script 1.0 - (do not remove this comment)
(script# 200)
(include game.sh) (include "200.shm")
(use Main)
(use Game)
(use System)

(public
	rm200 0
)

(local
	nextRoom
	local1
	[str 20]
)
(instance rm200 of Room
	(properties
		picture 200
		horizon 66
		picAngle 15
	)
	
	(method (init)
		(if (!= (cSound signal?) -1)
			(cSound client: curRoom fade: 0 1 10 1)
		else
			(self cue:)
		)
		(Bclr fInMainGame)
		(if (= palVaryInfo (PalVary PALVARYINFO))
			(PalVary PALVARYPAUSE TRUE)
		)
		(switch prevRoomNum
			(150
				(= style (| BLACKOUT PIXELDISSOLVE))
			)
			(210
				(= style (| BLACKOUT IRISOUT))
			)
			(420
				(= style (| BLACKOUT PIXELDISSOLVE))
				(self cue:)
			)
			(620
				(= style (| BLACKOUT PIXELDISSOLVE))
				(self cue:)
			)
			(else 
				(= style (| BLACKOUT PIXELDISSOLVE))
				(if (OneOf prevRoomNum 110 120 100)
					(self cue:)
				)
			)
		)
		(super init:)
		(self setScript: showTarnaFarShot)
	)
	
	(method (dispose)
		(if palVaryInfo
			(PalVary PALVARYPAUSE FALSE)
		)
		(Bset fInMainGame)
		(super dispose:)
	)
	
	(method (cue)
		(cSound number: 911 setLoop: 1 play: showTarnaFarShot)
	)
)

(instance showTarnaFarShot of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(SetCursor 0)
				(= cycles 2)
			)
			(1
				(DrawCel 200 0 0 125 171 15)
				(Message MsgGet 200 NULL NULL C_TARNA 1 @str)
				(Display @str
					p_at 140 173
					p_mode teJustCenter
					p_color 0
					p_font 123
				)
				(Display @str
					p_at 139 172
					p_mode teJustCenter
					p_color 29
					p_font 123
				)
				(switch prevRoomNum
					(120 (= nextRoom 280))
					(150 (= nextRoom 210))
					(420 (= nextRoom 210))
					(620 (= nextRoom 210))
					(else  (= nextRoom 210))
				)
			)
			(2
				(theIconBar enable:)
				(SetCursor 1)
				(curRoom newRoom: nextRoom)
			)
		)
	)
)
