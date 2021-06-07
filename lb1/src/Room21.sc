;;; Sierra Script 1.0 - (do not remove this comment)
(script# 21)
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
	Room21 0
)
(synonyms
	(path walk)
	(lamp ignite)
)

(local
	local0
)
(instance Room21 of Room
	(properties
		picture 21
	)
	
	(method (init)
		(= south 27)
		(= east 22)
		(= north 10)
		(= horizon 86)
		(super init:)
		(self setRegions: 206 setFeatures: House BHouse)
		(if howFast
			(light1 init: setScript: showers)
			(light2 init:)
			(light3 init:)
		)
		(addToPics add: House doit:)
		(if (== currentAct 1)
			(self setRegions: 381)
		)
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
			(10 (ego posn: 280 129))
			(14 (ego posn: 89 129))
			(15 (ego posn: 313 129))
			(20 (ego posn: 7 186))
			(22
				(if (> (ego y?) 150) (ego posn: 310 172))
			)
			(27 (ego posn: 183 188))
			(else  (ego posn: 7 150))
		)
		(ego illegalBits: cWHITE view: 0 init:)
	)
	
	(method (doit)
		(if (== (ego edgeHit?) WEST)
			(if (< (ego y?) 166)
				(curRoom newRoom: 13)
			else
				(curRoom newRoom: 20)
			)
		)
		(if (FirstEntry)
			(Print 21 0)
		)
		(if (& (ego onControl: FALSE) cCYAN)
			(curRoom newRoom: 14)
		)
		(if (& (ego onControl: FALSE) cGREEN)
			(curRoom newRoom: 10)
		)
		(if (& (ego onControl: FALSE) cRED)
			(curRoom newRoom: 13)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return TRUE))
		(return
			(if (and (== (event type?) saidEvent) (Said 'examine>'))
				(cond 
					((Said '[<around,at][/room]')
						(Print 21 0)
					)
					((Said '/path')
						(Print 21 1)
					)
					((Said '/monument')
						(Print 21 2)
					)
					((Said '/lamp[<gallery]')
						(event claimed: TRUE)
						(ParseName {door})
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance showers of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (= state 3)))
			(1
				(light1 setCycle: Forward)
				(light2 setCycle: Forward)
				(light3 setCycle: Forward)
				(= cycles 7)
			)
			(2
				(light1 setCycle: EndLoop)
				(light2 setCycle: EndLoop)
				(light3 setCycle: EndLoop self)
			)
			(3 (Thunder loop: 1 play: self))
			(4
				(if (< (Random 1 100) 25) (= state 0))
				(= cycles 7)
			)
			(5
				(= state 3)
				(= seconds 5)
			)
		)
	)
)

(instance light1 of Prop
	(properties
		y 21
		x 252
		view 121
		loop 1
		cel 1
	)
)

(instance light2 of Prop
	(properties
		y 22
		x 293
		view 121
		loop 2
		cel 1
	)
)

(instance light3 of Prop
	(properties
		y 22
		x 269
		view 121
		loop 3
		cel 1
	)
)

(instance Thunder of Sound
	(properties
		number 17
		loop 0
	)
)

(instance House of RPicView
	(properties
		y 87
		x 108
		view 121
	)
	
	(method (handleEvent)
		(if
			(or
				(Said 'examine/cabin<little')
				(Said 'examine/playhouse')
				(Said 'examine/cabin<play')
			)
			(Print 21 3)
		)
	)
)

(instance BHouse of RFeature
	(properties
		nsTop 23
		nsLeft 275
		nsBottom 98
		nsRight 319
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {house})
		)
	)
)
