;;; Sierra Script 1.0 - (do not remove this comment)
(script# 307)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	scene42a 0
)

(procedure (FifiPrint)
	(Print &rest
		#at 160 115
		#font 4
		#width 140
		#mode teJustCenter
		#dispose
	)
)

(instance Fifi of Actor)

(instance myMusic of Sound)

(instance scene42a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Fifi
			view: 305
			loop: 0
			cel: 5
			posn: 162 102
			setPri: 3
			cycleSpeed: 2
			init:
		)
		(self setScript: speech42a)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance speech42a of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(FifiPrint 307 0)
				(= seconds 4)
			)
			(1
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(not (event claimed?))
				(not script)
				(== keyDown (event type?))
				(or
					(== (event message?) `S)
					(== (event message?) `s)
				)
			)
			(cls)
			(curRoom newRoom: prevRoomNum)
		)
	)
)
