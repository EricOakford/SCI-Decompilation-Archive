;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Block)
(use LoadMany)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	talkCount
	local1
	paidToll
	i
	[ripple 3]
	[rippleX 3] = [195 23 251]
	[rippleY 3] = [104 90 118]
	[rippleLoop 3] = [1 3 3]
	[ripplePri 3] = [-1 1 3]
)
(procedure (TrollSays)
	(cls)
	(Print &rest
		#title {Troll}
		#font 4
		#at 144 20
		#width 150
		#dispose
	)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance rm25 of Room
	(properties
		picture 25
		horizon 45
		north 40
		east 26
		south 24
		west 32
	)
	
	(method (init)
		(Load VIEW 241)
		(LoadMany VIEW 120 124 123 126 168 44)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east WIPELEFT)
				(south WIPEUP)
			)
		)
		(super init:)
		(switch prevRoomNum
			(north
				(ego y: (+ horizon 2))
			)
			(south
				(ego y: 188)
			)
			(west
				(if (< (ego y?) 100)
					(ego posn: 3 (proc0_17 56 (ego y?) (+ 2 horizon)))
				else
					(ego posn: 3 (proc0_17 188 (ego y?) 134))
				)
			)
			(east
				(if (< (ego y?) 100)
					(ego posn: 317 (proc0_17 57 (ego y?) (+ 2 horizon)))
				else
					(ego posn: 317 (proc0_17 188 (ego y?) 130))
				)
			)
			(else
				(ego posn: 3 137)
			)
		)
		(ego init:)
		(NormalEgo)
		(= i 0)
		(while (< i 3)
			((= [ripple i] (Clone Ripple))
				view: 241
				x: [rippleX i]
				y: [rippleY i]
				setLoop: [rippleLoop i]
				setPri: [ripplePri i]
				ignoreActors: 1
				description: {ripple}
				init:
				stopUpd:
			)
			(if (>= howFast 1)
				([ripple i] setCycle: Forward)
			)
			(++ i)
		)
		(self setRegions: TROLL)
		(self setRegions: RIVER)
		(bushAndRock init:)
		(bush1 init:)
		(bush2 init:)
		(bush3 init:)
		(bush4 init:)
		(bridge1 init:)
		(bridge2 init:)
		(bridge3 init:)
		(bridge4 init:)
		(river init:)
		(bank init:)
		(theMenace stopUpd:)
	)
	
	(method (doit &tmp nRoom)
		(if (and (== (ego view?) 54) (Btst fTrollBlocksBridge))
			((ScriptID 0 23) fade:)
		)
		(if (and (Btst fTrollBlocksBridge) (not (theMenace script?)))
			(cond 
				(
					(and
						(<= (ego distanceTo: theMenace) 45)
						(<= (ego x?) 122)
						(>= (ego x?) 104)
						(== (leftArm cel?) 0)
						(== (rightArm cel?) 0)
						(not (theMenace script?))
					)
					(if (<= (ego y?) 107) (theMenace setScript: pushBack))
				)
				(
					(or
						(>= (ego distanceTo: theMenace) 45)
						(and
							(<= (ego distanceTo: theMenace) 45)
							(<= (ego x?) 122)
							(>= (ego x?) 104)
						)
					)
					(if (!= (leftArm cel?) 0)
						(leftArm setScript: leftArmBeg)
					)
					(if (!= (rightArm cel?) 0)
						(rightArm setScript: rightArmBeg)
					)
				)
				(
					(and
						(>= (ego x?) 123)
						(<= (ego distanceTo: theMenace) 45)
					)
					(if (!= (rightArm cel?) (rightArm lastCel:))
						(rightArm setScript: rightArmEnd)
					)
					(if (== (leftArm cel?) (leftArm lastCel:))
						(leftArm setScript: leftArmBeg)
					)
				)
				(
					(and
						(<= (ego x?) 103)
						(<= (ego distanceTo: theMenace) 45)
					)
					(if (!= (leftArm cel?) (leftArm lastCel:))
						(leftArm setScript: leftArmEnd)
					)
					(if (== (rightArm cel?) (rightArm lastCel:))
						(rightArm setScript: rightArmBeg)
					)
				)
			)
		else
			0
		)
		(if (Btst fTrollPaid)
			(Bclr fTrollPaid)
			(= paidToll TRUE)
			(self setScript: TrollLeaves)
		)
		(if
			(and
				(!= prevRoomNum 40)
				(== (ego onControl: origin) cLCYAN)
				(not (Btst fTrollBlocksBridge))
				(not (Btst fTrollPaid))
				(not (Btst fTrollDead))
				(not paidToll)
			)
			(theMenace setScript: TrollFirst)
		)
		(cond 
			(script (script doit:))
			((and (& (ego onControl: origin) $003c) (Btst fTrollBlocksBridge))
				(ego ignoreBlocks: bridge25Block)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((Said 'look,check[<at,around][/room]')
				(Print 25 0)
			)
			(
				(or
					(Said 'talk,speak//troll')
					(Said 'talk,speak')
					(Said 'hello')
					(Said 'say/hello')
				)
				(if (Btst fTrollBlocksBridge)
					(curRoom setScript: trollTalk)
				else
					(Print 25 1)
				)
			)
			((Said 'look,check/ceder')
				(Print 25 2)
			)
		)
	)
)

(instance TrollLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ScriptID 0 23) fade:)
				(ego stopUpd:)
				(leftArm dispose:)
				(rightArm dispose:)
				(trollFace dispose:)
				(theMenace
					view: 120
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 112 49 self
				)
			)
			(1
				(theMenace setMotion: MoveTo -25 49 self)
			)
			(2
				(ego ignoreBlocks: bridge25Block)
				(self cue:)
			)
			(3
				(theMenace dispose:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance TrollFirst of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (Btst fGoatFollows)
					(theGoat stopUpd:)
					((ScriptID 0 23) number: 53 init: play:)
				else
					((ScriptID 0 23) number: 61 init: play:)
				)
				(Bset 13)
				(if (not (>= howFast 1))
					(ego setMotion: 0 stopUpd:)
				)
				(ego observeBlocks: bridge25Block)
				(theMenace
					posn: -25 49
					show:
					setMotion: MoveTo 112 49 self
				)
			)
			(1
				(theMenace setMotion: MoveTo 112 95 self)
			)
			(2
				(leftArm init: stopUpd:)
				(rightArm init: stopUpd:)
				(trollFace init: stopUpd:)
				(theMenace view: 123 setLoop: 0 setCel: 1 ignoreControl:)
				(= cycles 1)
			)
			(3
				(theMenace stopUpd:)
				(if (Btst fInvisible)
					(Print 25 3)
				else
					(Print 25 4)
				)
				(= cycles 1)
			)
			(4
				(HandsOn)
				(if
					(and
						(or (cast contains: theGoat)
							(Btst fGoatFollows)
						)
						(!= deadGoatRoom curRoomNum)
					)
					(curRoom setScript: GoatButt)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance pushBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(leftArm hide: stopUpd:)
				(rightArm hide: stopUpd:)
				(trollFace hide: stopUpd:)
				(theMenace view: 126 setLoop: 1 setCycle: CycleTo 2 1 self)
			)
			(1
				(if (Btst fInvisible)
					(Print 25 5)
					(Bclr fInvisible)
					(ego put: iMagicRing)
				)
				(ego view: 44 setLoop: 1)
				(theMenace cel: 2 setCycle: EndLoop self)
			)
			(2
				(ego y: (+ (ego y?) 4))
				(= cycles 1)
			)
			(3
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 10))
				(= cycles 3)
			)
			(4
				(theMenace cycleSpeed: 1 setCycle: BegLoop self)
			)
			(5
				(HandsOn)
				(leftArm show: stopUpd:)
				(rightArm show: stopUpd:)
				(trollFace show: stopUpd:)
				(theMenace
					view: 123
					setLoop: 0
					setCel: 1
					cycleSpeed: 0
					ignoreControl:
					stopUpd:
				)
				(ego view: 0 loop: 3)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance trollFace of Prop
	(properties
		x 112
		y 66
		description {troll}
		view 123
		loop 6
		priority 6
		signal fixPriOn
	)
)

(instance splash of Prop)

(instance leftArm of Prop
	(properties
		x 106
		y 58
		description {troll}
		view 123
		loop 2
		priority 5
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(theMenace doVerb: verbLook)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rightArm of Prop
	(properties
		x 118
		y 58
		description {troll}
		view 123
		loop 3
		priority 5
		signal fixPriOn
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(theMenace doVerb: verbLook)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bridge25Block of Block
	(properties
		top 92
		left 82
		bottom 96
		right 144
	)
)

(instance GoatButt of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== state 2) (<= (theGoat y?) 105) (not local1))
			(= local1 1)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((theGoat looper?) viewNormal: 168)
				(theGoat
					view: 168
					setStep: 6 4
					setAvoider: 0
					setMotion: 0
					ignoreActors:
					illegalBits: 0
				)
				(Print 25 6)
				(if (< (theGoat x?) 121)
					(ego setMotion: MoveTo 166 144 self)
				else
					(ego setMotion: MoveTo 80 143 self)
				)
			)
			(1
				(ego setLoop: 3 stopUpd:)
				(theGoat setMotion: MoveTo 112 145 self)
			)
			(2
				(theGoat setMotion: MoveTo 112 100 self)
			)
			(3
				((ScriptID 0 21) number: 38 init: play:)
				(self cue:)
			)
			(4
				(leftArm dispose:)
				(rightArm dispose:)
				(trollFace dispose:)
				(theGoat stopUpd:)
				(theMenace
					view: 124
					setPri: 8
					illegalBits: 0
					setStep: 12 9
				)
				(theMenace setLoop: 0 setMotion: MoveTo 100 79 self)
			)
			(5
				(theMenace setMotion: MoveTo 77 70 self)
			)
			(6
				(theMenace setMotion: MoveTo 53 82 self)
			)
			(7
				(theMenace setMotion: MoveTo 46 100 self)
			)
			(8
				((ScriptID 0 21) number: 14 play:)
				(splash
					view: 124
					setLoop: 2
					cycleSpeed: 2
					setPri: 2
					posn: 39 110
					init:
				)
				(theMenace setMotion: MoveTo 42 107 self)
			)
			(9
				(Bclr fTrollBlocksBridge)
				(theMenace dispose:)
				(splash setCycle: EndLoop self)
			)
			(10
				(splash dispose:)
				(SolvePuzzle fGoatKilledTroll 4)
				(Print 25 7)
				((ScriptID 0 23) fade:)
				((theGoat looper?) viewNormal: 165)
				(theGoat setStep: 3 2 setMotion: MoveTo 112 220 self)
			)
			(11
				(ego illegalBits: cWHITE setLoop: -1)
				(HandsOn)
				(Bclr fGoatFollows)
				(Bset fTrollDead)
				(ego ignoreBlocks: bridge25Block)
				(LoadMany FALSE FOLLOW AVOIDER)
				(theGoat dispose:)
				(self dispose:)
			)
		)
	)
)

(instance leftArmBeg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (leftArm setCycle: BegLoop self))
			(1
				(leftArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance rightArmBeg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArm setCycle: BegLoop self)
			)
			(1
				(rightArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance leftArmEnd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (leftArm setCycle: EndLoop self))
			(1
				(leftArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance rightArmEnd of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rightArm setCycle: EndLoop self)
			)
			(1
				(rightArm stopUpd:)
				(self dispose:)
			)
		)
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/brook,boulder,water')
				(Print 25 8)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 25 8)
			)
		)
	)
)

(instance bushAndRock of NewFeature
	(properties
		x 50
		y 139
		noun '/bush'
		nsTop 125
		nsLeft 25
		nsBottom 154
		nsRight 76
		description {bush and rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A commonplace little bush has grown in the protective shade of the rock.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 256
		y 155
		noun '/bush'
		nsTop 149
		nsLeft 224
		nsBottom 162
		nsRight 288
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are more bushes in Daventry than you care to notice.}
	)
)

(instance bush2 of NewFeature
	(properties
		x 255
		y 143
		noun '/bush'
		nsTop 138
		nsLeft 241
		nsBottom 149
		nsRight 269
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are more bushes in Daventry than you care to notice.}
	)
)

(instance bush3 of NewFeature
	(properties
		x 19
		y 43
		noun '/bush'
		nsTop 27
		nsBottom 60
		nsRight 39
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are more bushes in Daventry than you care to notice.}
	)
)

(instance bush4 of NewFeature
	(properties
		x 44
		y 49
		noun '/bush'
		nsTop 40
		nsLeft 38
		nsBottom 59
		nsRight 51
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are more bushes in Daventry than you care to notice.}
	)
)

(instance river of NewFeature
	(properties
		x 160
		y 105
		noun '/brook,water,brook'
		nsTop 85
		nsBottom 129
		nsRight 320
		description {river}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {The current of this wildly raging river is even more treacherous than it looks.}
	)
)

(instance bank of NewFeature
	(properties
		x 160
		y 73
		noun '/bank[<brook]'
		nsTop 62
		nsBottom 84
		nsRight 320
		description {bank}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {The river bank drops off sharply here.__Best to watch your step.}
	)
)

(instance bridge1 of NewFeature
	(properties
		x 112
		y 95
		noun '/bridge,crossing'
		nsTop 62
		nsLeft 90
		nsBottom 129
		nsRight 135
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A rough-hewn plank bridge spans the deep rushing river below.}
	)
)

(instance bridge2 of NewFeature
	(properties
		x 140
		y 110
		noun '/bridge,crossing'
		nsTop 93
		nsLeft 135
		nsBottom 128
		nsRight 145
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A rough-hewn plank bridge spans the deep rushing river below.}
	)
)

(instance bridge3 of NewFeature
	(properties
		x 149
		y 121
		noun '/bridge,crossing'
		nsTop 114
		nsLeft 145
		nsBottom 129
		nsRight 154
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A rough-hewn plank bridge spans the deep rushing river below.}
	)
)

(instance bridge4 of NewFeature
	(properties
		x 85
		y 110
		noun '/bridge,crossing'
		nsTop 92
		nsLeft 82
		nsBottom 129
		nsRight 89
		description {bridge}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {A rough-hewn plank bridge spans the deep rushing river below.}
	)
)

(instance trollTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(trollFace setCycle: Forward)
				(self cue:)
			)
			(1
				(switch talkCount
					(0
						(TrollSays 25 9)
					)
					(1
						(TrollSays 25 10)
					)
					(else
						(TrollSays 25 11)
					)
				)
				(++ talkCount)
				(= register 1)
			)
			(2
				(trollFace setCycle: BegLoop self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			(
				(or
					(== (event type?) mouseDown)
					(and
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
				)
				(if modelessDialog
					(RedrawCast)
					(modelessDialog dispose:)
					(if seconds
						(= seconds 0)
						(= cycles 1)
					)
					(if register
						(= register 0)
						(= cycles 1)
					)
				)
				(event claimed: TRUE)
			)
		)
	)
)
