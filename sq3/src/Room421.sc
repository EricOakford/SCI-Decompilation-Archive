;;; Sierra Script 1.0 - (do not remove this comment)
(script# 421)
(include game.sh)
(use Main)
(use Intrface)
(use Chase)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room421 0
)

(local
	local0
	festerState
	thePulley
	local3
	local4
	talkBubble
	bonk
	twistSound
	local8
	local9
)
(instance deathSound of Sound)

(instance elevator of Actor)

(instance elevTop of Prop)

(instance elevBottom of Prop)

(instance cable1 of Prop)

(instance cable2 of View)

(instance gear of Prop)

(instance pulley1 of Actor)

(instance pulley2 of Actor)

(instance Room421 of Room
	(properties
		picture 421
		vanishingY -400
	)
	
	(method (init)
		(super init:)
		(= local8 500)
		(= local4 1)
		(Load VIEW 94)
		(Load VIEW 777)
		(Load VIEW 119)
		(Load VIEW 108)
		(Load VIEW 106)
		(Load VIEW 120)
		(Load SOUND 91)
		(Load SOUND 92)
		(ego init:)
		(if (== roomWithDeadTerminator 421)
			(= local0 5)
			((= terminator (View new:))
				view: 120
				setLoop: 4
				setCel: 6
				setPri: 0
				posn: 160 127
				init:
				stopUpd:
			)
		)
		(if (InRoom iInvisibilityBelt 421)
			((= theBelt (View new:))
				view: 120
				loop: 5
				cel: 0
				posn: 157 124
				setPri: 1
				init:
				stopUpd:
			)
		)
		(gear
			view: 120
			loop: 0
			posn: 158 65
			ignoreActors:
			setCycle: Forward
			setScript: gearActions
			init:
		)
		(theMusic number: 85 loop: -1 play:)
		(pulley1
			view: 120
			setLoop: 1
			setCel: 1
			ignoreActors:
			illegalBits: 0
			ignoreHorizon:
			posn: 77 -23
			setPri: 0
			init:
			stopUpd:
		)
		(pulley2
			view: 120
			setLoop: 1
			illegalBits: 0
			ignoreHorizon:
			ignoreActors:
			setCel: 1
			posn: 108 0
			setPri: 15
			init:
			stopUpd:
		)
		(ego
			view: 83
			loop: 2
			cel: 2
			posn: 56 202
			setPri: 8
			setLoop: 2
			setCel: 2
			ignoreActors:
			illegalBits: 0
			init:
		)
		(cable1
			view: 83
			loop: 3
			cel: 9
			ignoreActors:
			posn: 56 83
			setPri: 7
			cycleSpeed: 5
			init:
			stopUpd:
		)
		(cable2
			view: 83
			loop: 3
			cel: 0
			ignoreActors:
			posn: 56 71
			setPri: 7
			init:
			stopUpd:
		)
		(curRoom setScript: raise)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (and (& (ego onControl: 0) cGREEN) (not isHandsOff))
			(= global104 7)
			(if (and (cast contains: terminator) (< local0 5))
				(terminator setMotion: 0)
			)
			(pulley1 setScript: smash)
		)
		(if
			(and
				(& (ego onControl: 0) cLRED)
				(== global104 2)
				(== festerState 0)
				(<= (bringOnFester state?) 0)
			)
			(pulley1 setScript: fall)
		)
		(cond 
			((== global104 1)
				(ego setPri: 8)
			)
			((and (== global104 2) (ego inRect: 0 135 91 163))
				(ego setPri: 14)
			)
			(
				(and
					(== global104 2)
					(ego inRect: 266 140 303 147)
					(not isHandsOff)
				)
				(= global104 3)
				(ego
					setPri: 10
					ignoreControl: cYELLOW
					observeControl: cLMAGENTA
				)
			)
			(
				(and
					(== global104 3)
					(ego inRect: 268 148 308 154)
					(not isHandsOff)
				)
				(= global104 2)
				(ego
					setPri: -1
					observeControl: cYELLOW
					ignoreControl: cLMAGENTA
				)
			)
			(
				(and
					(== global104 3)
					(not isHandsOff)
					(ego inRect: 239 52 270 57)
				)
				(= global104 4)
				(ego
					setPri: -1
					observeControl: cYELLOW
					ignoreControl: cLMAGENTA
				)
				(if
					(and
						(or (== terminatorState 1) (== terminatorState 2))
						(== local0 0)
					)
					(self setScript: termComesUp)
				)
			)
			(
				(and
					(== global104 4)
					(ego inRect: 239 57 272 64)
					(not isHandsOff)
				)
				(= global104 3)
				(ego
					setPri: 10
					ignoreControl: cYELLOW
					observeControl: cLMAGENTA
				)
			)
			((== global104 3)
				(ego setPri: 10)
			)
			(
				(and
					(== global104 4)
					(ego inRect: 0 62 252 76)
					(not isHandsOff)
				)
				(ego setPri: 14)
			)
			(
				(and
					(== global104 4)
					(& (ego onControl: 0) cLBLUE)
					(not isHandsOff)
				)
				(ego setPri: 9)
			)
			((not isHandsOff)
				(ego setPri: -1)
			)
		)
		(cond 
			((and (== local0 4) (terminator inRect: 0 62 237 76))
				(terminator setPri: 14)
			)
			((and (== local0 2) (terminator inRect: 0 136 91 163))
				(terminator setPri: 14)
			)
			(
				(and
					(!= terminatorState terminatorDEAD)
					(== local0 4)
					(terminator setPri: -1)
				)
			)
			((== local0 3)
				(terminator setPri: 10)
			)
			((and (== local0 4) (& (terminator onControl: 0) cLBLUE))
				(terminator setPri: 9)
			)
			((== local0 4)
				(terminator setPri: -1)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'look,converse/blatz')
					(if (== (curRoom script?) bringOnFester)
						(cond 
							((== festerState 1)
								(Print 421 0)
							)
							((== festerState 2)
								(Print 421 1)
							)
							(else
								(Print 421 2)
							)
						)
					else
						(Print 421 2)
					)
				)
				((Said 'look>')
					(cond 
						(
							(or
								(Said '/area')
								(Said '/around')
								(Said '[<around][/noword]')
							)
							(Print 421 3)
						)
						((Said '/stair')
							(Print 421 4)
						)
						((Said '/pedestal')
							(Print 421 5)
						)
						((Said '<down')
							(Print 421 6)
						)
						((or (Said '<up') (Said '/ceiling'))
							(Print 421 7)
						)
						((Said '/android')
							(cond 
								((== local0 0)
									(Print 421 8)
								)
								((== local0 1)
									(Print 421 9)
								)
								((and (>= local0 2) (!= local0 5) (!= local0 6))
									(Print 421 10)
								)
								((== local0 5)
									(if
										(and
											(== global104 2)
											(<= (ego distanceTo: terminator) 30)
										)
										(Print
											(Format @invStr 421 11
												(if (InRoom iInvisibilityBelt 421)
													{Looking closely, you notice that the terminator's invisibility
													 belt has survived relatively intact.}
												else
													{}
												)
											)
										)
									else
										(Print 421 12)
									)
								)
							)
						)
						((and (Said '/debris') (== local0 5))
							(if
								(and
									(== global104 2)
									(<= (ego distanceTo: terminator) 30)
								)
								(Print
									(Format @invStr 421 11
										(if (InRoom iInvisibilityBelt 421)
											{Looking closely, you notice that the terminator's
											invisibility belt has survived relatively intact.}
										else
											{}
										)
									)
								)
							else
								(Print 421 12)
							)
						)
						((Said '/engine,device,equipment')
							(Print 421 13)
						)
						((Said '/cog')
							(Print 421 14)
						)
						((Said '/rope,scout,pulley,jar')
							(Print 421 15)
						)
						((Said '/banister')
							(Print 421 16)
						)
						((Said '/overhang')
							(Print 421 17)
						)
						((Said '/pit')
							(Print 421 18)
						)
						((Said '/bolt')
							(Print 421 19)
						)
						((Said '/elevator')
							(if (or (== festerState 2) (== festerState 1))
								(Print 421 20)
							else
								(Print 421 21)
							)
						)
						((Said '/button,control')
							(if (and (ego inRect: 0 37 73 133) (== global104 2))
								(Print 421 22)
							else
								(Print 421 23)
							)
						)
					)
				)
				((and (Said 'explore/android,debris,body') (== local0 5))
					(if
						(and
							(== global104 2)
							(<= (ego distanceTo: terminator) 30)
						)
						(Print
							(Format @invStr 421 11
								(if (InRoom iInvisibilityBelt 421)
									{Looking closely, you notice that the terminator's
									invisibility belt has survived relatively intact.}
								else
									{}
								)
							)
						)
					else
						(Print 421 12)
					)
				)
				((or (Said 'press<up') (Said 'press/button<up'))
					(if (ego inRect: 39 123 62 133)
						(Print 421 24)
					else
						(NotClose)
					)
				)
				(
					(or
						(Said 'press/button')
						(Said 'press<down')
						(Said 'use/elevator')
					)
					(if (and (ego inRect: 39 123 63 133) (== festerState 2))
						(Print 421 25)
						(curRoom setScript: lowerEgoElevator)
					else
						(NotClose)
					)
				)
				((Said 'converse/android')
					(if (cast contains: terminator)
						(Print 421 26)
					else
						(Print 421 27)
					)
				)
				((Said 'board,drag,hold/scout,rope,jar')
					(Print 421 28)
				)
				((Said 'swing,get,press,use/scout,pulley,rope,jar')
					(switch local3
						(0
							(ego setScript: doPulley)
						)
						(2
							(Print 421 29)
						)
						(else
							(Print 421 30)
						)
					)
				)
				((Said 'get/scout,banister,rope')
					(Print 421 31)
				)
				((Said 'get/belt')
					(cond 
						((ego has: iInvisibilityBelt)
							(Print 421 32)
						)
						((not (InRoom iInvisibilityBelt 421))
							(Print 421 33)
						)
						((> (ego distanceTo: theBelt) 12)
							(NotClose)
						)
						(else
							(Print 421 34)
							(theBelt dispose:)
							(theGame changeScore: 35)
							(if (> (bringOnFester seconds?) 5)
								(bringOnFester seconds: 5)
							)
							(ego get: iInvisibilityBelt)
						)
					)
				)
				((Said 'get/android')
					(if (== local0 5)
						(Print 421 35)
					else
						(Print 421 36)
					)
				)
				((Said 'get<in/elevator')
					(Print 421 37)
				)
				((Said 'attack/android')
					(Print 421 38)
				)
				((Said 'use/orat,stick')
					(Print 421 39)
				)
				((Said 'jump')
					(Print 421 40)
				)
				((Said 'turn<off/engine')
					(Print 421 41)
				)
				((Said 'climb')
					(Print 421 42)
				)
				((Said '/scout,jar,rope,cog,device,engine')
					(Print 421 43)
				)
			)
		)
		(return
			(if
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
					(== (curRoom script?) bringOnFester)
					(<= 2 (bringOnFester state?))
					(<= (bringOnFester state?) 4)
				)
				(cls)
				(bringOnFester seconds: 0)
				(bringOnFester cue:)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(sounds eachElementDo: #fade)
		(super newRoom: n)
	)
)

(instance raise of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global104 1)
				(ego setMotion: MoveTo 56 83 self)
				(cable1 setCycle: BegLoop)
			)
			(1
				(HandsOn)
				(elevTop
					view: 83
					setLoop: 2
					cel: 1
					posn: 56 85
					setPri: 10
					ignoreActors:
					init:
					stopUpd:
				)
				(elevBottom
					view: 83
					setLoop: 2
					cel: 0
					posn: 56 85
					setPri: 8
					ignoreActors:
					init:
					stopUpd:
				)
				(ego
					view: 0
					setLoop: -1
					setCycle: Walk
					illegalBits: cWHITE
					observeControl: cYELLOW
					posn: 56 129
					setPri: -1
				)
				(= global104 2)
				(= festerState 2)
			)
		)
	)
)

(instance termComesUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(= festerState 1)
				(ego observeControl: cLRED)
				(elevator
					view: 83
					setLoop: 2
					setCel: 4
					posn: 56 (elevTop y?)
					illegalBits: 0
					ignoreActors:
					setPri: 8
					setMotion: MoveTo 56 202 self
					init:
				)
				(cable1
					view: 83
					ignoreActors:
					posn: 56 83
					loop: 3
					cel: 255
					cycleSpeed: 5
					setCycle: EndLoop
				)
				(elevTop view: 777 forceUpd:)
				(elevBottom view: 777 forceUpd:)
			)
			(1
				(elevator setCel: 3 setMotion: MoveTo 56 83 self)
				(cable1 cel: 9 setCycle: BegLoop)
			)
			(2
				(elevator dispose:)
				(elevTop view: 83 setLoop: 2 setCel: 1)
				(elevBottom view: 83 setLoop: 2 setCel: 0)
				(cable1 view: 777 stopUpd:)
				(= terminator (Actor new:))
				(terminator
					view: 119
					posn: 64 128
					loop: 0
					setCycle: Walk
					setAvoider: Avoider
					setMotion: MoveTo 89 128 self
					init:
				)
			)
			(3
				(if (!= global104 7)
					((= talkBubble (View new:))
						view: 108
						setLoop: 6
						setCel: 0
						posn: 114 92
						ignoreActors:
						setPri: 15
						init:
					)
					(= seconds 3)
				)
			)
			(4
				(if (!= global104 7)
					(talkBubble dispose:)
					(terminator setScript: termChase)
					(= festerState 1)
					(ego observeControl: cLRED)
					(elevator
						view: 83
						setLoop: 2
						setCel: 4
						posn: 56 (elevTop y?)
						illegalBits: 0
						ignoreActors:
						setPri: 8
						setMotion: MoveTo 56 202 self
						init:
					)
					(cable1
						view: 83
						ignoreActors:
						posn: 56 83
						loop: 3
						cel: 255
						cycleSpeed: 5
						setCycle: EndLoop
					)
					(elevTop view: 777 stopUpd:)
					(elevBottom view: 777 stopUpd:)
				)
			)
			(5
				(= festerState 0)
				(cable1 stopUpd:)
				(ego ignoreControl: cLRED)
				(terminator observeControl: cLRED)
			)
		)
	)
)

(instance termChase of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(cond 
			((== local0 2)
				(cond 
					(
						(and
							(or (== global104 4) (== global104 3))
							(!= state 1)
						)
						(self changeState: 1)
					)
					((and (== global104 2) (!= state 7) (< state 8))
						(self changeState: 7)
					)
				)
			)
			((== local0 3)
				(cond 
					((and (== global104 4) (!= state 2) (< state 8))
						(self changeState: 2)
					)
					((and (== global104 3) (< state 8) (!= state 7))
						(self changeState: 7)
					)
					((and (== global104 2) (< state 8) (!= state 5))
						(self changeState: 5)
					)
				)
			)
			((== local0 4)
				(cond 
					((and (== global104 4) (< state 8) (!= state 7))
						(self changeState: 7)
					)
					((and (== global104 3) (< state 8) (!= state 4))
						(self changeState: 4)
					)
					((and (== global104 2) (!= state 4))
						(self changeState: 4)
					)
				)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 2)
				(terminator observeControl: cLRED)
			)
			(1
				(terminator setMotion: MoveTo 286 156 self)
			)
			(2
				(terminator
					setMotion: MoveTo 248 47 self
					setPri: 10
					ignoreControl: cYELLOW cLCYAN
					observeControl: cLMAGENTA
				)
				(= local0 3)
			)
			(3
				(= local0 4)
				(terminator
					setMotion: Chase ego 10 self
					observeControl: cGREEN cYELLOW
					ignoreControl: cLMAGENTA
					setPri: -1
				)
				(= state 7)
			)
			(4
				(terminator
					ignoreControl: 2048
					setMotion: MoveTo 252 51 self
				)
			)
			(5
				(= local0 3)
				(terminator
					observeControl: cLCYAN
					setMotion: MoveTo 290 156 self
					ignoreControl: cYELLOW
					setPri: 10
					observeControl: cLMAGENTA
				)
			)
			(6
				(= local0 2)
				(terminator
					setMotion: Chase ego 10 self
					observeControl: cYELLOW
					setPri: -1
					ignoreControl: cLMAGENTA
				)
				(= state 7)
			)
			(7
				(terminator setMotion: Chase ego 10 self)
			)
			(8
				(HandsOff)
				(terminator posn: (ego x?) (ego y?))
				(ego
					view: 106
					setLoop: 4
					cel: 255
					ignoreHorizon:
					illegalBits: 0
					setCycle: EndLoop
					setPri: (ego priority?)
					setStep: 4 4
					setMotion: MoveTo (+ (ego x?) 11) (- (ego y?) 15) self
				)
			)
			(9
				(ego setLoop: 5 setCycle: Forward)
				(terminator
					view: 106
					setLoop: 6
					illegalBits: 0
					setPri: (ego priority?)
					setCycle: Forward
					show:
				)
				(= seconds 2)
			)
			(10
				(terminator
					view: 106
					setLoop: 7
					setPri: (ego priority?)
					setCycle: Forward
				)
				(= seconds 4)
			)
			(11
				(ego hide:)
				((= twistSound (Sound new:))
					number: 97
					loop: -1
					priority: 99
					play:
				)
				(terminator setLoop: 8 setCycle: Forward)
				(= seconds 6)
			)
			(12
				(twistSound stop:)
				(terminator setLoop: 8 setCel: 0)
				(EgoDead 901 0 14 16)
			)
		)
	)
)

(instance doPulley of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if
			(and
				(== local3 1)
				(== local0 4)
				(cast contains: terminator)
			)
			(cond 
				(
					(and
						(== (thePulley heading?) 90)
						(< (Abs (- (terminator x?) (thePulley x?))) 12)
						(or
							(and
								(== thePulley pulley1)
								(> (terminator y?) 41)
								(< (terminator y?) 49)
							)
							(and
								(== thePulley pulley2)
								(> (terminator y?) 60)
								(< (terminator y?) 72)
							)
						)
					)
					(if (or (< (terminator x?) 113) (> (terminator x?) 192))
						(= local3 3)
						(Print 421 44)
					else
						(self changeState: 5)
					)
				)
				(
					(and
						(== (thePulley heading?) 270)
						(< (Abs (- (thePulley x?) (terminator x?))) 12)
						(or
							(and
								(== thePulley pulley1)
								(> (terminator y?) 41)
								(< (terminator y?) 49)
							)
							(and
								(== thePulley pulley2)
								(> (terminator y?) 60)
								(< (terminator y?) 72)
							)
						)
					)
					(if (or (< (terminator x?) 113) (> (terminator x?) 192))
						(Print 421 44)
						(= local3 3)
					else
						(self changeState: 5)
					)
				)
			)
		)
		(if
			(and
				(== local3 1)
				(or
					(and
						(< (thePulley x?) 39)
						(== (thePulley heading?) 270)
					)
					(and
						(> (thePulley x?) 230)
						(== (thePulley heading?) 90)
					)
				)
			)
			(thePulley cel: 1 setMotion: 0 stopUpd:)
			(= state 4)
			(RedrawCast)
			(Print 421 45)
			(= local3 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					(
						(and
							(cast contains: terminator)
							(< (ego distanceTo: terminator) 25)
						)
						(Print 421 46)
						(ego setScript: 0)
					)
					(
						(and
							(== global104 4)
							(< (ego y?) 49)
							(< (Abs (- (ego x?) (pulley1 x?))) 10)
						)
						(= thePulley pulley1)
						(self changeState: 1)
					)
					(
						(and
							(== global104 4)
							(> (ego y?) 61)
							(< (Abs (- (ego x?) (pulley2 x?))) 10)
						)
						(= thePulley pulley2)
						(self changeState: 1)
					)
					(else
						(NotClose)
						(ego setScript: 0)
					)
				)
			)
			(1
				(Print 421 47 #at -1 (- (ego y?) 20))
				(switch (ego loop?)
					(0
						(thePulley
							startUpd:
							cel: 0
							xStep: 8
							setMotion: MoveTo (+ (thePulley x?) 50) (thePulley y?) self
						)
					)
					(1
						(thePulley
							startUpd:
							cel: 2
							xStep: 6
							setMotion: MoveTo (- (thePulley x?) 50) (thePulley y?) self
						)
					)
					(else 
						(if (cast contains: terminator)
							(if (< (ego x?) (terminator x?))
								(thePulley
									startUpd:
									cel: 0
									xStep: 8
									setMotion: MoveTo (+ (thePulley x?) 50) (thePulley y?) self
								)
							else
								(thePulley
									startUpd:
									cel: 2
									xStep: 8
									setMotion: MoveTo (- (thePulley x?) 50) (thePulley y?) self
								)
							)
						else
							(thePulley
								startUpd:
								cel: 0
								xStep: 8
								setMotion: MoveTo (+ (thePulley x?) 50) (thePulley y?) self
							)
						)
					)
				)
				(= local3 1)
			)
			(2
				(if (== (thePulley heading?) 90)
					(thePulley
						cel: 1
						setMotion: MoveTo (+ (thePulley x?) 50) (thePulley y?) self
					)
				else
					(thePulley
						cel: 1
						setMotion: MoveTo (- (thePulley x?) 50) (thePulley y?) self
					)
				)
			)
			(3
				(if (== (thePulley heading?) 90)
					(thePulley
						cel: 2
						setMotion: MoveTo (+ (thePulley x?) 50) (thePulley y?) self
					)
				else
					(thePulley
						cel: 0
						setMotion: MoveTo (- (thePulley x?) 50) (thePulley y?) self
					)
				)
			)
			(4
				(= local3 0)
				(thePulley cel: 1 setMotion: 0 stopUpd:)
			)
			(5
				(= local3 2)
				(if (== (thePulley heading?) 90)
					(thePulley
						cel: 1
						setMotion: MoveTo (- (thePulley x?) 15) (thePulley y?)
					)
				else
					(thePulley
						cel: 1
						setMotion: MoveTo (+ (thePulley x?) 15) (thePulley y?)
					)
				)
				(= local0 6)
				(HandsOff)
				(= bonk (View new:))
				(bonk
					view: 120
					loop: 1
					cel: 3
					posn: (terminator x?) (- (terminator y?) 30)
					setPri: 15
					init:
				)
				(terminator
					view: 120
					setLoop: (- 3 (& (terminator loop?) $0001))
					cel: 255
					illegalBits: 0
					setCycle: EndLoop
					setStep: 6 4
					setMotion: MoveTo 160 55 self
				)
				(= seconds 2)
			)
			(6
				(bonk dispose:)
			)
			(7
				(deathSound number: 91 priority: 20 play:)
				(terminator
					setLoop: 4
					setCycle: Forward
					setPri: -1
					setMotion: MoveTo 160 127 self
				)
			)
			(8
				(HandsOn)
				(= local0 5)
				(terminator ignoreActors: 0 setCel: 6 setPri: 0 stopUpd:)
				((= theBelt (View new:))
					view: 120
					loop: 5
					cel: 0
					posn: 157 124
					setPri: 1
					init:
				)
				(theGame changeScore: 35)
				(PutInRoom iInvisibilityBelt 421)
				(ego setScript: 0)
				(curRoom setScript: bringOnFester)
				(= roomWithDeadTerminator 421)
				(= terminatorState terminatorDEAD)
			)
		)
	)
)

(instance smash of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(deathSound number: 92 priority: 21 play:)
				(ego
					view: 120
					setLoop: 6
					illegalBits: 0
					ignoreActors:
					posn: 156 63
					setPri: 7
					setCycle: Forward
				)
				(= seconds 7)
				(if
					(and
						(cast contains: terminator)
						(< local0 5)
						(cast contains: terminator)
						(== (terminator script?) termChase)
					)
					(curRoom setScript: 0)
					(terminator setMotion: 0)
				)
			)
			(1
				(EgoDead 901 0 3 99)
			)
		)
	)
)

(instance bringOnFester of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (and (== festerState 2) (& (ego onControl: 0) cLRED))
			(self changeState: 10)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 60)
			)
			(1
				(ego observeControl: cLRED)
				(= festerState 1)
				(elevator
					setCel: 5
					setPri: 9
					setMotion: MoveTo 56 83 self
				)
				(cable1 cel: 9 setCycle: BegLoop)
			)
			(2
				(elevator setPri: 9 setMotion: MoveTo 56 85 self)
			)
			(3
				(HandsOff)
				(pulley1 startUpd:)
				(pulley2 setPri: 14 startUpd:)
				(gear setCycle: Forward startUpd:)
				(theMusic loop: -1 play:)
				(== local8 -200)
				(RedrawCast)
				(Print 421 48 #at 66 53 #dispose #title {Fester})
				(= seconds 8)
			)
			(4
				(cls)
				(RedrawCast)
				(Print 421 49 #at 66 29 #dispose #title {Fester})
				(= seconds 12)
			)
			(5
				(cls)
				(Print 421 50 #at 66 65 #dispose #title {Fester})
				(ego ignoreControl: cLRED)
				(= festerState 2)
				(= seconds 4)
			)
			(6
				(cls)
				(HandsOn)
				(ego ignoreControl: cLRED)
				(= seconds 15)
			)
			(7
				(cls)
				(Print 421 51 #at 66 64 #time 5 #title {Fester})
				(-- state)
				(= seconds 20)
			)
			(10
				(HandsOff)
				(= seconds 0)
				(pulley1 stopUpd:)
				(pulley2 stopUpd:)
				(ego view: 777 stopUpd:)
				(elevTop view: 777 stopUpd:)
				(elevBottom view: 777 stopUpd:)
				(elevator
					setLoop: 2
					setCel: 6
					setMotion: MoveTo (elevator x?) 220 self
				)
				(cable1 setCycle: EndLoop)
			)
			(11
				(curRoom newRoom: 42)
			)
		)
	)
)

(instance fall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(bringOnFester seconds: 0)
				(curRoom setScript: 0)
				(elevator
					view: 94
					setLoop: 0
					cel: 255
					yStep: 9
					ignoreActors:
					illegalBits: 0
					posn: 56 (ego y?)
					cycleSpeed: 3
					setCycle: EndLoop
					init:
				)
				(deathSound number: 45 priority: 12 loop: 1 play:)
				(ego
					ignoreActors:
					illegalBits: 0
					setPri: 8
					yStep: 15
					posn: 64 127
					setCycle: 0
					setMotion: MoveTo 64 300 self
				)
				(= global104 7)
				(if (and (cast contains: terminator) (< local0 5))
					(terminator setScript: 0 setMotion: 0)
				)
			)
			(1
				(elevator hide:)
				(= seconds 6)
			)
			(2
				(EgoDead 901 0 0 1)
			)
		)
	)
)

(instance lowerEgoElevator of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= global104 1)
				(ego setMotion: MoveTo 56 129 self)
			)
			(1
				(ego view: 777 stopUpd:)
				(elevTop view: 777 stopUpd:)
				(elevBottom view: 777 stopUpd:)
				(elevator
					view: 83
					setLoop: 2
					setCel: 2
					setPri: 8
					illegalBits: 0
					ignoreActors:
					posn: (elevBottom x?) (elevBottom y?)
					setMotion: MoveTo (elevBottom x?) 220 self
					init:
				)
				(cable1 setCycle: EndLoop)
			)
			(2
				(curRoom newRoom: 420)
			)
		)
	)
)

(instance gearActions of Script
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum)
			(return (!= curRoomNum newRoomNum))
		)
		(return
			(if (not howFast)
				(cond 
					((== local4 0)
						(if
							(or
								(== global104 7)
								(== local3 1)
								(== local0 6)
								(>= local8 200)
							)
							(= local8 0)
							(gear setCycle: Forward startUpd:)
							(theMusic loop: -1 play:)
							(= local4 1)
						else
							(++ local8)
						)
					)
					(
						(and
							(!= global104 7)
							(or
								(>= local8 40)
								(and (cast contains: elevator) (< (elevator y?) 189))
							)
						)
						(= local4 0)
						(gear setCycle: 0 stopUpd:)
						(theMusic stop:)
						(= local8 0)
					)
					(else (++ local8))
				)
			else
				0
			)
		)
	)
)
