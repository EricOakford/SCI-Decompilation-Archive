;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room13 0
)
(synonyms
	(fence corral)
	(room barn)
)

(local
	local0
	local1
)
(instance Room13 of Room
	(properties
		picture 13
	)
	
	(method (init)
		(= north 9)
		(= horizon 100)
		(super init:)
		(LoadMany SOUND 107 108)
		(if (== currentAct 1)
			(self setRegions: 381)
		)
		(Door
			cel: (if (== prevRoomNum 69) 3 else 0)
			ignoreActors: TRUE
			init:
			stopUpd:
		)
		(= gDoor Door)
		(mySound init:)
		(= gMySound mySound)
		(if howFast
			(Splash1 ignoreActors: TRUE init: hide:)
			(Splash2 ignoreActors: TRUE init: hide:)
		)
		(self setFeatures: Window1 Window2 Window3 Hay1 Hay2)
		(ego illegalBits: (| cWHITE cBLUE))
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(if
			(or
				(and (== currentAct 3) (!= global114 10))
				(and (== currentAct 6) (not (& global118 $0002)))
			)
			(self setRegions: 281)
		)
		(switch prevRoomNum
			(9 (ego posn: 276 101))
			(14 (ego posn: 295 125))
			(20 (ego loop: 3 posn: 297 187))
			(21 (ego posn: 310 159))
			(25 (ego posn: 178 188))
			(69
				(ego posn: 147 142)
				(HandsOff)
				(self setScript: exit)
			)
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 13 0)
		)
		(if
			(and
				(& (ego onControl: FALSE) cRED)
				(or (== (ego loop?) 3) (== (ego loop?) 1))
				(not local0)
				(== (Door cel?) 0)
			)
			(= local0 1)
			(= gMyMusic 0)
			(self setScript: myDoor)
		)
		(if
			(and
				(== prevRoomNum 69)
				(not local1)
				(== (Door cel?) 0)
			)
			(= local1 1)
			(Door stopUpd:)
		)
		(if (& (ego onControl: origin) cBLUE)
			(curRoom newRoom: 69)
		)
		(if
			(and
				(& (ego onControl: origin) cYELLOW)
				(!= (ego mover?) 0)
				howFast
			)
			(switch (ego loop?)
				(2
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(3
					(if (== (ego cel?) 2)
						(Splash1
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 5)
						(Splash2
							posn: (+ (ego x?) 5) (ego y?)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
				(else 
					(if (== (ego cel?) 0)
						(Splash1
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
					(if (== (ego cel?) 4)
						(Splash2
							posn: (- (ego x?) 2) (+ (ego y?) 1)
							cel: 0
							show:
							setCycle: EndLoop
						)
					)
				)
			)
		)
		(switch (ego edgeHit?)
			(EAST
				(if (< (ego y?) 135)
					(curRoom newRoom: 14)
				else
					(curRoom newRoom: 21)
				)
			)
			(SOUTH
				(if (> (ego x?) 188)
					(curRoom newRoom: 20)
				else
					(curRoom newRoom: 25)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 13 0)
							)
							((Said '<in/room')
								(Print 13 1)
							)
							((Said '/chapel')
								(Print 13 2)
							)
							((Said '<(in,above)/fence')
								(Print 13 3)
							)
							((Said '/fence')
								(Print 13 4)
							)
						)
					)
					((Said '/archway')
						(Print 13 5)
					)
					(
						(or
							(Said 'climb,hop/fence')
							(Said 'enter,((go,get)<in)/fence')
						)
						(Print 13 6)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(cond 
			((== n 69)
				(cSound stop:)
			)
			((== global198 200)
				(++ global198)
				(|= deadGuests deadWILBUR)
			)
		)
		(super newRoom: n)
	)
)

(instance myDoor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(mySound number: 107 loop: 1 play:)
				(Door cycleSpeed: 3 setCycle: EndLoop self)
				(if (not gMyMusic)
					(ego ignoreControl: cBLUE)
				)
			)
			(1
				(Door stopUpd:)
				(if (not gMyMusic)
					(ego setMotion: MoveTo 145 133)
				else
					(= gMyMusic 2)
					(= cycles 1)
				)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance exit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Door setCycle: BegLoop self)
				(mySound number: 108 loop: 1 play:)
			)
			(1
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance Door of Prop
	(properties
		y 71
		x 163
		view 113
		priority 3
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/door'))
			(event claimed: TRUE)
			(Print 13 7)
		)
	)
)

(instance Splash1 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Splash2 of Prop
	(properties
		view 1
		loop 6
	)
)

(instance Window1 of RFeature
	(properties
		nsTop 69
		nsLeft 16
		nsBottom 92
		nsRight 44
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'open,examine<(in,through)/window,room')
				(Print 13 8)
			)
			((Said 'break/window')
				(Print 13 9)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/window'))
				(event claimed: TRUE)
				(Print 13 10)
			)
		)
	)
)

(instance Window2 of RFeature
	(properties
		nsTop 76
		nsLeft 60
		nsBottom 98
		nsRight 90
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 13 10)
		)
	)
)

(instance Window3 of RFeature
	(properties
		nsTop 11
		nsLeft 115
		nsBottom 36
		nsRight 175
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 13 10)
		)
	)
)

(instance Hay1 of RFeature
	(properties
		nsTop 122
		nsLeft 103
		nsBottom 149
		nsRight 130
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'move,press/bale')
				(Print 13 11)
			)
			((Said 'get/bale')
				(Print 13 12)
			)
			((or (MousedOn self event shiftDown) (Said 'examine/bale'))
				(event claimed: TRUE)
				(Print 13 13)
			)
		)
	)
)

(instance Hay2 of RFeature
	(properties
		nsTop 111
		nsLeft 212
		nsBottom 123
		nsRight 239
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(Print 13 13)
		)
	)
)

(instance mySound of Sound
	(properties
		number 107
	)
)
