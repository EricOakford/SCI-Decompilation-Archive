;;; Sierra Script 1.0 - (do not remove this comment)
(script# 306)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	scene34e 0
)

(local
	local0
	eyesCued
	theHand
)
(instance Hand of Actor
	(properties
		y 136
		x 155
		view 386
	)
)

(instance Head of Prop
	(properties
		y 95
		x 114
		view 386
	)
)

(instance Shoulder of Prop
	(properties
		y 111
		x 106
		view 386
		loop 1
	)
)

(instance Mouth of Prop
	(properties
		y 94
		x 115
		view 386
		loop 4
	)
)

(instance Eye of Prop
	(properties
		y 72
		x 115
		view 386
		loop 5
	)
)

(instance myMusic of Sound)

(instance scene34e of Room
	(properties
		picture 62
		style IRISOUT
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(myMusic number: 27 loop: -1 play:)
		(Head setPri: 0 ignoreActors: TRUE init:)
		(Shoulder setPri: 0 ignoreActors: TRUE init:)
		(Mouth setPri: 1 ignoreActors: TRUE init: hide:)
		(Eye setPri: 1 ignoreActors: TRUE init: hide:)
		(Hand
			setLoop: 2
			setCel: 0
			setPri: 3
			illegalBits: 0
			ignoreActors: TRUE
			init:
		)
		(self setScript: twice)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(not (event claimed?))
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

(instance twice of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
						(= cycles 1)
					)
					((not (& global118 $0001))
						(|= global118 $0001)
						(self setScript: (ScriptID 406 0))
						(= state -1)
						(= cycles 1)
					)
					((self script?)
						(= state -1)
						(= cycles 1)
					)
					(else
						(Print 306 0 #dispose)
						(Hand setMotion: MoveTo 135 120 self)
					)
				)
			)
			(1
				(Eye show: setScript: RudysEyes)
				(Hand setMotion: MoveTo 135 116 self)
				(Mouth show: loop: 3)
			)
			(2
				(Hand cel: 1 posn: 135 115)
				(= cycles 1)
			)
			(3
				(Hand cel: 2 setMotion: MoveTo 129 115 self)
			)
			(4
				(Hand setMotion: MoveTo 133 114 self)
			)
			(5
				(Mouth loop: 4 setCycle: Forward)
				(Hand cel: 3 setMotion: MoveTo 136 114 self)
			)
			(6
				(Hand setMotion: MoveTo 155 136 self)
				(= seconds 5)
			)
			(7
				((= theHand Hand)
					setLoop: 7
					cel: 0
					posn: 88 137
					setCycle: 0
					setPri: 3
				)
				(Mouth setCycle: BegLoop self)
			)
			(8
				(theHand setMotion: MoveTo 108 118 self)
			)
			(9
				(theHand setMotion: MoveTo 108 114 self)
			)
			(10
				(theHand setCycle: EndLoop)
				(Eye hide:)
				(Head setCycle: EndLoop)
				(Mouth cycleSpeed: 1 setCycle: Forward)
				(= seconds 2)
			)
			(11
				(Mouth hide:)
				(theHand setCycle: BegLoop self setMotion: MoveTo 108 118)
			)
			(12 (Head setCycle: BegLoop self))
			(13
				(Eye show:)
				(theHand setMotion: MoveTo 88 137 self)
			)
			(14
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance RudysEyes of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Eye setLoop: 6 setCel: 0 forceUpd:)
				(= seconds (Random 1 3))
			)
			(1
				(if (and (not eyesCued) (== (Random 1 2) 1))
					(Eye setLoop: 6 setCel: 1 forceUpd:)
					(= eyesCued TRUE)
					(= cycles 1)
				else
					(Eye setLoop: 5 setCel: 1 forceUpd:)
					(= eyesCued FALSE)
					(= seconds (Random 1 3))
				)
				(= state -1)
			)
		)
	)
)
