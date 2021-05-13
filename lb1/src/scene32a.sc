;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene32a 0
)

(local
	local0
)
(instance Wilbur of Prop)

(instance Book of Prop)

(instance myMusic of Sound)

(instance scene32a of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Wilbur
			view: 429
			loop: 0
			cel: 1
			posn: 216 103
			setPri: 1
			init:
			stopUpd:
		)
		(Book
			view: 429
			loop: 1
			cel: 0
			posn: 217 103
			setPri: 2
			init:
			hide:
		)
		(self setScript: page)
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

(instance page of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (>= global154 4)
					(cond 
						((not global216) (= state -1))
						((not (& global118 $0008))
							(|= global118 $0008)
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?)
							(= state -1)
						)
					)
				)
				(= cycles 1)
			)
			(1
				(Print 320 0 #dispose)
				(= seconds 4)
			)
			(2
				(Book show: cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(Book hide:)
				(= seconds 3)
			)
			(4
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)
