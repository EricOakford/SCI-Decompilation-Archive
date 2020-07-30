;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Talker)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm45 0
)

(local
	[local0 2] = [145 210]
	nearShip
	shipPrice
	tinyPts
)

(enum ;which ship is ego near?
	NONE
	SAUCER
	REALSHIP
)

(procedure (localproc_000e)
	(ship startUpd:)
	(gear startUpd:)
	(canopy startUpd:)
	(ladder startUpd:)
)

(procedure (localproc_0033)
	(ship stopUpd:)
	(gear stopUpd:)
	(canopy stopUpd:)
	(ladder stopUpd:)
)

(instance rm45 of SQRoom
	(properties
		lookStr {You are standing in the back part of Tiny's used space-craft lot.}
		picture 45
		east 46
		south 40
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init: 295 145 300 152 248 158 194 152 200 145 248 142
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init: 233 122 143 122 94 115 94 58 235 58
					yourself:
				)
		)
		(switch prevRoomNum
			(south
				(= style FADEOUT)
			)
			(east
				(= style SCROLLRIGHT)
				(ego x: 300)
				(HandsOn)
			)
			(else 
				(= style DISSOLVE)
				(ego posn: 160 180)
				(HandsOn)
			)
		)
		(self setRegions: ULENCE)
		(ego init:)
		(super init:)
		(if
			(and (== prevRoomNum south) (not (Btst fTinyFollows)))
			(globalSound fade:)
		)
		(saucer init: stopUpd:)
		(saucerShadow init: stopUpd:)
		(saucerLid init: stopUpd:)
		(ship init: stopUpd:)
		(canopy init: stopUpd:)
		(gear init: stopUpd:)
		(ladder init: stopUpd:)
		(shadow init: stopUpd:)
		(radar1 init: setCycle: Forward)
		(radar2 init: setCycle: Forward)
		(if (Btst fHaveNavDroid)
			(robottrac init: hide:)
		else
			(Load SOUND 631)
		)
	)
	
	(method (newRoom n)
		(if
			(and
				(== (globalSound number?) 608)
				(!= (globalSound prevSignal?) -1)
			)
			(globalSound fade:)
		)
		(super newRoom: n)
	)
	
	(method (notify)
		(super notify: &rest)
		(if (Btst fTinyFollows)
			(tiny init: setScript: sellShips)
			(self
				addObstacle:
					((= tinyPts (Polygon new:))
						type: PBarredAccess
						init: 78 128 88 137 69 150 44 151 27 139 37 129
						yourself:
					)
			)
		)
	)
)

(instance sellShips of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit: &rest)
		(cond 
			(
			(and (ego mover?) (== ((ego mover?) caller?) CueObj)) 0)
			((ego inRect: 185 140 300 175)
				(if (!= nearShip SAUCER)
					(= nearShip SAUCER)
					(client setScript: sellSaucer)
				)
			)
			((ego inRect: 100 80 246 136)
				(if (!= nearShip REALSHIP)
					(= nearShip REALSHIP)
					(client setScript: sellRealShip)
				)
			)
			(
				(and
					(!= (curRoom script?) (ScriptID SQROOM 0))
					(== (ego edgeHit?) SOUTH)
				)
				(curRoom setScript: walkTinyOutFirst)
				(self dispose:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tiny
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 54 140 self
				)
			)
			(1
				(tiny view: 445 setLoop: 0 cel: 0 stopUpd:)
				(arm init: stopUpd:)
				(arm
					show:
					setLoop: 2
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(2
				(arm stopUpd:)
				(localproc_000e)
				(= cycles 4)
			)
			(3
				(tinyTalk init: tinyHead tinyEyes tinyLips 145 0 0 1 self)
			)
			(4 (= cycles 4))
			(5
				(localproc_0033)
				(HandsOn)
				(= seconds 6)
			)
			(6
				(localproc_000e)
				(= cycles 4)
			)
			(7
				(switch (Random 0 4)
					(0
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 1 0 1 self)
						(= register 2)
					)
					(1
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 2 0 1 self)
						(= register 1)
					)
					(2
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 3 0 1 self)
						(= register 3)
					)
					(3
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 4 0 1 self)
						(= register 2)
					)
					(4
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 5 0 1 self)
						(= register 3)
					)
				)
			)
			(8
				(localproc_0033)
				(switch register
					(1
						(arm
							show:
							setLoop: 2
							cel: 0
							posn: (+ (tiny x?) 17) (- (tiny y?) 28)
							setPri: (tiny priority?)
							setCycle: Oscillate 1 self
						)
					)
					(2
						(arm
							show:
							setLoop: 4
							cel: 0
							posn: (+ (tiny x?) 17) (- (tiny y?) 28)
							setPri: (tiny priority?)
							setCycle: Oscillate 1 self
						)
					)
					(3
						(arm
							show:
							setLoop: 4
							cel: 0
							posn: (+ (tiny x?) 17) (- (tiny y?) 28)
							setPri: (tiny priority?)
							setCycle: Oscillate 1 self
						)
					)
				)
			)
			(9
				(localproc_000e)
				(= cycles 4)
			)
			(10
				(arm stopUpd:)
				(if (!= register 3)
					(tinyTalk
						init: tinyHead tinyEyes tinyLips {"Talk to me, chief."} 0 0 1 self
					)
				else
					(self cue:)
				)
			)
			(11 (= cycles 4))
			(12
				(localproc_0033)
				(= seconds 12)
			)
			(13 (= start 3) (self init:))
		)
	)
)

(instance walkTinyOutFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0 y: 188)
				(Face ego tiny self)
			)
			(1
				(localproc_000e)
				(= cycles 4)
			)
			(2
				(tinyTalk init: tinyHead tinyEyes tinyLips 145 6 0 1 self)
			)
			(3 (= cycles 4))
			(4
				(localproc_0033)
				(arm
					show:
					setLoop: 2
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(5
				(arm dispose:)
				(tiny view: 446 setLoop: 2 setScript: 0 setCycle: Walk)
				(if (> (ego x?) 55)
					(tiny setMotion: MoveTo 12 240 self)
				else
					(tiny setMotion: MoveTo -30 180 self)
				)
			)
			(6
				(ego setMotion: MoveTo (ego x?) 190 self)
			)
			(7 (self dispose:))
		)
	)
)

(instance sellSaucer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego saucer self)
			)
			(1
				(localproc_000e)
				(= cycles 4)
			)
			(2
				(tinyTalk init: tinyHead tinyEyes tinyLips 145 7 0 0 self)
			)
			(3
				(arm
					show:
					setLoop: 4
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(4
				(arm stopUpd:)
				(tinyTalk say: 145 8 0 (not (Btst fGotStoreCredit)) self)
			)
			(5
				(arm
					show:
					setLoop: 2
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(6
				(arm stopUpd:)
				(if (Btst fGotStoreCredit)
					(tinyTalk say: 145 9 0 1 self)
					(= shipPrice 115)
				else
					(= shipPrice 185)
					(arm
						show:
						setLoop: 4
						cel: 0
						posn: (+ (tiny x?) 17) (- (tiny y?) 28)
						setPri: (tiny priority?)
						setCycle: Oscillate 1 self
					)
				)
			)
			(7 (= cycles 4))
			(8
				(localproc_0033)
				(= cycles 4)
			)
			(9
				(arm stopUpd:)
				(sellShips start: 3)
				(HandsOn)
				(client setScript: sellShips)
			)
		)
	)
)

(instance sellRealShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego ship self)
			)
			(1
				(localproc_000e)
				(= cycles 4)
			)
			(2
				(tinyTalk
					init: tinyHead tinyEyes tinyLips 145 10 0 0 self
				)
			)
			(3
				(arm
					show:
					setLoop: 4
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(4
				(arm stopUpd:)
				(tinyTalk say: 145 11 0 (not (Btst fGotStoreCredit)) self)
			)
			(5
				(arm
					show:
					setLoop: 2
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(6
				(arm stopUpd:)
				(if (Btst fGotStoreCredit)
					(tinyTalk say: 145 12 0 1 self)
					(= shipPrice 144)
					(self cue:)
				else
					(= shipPrice 214)
					(arm
						show:
						setLoop: 4
						cel: 0
						posn: (+ (tiny x?) 17) (- (tiny y?) 28)
						setPri: (tiny priority?)
						setCycle: Oscillate 1 self
					)
				)
			)
			(7 (= cycles 4))
			(8
				(localproc_0033)
				(arm stopUpd:)
				(sellShips start: 3)
				(HandsOn)
				(client setScript: sellShips)
			)
		)
	)
)

(instance getInShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 167 122 self)
				(if (Btst fHaveNavDroid) ((ScriptID ULENCE 1) setMotion: 0))
			)
			(1
				(localproc_000e)
				(= cycles 4)
			)
			(2
				(if (cast contains: tiny)
					(if (Btst fHaveNavDroid)
						(tinyTalk
							init: tinyHead tinyEyes tinyLips 145 13 0 1 self
						)
					else
						(tinyTalk
							init: tinyHead tinyEyes tinyLips 145 14 0 1 self
						)
					)
				else
					(++ state)
					(++ state)
					(self cue:)
				)
			)
			(3 (= cycles 4))
			(4
				(localproc_0033)
				(arm
					show:
					setLoop: 2
					cel: 0
					posn: (+ (tiny x?) 17) (- (tiny y?) 28)
					setPri: (tiny priority?)
					setCycle: Oscillate 1 self
				)
			)
			(5
				(if (cast contains: tiny)
					(arm dispose:)
					(tiny
						view: 446
						setLoop: 2
						setScript: 0
						setCycle: Walk
						setMotion: MoveTo 12 240
					)
					(tinyPts type: 0)
					(globalSound fade:)
				)
				(ego
					posn: 171 86
					setPri: 14
					view: 145
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(6
				(canopy loop: 5 cel: 0 setCycle: EndLoop self)
				(ego hide:)
			)
			(7
				(soundFx number: 603 loop: 1 play:)
				(= seconds 4)
			)
			(8
				(if (Btst fHaveNavDroid)
					(client setScript: robotIntoShip)
				else
					(Print 45 0)
					(Print 45 1)
					(soundFx number: 603 loop: 1 play:)
					(canopy setCycle: BegLoop self)
					(ego show:)
				)
			)
			(9 (= seconds 3))
			(10
				(ego setPri: 14 setCycle: BegLoop self)
			)
			(11
				(NormalEgo 0 1 61)
				(ego
					posn: 167 122
					loop: 3
					heading: 0
					setMotion: MoveTo 167 133 self
				)
			)
			(12
				(tiny dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance robotIntoShip of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 4) (== (globalSound prevSignal?) 10))
			(globalSound prevSignal: 0)
			(= cycles 4)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID ULENCE 1)
					illegalBits: 0
					setMotion: PolyPath 235 117 self
				)
			)
			(1
				((ScriptID ULENCE 1)
					setPri: 4
					setMotion: MoveTo 151 117 self
				)
			)
			(2 (Print 45 2) (= cycles 4))
			(3
				(soundFx number: 606 loop: 1 play:)
				(robottrac posn: 151 117 show: stopUpd:)
				((ScriptID ULENCE 1)
					setPri: 14
					view: 145
					setLoop: 4
					cel: 0
					posn: 151 87
					setCycle: EndLoop self
				)
			)
			(4
				(soundFx stop:)
				(globalSound number: 624 loop: 1 flags: mNOPAUSE play:)
				(zap1 init: setCycle: Forward)
				(zap2 init: setCycle: Forward)
				(gear setCycle: EndLoop)
			)
			(5
				(canopy dispose:)
				((ScriptID ULENCE 1) dispose:)
				(ship setCel: 1 setMotion: MoveTo (ship x?) -50 self)
				(shadow setCycle: EndLoop)
				(ladder setScript: fallDown)
			)
			(6
				(ship stopUpd:)
				(zap1 dispose:)
				(zap2 dispose:)
				(globalSound fade: 40 25 4 0)
				(SolvePuzzle 25 fExitKerona)
				(mugger
					init:
					setCycle: Walk
					setMotion: MoveTo 250 120 self
				)
			)
			(7
				(mugger
					setLoop: 7
					cel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(8
				(Print 45 3)
				(mugger setCycle: EndLoop self)
			)
			(9
				(ship
					setPri: -1
					setCel: -1
					view: 835
					posn: 65 158
					cel: 10
					setLoop: 0
					cycleSpeed: 18
					setCycle: BegLoop self
				)
			)
			(10
				(music number: 465 loop: -1 play:)
				(curRoom newRoom: 49)
			)
		)
	)
)

(instance getInSaucer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 223 146 self)
			)
			(1
				(ego
					view: 245
					setLoop: 0
					cel: 0
					posn: 247 141
					setPri: 14
					setCycle: EndLoop self
				)
			)
			(2
				(saucerLid cel: 1 setCycle: EndLoop self)
				(ego hide:)
				(globalSound stop:)
			)
			(3
				(soundFx number: 603 loop: 1 play: self)
			)
			(4
				(soundFx number: 610 loop: 1 play: hold: 1)
				(saucerLid hide:)
				(saucer setLoop: 2 cel: 0)
				(= cycles 4)
			)
			(5
				(saucer setCycle: EndLoop self)
				(saucerShadow setCycle: EndLoop)
			)
			(6
				(soundFx hold: 0)
				(saucer
					cycleSpeed: 6
					setCycle: CycleTo 4 -1
					setStep: 4 7
					setMotion: MoveTo 200 60 self
				)
			)
			(7
				(soundFx fade:)
				(saucer
					cycleSpeed: 8
					setCycle: EndLoop
					setStep: 4 12
					setMotion: MoveTo 260 -60 self
				)
			)
			(8
				(saucerShadow dispose:)
				(saucerLid dispose:)
				(= seconds 3)
			)
			(9
				(soundFx number: 617 loop: 1 play:)
				(saucer
					posn: 220 30
					setLoop: 4
					cel: 0
					cycleSpeed: 6
					setCycle: CycleTo 5 1 self
				)
			)
			(10
				(soundFx number: 618 loop: 1 play:)
				(saucer setCycle: EndLoop self)
			)
			(11 (EgoDead 945 0 0 45 4))
		)
	)
)

(instance fallDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ladder setCycle: CycleTo 2 1 self)
			)
			(1
				(soundFx number: 631 loop: 1 play:)
				(ladder cel: 3 setCycle: EndLoop self)
			)
			(2 (self dispose:))
		)
	)
)

(instance arm of Prop
	(properties
		view 445
		loop 2
		signal (| ignrAct ignrHrz)
		cycleSpeed 7
	)
)

(instance zap1 of Prop
	(properties
		x 192
		view 145
		loop 2
		priority 14
		signal (| ignrHrz fixPriOn)
		cycleSpeed 1
	)
	
	(method (doit)
		(= y (- (ship y?) 13))
		(super doit: &rest)
	)
)

(instance zap2 of Prop
	(properties
		x 209
		view 145
		loop 3
		cel 3
		priority 14
		signal (| ignrHrz fixPriOn)
		cycleSpeed 1
	)
	
	(method (doit)
		(= y (- (ship y?) 13))
		(super doit: &rest)
	)
)

(instance tiny of Actor
	(properties
		x 12
		y 219
		description {Tiny}
		lookStr {Tiny is a large, green-scale-covered jaw jockey with the personality of a true salesman. Not a prime combination of traits, I must say.}
		yStep 3
		view 446
		signal ignrAct
		cycleSpeed 9
		illegalBits $0000
		xStep 4
		moveSpeed 9
	)
	
	(method (init)
		(self approachVerbs: verbUse)
		(super init: &rest)
	)
	
	(method (doit)
		(= approachX (+ x 16))
		(= approachY (+ y 8))
		(super doit: &rest)
	)
	
	(method (doVerb theVerb theItem &tmp [str 60])
		(switch theVerb
			(verbTalk
				(cond 
					((not (Btst 19)) (tinyTalk init: tinyHead tinyEyes tinyLips 145 15 0 1))
					(shipPrice
						(tinyTalk
							init:
								tinyHead tinyEyes tinyLips
								(Format @str 45 6 [local0 nearShip])
								0
								0
								1
						)
					)
					(else
						(Print 45 7)
					)
				)
			)
			(verbDo
				(Print 45 8))
			(verbSmell
				(Print 45 9)
			)
			(verbTaste
				(Print 45 10)
			)
			(verbUse
				(switch theItem
					(iBuckazoid
						(if shipPrice
							(cond 
								(
									(or
										(and (== nearShip SAUCER) (Btst fBoughtSaucer))
										(and (== nearShip REALSHIP) (Btst fBoughtRealShip))
									)
									(tinyTalk
										init:
											tinyHead tinyEyes tinyLips
											{I'd like to take your money, pal. But, even I wouldn't sell you the same ship twice.}
											0 0 1
									)
								)
								((< buckazoids shipPrice)
									(tinyTalk init: tinyHead tinyEyes tinyLips 145 16 0 1)
								)
								(else
									(tinyTalk init: tinyHead tinyEyes tinyLips 145 17 0 1)
									(Bset
										(if (== nearShip SAUCER)
											fBoughtSaucer
										else
											(Bclr fTinyFollows)
											(SolvePuzzle 4 f45BuyRealShip)
											fBoughtRealShip
										)
									)
									(= buckazoids (- buckazoids shipPrice))
									(self setScript: 0)
								)
							)
						else
							(Print 45 11)
						)
					)
					(iKeyCard
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 18 0 1)
						(ego put: iKeyCard)
					)
					(iGadget
						(tinyTalk
							init:
								tinyHead tinyEyes tinyLips
								{Lovely gadget - lovely. So are we gonna make a deal here? Talk to me.}
								0 0 1
						)
					)
					(iCartridge
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 19 0 1)
						(ego put: iCartridge)
					)
					(iKnife
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 20 0 1)
					)
					(iJetpack
						(tinyTalk
							init:
								tinyHead tinyEyes tinyLips
								{That's a smell jet ack, all right, but for exploring space, you need a real ship. Can we talk business.}
								0 0 1
						)
					)
					(iDehydratedWater
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 21 0 1)
						(ego put: iDehydratedWater)
					)
					(iBrokenGlass
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 22 0 1)
					)
					(iRock
						(tinyTalk init: tinyHead tinyEyes tinyLips 145 23 0 1)
					)
					(iSkimmerKey
						(tinyTalk
							init:
								tinyHead tinyEyes tinyLips
								{Sorry, bud, but I only deal in space ships, not sand skimmers.}
								0 0 1
						)
					)
					(iJetpack	;this case already exists, and will probably never be executed
						(tinyTalk
							init:
								tinyHead tinyEyes tinyLips
								{Sorry, bud, but I only deal in space ships, not itsy-bitsy jetpacks.}
								0 0 1
						)
					)
					(else 
						(tinyTalk
							init: tinyHead tinyEyes tinyLips {Sorry, pal, but I only take cash.} 0 0 1
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(if (Btst fGotStoreCredit) (Printf 45 5 shipPrice))
		(super cue: &rest)
	)
)

(instance mugger of Actor
	(properties
		x 332
		y 140
		description {mugger}
		lookStr {As you rise high above the sands of Ulence, you look down and see someone waving good-bye. This really is a friendly place after-all!}
		view 432
		loop 1
		signal fixedLoop
		cycleSpeed 4
		xStep 4
		moveSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print {That's not possible from up here.})
			)
			(verbTalk
				(Print {That's not possible from up here.})
			)
			(verbSmell
				(Print {That's not possible from up here.})
			)
			(verbTaste
				(Print {That's not possible from up here.})
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ship of Actor
	(properties
		x 171
		y 86
		description {ship}
		approachX 167
		approachY 130
		view 145
		loop 8
		priority 7
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
		cycleSpeed 8
		moveSpeed 4
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (Btst fBeenAtShipLot)
					(Print 45 12)
				else
					(Print 45 13)
				)
			)
			(verbDo
				(if (Btst fBoughtRealShip)
					(curRoom setScript: getInShip)
				else
					(Print 45 14)
				)
			)
			(verbTaste
				(Print 45 15)
			)
			(verbSmell
				(Print 45 16)
			)
			(verbUse
				(switch theItem
					(iJetpack
						(Print 45 17)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance canopy of Actor
	(properties
		x 188
		y 61
		view 145
		loop 5
		priority 7
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 8
		moveSpeed 8
	)
)

(instance gear of Prop
	(properties
		x 171
		y 86
		view 145
		loop 1
		priority 3
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
)

(instance robottrac of View
	(properties
		x 332
		y 139
		view 145
		loop 8
		cel 2
		signal (| ignrAct fixedLoop)
	)
)

(instance ladder of Prop
	(properties
		x 169
		y 118
		description {ladder}
		approachX 167
		approachY 130
		lookStr {It's just a regular old ladder.}
		view 145
		loop 6
		priority 7
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb verbDo)
			(ship doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance shadow of Prop
	(properties
		x 169
		y 118
		view 145
		loop 7
		priority 2
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance saucer of Actor
	(properties
		x 249
		y 141
		description {saucer ship}
		approachX 217
		approachY 142
		lookStr {It's awfully small, and also much older than you.}
		view 245
		loop 5
		priority 11
		signal (| ignrAct ignrHrz fixPriOn)
		cycleSpeed 18
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: verbDo verbWalk)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(if (Btst fBoughtSaucer)
					(curRoom setScript: getInSaucer)
				else
					(Print 45 14)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance saucerShadow of Prop
	(properties
		x 249
		y 156
		view 245
		loop 3
		priority 2
		signal (| ignrAct fixPriOn)
		cycleSpeed 18
	)
)

(instance saucerLid of Prop
	(properties
		x 263
		y 146
		z 7
		description {saucer's bubble}
		lookStr {That's the lid that covers and protects you during flight. Provided this thing does fly.}
		view 245
		loop 1
		priority 11
		signal (| ignrAct fixPriOn)
		cycleSpeed 8
	)
)

(instance radar1 of Prop
	(properties
		x 87
		y 78
		description {force field sensor}
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. 
		They repel such undesirables such as the grell which thrive below the sands. 
		It will allow only airborne vehicles in or out.}
		view 171
		loop 2
		cel 1
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(Print 45 18)
			)
			(verbTalk
				(Print 45 19)
			)
			(verbSmell
				(Print 45 18)
			)
			(verbTaste
				(Print 45 18)
			)
		)
	)
)

(instance radar2 of Prop
	(properties
		x 287
		y 78
		description {force field sensor}
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. 
		They repel such undesirables such as the grell which thrive below the sands. 
		It will allow only airborne vehicles in or out.}
		view 171
		loop 2
		cel 7
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(radar1 doVerb: theVerb)
	)
)

(instance tinyTalk of Talker
	(properties
		x 99
		y 16
		nsTop 5
		nsLeft 11
		view 514
	)
)

(instance tinyHead of View
	(properties
		view 514
		cel 1
	)
)

(instance tinyLips of Prop
	(properties
		nsTop 44
		nsLeft 29
		view 514
		loop 4
		cycleSpeed 9
	)
)

(instance tinyEyes of Prop
	(properties
		nsTop 26
		nsLeft 35
		view 514
		loop 2
		cycleSpeed 30
	)
)

(instance extraSound of Sound
	(properties)
)
