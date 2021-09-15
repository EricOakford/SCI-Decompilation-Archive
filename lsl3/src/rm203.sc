;;; Sierra Script 1.0 - (do not remove this comment)
(script# 203)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use System)

(public
	rm203 0
)

(local
	[str 30]
	plaqueTextColor
)
(procedure (DoDisplay theX theY theColor theFont message)
	(Display message
		p_at theX theY
		p_font theFont
		p_color theColor
	)
	(Display message
		p_at (+ theX 1) (+ theY 1)
		p_font theFont
		p_color (- theColor 8)
	)
)

(procedure (PlaqueDisplay)
	(DoDisplay 54 105 plaqueTextColor 1 (Format @str 203 4))
	(DoDisplay 90 120 plaqueTextColor 9 (Format @str 203 5))
	(DoDisplay 56 140 plaqueTextColor 1 (Format @str 203 6))
	(DoDisplay 49 155 plaqueTextColor 1 (Format @str 203 7))
)

(instance rm203 of Room
	(properties
		picture 203
	)
	
	(method (init)
		(super init:)
		(Bset fCursorHidden)
		(= oldStatus currentStatus)
		(= currentStatus curRoomNum)
		(if (> (Graph GDetect) 4)
			(= plaqueTextColor vYELLOW)
		else
			(= plaqueTextColor vLGREY)
		)
		(User canControl: FALSE)
		(User canInput: TRUE)
		(self setScript: RoomScript)
		(PlaqueDisplay)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (== (GameIsRestarting) 2)
			(PlaqueDisplay)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'look/away,area')
					(Said 'look<done,cease')
					(Said 'done,cease/look')
					(Said 'look<done,cease/awning')
					(Said 'exit,go,exit,done,cease')
				)
				(Ok)
				(= currentStatus oldStatus)
				(curRoom newRoom: 200)
			)
			((Said 'look/awning')
				(Print 203 0 #at 10 5 #width 290 #mode teJustCenter)
			)
			((Said 'look/eye,head')
				(Print 203 1)
			)
			((Said 'look')
				(Print 203 2)
				(if (not playingAsPatti)
					(Print 203 3)
				)
			)
		)
	)
)
