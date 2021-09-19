;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room3 0
)
(synonyms
	(cupid cupid cupid cupid man cupid cupid animal cupid boy baby)
	(kiss kiss embrace)
)

(enum ;Cupid states
	cupidFlyingWithBow
	cupidFlyingNoBow
	cupidScared
	cupidDivingIn
	cupidSwimming
	cupidFinishedSwim
	cupidLeaving
	cupidGone
)

(local
	local0
	cupidState
	local2
	local3
	local4
	local5
	cupidTimer
	aBow
	ripple
	aCupid
)
(instance fallSound of Sound)

(instance cupidCage of Cage)

(instance theme of Sound
	(properties
		number 9
	)
)

(instance Room3 of Room
	(properties
		picture 3
	)
	
	(method (init)
		(= north 27)
		(= south 9)
		(= east 4)
		(= west 2)
		(= horizon 75)
		(= isIndoors FALSE)
		(self setRegions: WOODS)
		(super init:)
		(ego view: 2 init:)
		(ego edgeHit: 0 setScript: fall)
		(if isNightTime
			(curRoom overlay: 103)
		)
		(curRoom setScript: egoActions)
		(Load VIEW 7)
		(Load VIEW 5)
		(Load VIEW 6)
		(Load VIEW 19)
		(Load VIEW 17)
		(Load VIEW 21)
		(Load SOUND 51)
		(Load SOUND 9)
		(cupidCage
			top: 118
			left: 118
			bottom: 135
			right: 235
			init:
		)
		(= ripple (Prop new:))
		(ripple
			isExtra: TRUE
			view: 650
			loop: 0
			cel: 1
			posn: 209 132
			setCycle: Forward
			cycleSpeed: 0
			ignoreActors:
			init:
		)
		(if
			(and
				(<= (Random 1 100) 33)
				((Inventory at: iCupidBow) ownedBy: 202)
			)
			(= aCupid (Actor new:))
			(aCupid
				view: 160
				posn: 5 71
				illegalBits: 0
				setPri: 7
				ignoreHorizon:
				setCycle: Forward
				xStep: 4
				yStep: 2
				init:
			)
			(aCupid setScript: doCupid)
		)
		(if ((Inventory at: iCupidBow) ownedBy: 3)
			((Inventory at: iCupidBow) moveTo: 202)
		)
		(switch prevRoomNum
			(2
				(if (<= (ego y?) horizon)
					(ego x: 1 y: (+ horizon (ego yStep?) 1))
				else
					(ego x: 1)
				)
			)
			(4
				(if (< (ego y?) horizon)
					(ego posn: 318 (+ horizon (ego yStep?) 1))
				else
					(ego posn: 318 (ego y?))
				)
			)
			(9
				(ego y: 187)
			)
			(27
				(ego posn: (ego x?) (+ horizon 2))
			)
			(0
				(ego x: 98 y: 176)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look<under,in/water')
						(Print 3 0)
					)
					((or (Said 'look/pool') (Said 'look/water'))
						(if (== (ego view?) 2)
							(Print 3 1)
						else
							(Print 3 2)
						)
					)
					(
						(or
							(Said 'look/room')
							(Said 'look/around')
							(Said 'look[<around][/noword]')
						)
						(if (== (ego view?) 2)
							(Print 3 3)
						else
							(Print 3 4)
						)
					)
					(
						(or
							(Said 'fish<enter')
							(Said 'capture/fish')
							(Said 'cast/pole')
						)
						(Print 3 5)
					)
					((Said 'look/column')
						(Print 3 6)
					)
					((Said 'get/water')
						(Print 3 7)
					)
					(
						(or
							(Said 'enter/bathe')
							(Said 'bathe,dive')
							(Said 'enter/bathe')
						)
						(if (== (ego view?) 2)
							(Print 3 8)
						else
							(Print 3 9)
						)
					)
					((Said 'look/stair')
						(Print 3 10)
					)
					((Said 'exit,(climb,get<out)')
						(if (!= (ego view?) 2)
							(Print 3 10)
						else
							(Print 3 11)
						)
					)
					((Said 'hop,dive/pool,water')
						(Print 3 12)
					)
					((or (Said 'drink') (Said 'get/drink'))
						(cond 
							((!= (ego view?) 2)
								(Print 3 13)
							)
							((& (= local0 (IsObjectOnControl ego 20)) cCYAN)
								(= local4 1)
								(egoActions changeState: 10)
							)
							(else
								(Print 800 1)
							)
						)
					)
					((Said 'get/arrow,arrow')
						(cond 
							(
								(and
									((Inventory at: iCupidBow) ownedBy: 3)
									(or
										(== cupidState cupidScared)
										(== cupidState cupidFlyingNoBow)
										(== cupidState cupidGone)
									)
								)
								(if (< (ego distanceTo: aBow) 10)
									((Inventory at: iCupidBow) moveTo: ego)
									(theGame changeScore: 2)
									(egoActions changeState: 1)
								else
									(Print 800 1)
								)
							)
							((ego has: iCupidBow)
								(Print 3 14)
							)
							(
								(and
									(not (cast contains: aCupid))
									(not ((Inventory at: iCupidBow) ownedBy: 3))
								)
								(Print 3 15)
							)
							((!= cupidState cupidScared)
								(Print 3 16)
							)
							((ego has: iCupidBow)
								(Print 3 17)
							)
							(else
								(Print 3 18)
							)
						)
					)
					((Said 'rob/arrow')
						(Print 3 19)
					)
					((Said 'look/dirt')
						(if ((Inventory at: iCupidBow) ownedBy: 3)
							(Print 3 20)
						else
							(Print 3 21)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if ((Inventory at: iCupidBow) ownedBy: 3)
			((Inventory at: iCupidBow) moveTo: 202)
		)
		(super newRoom: n)
	)
)

(instance fall of Script
	(method (doit)
		(super doit:)
		(cond 
			((and (== local3 0) (== local4 0))
				(cond 
					((and (& (ego onControl: 0) cGREEN) (== (ego view?) 2)) 
						(= local3 1)
						(fall changeState: 1)
					)
					((and (== (ego onControl: origin) cBLACK) (!= (ego view?) 21))
						(ego view: 2)
					)
					((& (ego onControl: origin) cLCYAN)
						(ego view: 5)
					)
					((& cLBLUE (ego onControl: origin))
						(ego view: 6)
					)
					((& cCYAN (ego onControl: origin))
						(ego view: 7)
					)
					((& cRED (ego onControl: 0))
						(ego view: 2)
					)
				)
			)
			((and (== (ego onControl: origin) cLRED) (< (fall state?) 2))
				(ego setMotion: 0)
				(self changeState: 2)
				(if (timers contains: local5)
					(local5 dispose: delete:)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(sounds eachElementDo: #stop 1)
				(fallSound number: 51 play:)
				(HandsOff)
				(ego
					view: 17
					loop: (+ (& (ego loop?) 1) 2)
					illegalBits: 0
					setCycle: Forward
				)
				(= local5 (Timer setCycle: self 20))
				(ego xStep: 8 yStep: 4 setMotion: MoveTo 170 122)
			)
			(2
				(ego
					view: 19
					setMotion: 0
					loop: (& (ego loop?) 1)
					cel: 255
					setCycle: EndLoop self
				)
				(ego setStep: 3 2)
			)
			(3
				(= local3 0)
				(ego view: 7)
				(ego illegalBits: -1 ignoreControl: cCYAN cLRED setCycle: Walk)
				(= seconds 2)
			)
			(4
				(ego illegalBits: cWHITE)
				(HandsOn)
				(self changeState: 0)
			)
		)
	)
)

(instance doCupid of Script
	(method (init param1)
		(Load VIEW 160)
		(Load VIEW 161)
		(Load VIEW 162)
		(Load VIEW 163)
		(Load VIEW 165)
		(Load VIEW 166)
		(Load VIEW 167)
		(Load VIEW 509)
		(super init: param1)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== state 5)
				(or
					(ego inRect: 220 100 305 140)
					(< (ego distanceTo: aCupid) 48)
				)
			)
			(if (and (> (ego x?) 210) (< (ego y?) 140))
				(self changeState: 30)
			else
				(self changeState: 20)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cupidState cupidFlyingWithBow)
				(theme play:)
				(aCupid ignoreActors: setMotion: MoveTo 164 70 self)
			)
			(1
				(if (ego inRect: 171 101 317 183)
					(self changeState: 10)
				else
					(aCupid setPri: -1 setMotion: MoveTo 250 119 self)
				)
			)
			(2
				(= cupidState cupidDivingIn)
				(aCupid
					view: 162
					ignoreActors: FALSE
					setLoop: 0
					cel: 255
					setCycle: EndLoop self
				)
			)
			(3
				(aCupid setLoop: 2 cel: 255 setCycle: EndLoop self)
			)
			(4
				(aCupid
					ignoreActors: TRUE
					view: 165
					xStep: 6
					setLoop: 1
					setCycle: EndLoop
				)
				(= aBow (View new:))
				((Inventory at: iCupidBow) moveTo: 3)
				(aBow
					ignoreActors:
					view: 509
					x: (+ (aCupid x?) 10)
					y: (aCupid y?)
					;stopUpd: ;EO: This turns the bow invisible when Cupid places it on the ground
					init:
				)
				(aCupid setMotion: MoveTo 206 126 self)
			)
			(5
				(= cupidState cupidSwimming)
				(aCupid
					view: 167
					setLoop: -1
					observeControl: cBLACK
					xStep: 1
					yStep: 1
					loop: 1
					cycleSpeed: 1
					setCycle: Forward
					setMotion: Wander 6
					moveSpeed: 1
				)
				(aCupid observeBlocks: cupidCage)
				(= cupidTimer (Timer setReal: self 15))
			)
			(6
				(= cupidState cupidFinishedSwim)
				(self changeState: 40)
			)
			(10
				(aCupid setMotion: MoveTo 61 83 self)
				(if (timers contains: cupidTimer)
					(cupidTimer dispose: delete:)
				)
			)
			(11
				(aCupid setMotion: MoveTo 4 72 self)
			)
			(12
				(aCupid dispose:)
			)
			(20
				(Print 3 35)
				(if (timers contains: cupidTimer)
					(cupidTimer dispose: delete:)
				)
				(aCupid ignoreBlocks: cupidCage)
				(aCupid
					ignoreControl: cBLACK
					setLoop: 0
					xStep: 3
					yStep: 2
					moveSpeed: 0
					setMotion: MoveTo 230 129 self
				)
				(= cupidState cupidScared)
			)
			(21
				(aCupid view: 166 setCel: 0)
				(aCupid setMotion: MoveTo 240 129)
				(aCupid cycleSpeed: 0 setCycle: EndLoop self)
			)
			(22
				(aCupid view: 164 cel: 255 setLoop: 1 setCycle: EndLoop self)
			)
			(23
				(aCupid
					view: 161
					xStep: 4
					yStep: 3
					setPri: 7
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 168 70 self
				)
			)
			(24
				(aCupid setMotion: MoveTo 41 70 self)
			)
			(25
				(aCupid dispose:)
			)
			(30
				(Print 3 35)
				(if (timers contains: cupidTimer)
					(cupidTimer dispose: delete:)
				)
				(aCupid
					ignoreBlocks: cupidCage
					ignoreControl: cBLACK
					xStep: 6
					yStep: 2
					setLoop: 1
					moveSpeed: 0
					setMotion: MoveTo 100 129 self
				)
				(= cupidState cupidScared)
			)
			(31
				(aCupid xStep: 3 setMotion: MoveTo 85 129)
				(aCupid view: 166 setLoop: 1 cel: 255 setCycle: EndLoop self)
			)
			(32
				(aCupid posn: 80 124 loop: 3 setCycle: EndLoop self)
			)
			(33
				(aCupid view: 164 cel: 255 setLoop: 1 setCycle: EndLoop self)
			)
			(34
				(= cupidState cupidFlyingNoBow)
				(aCupid
					view: 161
					xStep: 4
					yStep: 3
					setPri: 7
					setLoop: 1
					setCycle: Forward
					setMotion: MoveTo 41 70 self
				)
			)
			(35 (self changeState: 11))
			(40
				(aCupid ignoreBlocks: cupidCage)
				(aCupid
					ignoreControl: cBLACK
					setLoop: 0
					xStep: 3
					yStep: 2
					moveSpeed: 0
					setMotion: MoveTo 230 129 self
				)
				((Inventory at: iCupidBow) moveTo: 202)
			)
			(41
				(aCupid view: 166 setCel: 0)
				(aCupid setMotion: MoveTo 240 129)
				(aCupid cycleSpeed: 0 setCycle: EndLoop self)
			)
			(42
				(aCupid
					ignoreActors:
					posn: (- (aBow x?) 10) (- (aBow y?) 4)
				)
				(aCupid view: 163 loop: 0 cel: 255 setCycle: CycleTo 5 1 self)
			)
			(43
				(aBow dispose:)
				(aCupid cel: 6 setCycle: EndLoop self)
			)
			(44
				(= cupidState cupidFlyingWithBow)
				(aCupid view: 160 setLoop: 1 setCel: 0 setCycle: Forward)
				(aCupid setPri: 7 setMotion: MoveTo 171 80 self)
			)
			(45
				(self changeState: 10)
			)
		)
	)
	
	(method (handleEvent event &tmp i)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look/cupid')
						(cond 
							((== cupidState cupidFlyingWithBow)
								(Print 3 22)
							)
							((== cupidState cupidSwimming)
								(Print 3 23)
							)
							((== cupidState cupidFlyingNoBow)
								(Print 3 24)
							)
							((== cupidState cupidScared)
								(Print 3 25)
							)
							((== cupidState cupidDivingIn)
								(Print 3 26)
							)
							((== cupidState cupidFinishedSwim)
								(Print 3 27)
							)
						)
					)
					((Said 'play/cupid')
						(Print 3 28)
					)
					((Said 'get/cupid')
						(Print 3 29)
					)
					((Said 'kiss')
						(Print 3 30)
					)
					((Said 'converse')
						(cond 
							((not (cast contains: aCupid))
								(event claimed: FALSE)
							)
							((== cupidState cupidFlyingWithBow)
								(Print 3 31)
							)
							((== cupidState cupidScared)
								(Print 3 32)
							)
							((== cupidState cupidSwimming)
								(if (< (ego distanceTo: aCupid) 40)
									(Print 3 33)
									(if (and (> (ego x?) 210) (< (ego y?) 140))
										(self changeState: 30)
									else
										(self changeState: 20)
									)
								else
									(Print 3 31)
								)
							)
							(else
								(Print 3 31)
							)
						)
					)
					(
						(and
							(Said 'deliver>')
							(= i (inventory saidMe:))
						)
						(if (ego has: (inventory indexOf: i))
							(Print 3 34)
						else
							(DontHave)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance egoActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(Face ego aBow)
				(ego view: 21 setMotion: 0 cel: 255 setCycle: EndLoop self)
			)
			(2
				(= gotItem TRUE)
				(aBow dispose:)
				(ego setCycle: BegLoop self)
			)
			(3
				(ego view: 2 setCycle: Walk)
				(HandsOn)
			)
			(10
				(HandsOff)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(11
				(= seconds 5)
				(= globalPrint (Print 3 36 #at -1 10 #dispose))
			)
			(12
				(ego setCycle: BegLoop self)
			)
			(13
				(cls)
				(HandsOn)
				(ego view: 2 setCycle: Walk)
				(= local4 0)
			)
		)
	)
)
