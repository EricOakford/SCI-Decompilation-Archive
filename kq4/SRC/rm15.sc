;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room15 0
)
(synonyms
	(lake lake)
	(prince prince prince prince man)
)

(local
	waterControl
	thisControl
	local2
	aFrog
	aPoof
	frogLoop
	aBall
	ripple1
	ripple2
	ripple3
	ripple4
)
(instance frogTheme of Sound
	(properties
		number 28
	)
)

(instance poofSound of Sound
	(properties
		number 59
	)
)

(instance Room15 of Room
	(properties
		picture 15
	)
	
	(method (init)
		(= north 9)
		(= south 21)
		(= east 16)
		(= west 14)
		(= horizon 75)
		(= isIndoors FALSE)
		(if (<= (ego y?) horizon)
			(ego posn: (ego x?) (- horizon 2))
		)
		(if (< (ego x?) 29)
			(ego x: 29)
		)
		(super init:)
		(if isNightTime
			(curRoom overlay: 115)
		)
		(self setRegions: WOODS)
		(ego view: 2 setStep: 3 2 init:)
		(if (== frogPrinceState 0)
			(Load VIEW 518)
			(Load VIEW 375)
			(Load VIEW 372)
			(Load VIEW 373)
			(Load VIEW 376)
			(Load VIEW 377)
			(Load VIEW 370)
			(Load VIEW 378)
			(Load VIEW 26)
			(Load VIEW 27)
			(Load VIEW 29)
			(Load VIEW 21)
			(Load VIEW 6)
			(Load VIEW 27)
			(Load VIEW 28)
			(Load VIEW 680)
			(Load VIEW 225)
			(Load VIEW 226)
			(Load VIEW 227)
		)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(= ripple4 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 650
			loop: 2
			cel: 0
			posn: 242 153
			setPri: 11
			ignoreActors:
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 650
			loop: 3
			cel: 0
			posn: 187 146
			setPri: 10
			ignoreActors:
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		(ripple3
			isExtra: TRUE
			view: 650
			loop: 4
			cel: 0
			posn: 89 141
			setPri: 10
			ignoreActors:
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		(ripple4
			isExtra: TRUE
			view: 650
			loop: 5
			cel: 1
			posn: 220 138
			setPri: 10
			ignoreActors:
			cycleSpeed: 1
			setCycle: Forward
			init:
		)
		(if (== prevRoomNum 0)
			(ego x: 1 y: 150)
		)
		((View new:)
			view: 518
			loop: 0
			cel: (if isNightTime 1 else 0)
			setPri: 10
			posn: 204 147
			init:
			addToPic:
		)
		(if ((Inventory at: iCrown) ownedBy: 200)
			(= aFrog (Actor new:))
			(frogTheme play:)
			(= frogPrinceState 0)
			(aFrog
				view: 370
				cycleSpeed: 2
				setCycle: Forward
				x: 206
				y: 145
				ignoreActors:
				init:
			)
		)
		(if ((Inventory at: iGoldBall) ownedBy: 15)
			((= aBall (View new:))
				view: 518
				loop: 1
				cel: 0
				posn: 265 134
				ignoreActors:
				stopUpd:
				init:
			)
		)
		(curRoom setScript: frogActions)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== (= waterControl (ego onControl: origin)) cLBLUE)
				(== frogPrinceState 0)
			)
			(frogActions changeState: 1)
		)
		(if (!= thisControl waterControl)
			(= thisControl waterControl)
			(switch waterControl
				(cBLACK
					(if (not (ego has: iFrog))
						(= currentStatus egoNormal)
						(ego view: 2)
					)
				)
				(cLBLUE
					(if (not (ego has: iFrog))
						(ego view: 6)
						(= currentStatus egoInKneeDeepWater)
					)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp index)
		(if
			(and
				(== frogPrinceState 4)
				(or
					(!= (ego xLast?) (ego x?))
					(!= (ego yLast?) (ego y?))
					(== (event type?) direction)
					(== (event type?) mouseDown)
				)
			)
			((Inventory at: iFrog) moveTo: 15)
			(frogActions changeState: 60)
			(= frogPrinceState 7)
		)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said '/prince>')
						(if (and (== frogPrinceState 5) (cast contains: aFrog))
							(cond 
								((Said 'look')
									(Print 15 0)
								)
								((Said 'converse')
									(Print 15 1)
								)
								((Said 'get')
									(Print 15 2)
								)
								((Said 'kiss')
									(Print 15 3)
								)
							)
						else
							(Print 15 4)
							(event claimed: TRUE)
						)
					)
					(
						(or
							(Said 'fish<enter')
							(Said 'capture/fish')
							(Said 'cast/pole')
						)
						(Print 15 5)
					)
					((Said 'look,find,look/frog')
						(cond 
							((ego has: iFrog)
								((Inventory at: iFrog) showSelf:)
							)
							((or (== frogPrinceState frogInit) (== frogPrinceState frogHasBall))
								(Print 15 6)
							)
							((== frogPrinceState frogUnderwater)
								(Print 15 7)
							)
							((== frogPrinceState frogGettingBall)
								(Print 15 8)
							)
							((== frogPrinceState frogHeldByEgo)
								(Print 15 9)
							)
							(else
								(Print 15 10)
							)
						)
					)
					((Said 'look>')
						(cond 
							((Said '<under,in/water')
								(Print 15 7)
							)
							((Said '/lake,water')
								(Print
									(Format @str 15 11
										(if (== frogPrinceState frogInit)
											{You spy a large frog sitting on top of a big lily pad, and YES!...it's wearing a little gold crown!}
										else
											{_}
										)
									)
								)
							)
							((or (Said '<down[/dirt]') (Said '/dirt'))
								(cond 
									((and ((Inventory at: iGoldBall) ownedBy: 15) (== frogPrinceState frogHasBall))
										(Print 15 12)
									)
									(((Inventory at: iGoldBall) ownedBy: 15)
										(Print 15 13)
									)
									((== frogPrinceState frogHasBall)
										(Print 15 14)
									)
									(else
										(Print 15 15)
									)
								)
							)
							((Said '/lilies,pad')
								(Print
									(Format @str 15 16
										(if (== frogPrinceState frogInit)
											{Atop one large lily pad, sits a big frog.}
										else
											{}
										)
									)
								)
							)
							((Said '/crown')
								(cond 
									((== frogPrinceState frogHeldByEgo)
										(Print 15 17)
									)
									((ego has: iCrown)
										((Inventory at: iCrown) showSelf:)
									)
									(else
										(Print 800 1)
									)
								)
							)
							((Said '/blossom')
								(Print 15 18)
							)
							((Said '[<around][/room]')
								(Print
									(Format @str 15 19
										(if (== frogPrinceState frogInit)
											{Atop one of the bigger lily pads, you notice a rather large frog...wearing a little gold crown!}
										else
											{}
										)
									)
								)
							)
						)
					)
					((Said 'get/blossom')
						(Print 15 20)
					)
					((Said 'bathe,dive')
						(Print 15 21)
					)
					((Said 'wade')
						(Print 15 22)
					)
					((or (Said 'get/drink') (Said 'drink[/water]'))
						(= local2 (IsObjectOnControl ego 12))
						(cond 
							((== (ego view?) 2)
								(if (& local2 cLBLUE)
									(= oldEgoScript (ego script?))
									(ego setScript: drinking)
								else
									(Print 800 1)
								)
							)
							((== (ego view?) 6)
								(Print 15 23)
							)
							(else
								(Print 15 24)
							)
						)
					)
					((Said 'get,get,capture/frog')
						(cond 
							((== frogPrinceState frogHeldByEgo)
								(Print 800 0)
							)
							((== frogPrinceState frogUnderwater)
								(Print 15 25)
							)
							((== frogPrinceState frogPrinceLeft)
								(Print 15 26)
							)
							((!= frogPrinceState frogHasBall)
								(Print 15 27)
							)
							((== (ego view?) 6)
								(Print 15 28)
							)
							((ego has: iGoldBall)
								(CantDo)
							)
							((ego inRect: 249 133 266 139)
								(Print 15 29)
							)
							((== frogPrinceState 7)
								(CantDo)
							)
							((== frogPrinceState frogHasBall)
								(if (< (ego distanceTo: aFrog) 13)
									(HandsOff)
									(Face ego aFrog)
									(frogActions changeState: 10)
								else
									(Print 15 30)
								)
							)
						)
					)
					((Said 'get/lilies,pad')
						(Print 15 31)
					)
					((Said 'converse[/frog]')
						(if (or (== frogPrinceState 0) (== frogPrinceState 3) (== frogPrinceState 4))
							(Print 15 32)
							(Print 15 33)
						else
							(Print 15 34)
						)
					)
					((Said 'throw,drop,place,deliver/ball')
						(if (ego has: iGoldBall)
							(cond 
								((== frogPrinceState frogInit)
									(if ((Inventory at: iCrown) ownedBy: 200)
										(if
											(and
												(& (= local2 (IsObjectOnControl ego 15)) cLBLUE)
												(== (ego view?) 2)
											)
											(HandsOff)
											(Face ego aFrog)
											(Print 15 35 #draw)
											(= frogPrinceState 2)
											(frogActions changeState: 3)
										else
											(Print 15 36)
										)
									else
										(Print 15 37)
									)
								)
								((!= (ego view?) 2)
									(Print 15 38)
								)
								(else
									(Print 15 39)
								)
							)
						else
							(Print 15 40)
						)
					)
					((or (Said 'kiss/frog') (Said 'kiss[/noword]'))
						(if (== frogPrinceState frogHeldByEgo)
							(Print 15 41)
							(frogActions changeState: 50)
							(= frogPrinceState frogIsPrince)
						else
							(Print 15 42)
						)
					)
					((Said 'get/ball')
						(if (== (ego view?) 2)
							(cond 
								((== frogPrinceState 7)
									(CantDo)
								)
								(((Inventory at: iGoldBall) ownedBy: 15)
									(if (< (ego distanceTo: aBall) 10)
										(HandsOff)
										(ego setScript: ballActions)
										(ballActions changeState: 1)
									else
										(Print 800 1)
									)
								)
								((ego has: iGoldBall)
									(Print 800 0)
								)
								(else
									(Print 15 43)
								)
							)
						else
							(CantDo)
						)
					)
					((Said 'deliver>')
						(if (= index (inventory saidMe:))
							(if (ego has: (inventory indexOf: index))
								(if (cast contains: aFrog)
									(Print 15 44)
								else
									(Print 15 45)
								)
							else
								(DontHave)
							)
						)
					)
					((Said 'get,rob/crown')
						(cond 
							((ego has: iCrown)
								(Print 800 0)
							)
							((== frogPrinceState frogHeldByEgo)
								(Print 15 46)
								((Inventory at: iCrown) moveTo: ego)
								(= gotItem TRUE)
								(ego
									view: 2
									loop: 1
									cycleSpeed: 0
									setCycle: Walk
									put: iFrog 999
								)
								(theGame changeScore: 2)
								(= frogPrinceState frogPrinceLeft)
								(frogActions changeState: 70)
							)
							(else
								(Print 15 42)
							)
						)
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

(instance frogActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(= frogPrinceState frogUnderwater)
				(Print 15 47 #time 3)
				(aFrog
					view: 373
					cycleSpeed: 0
					loop: 0
					cel: 255
					setCycle: EndLoop
					illegalBits: 0
					setMotion: MoveTo (+ (aFrog x?) 10) (- (aFrog y?) 5) self
				)
			)
			(2
				(aFrog hide:)
				(frogActions changeState: 100)
			)
			(3
				(= seconds 2)
				(Load VIEW 377)
				(Load VIEW 372)
				(Load VIEW 373)
			)
			(4
				(ego setMotion: 0)
				(aFrog
					view: 373
					show:
					cycleSpeed: 0
					setLoop: 0
					cel: 255
					xStep: 2
					yStep: 2
					illegalBits: 0
					ignoreActors: 0
					setCycle: EndLoop self
					setMotion: MoveTo (+ (aFrog x?) 8) (aFrog y?)
				)
				((Inventory at: iGoldBall) moveTo: 15)
			)
			(5
				(aFrog ignoreActors: hide:)
				(= seconds 4)
			)
			(6
				(if (ego inRect: 247 132 278 140)
					(ego setScript: ballActions)
					(ballActions changeState: 10)
				)
				(aFrog
					view: 377
					setLoop: 0
					cel: 0
					xStep: 2
					setAvoider: (Avoider new:)
					setCycle: Forward
					illegalBits: 0
					cycleSpeed: 0
					moveSpeed: 0
					show:
					setMotion: MoveTo 250 139 self
				)
			)
			(7
				(aFrog
					view: 372
					xStep: 2
					setLoop: -1
					cel: 0
					cycleSpeed: 1
					moveSpeed: 1
					setMotion: MoveTo 265 137 self
				)
			)
			(8
				(aFrog
					view: 370
					cel: 0
					ignoreActors: 0
					cycleSpeed: 2
					setCycle: Forward
				)
				(= frogPrinceState frogHasBall)
				(= aBall (View new:))
				(aBall
					view: 518
					loop: 1
					cel: 0
					posn: (+ (aFrog x?) 5) (aFrog y?)
					ignoreActors:
					stopUpd:
					init:
				)
				(HandsOn)
			)
			(10
				(Face ego aFrog)
				(ego view: 21 ignoreActors: setCycle: EndLoop self)
			)
			(11
				(aFrog dispose:)
				;EO: The ball shouldn't be placed back into the inventory.
				; This seems to be an original script error.
;;;				((Inventory at: iGoldBall)
;;;					moveTo: ego
;;;				)
				(ego view: 27 ignoreActors: 0 setCycle: EndLoop self)
			)
			(12
				(ego
					view: 28
					setCycle: Walk
					setMotion: MoveTo 294 134 self
				)
			)
			(13
				(ego view: 30 loop: 0 cycleSpeed: 1 setCycle: Forward)
				(RedrawCast)
				(Print 15 48)
				(= frogPrinceState frogHeldByEgo)
				(HandsOn)
			)
			(50
				(HandsOff)
				(ego
					view: 29
					loop: 1
					cel: 0
					cycleSpeed: 0
					setCycle: EndLoop self
					put: iFrog 999
				)
				(theGame changeScore: 3)
			)
			(51
				(ego loop: 3 cel: 255 setCycle: EndLoop self)
			)
			(52
				(poofSound play:)
				((= aPoof (Prop new:))
					posn: (- (ego x?) 15) (ego y?)
					setPri: (+ (ego priority?) 1)
					view: 680
					cel: 255
					loop: 0
					ignoreActors:
					setCycle: CycleTo 5 1 self
					init:
				)
			)
			(53
				(aPoof setCycle: EndLoop self)
				(= aFrog (Actor new:))
				(aFrog
					posn: (- (ego x?) 19) (ego y?)
					view: 225
					cel: 0
					setLoop: 2
					illegalBits: -32256
					init:
				)
				(ego view: 2)
				(Face ego aFrog 1)
			)
			(54
				(aPoof dispose:)
				(Print 15 49 #at -1 10)
				(Print 15 50 #at -1 10)
				(aFrog cycleSpeed: 1 setAvoider: (Avoider new:))
				(Print 15 51)
				((Inventory at: iCrown) moveTo: ego)
				(= gotItem TRUE)
				(theGame changeScore: 2)
				(if (< (aFrog x?) (ego x?))
					(= frogLoop 0)
				else
					(= frogLoop 1)
				)
				(aFrog
					view: 226
					setStep: 2 1
					setLoop: -1
					loop: frogLoop
					setCycle: EndLoop self
				)
			)
			(55
				(aFrog
					view: 227
					cycleSpeed: 0
					setCycle: Walk
					setAvoider: (Avoider new:)
				)
				(if (< (aFrog x?) 150)
					(aFrog setMotion: MoveTo -10 150 self)
				else
					(aFrog setMotion: MoveTo 330 150 self)
				)
			)
			(56
				(HandsOn)
				(ego setCycle: Walk)
				(aFrog dispose:)
			)
			(60
				(ego view: 2 setLoop: -1 cycleSpeed: 0 setCycle: Walk)
				(= aFrog (Actor new:))
				(aFrog
					view: 372
					setCycle: Walk
					posn: (- (ego x?) 10) (ego y?)
					setMotion: MoveTo (- (aBall x?) 3) (aBall y?) self
					init:
				)
			)
			(61
				(= frogPrinceState frogHasBall)
				(aFrog view: 370 loop: 0 cycleSpeed: 3 setCycle: Forward)
			)
			(70
				(= frogPrinceState frogUnderwater)
				(= aFrog (Actor new:))
				(aFrog
					view: 378
					posn: (- (ego x?) 6) (+ (ego y?) 4)
					illegalBits: 0
					setAvoider: (Avoider new:)
					setCycle: Walk
					setMotion: MoveTo 244 142 self
					init:
				)
			)
			(71
				(aFrog dispose:)
			)
			(100
				(= seconds (Random 3 8))
			)
			(101
				(if (== (ego onControl: origin) cLBLUE)
					(self changeState: 100)
				else
					(aFrog
						view: 378
						loop: 1
						posn: 216 139
						cel: 255
						cycleSpeed: 1
						setCycle: EndLoop self
						setMotion: MoveTo 206 145
						show:
					)
				)
			)
			(102
				(aFrog
					view: 370
					loop: 0
					illegalBits: cWHITE
					cycleSpeed: 2
					setCycle: Forward
				)
				(= frogPrinceState frogInit)
			)
		)
	)
)

(instance ballActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Face ego aBall)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(aBall dispose:)
				(ego get: 5)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setCycle: Walk)
				(if (or (== frogPrinceState frogHeldByEgo) (== frogPrinceState frogHasBall))
					(aFrog
						illegalBits: 0
						ignoreActors:
						view: 373
						cel: 255
						setLoop: 1
						setMotion: MoveTo 244 142
						cycleSpeed: 1
						setCycle: EndLoop self
					)
				)
				(HandsOn)
			)
			(4
				(= frogPrinceState frogUnderwater)
				(frogActions changeState: 100)
				(aFrog hide:)
				(HandsOn)
			)
			(10
				(ego
					setMotion: MoveTo (+ (ego x?) 10) (- (ego y?) 10) self
				)
			)
			(11
				(ego loop: 1)
			)
		)
	)
)

(instance drinking of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(1
				(= seconds 5)
				(= globalPrint (Print 15 52 #at -1 10 #dispose))
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(cls)
				(ego view: 2 setCycle: Walk)
				(ego script: oldEgoScript)
			)
		)
	)
)
