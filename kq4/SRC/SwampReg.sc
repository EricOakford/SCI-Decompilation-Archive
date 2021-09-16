;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWAMP)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	swampReg 0
)
(synonyms
	(water water marsh)
)

(local
	local0
	swampDepth
	poof
	swampThing
	thisControl
	saveViewer
)
(instance poofSound of Sound
	(properties
		number 59
	)
)

(instance swampReg of Region
	(method (init)
		(Load VIEW 5)
		(Load VIEW 6)
		(Load VIEW 7)
		(Load VIEW 10)
		(Load VIEW 372)
		(Load VIEW 377)
		(Load VIEW 193)
		(Load VIEW 680)
		(Load VIEW 370)
		(= local0 0)
		(ego viewer: swamp)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'bathe,dive,wade[<enter][/ocean]')
						(Print 513 0)
					)
					(
						(or
							(Said 'enter/fish')
							(Said 'fish[/!*]')
							(Said 'capture/fish')
							(Said 'cast/pole')
						)
						(Print 513 1)
					)
					((Said 'get/water')
						(Print 513 2)
					)
					((or (Said 'drink') (Said 'get/drink'))
						(if (== (ego view?) 2)
							(if
								(or
									(& (= thisControl (IsObjectOnControl ego 12)) cCYAN)
									(& thisControl cLCYAN)
									(& thisControl cBLUE)
									(& thisControl cLBLUE)
								)
								(= oldEgoScript (ego script?))
								(ego setScript: drinking)
							else
								(Print 800 1)
							)
						else
							(Print 513 3)
						)
					)
					((Said 'look<in/water')
						(Print 513 4)
					)
					((Said 'look,climb/boulder<[gray]')
						(Print 513 5)
					)
					((Said 'look>')
						(cond 
							((Said '/water')
								(Print 513 6)
							)
							((Said '/cliff')
								(Print 513 7)
							)
							((Said '/dirt')
								(Print 513 8)
							)
							((Said '/tuft')
								(Print 513 9)
							)
							((Said '/grass')
								(Print 513 10)
							)
							((Said '/bush')
								(Print 513 11)
							)
							((Said '/flora')
								(Print 513 12)
							)
							((Said '/blossom')
								(Print 513 13)
							)
							((Said '/forest')
								(Print 513 14)
							)
						)
					)
					((Said 'climb,cross/cliff')
						(Print 513 15)
					)
					((Said 'get/blossom')
						(Print 513 13)
					)
					(
					(or (Said 'dennis/crown') (Said 'place/crown'))
						(cond 
							((and (ego inRect: 115 149 274 181) (== curRoomNum 78))
								(Print 513 16)
							)
							((!= (ego view?) 2)
								(Print 513 17)
							)
							((ego has: iCrown)
								(swamp changeState: 20)
							)
							(else
								(Print 800 2)
							)
						)
					)
					((or (Said 'detach/crown') (Said 'get<off/crown'))
						(if (== currentStatus egoIsFrog)
							(swamp changeState: 30)
						else
							(Print 513 18)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance swamp of Script
	(method (doit)
		(super doit:)
		(= local0 (= swampDepth (ego onControl: origin)))
		(cond 
			((and (== currentStatus egoOnSwampGrass) (== laidDownBoard FALSE))
				(switch swampDepth
					(cBLACK
						(ego illegalBits: cWHITE view: 2)
					)
					(cLCYAN
						(ego illegalBits: -31744 view: 5)
					)
					(cLBLUE
						(ego illegalBits: -31744 view: 6)
					)
					(cCYAN
						(ego illegalBits: -31744 view: 7)
					)
					(cBLUE
						(self changeState: 1)
					)
				)
			)
			((== currentStatus egoIsFrog)
				(if (== (ego mover?) 0) (ego cel: 0))
				(switch swampDepth
					(cBLACK
						(ego illegalBits: (| cWHITE cYELLOW) view: 372)
					)
					(cLCYAN
						(ego illegalBits: cWHITE view: 377)
					)
					(cLBLUE
						(ego illegalBits: cWHITE view: 377)
					)
					(cCYAN
						(ego view: 377 illegalBits: cWHITE)
					)
					(cBLUE
						(self changeState: 10)
					)
					(1024
						(ego illegalBits: cWHITE view: 372)
					)
					(else 
						(ego illegalBits: -15360 view: 377)
					)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= currentStatus egoNormal)
				(ego
					illegalBits: 1
					view: 73
					cel: 0
					loop: (& (ego loop?) 1)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				(ego hide:)
				(Print 513 19)
				(Print 513 20)
				(Timer setReal: self 5)
			)
			(3
				(= dead TRUE)
			)
			(10
				(HandsOff)
				(= currentStatus egoNormal)
				(= swampThing (Actor new:))
				(swampThing
					view: 193
					posn: (ego x?) (- (ego y?) 5)
					loop: 0
					cel: 0
					ignoreActors:
					setCycle: EndLoop self
					init:
				)
				((Sound new:) number: 47 play:)
			)
			(11
				(ego hide:)
				(= currentStatus egoNormal)
				(swampThing loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(12
				(swampThing loop: 2 cel: 0 setCycle: Forward)
				(Timer setReal: self 4)
			)
			(13
				(Print 513 21 #at -1 10)
				(swampThing cel: 0 loop: 3 setCycle: EndLoop self)
			)
			(14
				(swampThing dispose:)
				(Timer setReal: self 5)
			)
			(15
				(= dead TRUE)
			)
			(20
				(HandsOff)
				(sounds eachElementDo: #stop 0)
				(poofSound play:)
				(= poof (Prop new:))
				(poof
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					view: 680
					cel: 0
					loop: 0
					ignoreActors:
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(21
				(poof setCycle: EndLoop self)
				(= currentStatus egoIsFrog)
				(ego observeControl: 16384)
				(ego view: 370)
			)
			(22
				(Timer setReal: self 3)
				(poof dispose:)
			)
			(23
				(ego view: 372 setCycle: Walk)
				(HandsOn)
			)
			(30
				(HandsOff)
				(sounds eachElementDo: #stop 0)
				(poofSound play:)
				(= poof (Prop new:))
				(poof
					posn: (ego x?) (ego y?)
					setPri: (+ (ego priority?) 1)
					view: 680
					cel: 0
					loop: 0
					ignoreActors:
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(31
				(poof setCycle: EndLoop self)
				(= currentStatus egoOnSwampGrass)
				(ego view: 2 ignoreControl: 16384 setCycle: Walk)
				(HandsOn)
			)
			(32
				(poof dispose:)
			)
		)
	)
)

(instance drinking of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveViewer (ego viewer?))
				(ego viewer: 0 view: 21 cel: 0 setCycle: EndLoop self)
			)
			(1
				(= underBits (Print 513 22 #at -1 10 #dispose))
				(Timer setReal: self 4)
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(cls)
				(ego view: 2 setCycle: Walk)
				(ego viewer: saveViewer script: oldEgoScript)
				(HandsOn)
			)
		)
	)
)
