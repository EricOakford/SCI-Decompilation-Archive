;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10)
(include game.sh)
(use Main)
(use Intrface)
(use mwAvoider)
(use TurnLooper)
(use NewFeature)
(use Block)
(use LoadMany)
(use Wander)
(use Follow)
(use Avoider)
(use Motion)
(use Game)
(use System)

(public
	rm10 0
)

(local
	goatTimer
)
(instance rm10 of Room
	(properties
		picture 10
		horizon 57
		north 23
		east 11
		south 7
		west 9
	)
	
	(method (init)
		(self style:
			(switch prevRoomNum
				(north WIPEDOWN)
				(west WIPERIGHT)
				(east SCROLLRIGHT)
				(south WIPEUP)
			)
		)
		(super init:)
		(LoadMany SCRIPT AVOIDER SIGHT WANDER FOLLOW)
		(switch prevRoomNum
			(north
				(ego
					posn: (proc0_17 319 (proc0_18 255 (ego x?) 236) 171) (+ 2 horizon)
				)
			)
			(south
				(ego posn: (proc0_17 319 (ego x?) 90) 188)
			)
			(west
				(ego posn: 3 (proc0_17 183 (ego y?) 113))
			)
			(else 
				(ego posn: 317 (proc0_17 189 (ego y?) (+ horizon 2)))
			)
		)
		(ego init:)
		(NormalEgo)
		(trough init:)
		(tree7 init:)
		(tree5 init:)
		(tree8 init:)
		(tree6 init:)
		(tree3 init:)
		(tree4 init:)
		(tree2 init:)
		(tree init:)
		(tree1 init:)
		(bush1 init:)
		(bush init:)
		(cond 
			((not (Btst fGoatFollows))
				(if (and (not deadGoatRoom) (not (Btst fOfferedGoatCarrot)))
					(LoadMany VIEW 21 167)
					(goatLpr viewChange: 167)
					(if (and (== roomWithLiveGoat 11) (not (Btst fGoatFriend)))
						(theGoat
							view: 165
							posn: 340 (Random 120 140)
							init:
							looper: goatLpr
							setCycle: Walk
							setScript: changeGoatRoom
						)
						(= goatTimer (Random 100 180))
					else
						(theGoat
							view: 165
							posn: (Random 232 260) (Random 120 140)
							init:
							looper: goatLpr
							setCycle: Walk
							observeBlocks: pen
							setScript: goatWander
						)
						(= goatTimer (Random (Random 100 140) (Random 160 200)))
					)
				)
			)
			((and (& (ego onControl: origin) cLRED cLMAGENTA) (Btst fGoatFollows))
				(Bclr fGoatFollows)
				(Print 10 0)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'rub/ring')
				(cond 
					((Btst fInvisible)
						(Print 10 1)
					)
					((not invisibleRingTimer)
						(Print 10 2)
					)
					((Btst fWearingRing)
						(Print 10 3)
						(Bset fInvisible)
						(NormalEgo)
						(if (and (Btst fGoatFollows) (not (Btst fGoatFriend)))
							(Print 10 4)
							(Bclr fGoatFollows)
							(Bclr fOfferedGoatCarrot)
							(goatLpr viewChange: 167)
							(if (== roomWithLiveGoat 11)
								(theGoat
									posn: 340 (Random 120 140)
									init:
									looper: goatLpr
									setCycle: Walk
									setScript: changeGoatRoom
								)
								(= goatTimer (Random 100 180))
							else
								(theGoat
									init:
									looper: goatLpr
									setCycle: Walk
									observeBlocks: pen
									setScript: goatWander
								)
								(= goatTimer (Random (Random 100 140) (Random 160 200)))
							)
						)
					)
					(else (Print 10 5))
				)
			)
			(
			(or (Said 'remove/ring') (Said 'take<off/ring'))
				(cond 
					((== (ego view?) (if (Btst fLittleEgo) 23 else 16))
						(CantDo)
					)
					((not (Btst fWearingRing))
						(Print 10 6)
					)
					(else
						(Print 10 7)
						(Bclr fWearingRing)
						(Bclr fInvisible)
						(NormalEgo)
					)
				)
			)
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((cast contains: theGoat)
				(cond 
					(deadGoatRoom (event claimed: FALSE))
					((or (Said 'talk,speak,say,call') (Said '/hello'))
						(Print 10 8)
					)
					((Said 'capture,pull,pull,get,take/goat')
						(cond 
							((Btst fGoatFollows) (Print 10 9))
							((& (ego onControl: origin) (| cLRED cLMAGENTA))
								(Print 10 10)
							)
							(else
								(Print 10 11)
							)
						)
					)
					((Said 'feed/goat[/!*]')
						(Print 10 12)
					)
					((Said 'pet,free/goat')
						(Print 10 13)
					)
					((Said 'ride/goat') (Print 10 14))
					((or (Said 'feed/carrot') (Said 'give/carrot'))
						(cond 
							((not (ego has: iCarrot))
								(Print 10 15)
							)
							((> (ego distanceTo: theGoat) 32)
								(Print 10 16)
							)
							((Btst fInvisible)
								(Print 10 17)
							)
							(else
								(if (Btst fGoatFollows)
									(Print 10 18)
								else
									(Print 10 19)
								)
								(PutInRoom iCarrot 15)
								(Bclr fGoatFollows)
								(if
								(and (not (Btst fGoatPenOpen)) (& (theGoat onControl: origin) (| cLRED cLMAGENTA)))
									(Bclr fOfferedGoatCarrot)
									(goatLpr viewChange: 167)
									(theGoat
										view: 165
										looper: goatLpr
										setCycle: Walk
										observeBlocks: pen
										setScript: goatWander
									)
								else
									(theGoat setScript: goatBobs)
								)
								(Bclr fPickedCarrot)
								(theGame changeScore: -2)
							)
						)
					)
					((or (Said 'show/carrot') (Said 'coax,coax/goat') (Said 'show/goat/carrot') (Said 'coax,coax/goat/carrot'))
						(cond 
							((not (ego has: iCarrot))
								(DontHave)
							)
							((Btst fGoatFollows)
								(Print 10 20)
							)
							((and (& (theGoat onControl: origin) (| cLRED cLMAGENTA)) (not (& (ego onControl:) (| cLRED cLMAGENTA))))
								(Print 10 21)
							)
							((> (ego distanceTo: theGoat) 36) (Print 10 22))
							((Btst fInvisible) (Print 10 23))
							((curRoom script?)
								(CantDo)
							)
							(
								(and
									(not (Btst fGoatFriend))
									(& (ego onControl: origin) (| cLRED cLMAGENTA))
									(not (& (theGoat onControl: 1) (| cLRED cLMAGENTA)))
								)
								(Print 10 24)
							)
							(else
								(curRoom setScript: getGoat)
							)
						)
					)
					(
					(or (Said 'shoot/goat') (Said 'kill/goat/shot'))
						(cond 
							(deadGoatRoom
								(Print 10 25)
							)
							((curRoom script?)
								(CantDo)
							)
							((Btst fInvisible)
								(Print 10 26)
							)
							((and (not (Btst fGoatFriend)) (not (& (theGoat onControl:) (| cLRED cLMAGENTA))))
								(Print 10 24)
							)
							((or (not (ego has: iPebbles)) (not (ego has: iSlingshot)))
								(Print 10 27)
							)
							(else
								(Print 10 28)
							)
						)
					)
					(
					(or (Said 'stab,kill/goat') (Said 'use,throw/dagger'))
						(cond 
							(deadGoatRoom
								(Print 10 25)
							)
							((curRoom script?)
								(CantDo)
							)
							((and (> (theGoat x?) 320) (not (Btst fGoatFriend)))
								(Print 10 24)
							)
							(
								(and
									(not (& (ego onControl: origin) (| cLRED cLMAGENTA)))
									(not (Btst fGoatFriend))
								)
								(Print 10 29)
							)
							((> (ego distanceTo: theGoat) 35)
								(Print 10 30)
							)
							((not (ego has: iDagger))
								(Print 10 31)
							)
							(else
								(curRoom setScript: (ScriptID 600 2))
							)
						)
					)
				)
			)
			((or (Said '/goat') (Said 'show/carrot'))
				(cond 
					((Btst fInvisible)
						(Print 10 32)
					)
					((Btst fGoatFollows)
						(Print 10 20)
					)
					(else
						(Print 10 33)
					)
				)
			)
		)
		(cond 
			((Said 'jump/fence')
				(Print 10 34)
			)
			((Said 'climb,scale/fence')
				(Print 10 35)
			)
			(
				(or
					(Said 'look,check/fence')
					(MouseClaimed event 215 131 319 157)
					(MouseClaimed event 198 121 214 148)
					(MouseClaimed event 186 103 199 124)
					(MouseClaimed event 191 73 215 86)
					(MouseClaimed event 178 81 192 98)
					(MouseClaimed event 215 66 319 83)
				)
				(if (& (ego onControl: origin) (| cLRED cLMAGENTA))
					(Print 10 36)
				else
					(Print 10 37)
				)
			)
			((Said 'look,check>')
				(cond 
					((Said '[<at,around][/room,fence]')
						(if (& (ego onControl: origin) (| cLRED cLMAGENTA))
							(Print 10 38)
						else
							(Print 10 39)
						)
					)
					((Said '/gate')
						(Print 10 40)
					)
				)
			)
			((Said 'open,open,close,shut,find/gate')
				(Print 10 41)
			)
			((Said 'attack,kick/goat')
				(if deadGoatRoom
					(Print 10 42)
				else
					(Print 10 43)
				)
			)
		)
	)
)

(instance goatLpr of TurnLooper
	(properties)
)

(instance pen of Cage
	(properties
		top 92
		left 213
		bottom 142
		right 316
	)
)

(instance goatWander of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(
				(and
					(not deadGoatRoom)
					(< (ego distanceTo: theGoat) 30)
				)
				(= cycles 0)
				(theGoat setScript: goatRuns)
			)
			((== (-- goatTimer) 0) (theGoat setScript: changeGoatRoom))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat setLoop: -1 setMotion: Wander startUpd:)
				(= cycles (Random 14 30))
			)
			(1
				(theGoat setMotion: 0)
				(= cycles (Random 8 17))
			)
			(2 (self changeState: 0))
		)
	)
)

(instance goatRuns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat
					setAvoider: mwAvoider
					ignoreBlocks: pen
					setMotion: MoveTo 333 (theGoat y?) self
				)
			)
			(1
				(= roomWithLiveGoat 11)
				(theGoat hide: setScript: goatCounter)
				(self dispose:)
			)
		)
	)
)

(instance getGoat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Face ego theGoat)
				(Face theGoat ego)
				(ego view: 21 cel: 0 setCycle: EndLoop)
				(theGoat setMotion: 0 setScript: 0)
				(= cycles 22)
			)
			(1
				(if (Btst fTrollPaid)
					(Print 10 44)
				else
					(Print 10 45)
					(SolvePuzzle fOfferedGoatCarrot 5)
				)
				((ScriptID 0 21) number: 60 init: play:)
				(ego setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(if (not (Btst fTrollPaid))
					(theGoat setAvoider: Avoider setMotion: Follow ego 50)
					(Bset fGoatFollows)
				)
				(self dispose:)
			)
		)
	)
)

(instance changeGoatRoom of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 0) (& (theGoat onControl:) (| cLRED cLMAGENTA)))
			(= roomWithLiveGoat 10)
		else
			(= roomWithLiveGoat 11)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== roomWithLiveGoat 10)
					(theGoat
						setLoop: -1
						setAvoider: mwAvoider
						ignoreBlocks: pen
						setMotion: MoveTo 340 (theGoat y?) self
					)
				else
					(theGoat
						show:
						setLoop: -1
						setAvoider: 0
						setMotion: MoveTo 300 (theGoat y?) self
					)
				)
			)
			(1
				(if (!= roomWithLiveGoat 10)
					(self changeState: 2)
				else
					(theGoat observeBlocks: pen setScript: goatWander)
					(= goatTimer (Random (Random 100 140) (Random 160 200)))
					(self dispose:)
				)
			)
			(2
				(if (and (Btst fGoatPenOpen) (== roomWithLiveGoat 11))
					(self dispose:)
				else
					(= cycles 70)
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance goatCounter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 70))
			(1
				(client setScript: changeGoatRoom)
				(self dispose:)
			)
		)
	)
)

(instance goatBobs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGoat
					setAvoider: 0
					illegalBits: -16322
					setMotion:
						MoveTo
						(Random 10 310)
						(Random (+ (curRoom horizon?) 2) 180)
				)
				(= cycles (Random 20 40))
			)
			(1 (self changeState: 0))
		)
	)
)

(instance trough of NewFeature
	(properties
		x 286
		y 82
		noun '/trough'
		nsTop 77
		nsLeft 260
		nsBottom 87
		nsRight 313
		description {trough}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {This is the goat's trough, currently empty.__Goats are always hungry!}
	)
)

(instance tree8 of NewFeature
	(properties
		x 78
		y 50
		noun '/ceder'
		nsTop -1
		nsBottom 101
		nsRight 157
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree7 of NewFeature
	(properties
		x 27
		y 142
		noun '/ceder'
		nsTop 101
		nsLeft 10
		nsBottom 184
		nsRight 44
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree6 of NewFeature
	(properties
		x 53
		y 111
		noun '/ceder'
		nsTop 101
		nsLeft 43
		nsBottom 121
		nsRight 63
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree5 of NewFeature
	(properties
		x 84
		y 111
		noun '/ceder'
		nsTop 101
		nsLeft 63
		nsBottom 122
		nsRight 105
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree4 of NewFeature
	(properties
		x 165
		y 37
		noun '/ceder'
		nsTop -1
		nsLeft 157
		nsBottom 76
		nsRight 173
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 181
		y 23
		noun '/ceder'
		nsTop -1
		nsLeft 172
		nsBottom 48
		nsRight 191
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 255
		y 17
		noun '/ceder'
		nsTop -1
		nsLeft 191
		nsBottom 36
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree1 of NewFeature
	(properties
		x 244
		y 45
		noun '/ceder'
		nsTop 36
		nsLeft 229
		nsBottom 54
		nsRight 259
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance tree of NewFeature
	(properties
		x 252
		y 63
		noun '/ceder'
		nsTop 54
		nsLeft 242
		nsBottom 72
		nsRight 262
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are numerous beautiful trees ringing the goat pen.}
	)
)

(instance bush of NewFeature
	(properties
		x 164
		y 110
		noun '/bush'
		nsTop 104
		nsLeft 146
		nsBottom 117
		nsRight 183
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many small decorative bushes scattered around Daventry.}
	)
)

(instance bush1 of NewFeature
	(properties
		x 173
		y 99
		noun '/bush'
		nsTop 94
		nsLeft 166
		nsBottom 104
		nsRight 181
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		contClick 371
		lookStr {There are many small decorative bushes scattered around Daventry.}
	)
)
