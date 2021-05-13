;;; Sierra Script 1.0 - (do not remove this comment)
(script# 355)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use System)

(public
	scene42f 0
)

(instance myMusic of Sound)

(instance scene42f of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Load FONT 41)
		(LoadMany 143 406)
		(Load VIEW 642)
		(LoadMany SOUND 94 95 96 29)
		(self setScript: missColo)
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

(instance missColo of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0004))
						(|= global118 $0004)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(Print 355 0 #at 70 63 #dispose)
				(= seconds 4)
			)
			(2
				(Bset 37)
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)
