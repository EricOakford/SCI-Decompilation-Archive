;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room57 0
)
(synonyms
	(dirt dirt dirt dirt dirt)
	(hag hag woman)
)

(local
	cauldron
	firePit
	brew
	witch1
	witch2
	witch2Body
	witch3Body
	witch3
	local8
	theEye
	witchesEyeless
	scarab
	witchGrabEgo
	ouchMsg
	skullEyes
)
(instance Room57 of Room
	(properties
		picture 57
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(= south 6)
		(Load VIEW 631)
		(if (>= gamePhase getPandoraBox)
			(Load VIEW 183)
			(Load VIEW 184)
			(Load VIEW 185)
			(Load VIEW 186)
			(Load VIEW 180)
			(Load VIEW 64)
			(Load VIEW 21)
		)
		(Load VIEW 65)
		(Load VIEW 66)
		(super init:)
		(= isIndoors TRUE)
		(= noWearCrown TRUE)
		(self setScript: rm57Script)
		(ego
			posn: 160 184
			view: 2
			setStep: 3 2
			setPri: -1
			loop: 3
			init:
		)
		(if witchesTossedScarab
			((= scarab (Actor new:))
				view: 631
				loop: 3
				cel: 0
				illegalBits: 0
				ignoreActors: TRUE
				posn: scarabX scarabY
				setScript: scarabToss
				init:
			)
		)
		((= skullEyes (Prop new:))
			isExtra: TRUE
			view: 631
			loop: 1
			cel: 0
			posn: 105 83
			init:
			cycleSpeed: 2
			setCycle: Forward
		)
		((= cauldron (View new:))
			view: 631
			loop: 4
			cel: 0
			posn: 159 151
			setPri: 12
			init:
			addToPic:
		)
		((= firePit (Prop new:))
			isExtra: TRUE
			view: 631
			loop: 5
			posn: 159 157
			setCycle: Forward
			setPri: 12
			init:
		)
		((= brew (Prop new:))
			view: 631
			loop: 0
			posn: 159 140
			setPri: 12
			ignoreActors:
			setCycle: Forward
			init:
		)
		(brew
			brTop: (+ (brew brTop?) 14)
			brBottom: (+ (brew brBottom?) 19)
		)
		(if (> gamePhase getTheHen)
			(witchMusic loop: 1 play:)
			((= theEye (Prop new:))
				view: 631
				setLoop: 2
				cycleSpeed: 2
				setCycle: Forward
				setPri: 12
				init:
				stopUpd:
				hide:
			)
			((= witch2 (Prop new:))
				view: 186
				loop: 0
				cel: 5
				posn: 108 101
				setLoop: 1
				init:
				stopUpd:
			)
			((= witch2Body (Prop new:))
				view: 186
				setLoop: 3
				cel: 0
				posn: 108 121
				init:
				stopUpd:
			)
			((= witch3 (Prop new:))
				view: 184
				loop: 0
				cel: 5
				posn: 135 100
				init:
				stopUpd:
			)
			((= witch3Body (Prop new:))
				view: 184
				setLoop: 2
				cel: 0
				posn: 135 120
				init:
				stopUpd:
			)
			(blockWitch2
				top: 115
				bottom: (witch2 brBottom?)
				left: (- (witch2 brLeft?) 2)
				right: (+ (witch2 brBottom?) 2)
				init:
			)
			(blockWitch3
				top: 115
				bottom: (witch3 brBottom?)
				left: (- (witch3 brLeft?) 2)
				right: (+ (witch3 brBottom?) 2)
				init:
			)
			(ego observeBlocks: blockWitch2 blockWitch3)
			((= witch1 (Actor new:))
				view: 185
				posn: 83 126
				illegalBits: cWHITE
				setLoop: 0
				init:
			)
			(if (ego has: iGlassEye)
				(= witchesEyeless TRUE)
				(if (and (not (ego has: iScarab)) (not witchesTossedScarab))
					(Load SCRIPT JUMP)
					((= scarab (Actor new:))
						view: 631
						loop: 3
						cel: 0
						illegalBits: 0
						ignoreActors: TRUE
						posn: (witch1 x?) (- (witch1 y?) 30)
						setScript: scarabToss
						init:
					)
					(rm57Script changeState: 1)
				else
					(rm57Script changeState: 3)
				)
				(witch3 setScript: witchMoan)
			else
				(= witchesEyeless FALSE)
				(witch2 setScript: witchEye)
			)
		)
	)
	
	(method (doit)
		(cond 
			(
				(and
					(& (ego onControl:) cGREEN)
					(not witchGrabEgo)
					(not ouchMsg)
				)
				(Print 57 0 #time 3)
				(ouchness cue:)
			)
			((> gamePhase getTheHen)
				(cond 
					((and (ego has: iGlassEye) (!= (witch3 script?) witchMoan))
						(witch1 setScript: 0 setMotion: 0)
						(witchEye changeState: 20)
					)
					(
						(and
							(!= (witch1 script?) witchChase)
							(< (ego distanceTo: witch1) 30)
							(not (ego has: 6))
							(!= (witch3 script?) witchMoan)
						)
						(witch1 setScript: witchChase)
					)
					(
						(and
							(ego inRect: 110 123 119 133)
							(not (ego has: iGlassEye))
							(not witchGrabEgo)
							(== (witch1 script?) witchChase)
						)
						(Print 57 1 #time 2)
						(Print 57 2 #time 2)
						(witch2 hide:)
						(witch2Body hide: ignoreActors:)
						(ego
							setMotion: 0
							view: 186
							loop: 2
							posn: (witch2Body x?) (witch2Body y?)
							setLoop: 2
							setCycle: Forward
						)
						(= witchGrabEgo TRUE)
						(witchMusic number: 13 loop: 1 play:)
						(User canControl: FALSE canInput: FALSE)
						(witchEye changeState: 7)
					)
					(
						(and
							(ego inRect: 131 122 138 131)
							(not (ego has: iGlassEye))
							(not witchGrabEgo)
							(== (witch1 script?) witchChase)
						)
						(Print 57 1 #time 2)
						(Print 57 2 #time 2)
						(HandsOff)
						(ego
							setMotion: 0
							view: 184
							loop: 1
							posn: (witch3Body x?) (witch3Body y?)
							setLoop: 1
							setCycle: Forward
						)
						(witch3 hide:)
						(witch3Body hide: ignoreActors:)
						(= witchGrabEgo TRUE)
						(witchMusic number: 13 loop: 1 play:)
						(witchEye changeState: 4)
					)
					(
						(and
							(!= (witch1 script?) witchChase)
							(!= (witch3 script?) witchMoan)
							(ego inRect: 110 123 138 133)
						)
						(witch1 setScript: witchChase)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(sounds eachElementDo: #stop 0)
		(timers eachElementDo: #dispose 84)
		(= noWearCrown FALSE)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp index)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (> gamePhase getTheHen)
			(cond 
				((Said 'look>')
					(cond 
						((Said '/eye')
							(if ((inventory at: iGlassEye) ownedBy: 57)
								(Print 57 3)
							else
								(event claimed: FALSE)
							)
						)
						((Said '/hag')
							(cond 
								((not (ego has: iGlassEye))
									(Print 57 4)
								)
								(witchesEyeless
									(Print 57 5)
								)
								(((inventory at: iGlassEye) ownedBy: -1)
									(Print 57 6)
								)
								(else (Print 57 5))
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
				)
				((Said 'converse')
					(cond 
						(((inventory at: iGlassEye) ownedBy: 57)
							(answer1 cue:)
						)
						((ego has: iGlassEye)
							(answer2 cue:)
						)
						(else
							(answer3 cue:)
						)
					)
				)
				((Said 'rob,get/charm')
					(cond 
						((ego has: iScarab)
							(Print 800 0)
						)
						((not witchesTossedScarab)
							(Print 57 7)
						)
						((> (ego distanceTo: scarab) 20)
							(Print 800 1)
						)
						(else
							(ego setScript: pickUp)
						)
					)
				)
				((Said 'get,rob/eye')
					(if (not (ego has: iGlassEye))
						(if ((inventory at: iGlassEye) ownedBy: 57)
							(if (ego inRect: 119 122 129 136)
								(Print 57 8)
								(ego get: iGlassEye)
								(theGame changeScore: 3)
								(= gotItem TRUE)
							else
								(Print 800 1)
							)
						else
							(Print 57 9)
						)
					else
						(Print 800 0)
					)
				)
				((Said 'deliver,return,throw/eye')
					(if (ego has: iGlassEye)
						(if (not witchesEyeless)
							(Print 57 10)
							(ego put: iGlassEye -1)
						else
							(theGame changeScore: 3)
							(ego put: iGlassEye -1)
							(Print 57 11)
						)
						(witchMoan changeState: 4)
					else
						(Print 800 2)
					)
				)
				((Said '/hag>')
					(cond 
						((Said 'kill')
							(Print 57 12)
						)
						((Said 'get')
							(Print 57 13)
						)
						((Said 'kiss')
							(Print 57 14)
						)
						(
							(and
								(Said 'deliver>')
								(= index (inventory saidMe:))
							)
							(if (ego has: (inventory indexOf: index))
								(Print 57 15)
							else
								(DontHave)
							)
						)
					)
				)
			)
		)
		(cond 
			((Said 'look>')
				(cond 
					((Said '/bottle')
						(Print 57 16)
					)
					((or (Said '[<around,in][/room,cave]') (Said '[/noword]'))
						(Print
							(Format @str 57 17
								(if (> gamePhase getTheHen)
									{Within it, reside three one-eyed old witches.}
								else
									{_}
								)
								(if (cast contains: scarab)
									{You see a black scarab on the cave floor.}
								else
									{_}
								)
							)
						)
					)
					((Said '/caldron')
						(Print 57 18)
					)
					((Said '/brew')
						(Print 57 19)
					)
					((Said '/fire')
						(Print 57 20)
					)
					((Said '<out/cave')
						(Print 57 21)
					)
					((or (Said '/dirt') (Said '<down'))
						(if witchesTossedScarab
							(Print 57 22)
						else
							(Print 57 23)
						)
					)
					((Said '/wall')
						(Print 57 24)
					)
					((Said '/shelf')
						(Print 57 25)
					)
					((Said '/skull')
						(Print 57 26)
					)
				)
			)
			((Said 'get,rob>')
				(cond 
					((Said '/bottle')
						(Print 57 27)
					)
					((Said '/skull')
						(Print 57 28)
					)
					((Said '/brew')
						(Print 57 29)
					)
				)
			)
			((Said 'eat,drink/brew')
				(Print 57 30)
			)
			((Said 'get/caldron')
				(Print 57 31)
			)
		)
	)
	
	(method (newRoom n)
		(ego loop: 2)
		(Animate (cast elements?) FALSE)
		(= noWearCrown FALSE)
		(super newRoom: n)
	)
)

(instance witchEye of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(witch1 view: 185 loop: 0 setCycle: BegLoop self)
			)
			(1
				(theEye
					x: (+ (witch1 x?) 1)
					y: (- (witch1 y?) 43)
					show:
				)
				(= seconds 4)
			)
			(2
				(theEye hide:)
				(witch1 loop: 1 setCycle: EndLoop self)
			)
			(3
				(if (not ((inventory at: iGlassEye) ownedBy: -1))
					(witch1 setScript: witchChase)
				)
				(witch2 loop: 0 setCycle: CycleTo 4 1 self)
			)
			(4
				(witch3 stopUpd:)
				(theEye
					x: (- (witch2 x?) 1)
					y: (- (witch2 y?) 12)
					show:
				)
				(if (not witchGrabEgo)
					(= seconds 5)
				)
			)
			(5
				(theEye hide:)
				(witch2 setLoop: 0 setCycle: BegLoop self)
			)
			(6
				(witch2 setCycle: CycleTo 5 -1)
				(witch3 setCycle: CycleTo 4 1 self)
			)
			(7
				(witch2 stopUpd:)
				(theEye
					x: (+ (witch3 x?) 0)
					y: (- (witch3 y?) 12)
					show:
				)
				(if (not witchGrabEgo)
					(= seconds 5)
				)
			)
			(8
				(theEye hide:)
				(witch3 setCycle: BegLoop self)
			)
			(9
				(witch3 setCycle: CycleTo 5 -1)
				(if (== (witch1 script?) witchChase)
					(= state 3)
				)
				(witch2 setCycle: CycleTo 4 1 self)
			)
			(10
				(witch3 stopUpd:)
				(theEye
					x: (- (witch2 x?) 1)
					y: (- (witch2 y?) 40)
					show:
				)
				(if (not witchGrabEgo)
					(= seconds 5)
				)
			)
			(11
				(theEye hide:)
				(witch2 loop: 0 cel: 255 setCycle: EndLoop self)
			)
			(12
				(self changeState: 0)
			)
			(20
				(theEye hide:)
				(client setScript: 0)
				(witch3 setScript: witchMoan)
			)
		)
	)
)

(instance witchChase of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(witch1
					view: 180
					moveSpeed: 2
					setAvoider: Avoider
					setLoop: -1
					setCycle: Walk
					setMotion: Chase ego 7 self
				)
			)
			(1
				(HandsOff)
				(ego ignoreActors: hide:)
				(witch2 show:)
				(witch2Body show:)
				(witch3 show:)
				(witch3Body show:)
				(witch1 view: 65 moveSpeed: 0)
				(self cue:)
			)
			(2
				(witchMusic number: 13 loop: 1 play:)
				(= witchGrabEgo TRUE)
				(witch1
					view: 66
					setLoop:
						(if
							(<
								180
								(GetAngle
									(ego x?)
									(ego y?)
									(witch1 x?)
									(witch1 y?)
								)
							)
							1
						else
							0
						)
					setAvoider: Avoider
					setMotion: MoveTo 157 (if (< (witch1 y?) 154) 143 else 164) self
				)
			)
			(3
				(Print 57 32 #at -1 20)
				(witch1
					view: 66
					setLoop: (+ (witch1 loop?) 2)
					cel: 255
					setCycle: EndLoop self
				)
			)
			(4
				(witch1
					setLoop: -1
					illegalBits: cWHITE
					ignoreActors: FALSE
					setAvoider: Avoider
					view: 180
					setMotion: MoveTo 95 (witch1 y?)
				)
				(witch2 stopUpd:)
				(witch3 stopUpd:)
				(witchMusic stop:)
				(ego
					view: 64
					posn: (brew x?) (+ (brew y?) 1)
					cel: 255
					ignoreActors: TRUE
					setPri: 12
					setLoop: (witch1 loop?)
					show:
					setMotion: 0
					setCycle: EndLoop self
				)
			)
			(5
				(ego setLoop: 2 cycleSpeed: 1 setCycle: Forward)
				(= cycles (* (NumCels ego) 6))
			)
			(6
				(ego cel: 255 setLoop: 3 setCycle: EndLoop self)
			)
			(7
				(= seconds 6)
			)
			(8
				(= dead TRUE)
			)
		)
	)
)

(instance witchMoan of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(witch1 setScript: 0)
				(theEye hide:)
				(witch3 hide: ignoreActors:)
				(witch3Body
					view: 183
					setLoop: 2
					cel: 255
					setCycle: EndLoop self
					ignoreActors: 0
				)
			)
			(1
				(witch3Body stopUpd:)
				(witch2 hide: ignoreActors:)
				(witch2Body
					view: 183
					setLoop: 1
					cel: 255
					ignoreActors: 0
					setCycle: EndLoop self
				)
			)
			(2
				(witch2Body stopUpd:)
				(witch1 ignoreActors: 0 setMotion: MoveTo 125 133 self)
			)
			(3
				(if (not ((inventory at: iGlassEye) ownedBy: -1))
					(Print 57 33)
				)
				(witch1 view: 183 setLoop: 0 cel: 255 setCycle: EndLoop)
			)
			(4
				(witch3Body setCycle: BegLoop)
				(witch1 setCycle: BegLoop)
				(witch2Body setCycle: BegLoop)
				((ScriptID 0 4) setReal: self 4)
			)
			(5
				(witch1
					view: 180
					setLoop: -1
					setMotion: MoveTo 88 129 self
				)
			)
			(6
				(witch2Body view: 186 setLoop: 3 cel: 0)
				(witch3Body view: 184 setLoop: 2 cel: 0)
				(witch2 show:)
				(witch3 show:)
				(client setScript: 0)
				(witch2 setScript: witchEye)
			)
		)
	)
)

(instance answer1 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 57 34)
			)
			(1
				(Print 57 35)
			)
			(2
				(Print 57 36)
				(= state 1)
			)
		)
	)
)

(instance answer2 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((inventory at: iGlassEye) ownedBy: -1))
					(Print 57 37)
				)
			)
			(1
				(if (not ((inventory at: iGlassEye) ownedBy: -1))
					(switch (Random 1 4)
						(1
							(Print 57 38)
						)
						(2
							(Print 57 39)
						)
						(3
							(Print 57 40)
						)
						(4
							(Print 57 37)
						)
					)
					(= state 0)
				)
			)
		)
	)
)

(instance answer3 of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 57 41)
			)
			(1
				(switch (Random 1 3)
					(1
						(Print 57 42)
					)
					(2
						(Print 57 43)
					)
					(3
						(Print 57 41)
					)
				)
				(= state 0)
			)
		)
	)
)

(instance scarabToss of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if witchesTossedScarab
					(self changeState: 1)
				else
					(scarab
						setMotion: JumpTo (- (ego x?) 20) (- (ego y?) 20) self
					)
				)
			)
			(1
				(= scarabX (scarab x?))
				(= scarabY (scarab y?))
				(= witchesTossedScarab TRUE)
				(scarab setPri: -1)
				(= seconds 2)
			)
			(2
				(if (cast contains: scarab)
					(scarab setCycle: EndLoop self)
				)
			)
			(3
				(if (cast contains: scarab)
					(scarab setCycle: BegLoop self)
				)
			)
			(4
				(scarab stopUpd:)
				(= state 1)
				(= seconds 5)
			)
		)
	)
)

(instance ouchness of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ouchMsg TRUE)
				((ScriptID 0 5) setReal: self 3)
			)
			(1
				(= state -1)
				(= ouchMsg FALSE)
			)
		)
	)
)

(instance witchMusic of Sound
	(properties
		number 12
	)
)

(instance blockWitch2 of Block)

(instance blockWitch3 of Block)

(instance pickUp of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self cue:)
			)
			(1
				(HandsOff)
				(Face ego scarab)
				(ego view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(scarab dispose:)
				(= witchesTossedScarab FALSE)
				(ego get: iScarab)
				(= gotItem TRUE)
				(theGame changeScore: 2)
				(= seconds 2)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(ego view: 2 setCycle: Walk)
				(HandsOn)
			)
		)
	)
)

(instance rm57Script of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(= seconds 3)
			)
			(2
				(Print 57 44 #at -1 20)
				(= state 4)
				(= seconds 3)
				(HandsOn)
			)
			(3
				(= seconds 3)
			)
			(4
				(if (not ((inventory at: iGlassEye) ownedBy: -1))
					(Print 57 45)
				)
			)
			(5
				(Print 57 46)
			)
		)
	)
)
