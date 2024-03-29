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
	printObj
	aCar
	aSignal
	aHench
	aDoorWest
	aDoorEast
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
		((View new:)
			view: 220
			loop: 2
			cel: 0
			posn: 116 92
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
			((= aDoorWest (AutoDoor new:))
				view: 220
				setLoop: 0
				posn: 43 114
				entranceTo: (if (!= prevRoomNum 115) 115 else 0)
				msgLook: {A sign in the window says, "Now OPEN for business. Please come in!"}
				msgCloser: {The entrance door is to the left. Just walk near it.}
				init:
			)
		else
			((View new:)
				view: 220
				ignoreActors:
				posn: 43 114
				addToPic:
			)
			(ego observeControl: cYELLOW)
		)
		(if (== prevRoomNum 115)
			((= aDoorEast (Prop new:))
				view: 220
				setLoop: 1
				setCel: 255
				posn: 262 80
				cycleSpeed: 2
				init:
				setCycle: BegLoop
			)
		else
			((View new:)
				view: 220
				ignoreActors:
				setLoop: 1
				posn: 262 80
				addToPic:
			)
		)
		(if afterOnklunk
			(= gotOnklunk TRUE)
			(= currentStatus egoAUTO)
			(rm15Script changeState: 2)
			(Load VIEW 223)
			(curRoom east: 0)
			((View new:)
				view: 220
				loop: 3
				cel: 0
				posn: 27 63
				setPri: 15
				addToPic:
			)
			((= aCar (Extra new:))
				view: 220
				setLoop: 4
				setPri: 8
				posn: 20 171
				cycleSpeed: 0
				init:
			)
			((= aHench (Actor new:))
				view: 223
				loop: 0
				posn: 10 173
				setCycle: Walk
				stopUpd:
				init:
			)
			(ego posn: 253 88 init:)
			(HandsOff)
		else
			((= aSignal (Prop new:))
				view: 220
				loop: 3
				setCel: 0
				posn: 27 63
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
				(= printObj
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
		(if (and printObj (== state 4))
			(event claimed: TRUE)
			(= printObj 0)
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
