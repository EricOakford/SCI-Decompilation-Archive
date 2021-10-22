;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	drownCycles
)
(instance rm510 of Room
	(properties
		picture 510
		horizon 65
		north 520
		south 500
	)
	
	(method (init)
		(Bset fPassedMaze)
		(Load VIEW 511)
		(Load VIEW 812)
		(Load SOUND 6)
		(super init:)
		(self setScript: RoomScript)
		(aRock1 init:)
		(aRock2 init:)
		(aRock3 init:)
		(aRock4 init:)
		(aRock5 init:)
		(if (== prevRoomNum 520)
			(ego posn: 275 79 loop: 2)
		else
			(ego posn: 284 188)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (& (ego onControl: origin) cLBLUE) (== currentStatus egoNORMAL))
				(self changeState: 2)
			)
			((and (== currentStatus egoSWIMMING) (< 8 (++ drownCycles)))
				(= drownCycles 0)
				(ego setLoop: (+ (Random 0 1) (* 2 (< (ego y?) 87))))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(2
				(= currentStatus egoSWIMMING)
				(HandsOff)
				(rm510 horizon: 1)
				(ego
					view: 812
					setLoop: 0
					setStep: 1 3
					setCycle: Forward
					illegalBits: 0
					posn: (- (ego x?) 20) (ego y?)
					setMotion: MoveTo 160 104 self
				)
				(music number: 6 loop: -1 play:)
			)
			(3
				(ego setMotion: MoveTo 179 87 self)
			)
			(4
				(ego setMotion: MoveTo 200 54 self)
			)
			(5
				(curRoom newRoom: 520)
			)
			(6
				(HandsOff)
				(= currentStatus egoDRINKWATER)
				(ego
					view: 511
					cycleSpeed: 2
					setLoop: 0
					cel: 0
					setCycle: EndLoop self
				)
			)
			(7
				(ego setLoop: 1 setCycle: Forward)
				(= cycles
					(* 2 (ego cycleSpeed?) 4 (- (NumCels ego) 1))
				)
			)
			(8
				(ego setLoop: 0 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(= seconds 2)
			)
			(10
				(theGame changeScore: 42)
				(Bset fDrankRiverWater)
				(NormalEgo loopW)
				(= currentStatus egoNORMAL)
				(Print 510 10)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			(
				(or
					(Said 'get/drink,water')
					(Said 'drink')
					(Said 'drink/water')
				)
				(cond 
					((Btst fDrankRiverWater)
						(Print 510 0)
					)
					((not (& (ego onControl:) cLBLUE))
						(NotClose)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(self changeState: 6)
					)
				)
			)
			((Said 'make/hemp')
				(Print 510 1)
			)
			((or (Said 'make/boat') (Said 'climb,get,use/bamboo'))
				(Print 510 2)
			)
			((or (Said 'go<swim') (Said 'swim'))
				(Print 510 3)
			)
			((Said 'look>')
				(cond 
					((Said '/palm')
						(Print 510 4)
					)
					((Said '/boulder,boob')
						(Print 510 5)
					)
					((Said '/bamboo')
						(Print 510 6)
					)
					((Said '/cascade,creek')
						(Print 510 7)
						(Print 510 8 #at -1 144)
					)
					((Said '[/area]')
						(Print 510 9)
					)
				)
			)
		)
	)
)

(instance aRock1 of Prop
	(properties
		y 177
		x 49
		view 520
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock2 of Prop
	(properties
		y 170
		x 169
		view 520
		loop 1
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock3 of Prop
	(properties
		y 189
		x 157
		view 520
		loop 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock4 of Prop
	(properties
		y 143
		x 52
		view 520
		loop 2
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)

(instance aRock5 of Prop
	(properties
		y 104
		x 111
		view 520
		loop 2
		cel 1
		cycleSpeed 1
	)
	
	(method (init)
		(super init:)
		(self setCycle: Forward isExtra: TRUE ignoreActors:)
	)
)
