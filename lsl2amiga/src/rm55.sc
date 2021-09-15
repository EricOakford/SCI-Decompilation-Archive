;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include game.sh)
(use Main)
(use AirplaneActor)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm55 0
)

(local
	waitressX
	local1
	bluePateState
	wrongWayMsg
)

(enum ;Blue Pate states
	readyToOrder
	orderedIt
	gotServed
	removedPin
)

(instance rm55 of Room
	(properties
		picture 55
		horizon 5
		west 54
	)
	
	(method (init)
		(Load VIEW 511)
		(Load VIEW 519)
		(Load VIEW 520)
		(super init:)
		(addToPics add: aMachine1 aMachine2 aSign doit:)
		(aPlate
			init:
		)
		(aDoor
			setPri: 11
			cycleSpeed: 1
			stopUpd:
			init:
		)
		(aParachute
			setCel: 0
			setPri: 11
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		(aPlane
			startX: 306
			startY: 22
			endX: 22
			endY: 22
			init:
		)
		(aSidewalkNorth
			setLoop: 1
			setPri: 0
			setStep: 1 1
			illegalBits: 0
			init:
			setScript: sidewalkNorthScript
		)
		(aSidewalkSouth
			setLoop: 1
			setPri: 0
			setStep: 1 1
			illegalBits: 0
			init:
			setScript: sidewalkSouthScript
		)
		(aWaitress
			setPri: 15
			illegalBits: 0
			init:
			setCycle: Walk
			setScript: waitressScript
		)
		(self setRegions: AIRPORT setScript: rm55Script)
		(if (== prevRoomNum 57)
			(= currentStatus egoINTERMINAL)
			(HandsOff)
			(ego
				illegalBits: cWHITE
				setPri: -1
				setLoop: 2
				setCycle: 0
				setCel: 0
				setStep: 1 1
				posn: 141 87
				init:
			)
			(rm55Script changeState: 1)
		else
			(NormalEgo)
			(ego observeControl: cGREEN posn: 33 145 init:)
		)
	)
)

(instance rm55Script of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (& (ego onControl:) cBLUE) (== currentStatus egoNORMAL))
				(curRoom newRoom: 54)
			)
			((and (& (ego onControl:) cRED) (== currentStatus egoNORMAL))
				(= currentStatus egoINTERMINAL)
				(self changeState: 3)
			)
			((& (ego onControl:) cCYAN)
				(if (and (== currentStatus egoNORMAL) (not wrongWayMsg))
					(= wrongWayMsg TRUE)
					(Print 55 0)
				)
			)
			(else
				(= wrongWayMsg FALSE)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(ego
					illegalBits: 0
					setCel: 0
					setMotion: MoveTo 125 114 self
				)
			)
			(2
				(NormalEgo loopS)
				(ego observeControl: cGREEN)
			)
			(3
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 3
					setCel: 4
					setStep: 1 1
					setMotion: MoveTo 174 88 self
				)
			)
			(4
				(curRoom newRoom: 56)
			)
			(5
				(HandsOff)
				(Print 55 34)
				(= seconds 3)
			)
			(6
				(aDoor setCycle: EndLoop self)
			)
			(7
				(aParachute posn: 264 146 setCycle: EndLoop self)
			)
			(8
				(aDoor setCycle: BegLoop self)
			)
			(9
				(= seconds 3)
			)
			(10
				(aParachute dispose:)
				(ego get: iParachute)
				(theGame changeScore: 3)
				(Print 55 35 #draw)
				(User canControl: TRUE canInput: TRUE)
			)
			(11
				(= bluePateState orderedIt)
				(HandsOff)
				(Print 55 36)
				(= seconds 3)
			)
			(12
				(Print 55 37)
				(= seconds 3)
			)
			(13
				(Print 55 38)
				(waitressScript changeState: 10)
			)
			(14
				(User canControl: TRUE canInput: TRUE)
				(Print 55 39)
				(Print 55 40)
				(aPlate posn: (ego x?) 176)
				(= bluePateState gotServed)
			)
			(15
				(= currentStatus egoSTOPPED)
				(HandsOff)
				(Ok)
				(Print 55 41)
				(ego hide:)
				(Print 55 42 #draw)
				(= seconds 3)
			)
			(16
				(Print 55 43)
				(Print 55 44)
				(Print 55 45 #at -1 130)
				(= currentStatus egoDYING)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'explore,(look<in)/appetizer,pate,gravy,special')
			(cond 
				((!= bluePateState gotServed)
					(DontHave)
				)
				((not (ego inRect: 35 181 151 189))
					(NotClose)
				)
				(else
					(Print 55 1)
				)
			)
		)
		(if (Said 'look>')
			(if (Said '/appetizer,gravy,special')
				(cond 
					((!= bluePateState gotServed)
						(DontHave)
					)
					((not (ego inRect: 35 181 151 189))
						(NotClose)
					)
					(else
						(Print 55 2)
					)
				)
			)
			(if (Said '/carpet')
				(Print 55 3)
			)
			(if (Said '/dispenser')
				(Print 55 4)
				(Print 55 5 #at -1 130)
			)
			(if (Said '/buffet,buffet,bar')
				(Print 55 6)
			)
			(if (Said '/bimbo,agent')
				(Print 55 7)
				(Print 55 8 #at -1 130)
			)
			(if (Said '/menu,sign')
				(Print 55 9 #mode teJustCenter)
				(Print 55 10)
			)
			(if (Said '[/airport,brick]')
				(Print 55 11)
			)
		)
		(if (Said 'ask,buy/menu')
			(Print 55 12)
			(Print 55 13)
		)
		(if (Said 'call/bimbo,agent')
			(cond 
				((ego has: iBobbyPin)
					(Print 55 14)
					(Print 55 15)
				)
				((== bluePateState gotServed)
					(Print 55 16)
					(Print 55 17 #at -1 130)
				)
				((and bluePateState (< bluePateState gotServed))
					(Print 55 18)
					(Print 55 19 #at -1 130)
				)
				(else
					(Print 55 20)
					(Print 55 21)
				)
			)
		)
		(if (Said 'buy,buy/special,appetizer')
			(cond 
				(
					(or
						bluePateState
						(not ((inventory at: iBobbyPin) ownedBy: curRoomNum))
					)
					(Print 55 22)
					(Print 55 23 #at -1 130)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not (ego inRect: 35 181 151 189))
					(NotClose)
				)
				(else
					(self changeState: 11)
				)
			)
		)
		(if (Said 'get/special,appetizer')
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((< bluePateState gotServed)
					(Print 55 24)
				)
				((not (ego inRect: 35 181 151 189))
					(NotClose)
				)
				(else
					(Print 55 25)
				)
			)
		)
		(if (Said 'eat/special,appetizer')
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((== bluePateState removedPin)
					(Print 55 26)
				)
				((!= bluePateState gotServed)
					(Print 55 27)
				)
				((not (ego inRect: 35 181 151 189))
					(NotClose)
				)
				(else
					(self changeState: 15)
				)
			)
		)
		(if (Said 'eat/noword')
			(Print 55 28)
		)
		(if (Said 'explore,explore,get/gravy,special,bobbypin')
			(cond 
				((not ((inventory at: iBobbyPin) ownedBy: curRoomNum))
					(AlreadyTook)
				)
				((!= bluePateState gotServed)
					(Print 55 29)
				)
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((not (ego inRect: 35 181 151 189))
					(NotClose)
				)
				(else
					(ego get: iBobbyPin)
					(= bluePateState removedPin)
					(aPlate posn: 999 999)
					(theGame changeScore: 7)
					(Print 55 30 #draw)
				)
			)
		)
		(if
			(or
				(Said 'buy,get/insurance,parachute')	;EO: fixed said spec
				(Said 'apply/dispenser')
				(Said 'conceal/buck/dispenser')
			)
			(cond 
				((!= currentStatus egoNORMAL)
					(NotNow)
				)
				((ego inRect: 272 178 287 186)
					(Print 55 31)
				)
				((not (ego inRect: 248 150 275 172))
					(NotClose)
				)
				((not ((inventory at: iParachute) ownedBy: curRoomNum))
					(Print 55 32)
					(Print 55 33 #at -1 130)
				)
				(else
					(self changeState: 5)
				)
			)
		)
	)
)

(instance waitressScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 1 3))
			)
			(1
				(= waitressX (Random 5 150))
				(aWaitress
					setLoop: (if (> waitressX (aWaitress x?)) 2 else 3)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(aWaitress
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo waitressX 175 self
				)
			)
			(3
				(aWaitress
					setLoop: (+ (aWaitress loop?) 2)
					cel: 3
					setCycle: BegLoop self
				)
			)
			(4
				(= seconds (Random 2 5))
			)
			(5
				(switch (Random 1 7)
					(1
						(aWaitress
							cycleSpeed: 1
							setLoop: 4
							cel: 0
							setCycle: EndLoop self
						)
					)
					(2
						(aWaitress
							cycleSpeed: 1
							setLoop: 6
							cel: 0
							setCycle: EndLoop self
						)
					)
					(else
						(self changeState: 8)
					)
				)
			)
			(6
				(aWaitress
					setLoop: (+ (aWaitress loop?) 1)
					setCycle: Forward
				)
				(= cycles (Random 30 50))
			)
			(7
				(aWaitress
					setLoop: (- (aWaitress loop?) 1)
					cel: 3
					setCycle: BegLoop self
				)
			)
			(8
				(aWaitress
					setLoop: 2
					setCel: 0
					cycleSpeed: 0
					setCycle: Walk
				)
				(= seconds (Random 1 3))
			)
			(9
				(self changeState: 0)
			)
			(10
				(= seconds (= cycles 0))
				(aWaitress
					setCycle: Walk
					setLoop: -1
					setMotion: MoveTo -222 175 self
				)
			)
			(11
				(aWaitress setMotion: MoveTo (ego x?) 175 self)
			)
			(12
				(rm55Script cue:)
				(self changeState: 3)
			)
		)
	)
)

(instance sidewalkNorthScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aSidewalkNorth setMotion: MoveTo 184 66 self)
			)
			(1
				(aSidewalkNorth posn: 187 122)
				(self changeState: 0)
			)
		)
	)
)

(instance sidewalkSouthScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(aSidewalkSouth setMotion: MoveTo 124 118 self)
			)
			(1
				(aSidewalkSouth posn: 127 65)
				(self changeState: 0)
			)
		)
	)
)

(instance aMachine1 of PicView
	(properties
		y 166
		x 279
		view 519
		cel 1
		priority 11
		signal ignrAct
	)
)

(instance aMachine2 of PicView
	(properties
		y 187
		x 301
		view 519
		cel 2
		priority 11
		signal ignrAct
	)
)

(instance aSign of PicView
	(properties
		y 160
		x 19
		view 519
		priority 12
		signal ignrAct
	)
)

(instance aPlate of View
	(properties
		y 999
		x 999
		view 519
		cel 3
		priority 15
		signal ignrAct
	)
)

(instance aDoor of Prop
	(properties
		y 127
		x 257
		view 519
		loop 2
		signal ignrAct
	)
)

(instance aParachute of Prop
	(properties
		y 1146
		x 264
		view 519
		loop 3
		signal ignrAct
	)
)

(instance aPlane of Airplane
	(properties)
)

(instance aSidewalkNorth of Actor
	(properties
		y 98
		x 187
		view 519
		signal ignrAct
	)
)

(instance aSidewalkSouth of Actor
	(properties
		y 65
		x 127
		view 519
		signal ignrAct
	)
)

(instance aWaitress of Actor
	(properties
		y 173
		x 52
		view 520
		loop 3
		signal ignrAct
	)
)
