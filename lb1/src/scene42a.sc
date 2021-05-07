;;; Sierra Script 1.0 - (do not remove this comment)
(script# 307)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use Actor)
(use System)

(public
	scene42a 0
)

(procedure (localproc_009a)
	(Print
		&rest
		#at
		160
		115
		#font
		4
		#width
		140
		#mode
		1
		#dispose
	)
)

(instance Fifi of Act
	(properties)
)

(instance myMusic of Sound
	(properties)
)

(instance scene42a of Rm
	(properties
		picture 62
		style $0007
	)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_009a 307 0)
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
				(== evKEYBOARD (event type?))
				(or
					(== (event message?) KEY_S)
					(== (event message?) KEY_s)
				)
			)
			(cls)
			(curRoom newRoom: prevRoomNum)
		)
	)
)
