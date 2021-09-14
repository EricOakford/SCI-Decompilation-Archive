;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm15 0
)

(local
	storeIsOpen
	afterOnklunk
	saveBits
)
(instance rm15 of Room
	(properties
		picture 15
		horizon 77
		north 11
		east 16
		south 19
	)
	
	(method (init)
		(Load VIEW 220)
		(Load VIEW 223)
		(super init:)
		(NormalEgo)
		(self setRegions: CITY setScript: rm15Script)
		(aView1
			loop: 2
			cel: 0
			setPri: 8
			addToPic:
		)
		(if (and (== gotOnklunk FALSE) (ego has: iOnklunk))
			(= afterOnklunk TRUE)
		)
		(if (and gotHaircutInCity (not (ego has: iOnklunk)))
			(= storeIsOpen TRUE)
		)
		(if (or afterOnklunk storeIsOpen)
			(aDoorWest
				setLoop: 0
				entranceTo: (if (!= prevRoomNum 115) 115 else 0)
				msgLook: {A sign in the window says, "Now OPEN for business. Please come in!"}
				msgCloser: {The entrance door is to the left. Just walk near it.}
				init:
			)
		else
			(aView2
				ignoreActors:
				addToPic:
			)
			(ego observeControl: cYELLOW)
		)
		(if (== prevRoomNum 115)
			(aDoorEast
				setLoop: 1
				setCel: 255
				cycleSpeed: 2
				init:
				setCycle: BegLoop
			)
		else
			(aView3
				ignoreActors:
				setLoop: 1
				addToPic:
			)
		)
		(if afterOnklunk
			(= gotOnklunk TRUE)
			(= currentStatus egoAUTO)
			(rm15Script changeState: 2)
			(Load VIEW 223)
			(curRoom east: 0)
			(aView4
				loop: 3
				cel: 0
				setPri: 15
				addToPic:
			)
			(aCar
				setLoop: 4
				setPri: 8
				cycleSpeed: 0
				init:
			)
			(aHench
				loop: 0
				setCycle: Walk
				stopUpd:
				init:
			)
			(ego posn: 253 88 init:)
			(HandsOff)
		else
			(aSignal
				loop: 3
				setCel: 0
				setPri: 15
				stopUpd:
				init:
				setScript: trafficSignalScript
			)
			(cond 
				((== prevRoomNum 0)
					(ego posn: 302 80)
				)
				((== prevRoomNum 11)
					(ego posn: 302 80)
				)
				((== prevRoomNum 19)
					(ego posn: 8 187)
				)
				((== prevRoomNum 115)
					(ego posn: 253 88)
				)
			)
			(ego init:)
		)
	)
)

(instance rm15Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 30)
			)
			(1
				(if (and storeIsOpen (!= prevRoomNum 115))
					(Print 15 8)
				)
				(if (== prevRoomNum 115)
					(aDoorWest entranceTo: 115)
				)
			)
			(2
				(= cycles 0)
				(= seconds 3)
			)
			(3
				(ego setMotion: MoveTo 262 140 self)
			)
			(4
				(= saveBits
					(Print 15 9
						#at -1 20
						#dispose
					)
				)
			)
			(5
				(ego setMotion: MoveTo 262 170 self)
				(aDoorEast stopUpd:)
			)
			(6
				(aDoorWest locked: FALSE)
				(Print 15 10)
				(Print 15 11)
				(ego setMotion: MoveTo 350 170 self)
			)
			(7
				(Print 15 12)
				(aHench setMotion: MoveTo 333 (aHench y?) self)
			)
			(8
				(aHench dispose:)
				(= seconds 4)
			)
			(9
				(= currentStatus egoDOPPLEGANGER)
				(aDoorWest entranceTo: 115)
				(ego
					posn: -30 125
					setMotion: MoveTo 49 119 self
					illegalBits: 0
				)
			)
			(10
				(Print 15 13)
				(Print 15 14)
				(Print 15 15)
				(ego setMotion: MoveTo 78 105 self)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (and saveBits (== state 4))
			(event claimed: TRUE)
			(= saveBits 0)
			(cls)
			(self cue:)
		)
		(if (!= (event type?) saidEvent) (return))
		(if (Said '/door,sign')
			(cond 
				(storeIsOpen
					(Print 15 0)
				)
				((ego has: iOnklunk)
					(Print 15 1)
				)
				(else
					(Print 15 2)
				)
			)
			(if (< (ego x?) 155)
				(Print 15 3)
			else
				(Print 15 4)
			)
		)
		(if (Said '/pole,burn,(burn<done),(sign<done)')
			(Print 15 5)
		)
		(if
			(and
				(Said 'look>')
				(Said '[/building,(building<music),building,sign,airport]')
			)
			(Print 15 6)
			(Print 15 7)
			(if storeIsOpen
				(Print 15 0)
			)
		)
	)
)

(instance trafficSignalScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 6)
			)
			(1
				(aSignal setCel: 1)
				(= seconds 4)
			)
			(2
				(aSignal setCel: 2)
				(= seconds 13)
			)
			(3
				(aSignal setCel: 0)
				(= seconds 13)
				(= state 0)
			)
		)
	)
)

(instance aView1 of View
	(properties
		y 92
		x 116
		view 220
	)
)

(instance aView2 of View
	(properties
		y 114
		x 43
		view 220
	)
)

(instance aView3 of View
	(properties
		y 80
		x 262
		view 220
	)
)

(instance aView4 of View
	(properties
		y 63
		x 27
		view 220
	)
)

(instance aDoorWest of AutoDoor
	(properties
		y 114
		x 43
		view 220
		msgLook {A sign in the window says, "Now OPEN for business. Please come in!"}
	)
)

(instance aDoorEast of Prop
	(properties
		y 80
		x 262
		view 220
	)
)

(instance aCar of Extra
	(properties
		y 171
		x 20
		view 220
	)
)

(instance aHench of Actor
	(properties
		y 173
		x 10
		view 223
	)
)

(instance aSignal of Prop
	(properties
		y 63
		x 27
		view 220
	)
)
